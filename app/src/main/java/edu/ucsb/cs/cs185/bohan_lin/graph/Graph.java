package edu.ucsb.cs.cs185.bohan_lin.graph;
// https://github.com/yahoo/android-range-seek-bar/blob/master/rangeseekbar-sample/src/main/java/com/yahoo/mobile/client/android/demo/DemoActivity.java

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Range;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Graph extends ActionBarActivity {

    public ArrayList<String> csvCategoryNames = new ArrayList<String>();                       // arraylist to store the name of the csv stuff
    public ArrayList<inputCSVdata> csvDataRows = new ArrayList<inputCSVdata>();                //creating an arraylist of inputCSV Data Class

    float [] minimumCategories= new float[8];           // should be 7 actually
    float [] maximumCategories = new float[8];
    int numberOfColumns ;
    int numberOfRows = 0 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        parseCSV();
        setMinMax();

        drawDataLines();

        super.onCreate(savedInstanceState);


        /**
         *
         * setup range seek bars using java
         *
         */

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;


        // Add to layout
        LinearLayout layout = new LinearLayout(this);

        RangeSeekBar<Float> rangeSeekBar0 = new RangeSeekBar<Float>(this);
        rangeSeekBar0.setRangeValues(minimumCategories[0], maximumCategories[0]);
        rangeSeekBar0.setSelectedMinValue(minimumCategories[0]);
        rangeSeekBar0.setSelectedMaxValue(maximumCategories[0]);
        rangeSeekBar0.setId(1);


        RangeSeekBar<Float> rangeSeekBar1 = new RangeSeekBar<Float>(this);
        rangeSeekBar1.setRangeValues(minimumCategories[1], maximumCategories[1]);
        rangeSeekBar1.setSelectedMinValue(minimumCategories[1]);
        rangeSeekBar1.setSelectedMaxValue(maximumCategories[1]);
        rangeSeekBar1.setId(2);


        RangeSeekBar<Float> rangeSeekBar2 = new RangeSeekBar<Float>(this);
        rangeSeekBar2.setRangeValues(minimumCategories[2], maximumCategories[2]);
        rangeSeekBar2.setSelectedMinValue(minimumCategories[2]);
        rangeSeekBar2.setSelectedMaxValue(maximumCategories[2]);
        rangeSeekBar2.setId(3);

        RangeSeekBar<Float> rangeSeekBar3 = new RangeSeekBar<Float>(this);
        rangeSeekBar3.setRangeValues(minimumCategories[3], maximumCategories[3]);
        rangeSeekBar3.setSelectedMinValue(minimumCategories[3]);
        rangeSeekBar3.setSelectedMaxValue(maximumCategories[3]);
        rangeSeekBar3.setId(4);


        RangeSeekBar<Float> rangeSeekBar4 = new RangeSeekBar<Float>(this);
        rangeSeekBar4.setRangeValues(minimumCategories[4], maximumCategories[4]);
        rangeSeekBar4.setSelectedMinValue(minimumCategories[4]);
        rangeSeekBar4.setSelectedMaxValue(maximumCategories[4]);
        rangeSeekBar4.setId(5);



        //set layout details
        layout.setBackgroundColor(Color.parseColor("#ff0000"));
        layout.setOrientation(LinearLayout.VERTICAL);


        LinearLayout.LayoutParams seekBarParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT , 1  );


        seekBarParams.setMargins(24,48,24,48);

        rangeSeekBar0.setLayoutParams( seekBarParams );
        rangeSeekBar1.setLayoutParams( seekBarParams );
        rangeSeekBar2.setLayoutParams( seekBarParams );
        rangeSeekBar3.setLayoutParams( seekBarParams );
        rangeSeekBar4.setLayoutParams( seekBarParams );


        layout.addView(rangeSeekBar0);
        layout.addView(rangeSeekBar1);
        layout.addView(rangeSeekBar2);
        layout.addView(rangeSeekBar3);
        layout.addView(rangeSeekBar4);
        //layout.addView(rangeSeekBar4);

        setContentView(layout);












    }

    public void parseCSV() {

        try {
            InputStreamReader csvStreamReader = new InputStreamReader(getAssets().open("cars.csv"));
            CSVReader reader = new CSVReader(csvStreamReader);

            String[] firstLine;
            firstLine = reader.readNext();
            numberOfColumns = firstLine.length -1 ;

            for (int i = 0; i < firstLine.length; i++) {
                csvCategoryNames.add(firstLine[i]); /// adding the row names to the category names
            }
            System.out.println("Header names: " + firstLine[0] + "   " + firstLine[1] + "  " + firstLine[2] + "   " + firstLine[3] + " " + firstLine[4]);

            //**
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {

                inputCSVdata tempRowdata = new inputCSVdata();



                try
                {
                    tempRowdata.name = nextLine[0];
                    for (int i = 0; i < nextLine.length - 1; i++) {
                        tempRowdata.dataFloats[i] = Float.parseFloat(nextLine[i + 1]);
                        System.out.println(tempRowdata.dataFloats[i]);
                    }

                    csvDataRows.add(tempRowdata);


                }
                catch (NumberFormatException ex)
                {
                    System.out.println("found a 0 lol ");
                    tempRowdata.name = "0";
                    for (int i = 0; i < nextLine.length - 1; i++)
                        tempRowdata.dataFloats[i] = 0.0f;
                    //setting them to 0 but don't add them anywhere

                }

                //csvDataRows.add(tempRowdata);
                // stop adding the 0s to the list as thiswill screw up the min and max
            }

            System.out.println("size of arraylist is: " + csvDataRows.size());
            numberOfRows = csvDataRows.size();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setMinMax() {
        //write the setting stuff here
        // setting all to the first row
        for( int  i = 0 ; i < numberOfColumns ; i++ )
        {
            minimumCategories[i] =  csvDataRows.get(0).dataFloats[i];
            maximumCategories[i] =  csvDataRows.get(0).dataFloats[i];
        }


        // calculating min and max

        for (int  i = 0 ; i < numberOfColumns ; i++ )
        {
            for ( int  j = 0 ; j < numberOfRows ; j++ )
            {
                if (  minimumCategories[i] > csvDataRows.get(j).dataFloats[i] )
                {
                    minimumCategories[i] = csvDataRows.get(j).dataFloats[i];
                }

                if (  maximumCategories[i] < csvDataRows.get(j).dataFloats[i] )
                {
                    maximumCategories[i] = csvDataRows.get(j).dataFloats[i];
                }

            }
        }

        //printing out minimum and maximum

        System.out.println("Minimum categories");
        for( int  i = 0 ; i < numberOfColumns ; i++ )
        {
            System.out.print( "  " + minimumCategories[i]);
        }
        System.out.println();
        System.out.println("Maximum categories");
        for( int  i = 0 ; i < numberOfColumns ; i++ )
        {
            System.out.print( "  " + maximumCategories[i]);
        }
        System.out.println();
        System.out.println("end of setminmax");



    }




    public void drawDataLines()
    {

        System.out.println("Drawline data lines");



        // one loop to figure out the points
        //draw the lines
        for ( int i = 0 ; i < numberOfRows ; i++ )
        {
            ArrayList<Point> linePoint = new ArrayList<Point>();

            for ( int j = 0 ; j < numberOfColumns ; j ++ )
            {
                // i constant change j and height
                Point tempPoint;
                int x;
                int y;

               // x =  inputEcon * ( maxRange - minrange ) / (maxEcon - minEcon);

                //RangeSeekBar name = (RangeSeekBar) findViewById(R.id.value1);

            }
        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
