package com.newbie.kev.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class calculatedActivity extends ActionBarActivity {

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
    };

    private double roundUpHalf(double input) {
        double retVal = 0;
        retVal = Math.round(input*2)/2f;
        return retVal;
    };

    private String[] exerciseName1;
    private String[] exerciseName2;
    private String[] exerciseName3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated);

        exerciseName1 = getResources().getStringArray(R.array.pip_2_exercises_1);
        exerciseName2 = getResources().getStringArray(R.array.pip_2_exercises_2);
        exerciseName3 = getResources().getStringArray(R.array.pip_2_exercises_3);

        Intent intent = getIntent();
        String weight1S = intent.getStringExtra("weight1");
        Log.d("test", weight1S);
        String rep1S = intent.getStringExtra("rep1");
        Log.d("test", rep1S);
        String rpe1S = intent.getStringExtra("rpe1");
        Log.d("test", rpe1S);
        String weight2S = intent.getStringExtra("weight2");
        Log.d("test", weight2S);
        String rep2S = intent.getStringExtra("rep2");
        Log.d("test", rep2S);
        String rpe2S = intent.getStringExtra("rpe2");
        Log.d("test", rpe2S);
        String weight3S = intent.getStringExtra("weight3");
        Log.d("test", weight3S);
        String rep3S = intent.getStringExtra("rep3");
        Log.d("test", rep3S);
        String rpe3S = intent.getStringExtra("rpe3");
        Log.d("test", rpe3S);
        String weekNum = intent.getStringExtra("weekNum");
        Log.d("test", weekNum);
        String dayNum = intent.getStringExtra("dayNum");
        Log.d("test", dayNum);

        double weight1I = Double.parseDouble(weight1S);
        int rep1I = Integer.parseInt(rep1S);
        double rpe1I = Double.parseDouble(rpe1S);
        double weight2I = Double.parseDouble(weight2S);
        int rep2I = Integer.parseInt(rep2S);
        double rpe2I = Double.parseDouble(rpe2S);
        double weight3I = Double.parseDouble(weight3S);
        int rep3I = Integer.parseInt(rep3S);
        double rpe3I = Double.parseDouble(rpe3S);
        int weekNumber = Integer.parseInt(weekNum);
        int dayNumber = Integer.parseInt(dayNum);
        Log.d("test", "Got the intent");

        DecimalFormat df = new DecimalFormat("#.#");
        //for the PIP2 numbers work out this way
        int rep1 = 0;
        double weightMult1 = 0;
        int rep2 = 0;
        double weightMult2 = 0;
        int rep3 = 0;
        double weightMult3 = 0;

        weightMult1 = exerMultArray[0][(dayNumber - 1)][(weekNumber - 1)];
        weightMult2 = exerMultArray[1][(dayNumber - 1)][(weekNumber - 1)];
        weightMult3 = exerMultArray[2][(dayNumber - 1)][(weekNumber - 1)];

        Log.d("test", String.valueOf(weightMult1));
        Log.d("test", String.valueOf(weightMult2));
        Log.d("test", String.valueOf(weightMult3));
        Log.d("test", String.valueOf(rpeLookup(rpe1I)));
        double laste1rm = (weight1I/rpeTable[rpeLookup(rpe1I)][(rep1I-1)]) * 1.005;
        laste1rm = Double.valueOf(df.format(laste1rm));
        laste1rm = roundUpHalf(laste1rm);
        double laste2rm = (weight2I/rpeTable[rpeLookup(rpe2I)][(rep2I-1)]) * 1.005;
        laste2rm = Double.valueOf(df.format(laste2rm));
        laste2rm = roundUpHalf(laste2rm);
        double laste3rm = (weight3I/rpeTable[rpeLookup(rpe3I)][(rep3I-1)]) * 1.005;
        laste3rm = Double.valueOf(df.format(laste3rm));
        laste3rm = roundUpHalf(laste3rm);
        double workingWeight1 = laste1rm * 1.005 * weightMult1;
        workingWeight1 = Double.valueOf(df.format(workingWeight1));
        double workingWeight2 = laste2rm * 1.005 * weightMult2;
        workingWeight2 = Double.valueOf(df.format(workingWeight2));
        double workingWeight3 = laste3rm *1.005 * weightMult3;
        workingWeight3 = Double.valueOf(df.format(workingWeight3));
        Log.d("test", "made it past the double calculations");

        String exer1Name = exerciseName1[dayNumber-1];
        String exer1rep1 = String.valueOf(df.format(roundFive(workingWeight1 * .3))) + " x 5";
        String exer1rep2 = String.valueOf(df.format(roundFive(workingWeight1 * .5))) + " x 5";
        String exer1rep3 = String.valueOf(df.format(roundFive(workingWeight1 * .7))) + " x 3";
        String exer1rep4 = String.valueOf(df.format(roundFive(workingWeight1 * .8))) + " x 1";
        String exer1rep5 = String.valueOf(df.format(roundFive(workingWeight1 * .9))) + " x " + String.valueOf(exerRepArray[0][(dayNumber - 1)][(weekNumber - 1)]);
        String exer1rep6 = String.valueOf(df.format(roundFive(workingWeight1 * .95))) + " x " + String.valueOf(exerRepArray[0][(dayNumber - 1)][(weekNumber - 1)]);
        String exer1rep7 = String.valueOf(df.format(workingWeight1)) + " x " + String.valueOf(exerRepArray[0][(dayNumber - 1)][(weekNumber - 1)]);
        String exer2Name = exerciseName2[dayNumber-1];
        String exer2rep1 = String.valueOf(df.format(roundFive(workingWeight2 * .3))) + " x 5";
        String exer2rep2 = String.valueOf(df.format(roundFive(workingWeight2 * .5))) + " x 5";
        String exer2rep3 = String.valueOf(df.format(roundFive(workingWeight2 * .7))) + " x 3";
        String exer2rep4 = String.valueOf(df.format(roundFive(workingWeight2 * .8))) + " x 1";
        String exer2rep5 = String.valueOf(df.format(roundFive(workingWeight2 * .9))) + " x " + String.valueOf(exerRepArray[1][(dayNumber - 1)][(weekNumber - 1)]);
        String exer2rep6 = String.valueOf(df.format(roundFive(workingWeight2 * .95))) + " x " + String.valueOf(exerRepArray[1][(dayNumber - 1)][(weekNumber - 1)]);
        String exer2rep7 = String.valueOf(df.format(workingWeight2)) + " x " + String.valueOf(exerRepArray[1][(dayNumber - 1)][(weekNumber - 1)]);
        String exer3Name = exerciseName3[dayNumber-1];
        String exer3rep1 = String.valueOf(df.format(roundFive(workingWeight3 * .3))) + " x 5";
        String exer3rep2 = String.valueOf(df.format(roundFive(workingWeight3 * .5))) + " x 5";
        String exer3rep3 = String.valueOf(df.format(roundFive(workingWeight3 * .7))) + " x 3";
        String exer3rep4 = String.valueOf(df.format(roundFive(workingWeight3 * .8))) + " x 1";
        String exer3rep5 = String.valueOf(df.format(roundFive(workingWeight3 * .9))) + " x " + String.valueOf(exerRepArray[2][(dayNumber - 1)][(weekNumber - 1)]);
        String exer3rep6 = String.valueOf(df.format(roundFive(workingWeight3 * .95))) + " x " + String.valueOf(exerRepArray[2][(dayNumber - 1)][(weekNumber - 1)]);
        String exer3rep7 = String.valueOf(df.format(workingWeight3)) + " x " + String.valueOf(exerRepArray[2][(dayNumber - 1)][(weekNumber - 1)]);

        GridView outputGrid = (GridView) findViewById(R.id.calculationGridView);
        ArrayList<String> items = new ArrayList<String>();

        items.add(exer1Name);
        items.add(exer2Name);
        items.add(exer3Name);

        items.add(exer1rep1);
        items.add(exer2rep1);
        items.add(exer3rep1);

        items.add(exer1rep2);
        items.add(exer2rep2);
        items.add(exer3rep2);

        items.add(exer1rep3);
        items.add(exer2rep3);
        items.add(exer3rep3);

        items.add(exer1rep4);
        items.add(exer2rep4);
        items.add(exer3rep4);

        items.add(exer1rep5);
        items.add(exer2rep5);
        items.add(exer3rep5);

        items.add(exer1rep6);
        items.add(exer2rep6);
        items.add(exer3rep6);

        items.add(exer1rep7);
        items.add(exer2rep7);
        items.add(exer3rep7);

        items.add(String.valueOf(laste1rm));
        items.add(String.valueOf(laste2rm));
        items.add(String.valueOf(laste3rm));
        
        System.out.println("adding items");
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        outputGrid.setAdapter(simpleAdapter);
        Log.d("test", "set adapter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}

/*
 */