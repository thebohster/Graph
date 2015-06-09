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
final public class DrawLines extends View {

    private ArrayList<String> csvCategoryNames;                       // arraylist to store the name of the csv stuff
    private ArrayList<inputCSVdata> csvDataRows;                //creating an arraylist of inputCSV Data Class

    private float [] minimumCategories;           // should be 7 actually
    private float [] maximumCategories;

    private float [] updatedMinimumCategories;           // should be 7 actually
    private float [] updatedMaximumCategories;

    private int numberOfColumns;
    private int numberOfRows;
    private int screenWidth;
    private int screenHeight;
    int sWidth  = 48 + 48 + 28;
    int tWidth = 48 + 48 + 44;
    int bWidth = 48+ 42;


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


    public void setData(ArrayList<String> categoryNames, ArrayList<inputCSVdata> dataRows, float[] minCategories, float[] maxCategories, int numColumns, int numRows, int width, int height,float[] gMinCategories, float[] gMaxCategories ) {
        this.csvCategoryNames = categoryNames;
        this.csvDataRows = dataRows;
        this.minimumCategories = gMinCategories;
        this.maximumCategories = gMaxCategories;
        //this.minimumCategories = minCategories;
        //this.maximumCategories = maxCategories;

        this.numberOfColumns = numColumns;
        this.numberOfRows = numRows;
        this.screenWidth = width;
        this.screenHeight = height;

        this.updatedMinimumCategories = minCategories;
        this.updatedMaximumCategories = maxCategories;

    }

    public void updateSelectedRange( int i , float x , float y )
    {
        updatedMinimumCategories[i] = x;
        updatedMaximumCategories[i] = y;

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawLine(10, 10, 500, 500, paint);


       for ( int  i = 0 ; i < numberOfRows ; i ++ ) {

           boolean itsIn = true;

           // check if line is within limits to pick the color of the line, use updated Min categories here
           if (csvDataRows.get(i).dataFloats[0] >= updatedMinimumCategories[0] && csvDataRows.get(i).dataFloats[0] <= updatedMaximumCategories[0]
                   && csvDataRows.get(i).dataFloats[1] >= updatedMinimumCategories[1] && csvDataRows.get(i).dataFloats[1] <= updatedMaximumCategories[1]
                   && csvDataRows.get(i).dataFloats[2] >= updatedMinimumCategories[2] && csvDataRows.get(i).dataFloats[2] <= updatedMaximumCategories[2]
                   && csvDataRows.get(i).dataFloats[3] >= updatedMinimumCategories[3] && csvDataRows.get(i).dataFloats[3] <= updatedMaximumCategories[3]
                   && csvDataRows.get(i).dataFloats[4] >= updatedMinimumCategories[4] && csvDataRows.get(i).dataFloats[4] <= updatedMaximumCategories[4])
               itsIn = true;
           else
               itsIn = false;


           if (itsIn) {
               paint.setColor(Color.WHITE);
               paint.setAlpha(200);
           }
        else {
            paint.setColor(Color.GRAY);
            paint.setAlpha(90);

        }

        // draw lines here in for loop
           for ( int j = 0 ; j < numberOfColumns- 3 ; j++ )
           {

               float sx = sWidth +  ( (  csvDataRows.get(i).dataFloats[j]  - minimumCategories[j]   ) * (screenWidth - 2* sWidth ) / (maximumCategories[j] - minimumCategories[j])  ) ;
               float sy = tWidth+  j *  ( screenHeight - bWidth ) /5 ;

               float ex =sWidth + (  ( csvDataRows.get(i).dataFloats[j+1]-minimumCategories[j+1] ) * (screenWidth  - 2* sWidth) / (maximumCategories[j+1] - minimumCategories[j+1])   ) ;
               float ey = tWidth +  ( j+1 ) * ( screenHeight - bWidth )/5 ;

               canvas.drawLine(sx,sy,ex,ey ,paint);


           }
       }





    }








}
