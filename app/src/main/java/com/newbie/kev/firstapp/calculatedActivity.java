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

        int weekNumber = Integer.parseInt(weekNum);
        int dayNumber = Integer.parseInt(dayNum);
        Log.d("test", "Got the intent");
        Log.d("Week number", weekNum);
        Log.d("day number", dayNum);
        cPowerToLift liftTemplate = new cPowerToLift(dayNumber, weekNumber);

        //from here to before creating the grid i should make a function that returns a string array of the value passed on the weight inputs
        String exer1Name = exerciseName1[dayNumber-1];
        String exer2Name = exerciseName2[dayNumber-1];
        String exer3Name = exerciseName3[dayNumber-1];

        GridView outputGrid = (GridView) findViewById(R.id.calculationGridView);
        ArrayList<String> items = new ArrayList<String>();

        //too lazy right now to fix the grid just going to cheat
        //all list have the same length
        String[] setList1 = liftTemplate.getEx1AllSets(weight1S, rpe1S, rep1S);
        String[] setList2 = liftTemplate.getEx2AllSets(weight2S, rpe2S, rep2S);
        String[] setList3 = liftTemplate.getEx3AllSets(weight3S, rpe3S, rep3S);

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