package com.trainer.g14.g_trainer;

import android.content.Context;

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

    private DatabaseHelper db;
    private DatabaseHelper2 db2;

    public setupDB(Context context){
        // workout db
        db=new DatabaseHelper(context);
        // history db
        db2=new DatabaseHelper2(context);
    }

    public void setup(){
        // check if empty
        if(db.getExerciseCount()==0) {

            long rt, exercise, rp;

            // core workout
            rt = db.createRoutine(new Routine("Core"));
            rp = db.createRepSet(new RepSet(20, 10));
            exercise = db.createExercise(new Exercise("Sit Ups"));
            db.createExerciseRoutine(exercise, rt, rp);

            exercise = db.createExercise(new Exercise("Push Ups"));
            rp = db.createRepSet(new RepSet(20, 10));
            db.createExerciseRoutine(exercise, rt, rp);

            //legs
            // core workout
            rt = db.createRoutine(new Routine("Legs"));
            rp = db.createRepSet(new RepSet(20, 2));
            exercise = db.createExercise(new Exercise("Squats"));
            db.createExerciseRoutine(exercise, rt, rp);

            exercise = db.createExercise(new Exercise("Step Ups"));
            rp = db.createRepSet(new RepSet(20, 10));
            db.createExerciseRoutine(exercise, rt, rp);
        }
        if(db2.getHistoryCount()==0){
            db2.createHistory(new History(100,"Core",50,getDateTime()));
            db2.createHistory(new History(500,"Legs",50,getDateTime()));
        }
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
}
