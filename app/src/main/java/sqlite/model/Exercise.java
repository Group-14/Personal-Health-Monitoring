package sqlite.model;

/**
 * Created by Jan on 4/1/15.
 */
public class Exercise {

    int id;
    String name;

    public Exercise(){

    }

    public Exercise(String name){
        this.name=name;
    }

    public Exercise(int id, String name){
        this.id=id;
        this.name=name;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
