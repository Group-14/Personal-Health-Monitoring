package sqlite.model;

/**
 * Created by Jan on 4/2/15.
 */
public class History {
    int id;
    int caloriesOut;
    int caloriesIn;
    String routine;
    int steps;
    String date;

    public History(){

    }

    public History(int caloriesIn, int caloriesOut, String routine, int steps, String date){
        this.caloriesIn=caloriesIn;
        this.caloriesOut=caloriesOut;
        this.routine = routine;
        this.steps = steps;
        this.date = date;
    }

    public void setId(int id){
        this.id =id;
    }

    public void setcaloriesIn(int caloriesIn){
        this.caloriesIn=caloriesIn;
    }

    public void setcaloriesOut(int caloriesOut){
        this.caloriesOut=caloriesOut;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setDate(String date){
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public int getCaloriesIn(){
        return caloriesIn;
    }

    public int getcaloriesOut(){
        return caloriesOut;
    }

    public int getSteps(){
        return steps;
    }

    public String getRoutine(){
        return routine;
    }

    public String getDate(){
        return date;
    }
}
