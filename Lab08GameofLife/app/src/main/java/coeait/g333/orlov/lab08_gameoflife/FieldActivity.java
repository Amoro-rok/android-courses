package coeait.g333.orlov.lab08_gameoflife;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FieldActivity extends AppCompatActivity {

    EditText edit_name;
    ImageButton save_btn;
    ImageButton exit_btn;
    ImageButton delete_btn;
    ImageButton play_btn;
    ImageButton pause_btn;
    ImageButton stop_btn;
    SurfaceView field;
    DB database;
    FieldData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_field);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = new DB(this, "Field.db", null, 1);
        edit_name = findViewById(R.id.field_name_edit);
        field = findViewById(R.id.field_surfaceview);

        Intent i = getIntent();
        data = database.do_select(i.getIntExtra("id", 0));
        edit_name.setText(data.name);
        
    }
}