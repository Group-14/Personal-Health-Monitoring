package sqlite.model;

/**
 * Created by Jan on 4/1/15.
 */
public class RepSet {

    int id;
    int reps;
    int sets;

    public RepSet(){

    }

    public RepSet(int reps, int sets){
        this.sets=sets;
        this.reps=reps;
    }

    public RepSet(int id, int reps, int sets){
        this.id=id;
        this.sets=sets;
        this.reps=reps;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setReps(int reps){
        this.reps=reps;
    }

    public void setSets(int sets){
        this.sets=sets;
    }

    public long getId(){
        return id;
    }

    public int getReps(){
        return reps;
    }

    public int getSets(){
        return sets;
    }
}
