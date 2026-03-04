package coeait.g333.orlov.lab08_gameoflife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FieldActivity extends AppCompatActivity {

    EditText edit_name;
    ImageButton save_btn, exit_btn, delete_btn, play_btn, pause_btn, stop_btn;
    myimg field;
    DB database;
    FieldData data;

    String[] names;

    boolean playing = false;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!playing) return;
            step();
            handler.postDelayed(this, 500);
        }
    };

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
        names = i.getStringArrayExtra("names");
        if (data != null) {
            edit_name.setText(data.name);
            field.set_data(data.width, data.height, data.map_data);
        }
    }

    public void on_save_click(View v) {
        if (data == null) return;
        String newName = edit_name.getText().toString();
        String newData = field.get_data();
        if (newName.equals(data.name) && newData.equals(data.map_data)) return;
        for (int i = 0; i < names.length; i++) {
            if (i == data.id - 1) continue;
            if (names[i] == null) continue;
            if (names[i].equals(newName)) {
                Toast.makeText(this, "Field with this name already exists", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        data.name = newName;
        data.map_data = newData;
        database.on_update(data);
    }

    public void on_exit_click(View v) {
        finish();
    }

    public void on_delete_click(View v) {
        if (data != null) {
            database.on_delete(data.id);
        }
        finish();
    }

    public void on_play_click(View v) {
        if (playing) return;
        playing = true;
        handler.post(runnable);
    }

    public void on_pause_click(View v) {
        playing = false;
        handler.removeCallbacks(runnable);
    }

    public void on_stop_click(View v) {
        playing = false;
        handler.removeCallbacks(runnable);
        if (data != null) {
            field.set_data(data.width, data.height, data.map_data);
        }
    }

    void step() {
        if (data == null || field.d == null) return;
        int w = data.width;
        int h = data.height;
        int[] next = new int[w * h];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int neighbors = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        if (dx == 0 && dy == 0) continue;
                        int nx = x + dx;
                        int ny = y + dy;
                        if (nx >= 0 && nx < w && ny >= 0 && ny < h) {
                            if (field.d[ny * w + nx] == 1) neighbors++;
                        }
                    }
                }

                int current = field.d[y * w + x];
                if (current == 1) {
                    if (neighbors == 2 || neighbors == 3) next[y * w + x] = 1;
                    else next[y * w + x] = 0;
                } else {
                    if (neighbors == 3) next[y * w + x] = 1;
                    else next[y * w + x] = 0;
                }
            }
        }
        field.d = next;
        field.invalidate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        playing = false;
        handler.removeCallbacks(runnable);
    }

    public void on_info_click(View v) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        AlertDialog dlg = bld.create();

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.info_window, null);

        Button close_btn = view.findViewById(R.id.info_close_btn);

        TextView created = view.findViewById(R.id.info_created);
        TextView modified = view.findViewById(R.id.info_modified);
        TextView basemap = view.findViewById(R.id.info_basemap);
        TextView id = view.findViewById(R.id.info_id);


        created.setText(data.created.toString());
        modified.setText(data.modified.toString());
        if (data.base_map != 0) basemap.setText(names[data.base_map]);
        else basemap.setText("None");
        id.setText(String.valueOf(data.id));

        close_btn.setOnClickListener(v1 -> {
            dlg.dismiss();
        });

        dlg.setView(view);
        dlg.setTitle("Info");
        dlg.show();
    }
}
