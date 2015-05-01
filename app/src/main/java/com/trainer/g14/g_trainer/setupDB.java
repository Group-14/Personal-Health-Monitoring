package com.trainer.g14.g_trainer;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sqlite.helper.DatabaseHelper;
import sqlite.helper.DatabaseHelper2;
import sqlite.model.RepSet;
import sqlite.model.Exercise;
import sqlite.model.Routine;
import sqlite.model.History;

/**
 * Created by Jan on 4/1/15.
 */
public class setupDB {
    private static final String TAG = setupDB.class.getSimpleName();

    private DatabaseHelper db; //routine db
    private DatabaseHelper2 db2; //history db

    public setupDB(Context context){
        // workout db
        db=new DatabaseHelper(context);
        // history db
        db2=new DatabaseHelper2(context);
    }

    public void setup(){
        // check if empty
        if(db.getExerciseCount()==0) { //only fill in db if it's empty

            long rt, exercise, rp;

            // core workout
            rt = db.createRoutine(new Routine("Core"));
            //situps
            rp = db.createRepSet(new RepSet(20, 10));
            exercise = db.createExercise(new Exercise("Sit Ups"));
            db.createExerciseRoutine(exercise, rt, rp);
            //pushups
            exercise = db.createExercise(new Exercise("Push Ups"));
            rp = db.createRepSet(new RepSet(20, 10));
            db.createExerciseRoutine(exercise, rt, rp);

            //legs
            rt = db.createRoutine(new Routine("Legs"));
            //squats
            rp = db.createRepSet(new RepSet(20, 2));
            exercise = db.createExercise(new Exercise("Squats"));
            db.createExerciseRoutine(exercise, rt, rp);
            //step ups
            exercise = db.createExercise(new Exercise("Step Ups"));
            rp = db.createRepSet(new RepSet(20, 10));
            db.createExerciseRoutine(exercise, rt, rp);
            //leg curls
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Leg Curls"));
            db.createExerciseRoutine(exercise, rt, rp);
            //leg press
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Leg Press"));
            db.createExerciseRoutine(exercise, rt, rp);
            //leg extensions
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Leg Extensions"));
            db.createExerciseRoutine(exercise, rt, rp);

            //chest workout
            rt = db.createRoutine(new Routine("Chest"));
            //bench press
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Flat Bench Press"));
            db.createExerciseRoutine(exercise, rt, rp);
            //Incline Press
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Incline Bench Press"));
            db.createExerciseRoutine(exercise, rt, rp);
            //Flat Fly
            rp = db.createRepSet(new RepSet(15, 3));
            exercise = db.createExercise(new Exercise("Flat Bench Dumbbell Flys"));
            db.createExerciseRoutine(exercise, rt, rp);
            //Incline Fly
            rp = db.createRepSet(new RepSet(15, 3));
            exercise = db.createExercise(new Exercise("Incline Bench Dumbbell Flys"));
            db.createExerciseRoutine(exercise, rt, rp);
            //Decline Flys
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Decline Bench Press"));
            db.createExerciseRoutine(exercise, rt, rp);
            //Push Ups
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Push Ups"));
            db.createExerciseRoutine(exercise, rt, rp);

<<<<<<< HEAD
            //shoulder workout
            rt = db.createRoutine(new Routine("Shoulders"));
            //shoulder press
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Dumbbell Shoulder Press"));
            db.createExerciseRoutine(exercise, rt, rp);
            //Arnoald press
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Arnoald Dumbbell Press"));
            db.createExerciseRoutine(exercise, rt, rp);
            // Side Laterals
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Dumbbell Side Laterals"));
            db.createExerciseRoutine(exercise, rt, rp);
            // Reverse Flys
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Reverse Flys"));
            db.createExerciseRoutine(exercise, rt, rp);

            //Back workout
            rt = db.createRoutine(new Routine("Back"));
            //wide pull ups
            rp = db.createRepSet(new RepSet(8, 3));
            exercise = db.createExercise(new Exercise("Wide Grip Pull Ups"));
            db.createExerciseRoutine(exercise, rt, rp);
            //bent ver row
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Bent Over Row"));
            db.createExerciseRoutine(exercise, rt, rp);
            // lat pull downs
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Lat Pulldowns"));
            db.createExerciseRoutine(exercise, rt, rp);

            //Arms workout
            rt = db.createRoutine(new Routine("Arms"));
            //dumbbell curls
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Alt Dubbell Curls"));
            db.createExerciseRoutine(exercise, rt, rp);
            //Preacher Curls
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Preacher Curls"));
            db.createExerciseRoutine(exercise, rt, rp);
            //21's
            rp = db.createRepSet(new RepSet(21, 3));
            exercise = db.createExercise(new Exercise("21"));
            db.createExerciseRoutine(exercise, rt, rp);
            //Press Downs
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Stright Bar Pressdowns"));
            db.createExerciseRoutine(exercise, rt, rp);
            //Dumbbell Kick Backs
            rp = db.createRepSet(new RepSet(6, 3));
            exercise = db.createExercise(new Exercise("Dumbbell Kickbacks"));
            db.createExerciseRoutine(exercise, rt, rp);
            //Dips
            rp = db.createRepSet(new RepSet(10, 3));
            exercise = db.createExercise(new Exercise("Dips"));
            db.createExerciseRoutine(exercise, rt, rp);

=======
>>>>>>> origin/master
        }
        // fill in history db if empty
        if(db2.getHistoryCount()==0){
            db2.createHistory(new History(1000,100,"Core",50,getDateTime(2015,3,1)));
            db2.createHistory(new History(980,500,"Legs",50,getDateTime(2015,3,10)));
            db2.createHistory(new History(600,500,"Legs",50,getDateTime(2015,3,15)));
            db2.createHistory(new History(550,400,"",50,getDateTime(2015,3,20)));
            db2.createHistory(new History(550,400,"Core",50,getDateTime(2015,3,26)));
            db2.createHistory(new History(550,400,"Core",50,getDateTime(2015,3,27)));
            db2.createHistory(new History(550,400,"Core",50,getDateTime(2015,3,28)));
        }

        //close databases
        db.closeDB();
        db2.closeDB();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, MMM d, yyyy h:mm a", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * get datetime
     * */
    private String getDateTime(int year, int month, int day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, MMM d, yyyy h:mm a", Locale.getDefault());
        Date date = new Date(year-1900,month,day,0,0);
        return dateFormat.format(date);
    }
}
