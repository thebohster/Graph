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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Graph extends ActionBarActivity {

    public ArrayList<String> csvCategoryNames = new ArrayList<String>();                       // arraylist to store the name of the csv stuff
    public ArrayList<inputCSVdata> csvDataRows = new ArrayList<inputCSVdata>();                //creating an arraylist of inputCSV Data Class

    float [] minimumCategories= new float[8];           // should be 7 actually
    float [] maximumCategories = new float[8];

    float [] globalMinimumCategories= new float[8];           // should be 7 actually
    float [] globalMaximumCategories = new float[8];

    int numberOfColumns ;
    int numberOfRows = 0 ;
    int screenWidth;
    int screenHeight;
    int sideWidth = 24;
    int topWidth = 48;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        parseCSV();
        setMinMax();


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
        screenWidth = size.x;
        screenHeight = size.y;


        // Add to layout
        RelativeLayout layout = new RelativeLayout(this);
        setContentView(layout);

        final DrawLines linesView = new DrawLines(this);

        linesView.setData(csvCategoryNames, csvDataRows, minimumCategories, maximumCategories, numberOfColumns, numberOfRows, screenWidth, screenHeight, globalMinimumCategories,globalMaximumCategories);
        drawDataLines();

        RangeSeekBar<Float> rangeSeekBar0 = new RangeSeekBar<Float>(this);
        rangeSeekBar0.setRangeValues(minimumCategories[0], maximumCategories[0]);
        rangeSeekBar0.setSelectedMinValue(minimumCategories[0]);
        rangeSeekBar0.setSelectedMaxValue(maximumCategories[0]);
        rangeSeekBar0.setMaxLabel(csvCategoryNames.get(1));
        rangeSeekBar0.setId(1);

        RangeSeekBar<Float> rangeSeekBar1 = new RangeSeekBar<Float>(this);
        rangeSeekBar1.setRangeValues(minimumCategories[1], maximumCategories[1]);
        rangeSeekBar1.setSelectedMinValue(minimumCategories[1]);
        rangeSeekBar1.setSelectedMaxValue(maximumCategories[1]);
        rangeSeekBar1.setMaxLabel(csvCategoryNames.get(2));
        rangeSeekBar1.setId(2);

        RangeSeekBar<Float> rangeSeekBar2 = new RangeSeekBar<Float>(this);
        rangeSeekBar2.setRangeValues(minimumCategories[2], maximumCategories[2]);
        rangeSeekBar2.setSelectedMinValue(minimumCategories[2]);
        rangeSeekBar2.setSelectedMaxValue(maximumCategories[2]);
        rangeSeekBar2.setMaxLabel(csvCategoryNames.get(3));
        rangeSeekBar2.setId(3);

        RangeSeekBar<Float> rangeSeekBar3 = new RangeSeekBar<Float>(this);
        rangeSeekBar3.setRangeValues(minimumCategories[3], maximumCategories[3]);
        rangeSeekBar3.setSelectedMinValue(minimumCategories[3]);
        rangeSeekBar3.setSelectedMaxValue(maximumCategories[3]);
        rangeSeekBar3.setMaxLabel(csvCategoryNames.get(4));
        rangeSeekBar3.setId(4);


        RangeSeekBar<Float> rangeSeekBar4 = new RangeSeekBar<Float>(this);
        rangeSeekBar4.setRangeValues(minimumCategories[4], maximumCategories[4]);
        rangeSeekBar4.setSelectedMinValue(minimumCategories[4]);
        rangeSeekBar4.setSelectedMaxValue(maximumCategories[4]);
        rangeSeekBar4.setMaxLabel(csvCategoryNames.get(5));
        rangeSeekBar4.setId(5);

        //System.out.println(csvCategoryNames.get(1));

        //rangeSeekBar0.setOnRangeSeekBarChangeListener();

        //set layout details
        layout.setBackgroundColor(Color.parseColor("#ff0000"));


        RelativeLayout.LayoutParams seekBarParams0 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        seekBarParams0.addRule(RelativeLayout.ALIGN_PARENT_TOP );
        seekBarParams0.setMargins(sideWidth,topWidth, sideWidth,topWidth);

        RelativeLayout.LayoutParams seekBarParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        seekBarParams1.addRule( RelativeLayout.BELOW, rangeSeekBar0.getId() );
        seekBarParams1.setMargins(sideWidth,topWidth, sideWidth,topWidth);


        RelativeLayout.LayoutParams seekBarParams2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        seekBarParams2.addRule( RelativeLayout.BELOW, rangeSeekBar1.getId() );
        seekBarParams2.setMargins(sideWidth,topWidth, sideWidth,topWidth);


        RelativeLayout.LayoutParams seekBarParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        seekBarParams3.addRule( RelativeLayout.BELOW, rangeSeekBar2.getId() );
        seekBarParams3.setMargins(sideWidth,topWidth, sideWidth,topWidth);


        RelativeLayout.LayoutParams seekBarParams4 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        seekBarParams4.addRule( RelativeLayout.BELOW, rangeSeekBar3.getId() );
        seekBarParams4.setMargins(sideWidth, topWidth, sideWidth, topWidth);

        rangeSeekBar0.setLayoutParams(seekBarParams0);
        rangeSeekBar1.setLayoutParams(seekBarParams1);
        rangeSeekBar2.setLayoutParams(seekBarParams2);
        rangeSeekBar3.setLayoutParams(seekBarParams3);
        rangeSeekBar4.setLayoutParams(seekBarParams4);



        layout.addView(linesView);
        layout.addView(rangeSeekBar0);
        layout.addView(rangeSeekBar1);
        layout.addView(rangeSeekBar2);
        layout.addView(rangeSeekBar3);
        layout.addView(rangeSeekBar4);

        rangeSeekBar0.bringToFront();
        rangeSeekBar1.bringToFront();
        rangeSeekBar2.bringToFront();
        rangeSeekBar3.bringToFront();
        rangeSeekBar4.bringToFront();


        rangeSeekBar0.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Float>() {
                @Override
                public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Float minValue, Float maxValue) {

                    int i = 0 ;
                    float x =    minValue;

                    float  y =    maxValue;

                    linesView.updateSelectedRange(i,x,y);
                    linesView.invalidate();

                }
            });

        rangeSeekBar1.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Float>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Float minValue, Float maxValue) {

                int i = 1 ;
                float x =    minValue;

                float  y =    maxValue;

                linesView.updateSelectedRange(i,x,y);
                linesView.invalidate();

            }
        });

        rangeSeekBar2.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Float>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Float minValue, Float maxValue) {

                int i = 2 ;
                float x =    minValue;

                float  y =    maxValue;

                linesView.updateSelectedRange(i,x,y);
                linesView.invalidate();

            }
        });

        rangeSeekBar3.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Float>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Float minValue, Float maxValue) {

                int i = 3 ;
                float x =    minValue;

                float  y =    maxValue;

                linesView.updateSelectedRange(i,x,y);
                linesView.invalidate();

            }
        });

        rangeSeekBar4.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Float>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Float minValue, Float maxValue) {

                int i = 4 ;
                float x =    minValue;

                float  y =    maxValue;

                linesView.updateSelectedRange(i,x,y);
                linesView.invalidate();

            }
        });



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
            //System.out.println("Header names: " + firstLine[0] + "   " + firstLine[1] + "  " + firstLine[2] + "   " + firstLine[3] + " " + firstLine[4]);

            //**
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {

                inputCSVdata tempRowdata = new inputCSVdata();

                try
                {
                    tempRowdata.name = nextLine[0];
                    for (int i = 0; i < nextLine.length - 1; i++) {
                        tempRowdata.dataFloats[i] = Float.parseFloat(nextLine[i + 1]);
                        //System.out.println(tempRowdata.dataFloats[i]);
                    }

                    csvDataRows.add(tempRowdata);


                }
                catch (NumberFormatException ex)
                {
                    //System.out.println("found a 0 lol ");
                    tempRowdata.name = "0";
                    for (int i = 0; i < nextLine.length - 1; i++)
                        tempRowdata.dataFloats[i] = 0.0f;
                    //setting them to 0 but don't add them anywhere

                }

                //csvDataRows.add(tempRowdata);
                // stop adding the 0s to the list as thiswill screw up the min and max
            }

            //System.out.println("size of arraylist is: " + csvDataRows.size());
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

        for (int  i = 0 ; i < numberOfColumns-2 ; i++ )
        {
            for ( int  j = 0 ; j < numberOfRows ; j++ )
            {
                if (  minimumCategories[i] > csvDataRows.get(j).dataFloats[i] )
                {
                    minimumCategories[i] = csvDataRows.get(j).dataFloats[i];
                    //globalMinimumCategories[i] = csvDataRows.get(j).dataFloats[i];

                }

                if (  maximumCategories[i] < csvDataRows.get(j).dataFloats[i] )
                {
                    maximumCategories[i] = csvDataRows.get(j).dataFloats[i];
                    //globalMaximumCategories[i] = csvDataRows.get(j).dataFloats[i];
                }

            }
        }

        //printing out minimum and maximum

        //System.out.println("Minimum categories");
        for( int  i = 0 ; i < numberOfColumns ; i++ )
        {
            //System.out.print( "  " + minimumCategories[i]);
            globalMinimumCategories[i] = minimumCategories[i];

        }
        //System.out.println();
        //System.out.println("Maximum categories");
        for( int  i = 0 ; i < numberOfColumns ; i++ )
        {
            //System.out.print( "  " + maximumCategories[i]);
            globalMaximumCategories[i] = maximumCategories[i];
        }
        //System.out.println();
        //System.out.println("end of setminmax");

    }

    public void drawDataLines()
    {

        //System.out.println("Drawline data lines");

        // one loop to figure out the points
        //draw the lines
        for ( int i = 0 ; i < numberOfRows ; i++ )
        {
            ArrayList<Point> linePoint = new ArrayList<Point>();
            linePoint.clear();
            for ( int j = 0 ; j < numberOfColumns-3 ; j ++ )
            {
                // i constant change j and height
                Point tempPoint = new Point();

               //x =  inputEcon * ( maxRange - minrange ) / (maxEcon - minEcon);
                //mapping function for x
                float x = csvDataRows.get(i).dataFloats[j] * (screenWidth  - 0 ) / (maximumCategories[j] - minimumCategories[j]);
                //mapping function for y

                //some padding + i * distancebetween
                float y =  i * screenHeight/5 ;

                tempPoint.set((int)x,(int)y);


               //RangeSeekBar name = (RangeSeekBar) findViewById(R.id.value1);

                linePoint.add(tempPoint);

            }

            // some call to draw the lines

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
