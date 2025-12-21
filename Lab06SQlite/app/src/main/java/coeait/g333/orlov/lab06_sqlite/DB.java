package coeait.g333.orlov.lab06_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;


public class DB extends SQLiteOpenHelper {

    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE my_test (my_key TEXT PRIMARY KEY, my_value TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void do_insert(String key, String value) {
        String sql = "INSERT INTO my_test VALUES('" + key + "', '" + value + "');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public String do_select(String key) {
        String sql = "SELECT my_value FROM my_test WHERE my_key = '" + key + "';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql, null);

        if (cur.moveToFirst() == true)
            return cur.getString(0);

        return "(!) not found";
    }

    public void on_update(String key, String value) {
        String sql = "UPDATE my_test SET my_value = '" + value + "' WHERE my_key = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void on_delete(String key) {
        String sql = "DELETE FROM my_test where my_key = '" + key + "';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
}
