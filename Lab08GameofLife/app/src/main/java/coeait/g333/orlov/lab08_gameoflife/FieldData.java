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


    public FieldData(int id, String name, int width, int height, String map_data, Timestamp created, Timestamp modified, int base_map) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.map_data = map_data;
        this.created = created;
        this.modified = modified;
        this.base_map = base_map;
    }

}
