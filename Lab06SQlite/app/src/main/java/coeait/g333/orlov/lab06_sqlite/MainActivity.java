package coeait.g333.orlov.lab06_sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {

    EditText txt_key;
    EditText txt_value;

    DB mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_key = findViewById(R.id.edit_key);
        txt_value = findViewById(R.id.edit_value);

        mydb = new DB(this, "mybase.db", null, 1);
    }

    public void on_insert_click(View v)
    {
        String key = txt_key.getText().toString();
        String value = txt_value.getText().toString();

        mydb.do_insert(key, value);
    }

    public void on_update_click(View v)
    {
        String key = txt_key.getText().toString();
        String value = txt_value.getText().toString();

        mydb.on_update(key, value);
    }

    public void on_select_click(View v)
    {
        String key = txt_key.getText().toString();
        String value = mydb.do_select(key);

        txt_value.setText(value);
    }

    public void on_delete_click(View v)
    {
        String key = txt_key.getText().toString();

        mydb.on_delete(key);
        txt_value.setText(null);
    }

}