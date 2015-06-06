package edu.ucsb.cs.cs185.bohan_lin.graph;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

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
        setContentView(R.layout.activity_graph);
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
