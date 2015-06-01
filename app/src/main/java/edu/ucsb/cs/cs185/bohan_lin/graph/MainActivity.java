package edu.ucsb.cs.cs185.bohan_lin.graph;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private InputStream is;
    private BufferedReader reader;
    private String line;

    private int[] xValues;
    private String[] yValues;
    private int[][] zValues;
    private int zIndex = 0;

    private int rowIndex = 0;

    private String[] row;

    public void graphActivity(View view) {
        Intent graph = new Intent(this, Graph.class);
        if(view.getId() == R.id.data1){
            try {
                is = getAssets().open("dataset1.csv");
                reader = new BufferedReader(new InputStreamReader(is));
                line = reader.readLine();
                yValues = line.split(","); //first row contains the y axis value
                while((line = reader.readLine()) != null) { //rest of the rows
                    row = line.split(","); //split the row

                    //Toast.makeText(this, row[0], Toast.LENGTH_LONG).show();
                    //xValues[rowIndex] = Integer.parseInt(row[0]); //first cell in each row is x axis value

                    for(int i = 1; i < row.length; i++) { //every following cell
                        //zValues[rowIndex][zIndex] = Integer.parseInt(row[i]); //z value
                        zIndex++;
                    }

                    rowIndex++;
                    zIndex = 0;
                }
            } catch(IOException ex) {
            }
        }
        graph.putExtra("xValues", xValues);
        graph.putExtra("yValues", yValues);
        graph.putExtra("zValues", zValues);
        startActivity(graph);
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
