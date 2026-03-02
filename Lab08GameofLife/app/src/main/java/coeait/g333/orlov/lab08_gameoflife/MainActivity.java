package coeait.g333.orlov.lab08_gameoflife;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;


import java.sql.Timestamp;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FieldAdapter adapter;
    ArrayList<FieldData> fieldDataList;
    ImageView btn;

    DB database;

    int id = 1;

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


        database = new DB(this, "Field.db", null, 1);

        recyclerView = findViewById(R.id.rv_fields);
        btn = findViewById(R.id.addField_btn);

        fieldDataList = new ArrayList<>();
        FieldData data = database.do_select(id);
        while (data != null) {
            id++;
            fieldDataList.add(data);
            data = database.do_select(id);
        }

        FieldAdapter.onItemClickListener onClickListener = new FieldAdapter.onItemClickListener() {
            @Override
            public void onItemClick(FieldData fieldData, int position) {

                FieldData fd = fieldDataList.get(position);
                if (fd != null) {
                    Intent i = new Intent(MainActivity.this, FieldActivity.class);
                    i.putExtra("id", fd.id);
                    /*i.putExtra("name", fd.name);
                    i.putExtra("width", fd.width);
                    i.putExtra("height", fd.height);
                    i.putExtra("map_data", fd.map_data);
                    i.putExtra("created", fd.created);
                    i.putExtra("modified", fd.modified);
                    i.putExtra("base_map", fd.base_map);*/
                    startActivityForResult(i, 12345);
                }
            }
        };
        adapter = new FieldAdapter(fieldDataList, onClickListener);
        recyclerView.setAdapter(adapter);
    }
    public void addField(View v) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        AlertDialog dlg = bld.create();

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_window, null);

        Button btnOk = view.findViewById(R.id.buttonOK);
        Button btnCancel = view.findViewById(R.id.buttonCancel);

        EditText name = view.findViewById(R.id.nameEditText);
        EditText width = view.findViewById(R.id.widthEditText);
        EditText height = view.findViewById(R.id.heightEditText);

        btnOk.setOnClickListener(v1 -> {
            database.do_insert(name.getText().toString(),
                    Integer.parseInt(width.getText().toString()),
                    Integer.parseInt(height.getText().toString()),
                    "0",
                    0);
            FieldData data = database.do_select(id);
            fieldDataList.add(data);
            adapter.notifyDataSetChanged();
            id++;
            dlg.dismiss();
        });

        btnCancel.setOnClickListener(v1 -> {
            dlg.dismiss();
        });

        dlg.setView(view);
        dlg.setTitle("Add field");
        dlg.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            if (data != null) {

            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}