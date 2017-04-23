package com.newbie.kev.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class calculatedActivity extends ActionBarActivity {

    //members
    private int weekNumber;
    private int dayNumber;

    private double[][] rpeTable = new double[][] {
        {.88, .85, .82, .80, .77, .75, .72, .69, .67, .64}, //6.5
        {.89, .86, .84, .81, .79, .76, .74, .71, .68, .65}, //7
        {.91, .88, .85, .82, .80, .77, .75, .72, .69, .67}, //7.5
        {.92, .89, .86, .84, .81, .79, .76, .74, .71, 68}, //8
        {.94, .91, .88, .85, .82, .8, .77, .75, .72, .69}, //8.5
        {.96, .92, .89, .86, .84, .81, .79, .76, .74, .71}, //9
        {.98, .94, .91, .88, .85, .82, .8, .77, .75, .72}, //9.5
        {1, .96, .92, .89, .86, .84, .81, .79, .76, .74} //10
    };

    //[exer][day][week]
    private double [][][] exerMultArray = {
            {
                    {.81, .84, .86},
                    {.81, .84, .86},
                    {.89, .92, .96},
                    {.89, .92, .96}
            },
            {
                    {.81, .84, .86},
                    {.81, .84, .86},
                    {.89, .92, .96},
                    {.89, .92, .96}
            },
            {
                    {.74, .76, .79},
                    {.74, .76, .79},
                    {.74, .76, .79},
                    {.74, .76, .79}
            }
    };

    //[exer][day][week]
    private double [][][] exerRepArray = {
            {
                    {6, 5, 4},
                    {6, 5, 4},
                    {3, 2, 1},
                    {3, 2, 1}
            },
            {
                    {6, 5, 4},
                    {6, 5, 4},
                    {3, 2, 1},
                    {3, 2, 1}
            },
            {
                    {8, 7, 6},
                    {8, 7, 6},
                    {8, 7, 6},
                    {8, 7, 6}            }
    };
    private int rpeLookup(double rpe) {
        int retVal = -1;
        if(rpe == 6.5){
            retVal = 0;
        }
        else if(rpe == 7.0) {
            retVal = 1;
        }
        else if(rpe == 7.5) {
            retVal = 2;
        }
        else if(rpe == 8.0) {
            retVal = 3;
        }
        else if(rpe == 8.5) {
            retVal = 4;
        }
        else if(rpe == 9.0) {
            retVal = 5;
        }
        else if(rpe == 9.5) {
            retVal = 6;
        }
        else if(rpe == 10.0) {
            retVal = 7;
        }
        return retVal;
    }

    private double roundFive(double input) {
        double retVal = 0;
        retVal = Math.round(input + 2.5) / 5 * 5;
        return retVal;
    }

    private double roundUpHalf(double input) {
        double retVal = 0;
        retVal = Math.round(input*2)/2f;
        return retVal;
    }

    private String[] getAllSets(String weightSInput, String rpeSInput, String repSInput, double weightMult) {
        String[] retStringList = new String[8];
        Arrays.fill(retStringList,"");
        DecimalFormat df = new DecimalFormat("#.#");

        if(!(weightSInput.isEmpty() || rpeSInput.isEmpty() || repSInput.isEmpty())) {
            double weightInput = Double.parseDouble(weightSInput);
            int repInt = Integer.parseInt(repSInput);
            double rpeInput = Double.parseDouble(rpeSInput);

            Log.d("weightInput", String.valueOf(weightInput));
            Log.d("repInt", String.valueOf(repInt));
            Log.d("rpeInput", String.valueOf(rpeInput));
            if(weightInput == 0 || repInt == 0 || rpeInput == 0) {
                return retStringList;
            }
            else {
                double lasterm = (weightInput / rpeTable[rpeLookup(rpeInput)][(repInt - 1)]) * 1.005;
                lasterm = Double.valueOf(df.format(lasterm));
                lasterm = roundUpHalf(lasterm);
                double workingWeight = lasterm * 1.005 * weightMult;
                workingWeight = Double.valueOf(df.format(workingWeight));

                retStringList[0] = String.valueOf(df.format(roundFive(workingWeight * .3))) + " x 5";
                retStringList[1] = String.valueOf(df.format(roundFive(workingWeight * .5))) + " x 5";
                retStringList[2] = String.valueOf(df.format(roundFive(workingWeight * .7))) + " x 3";
                retStringList[3] = String.valueOf(df.format(roundFive(workingWeight * .8))) + " x 1";
                retStringList[4] = String.valueOf(df.format(roundFive(workingWeight * .9))) + " x " + String.valueOf(exerRepArray[0][(dayNumber - 1)][(weekNumber - 1)]);
                retStringList[5] = String.valueOf(df.format(roundFive(workingWeight * .95))) + " x " + String.valueOf(exerRepArray[0][(dayNumber - 1)][(weekNumber - 1)]);
                retStringList[6] = String.valueOf(df.format(workingWeight)) + " x " + String.valueOf(exerRepArray[0][(dayNumber - 1)][(weekNumber - 1)]);
                retStringList[7] = String.valueOf("E 1rm " + lasterm);
            }
        }
        return retStringList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);

        String[] exerciseName1 = getResources().getStringArray(R.array.pip_2_exercises_1);
        String[] exerciseName2 = getResources().getStringArray(R.array.pip_2_exercises_2);
        String[] exerciseName3 = getResources().getStringArray(R.array.pip_2_exercises_3);

        //should really learn how to pass an array around
        Intent intent = getIntent();
        String weight1S = intent.getStringExtra("weight1");
        String rep1S = intent.getStringExtra("rep1");
        String rpe1S = intent.getStringExtra("rpe1");
        String weight2S = intent.getStringExtra("weight2");
        String rep2S = intent.getStringExtra("rep2");
        String rpe2S = intent.getStringExtra("rpe2");
        String weight3S = intent.getStringExtra("weight3");
        String rep3S = intent.getStringExtra("rep3");
        String rpe3S = intent.getStringExtra("rpe3");
        String weekNum = intent.getStringExtra("weekNum");
        String dayNum = intent.getStringExtra("dayNum");

        weekNumber = Integer.parseInt(weekNum);
        dayNumber = Integer.parseInt(dayNum);
        Log.d("test", "Got the intent");

        //for the PIP2 numbers work out this way
        double weightMult1 = 0;
        double weightMult2 = 0;
        double weightMult3 = 0;

        //from here to before creating the grid i should make a function that returns a string array of the value passed on the weight inputs
        weightMult1 = exerMultArray[0][(dayNumber - 1)][(weekNumber - 1)];
        weightMult2 = exerMultArray[1][(dayNumber - 1)][(weekNumber - 1)];
        weightMult3 = exerMultArray[2][(dayNumber - 1)][(weekNumber - 1)];
        String exer1Name = exerciseName1[dayNumber-1];
        String exer2Name = exerciseName2[dayNumber-1];
        String exer3Name = exerciseName3[dayNumber-1];

        GridView outputGrid = (GridView) findViewById(R.id.calculationGridView);
        ArrayList<String> items = new ArrayList<String>();

        //too lazy right now to fix the grid just going to cheat
        //all list have the same length
        String[] setList1 = getAllSets(weight1S, rpe1S, rep1S, weightMult1);
        String[] setList2 = getAllSets(weight2S, rpe2S, rep2S, weightMult2);
        String[] setList3 = getAllSets(weight3S, rpe3S, rep3S, weightMult3);

        items.add(exer1Name);
        items.add(exer2Name);
        items.add(exer3Name);

        for(int alIndex = 0; alIndex < setList1.length; alIndex++)
        {
            items.add(setList1[alIndex]);
            items.add(setList2[alIndex]);
            items.add(setList3[alIndex]);
        }

        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        outputGrid.setAdapter(simpleAdapter);
        Log.d("test", "set adapter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}

/*
 */