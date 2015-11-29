package ua.cc.tomik.twitteronixexam;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class SkillTab extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new StampSkill(this));
    }


    public class StampSkill extends View {
        private RotateAnimation rotation;
        private Paint myPaint;
        private Paint textPaint;
        private Path circlePath;

        public StampSkill(Context context) {
            super(context);
            myPaint = new Paint();
            textPaint = new Paint();
            circlePath = new Path();
        }

        @Override
        protected void onDraw(Canvas canvas) {

            if (rotation == null) {
                createAnimation(canvas);
            }



            myPaint.setColor(Color.BLACK);
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(2.0f);
            myPaint.setAntiAlias(true);

            textPaint.setTextSize(36);
            textPaint.setAntiAlias(true);


            circlePath.addCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 205, Path.Direction.CW);


            canvas.drawPath(circlePath, myPaint);
            textPaint.setColor(Color.RED);
            canvas.drawTextOnPath(getString(R.string.skill), circlePath, 0, 32, textPaint);
            textPaint.setColor(Color.GREEN);
            canvas.drawTextOnPath(getString(R.string.skill), circlePath, 2, 30, textPaint);
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        private void createAnimation(Canvas canvas) {
            rotation = new RotateAnimation(0, 360, canvas.getWidth()/2, canvas.getHeight()/2);
            rotation.setRepeatMode(Animation.REVERSE);
            rotation.setRepeatCount(Animation.INFINITE);
            rotation.setDuration(10000L);
            startAnimation(rotation);
        }
    }
}
