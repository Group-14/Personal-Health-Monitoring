package sqlite.model;

/**
 * Created by Jan on 4/2/15.
 */
public class History {
    int id;
    int calories;
    String routine;
    int steps;
    String date;

    public History(){

    }

    public History(int calories, String routine, int steps, String date){
        this.calories=calories;
        this.routine = routine;
        this.steps = steps;
        this.date = date;
    }

    public void setId(int id){
        this.id =id;
    }

    public void setCalories(int calories){
        this.calories=calories;
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

    public int getCalories(){
        return calories;
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
