package com.trainer.g14.g_trainer;

/**
 * Created by Jan on 4/2/15.
 */
public class exercise {

    String name;
    int sets;
    int reps;

    public exercise(String name, int sets, int reps){
        this.name=name;
        this.sets=sets;
        this.reps=reps;
    }

    public String getName(){
        return name;
    }

    public int getSets(){
        return sets;
    }

    public int getReps(){
        return reps;
    }
}
