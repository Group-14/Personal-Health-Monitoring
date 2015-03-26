package com.trainer.g14.g_trainer.database;

/**
 * Created by Holland on 3/25/15.
 */
public class Location {

    public int id;
    public double lat, lng;
    public String name;

    public Location() {
    }

    @Override
    //public String toString() {
        public String toString() {
            return "Location [id=" + id
                    + ",name=" + name
                    + ",lat=" + lat
                    + ",lng=" + lng + "]";
        }
    //}
}