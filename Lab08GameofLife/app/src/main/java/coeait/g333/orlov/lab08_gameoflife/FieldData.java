package coeait.g333.orlov.lab08_gameoflife;

import java.sql.Timestamp;

public class FieldData {

    public int id;
    public String name;
    public int width;
    public int height;
    public String map_data;
    public Timestamp created;
    public Timestamp modified;
    public int base_map;


    public FieldData(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

}
