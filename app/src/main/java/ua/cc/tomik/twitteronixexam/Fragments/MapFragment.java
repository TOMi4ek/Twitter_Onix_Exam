package ua.cc.tomik.twitteronixexam.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ua.cc.tomik.twitteronixexam.R;

public class MapFragment extends Fragment implements GoogleMap.OnInfoWindowClickListener{
    MapView mMapView;
    LatLng indicative, hydrosila, zirka;
    String indURL = "http://indicative.com.ua";
    String hydURL = "http://www.hydrosila.com";
    String zirURL = "http://fczirka.com.ua";

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        try{
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        GoogleMap googleMap = mMapView.getMap();
        googleMap.setOnInfoWindowClickListener(this);

        indicative = new LatLng(48.511902,32.269087);
        hydrosila = new LatLng(48.524263,32.265076);
        zirka = new LatLng(48.515700,32.265896);
        MarkerOptions mrkIndicative = new MarkerOptions()
                .position(indicative)
                .title("Indicative")
                .snippet(getString(R.string.indicative_snippet));
        MarkerOptions mrkHydrosila = new MarkerOptions()
                .position(hydrosila)
                .title("Hydrosila")
                .snippet(getString(R.string.hydrosila_snippet));
        MarkerOptions mrkZirka = new MarkerOptions()
                .position(zirka)
                .title("Zirka")
                .snippet(getString(R.string.zirka_snippet));

        googleMap.addMarker(mrkHydrosila);
        googleMap.addMarker(mrkIndicative);
        googleMap.addMarker(mrkZirka);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(indicative).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        switch (marker.getTitle()){
            case "Indicative":{
                Intent indIntent = new Intent(Intent.ACTION_VIEW);
                indIntent.setData(Uri.parse(indURL));
                startActivity(indIntent);
            }
            break;
            case "Zirka":{
                Intent zirIntent = new Intent(Intent.ACTION_VIEW);
                zirIntent.setData(Uri.parse(zirURL));
                startActivity(zirIntent);
            }
            break;
            case "Hydrosila":{
                Intent hydIntent = new Intent(Intent.ACTION_VIEW);
                hydIntent.setData(Uri.parse(hydURL));
                startActivity(hydIntent);
            }
            break;
        }

    }

}
