package com.newbie.kev.firstapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private String[] exerciseName1;
    private String[] exerciseName2;
    private String[] exerciseName3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exerciseName1 = getResources().getStringArray(R.array.pip_2_exercises_1);
        exerciseName2 = getResources().getStringArray(R.array.pip_2_exercises_2);
        exerciseName3 = getResources().getStringArray(R.array.pip_2_exercises_3);

        Spinner weekNumberDrop = (Spinner) findViewById(R.id.weekNumSpinner);
        ArrayAdapter<CharSequence> wnSpinAdapter = ArrayAdapter.createFromResource(this, R.array.week_number_array, android.R.layout.simple_spinner_item);
        wnSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekNumberDrop.setAdapter(wnSpinAdapter);

        Spinner dayNumberDrop = (Spinner) findViewById(R.id.dayNumSpinner);
        ArrayAdapter<CharSequence> daySpinAdapter = ArrayAdapter.createFromResource(this, R.array.day_number_array, android.R.layout.simple_spinner_item);
        daySpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayNumberDrop.setAdapter(daySpinAdapter);

        Spinner exerciseRPE1Drop = (Spinner) findViewById(R.id.exerciseRPE1);
        ArrayAdapter<CharSequence> exerciseRPE1Adapter = ArrayAdapter.createFromResource(this, R.array.rpe_array, android.R.layout.simple_spinner_item);
        exerciseRPE1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseRPE1Drop.setAdapter(exerciseRPE1Adapter);
        exerciseRPE1Drop.setSelection(7);

        Spinner exerciseRPE2Drop = (Spinner) findViewById(R.id.exerciseRPE2);
        ArrayAdapter<CharSequence> exerciseRPE2Adapter = ArrayAdapter.createFromResource(this, R.array.rpe_array, android.R.layout.simple_spinner_item);
        exerciseRPE2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseRPE2Drop.setAdapter(exerciseRPE2Adapter);
        exerciseRPE2Drop.setSelection(7);

        Spinner exerciseRPE3Drop = (Spinner) findViewById(R.id.exerciseRPE3);
        ArrayAdapter<CharSequence> exerciseRPE3Adapter = ArrayAdapter.createFromResource(this, R.array.rpe_array, android.R.layout.simple_spinner_item);
        exerciseRPE3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseRPE3Drop.setAdapter(exerciseRPE3Adapter);
        exerciseRPE3Drop.setSelection(5);

        dayNumberDrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView exerciseTextView1 = (TextView) findViewById(R.id.exerciseName1);
                TextView exerciseTextView2 = (TextView) findViewById(R.id.exerciseName2);
                TextView exerciseTextView3 = (TextView) findViewById(R.id.exerciseName3);
                exerciseTextView1.setText(exerciseName1[((int) id)]);
                exerciseTextView2.setText(exerciseName2[((int) id)]);
                exerciseTextView3.setText(exerciseName3[((int) id)]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    public void calculatePage(View view){
        Intent intent = new Intent(this, calculatedActivity.class);
        String exerciseWeight1 = ((EditText) findViewById(R.id.exerciseWeight1)).getText().toString();
        String exerciseReps1 = ((EditText) findViewById(R.id.exerciseReps1)).getText().toString();
        String exerciseRPE1 = ((Spinner) findViewById(R.id.exerciseRPE1)).getSelectedItem().toString();
        String exerciseWeight2 = ((EditText) findViewById(R.id.exerciseWeight2)).getText().toString();
        String exerciseReps2 = ((EditText) findViewById(R.id.exerciseReps2)).getText().toString();
        String exerciseRPE2 = ((Spinner) findViewById(R.id.exerciseRPE2)).getSelectedItem().toString();
        String exerciseWeight3 = ((EditText) findViewById(R.id.exerciseWeight3)).getText().toString();
        String exerciseReps3 = ((EditText) findViewById(R.id.exerciseReps3)).getText().toString();
        String exerciseRPE3 = ((Spinner) findViewById(R.id.exerciseRPE3)).getSelectedItem().toString();
        Spinner dayNumberDrop = (Spinner) findViewById(R.id.dayNumSpinner);
        String dayNumber = dayNumberDrop.getSelectedItem().toString();
        Spinner weekNumberDrop = (Spinner) findViewById(R.id.weekNumSpinner);
        String weekNumber = weekNumberDrop.getSelectedItem().toString();
        intent.putExtra("weight1", exerciseWeight1);
        intent.putExtra("rep1", exerciseReps1);
        intent.putExtra("rpe1", exerciseRPE1);
        intent.putExtra("weight2", exerciseWeight2);
        intent.putExtra("rep2", exerciseReps2);
        intent.putExtra("rpe2", exerciseRPE2);
        intent.putExtra("weight3", exerciseWeight3);
        intent.putExtra("rep3", exerciseReps3);
        intent.putExtra("rpe3", exerciseRPE3);
        intent.putExtra("dayNum", dayNumber);
        intent.putExtra("weekNum", weekNumber);
        Log.d("test","Before starting intent");
        startActivity(intent);
    }
}
