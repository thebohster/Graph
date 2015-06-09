package edu.ucsb.cs.cs185.bohan_lin.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by Bohan on 6/7/2015.
 */
public class DrawLines extends View {

    private ArrayList<String> csvCategoryNames;                       // arraylist to store the name of the csv stuff
    private ArrayList<inputCSVdata> csvDataRows;                //creating an arraylist of inputCSV Data Class

    private float [] minimumCategories;           // should be 7 actually
    private float [] maximumCategories;
    private int numberOfColumns;
    private int numberOfRows;
    private int screenWidth;
    private int screenHeight;

    Paint paint = new Paint();


    public DrawLines (Context context) {
        super(context);
        this.setBackgroundColor(Color.TRANSPARENT);
        paint.setColor(Color.BLUE);
    }



    public DrawLines (Context context, AttributeSet attrs) {
        super(context,attrs);
        this.setBackgroundColor(Color.TRANSPARENT);
        paint.setColor(Color.BLUE);
    }


    public void setData(ArrayList<String> categoryNames, ArrayList<inputCSVdata> dataRows, float[] minCategories, float[] maxCategories, int numColumns, int numRows, int width, int height) {
        this.csvCategoryNames = categoryNames;
        this.csvDataRows = dataRows;
        this.minimumCategories = minCategories;
        this.maximumCategories = maxCategories;
        this.numberOfColumns = numColumns;
        this.numberOfRows = numRows;
        this.screenWidth = width;
        this.screenHeight = height;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(10, 10, 500, 500, paint);


       for ( int  i = 0 ; i < numberOfRows ; i ++ )
       {
           for ( int j = 0 ; j < numberOfColumns- 3 ; j++ )
           {
               Point startPoint = new Point();
               Point endPoint = new Point();

               float sx =24 + csvDataRows.get(i).dataFloats[j] * (screenWidth  - 0 ) / (maximumCategories[j] - minimumCategories[j]);
               float sy = 48+  j * screenHeight/5 ;

               float ex =24 +  csvDataRows.get(i).dataFloats[j] * (screenWidth  - 0 ) / (maximumCategories[j+1] - minimumCategories[j+1]);
               float ey = 48 +  ( j+1 ) * screenHeight/5 ;

               paint.setColor(Color.WHITE);
               canvas.drawLine(sx,sy,ex,ey ,paint);


           }
       }





    }








}
