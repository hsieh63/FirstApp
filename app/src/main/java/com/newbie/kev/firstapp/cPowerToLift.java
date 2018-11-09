package com.newbie.kev.firstapp;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Created by Admin on 1/31/2018.
 */

class cPowerToLift {
    //members
    private int weekNumber;
    private int dayNumber;

    private static final double[][] rpeTable = new double[][] {
         //reps 1   2    3   4    5    6    7    8    9    10
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
    private static final int [][][] exerRepArray = {
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

    private String[] getAllSets(String weightSInput, String rpeSInput, String repSInput, int exerciseChoice) {
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
                int targetReps = exerRepArray[exerciseChoice][dayNumber][weekNumber];
                //for the PIP2 numbers work out this way
                double weightMult = 0;
                //exercise 3 aims for rpe 8
                if(exerciseChoice == 2) {
                    weightMult = rpeTable[rpeLookup(8)][(targetReps - 1)];
                }
                //exercise 1 and 2 aim for rpe 9
                else {
                    weightMult = rpeTable[rpeLookup(9)][(targetReps - 1)];
                }

                lasterm = Double.valueOf(df.format(lasterm));
                lasterm = roundUpHalf(lasterm);
                double workingWeight = lasterm * 1.005 * weightMult;
                workingWeight = Double.valueOf(df.format(workingWeight));

                retStringList[0] = String.valueOf(df.format(roundFive(workingWeight * .3))) + " x 5";
                retStringList[1] = String.valueOf(df.format(roundFive(workingWeight * .5))) + " x 5";
                retStringList[2] = String.valueOf(df.format(roundFive(workingWeight * .7))) + " x 3";
                retStringList[3] = String.valueOf(df.format(roundFive(workingWeight * .8))) + " x 1";
                retStringList[4] = String.valueOf(df.format(roundFive(workingWeight * .9))) + " x " + String.valueOf(targetReps);
                retStringList[5] = String.valueOf(df.format(roundFive(workingWeight * .95))) + " x " + String.valueOf(targetReps);
                retStringList[6] = String.valueOf(df.format(workingWeight)) + " x " + String.valueOf(targetReps);
                retStringList[7] = String.valueOf("E 1rm " + lasterm);
            }
        }
        return retStringList;
    }

    public cPowerToLift(int dayNumber, int weekNumber) {
        //array is zero index, day/week is 1 index
        this.dayNumber = dayNumber - 1;
        this.weekNumber = weekNumber - 1;
    }

    public String[] getEx1AllSets(String weightSInput, String rpeSInput, String repSInput) {
        String[] retStringList = new String[8];
        retStringList = getAllSets(weightSInput, rpeSInput, repSInput, 0);
        return retStringList;
    }

    public String[] getEx2AllSets(String weightSInput, String rpeSInput, String repSInput) {
        String[] retStringList = new String[8];
        retStringList = getAllSets(weightSInput, rpeSInput, repSInput, 1);
        return retStringList;
    }

    public String[] getEx3AllSets(String weightSInput, String rpeSInput, String repSInput) {
        String[] retStringList = new String[8];
        retStringList = getAllSets(weightSInput, rpeSInput, repSInput, 2);
        return retStringList;
    }
}
