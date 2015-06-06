package edu.ucsb.cs.cs185.bohan_lin.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void graphActivity(View view) {
        Intent graph = new Intent(this, Graph.class);
        if (view.getId() == R.id.data1) {
            System.out.println("-----------------starting the parsing -----------------");


            ArrayList<String> csvCategoryNames = new ArrayList<>();                       // arraylist to store the name of the csv stuff
            ArrayList<inputCSVdata> csvDataRows = new ArrayList<>();                //creating an arraylist of inputCSV Data Class

            try {
                InputStreamReader csvStreamReader = new InputStreamReader(getAssets().open("cars.csv"));
                CSVReader reader = new CSVReader(csvStreamReader);

                String[] firstLine;
                firstLine = reader.readNext();
                for (int i = 0; i < firstLine.length; i++) {
                    csvCategoryNames.add(firstLine[i]); /// adding the row names to the category names
                }
                System.out.println("Header names: " + firstLine[0] + "   " + firstLine[1] + "  " + firstLine[2] + "   " + firstLine[3] + " " + firstLine[4]);

                //**
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {

                    inputCSVdata tempRowdata = new inputCSVdata();

                    tempRowdata.name = nextLine[0];

                    try {

                        for (int i = 0; i < nextLine.length - 1; i++) {
                            tempRowdata.dataFloats[i] = Float.parseFloat(nextLine[i + 1]);
                            System.out.println(tempRowdata.dataFloats[i]);
                        }


                    } catch (NumberFormatException ex) {
                        System.out.println("found a 0 lol ");
                        for (int i = 0; i < nextLine.length - 1; i++)
                            tempRowdata.dataFloats[i] = 0.0f;

                    }

                    csvDataRows.add(tempRowdata);
                }
                //*/


                System.out.println("size of arraylist is: " + csvDataRows.size());


            } catch (IOException e) {
                e.printStackTrace();
            }

            startActivity(graph);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
