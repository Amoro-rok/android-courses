package coeait.g333.orlov.lab08_gameoflife;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.sql.Timestamp;


public class DB extends SQLiteOpenHelper {

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Field (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL UNIQUE, width INTEGER NOT NULL, height INTEGER NOT NULL, map_data TEXT NOT NULL, created INTEGER NOT NULL, modified INTEGER NOT NULL, base_map INTEGER NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void onDrop(SQLiteDatabase db) {
        String sql = "DROP TABLE IF EXISTS Field;";
        db.execSQL(sql);
    }


    public void onClear(SQLiteDatabase db) {
        String sql = "DELETE FROM Field;";
        db.execSQL(sql);
    }

    public void onCreateRecord(SQLiteDatabase db) {
        String sql = "INSERT INTO Field (name, width, height, map_data, created, modified, base_map) VALUES ('name', 10, 10, '0', 123456789, 123456789, 0);";
        db.execSQL(sql);
    }


    public void do_insert(String name, int width, int height, String map_data, int base_map) {
        long date = System.currentTimeMillis() / 1000L;
        String sql = String.format("INSERT INTO Field (name, width, height, map_data, created, modified, base_map) VALUES ('%s', %s, %s, '%s', %s, %s, %s);", name, width, height, map_data, date, date, base_map);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public FieldData do_select(int key) {

        String sql = "SELECT name, width, height, map_data, created, modified, base_map FROM Field WHERE id = '" + key + "';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql, null);
        String name, map_data;
        int width, height, base_map;
        long created, modified;

        if (cur.moveToFirst() == true) {
            name = cur.getString(0); //name
            width = cur.getInt(1); //width
            height = cur.getInt(2); //height
            map_data = cur.getString(3); //map_data
            created = cur.getLong(4); //created
            modified = cur.getLong(5); //modified
            base_map = cur.getInt(6); //base_map
            return new FieldData(key, name, width, height, map_data, new Timestamp(created), new Timestamp(modified), base_map);
        }
        System.out.println(key);
        return new FieldData(-1, "", 0, 0, "", new Timestamp(0), new Timestamp(0), 0);
    }

    public void on_update(FieldData data) {
        String sql = "UPDATE Field SET map_data = '" + data.map_data + "', name = '" + data.name +
                "', modified = '" + System.currentTimeMillis() / 1000L + "' WHERE id = '" + data.id + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void on_delete(int key) {
        String sql = "DELETE FROM Field where id = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

}
