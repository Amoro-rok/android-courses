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

    public void do_insert(String name, int width, int height, String map_data, int base_map) {
        long date = System.currentTimeMillis() / 1000L;
        String sql = String.format("INSERT INTO Field (name, width, height, map_data, created, modified, base_map) VALUES ('%s', %s, %s, '%s', %s, %s, %s);", name, width, height, map_data, date, date, base_map);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public String[] do_select(int key) {

        String sql = "SELECT name, width, height, map_data, created, modified, base_map FROM Field WHERE id = '" + key + "';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql, null);
        String[] res = new String[8];

        if (cur.moveToFirst() == true) {
            res[0] = String.valueOf(key);
            res[1] = cur.getString(0);
            res[2] = cur.getString(1);
            res[3] = cur.getString(2);
            res[4] = cur.getString(3);
            res[5] = cur.getString(4);
            res[6] = cur.getString(5);
            res[7] = cur.getString(6);
            return res;
        }
        System.out.println(key);
        res[0] = "(!) not found";
        return res;
    }

    public void on_update(String key, String value) {
        /*String sql = "UPDATE my_test SET my_value = '" + value + "' WHERE my_key = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);*/
    }

    public void on_delete(String key) {
        /*String sql = "DELETE FROM my_test where my_key = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);*/
    }

    public String get_keys() {
        /*String sql = "SELECT * FROM my_test";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql, null);
        String res = "";
        while(cur.moveToNext()) {
            res += cur.getString(0) + ", " + cur.getString(1) + "\n";
        }
        return res;*/
        return "";
    }

}
