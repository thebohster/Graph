package edu.ucsb.cs.cs185.bohan_lin.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Bohan on 6/7/2015.
 */
public class DrawLines extends View {

    Paint paint = new Paint();

    public DrawLines (Context context) {
        super(context);
        this.setBackgroundColor(Color.TRANSPARENT);
        paint.setColor(Color.BLUE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(10, 10, 500, 500, paint);
    }

}
