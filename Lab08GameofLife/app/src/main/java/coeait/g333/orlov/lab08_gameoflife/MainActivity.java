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
import android.widget.TextView;
import android.widget.Toast;

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

    FieldAdapter.onItemClickListener onClickListener;

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

        //database.onDrop(database.getWritableDatabase());
        //database.onCreate(database.getWritableDatabase());
        //database.onClear(database.getWritableDatabase());
        //database.onCreateRecord(database.getWritableDatabase());


        recyclerView = findViewById(R.id.rv_fields);
        btn = findViewById(R.id.addField_btn);

        fieldDataList = new ArrayList<>();

        onClickListener = new FieldAdapter.onItemClickListener() {
            @Override
            public void onItemClick(FieldData fieldData, int position) {

                FieldData fd = fieldDataList.get(position);
                if (fd != null) {
                    Intent i = new Intent(MainActivity.this, FieldActivity.class);
                    i.putExtra("id", fd.id);
                    i.putExtra("amount_of_fields", fieldDataList.size());
                    startActivityForResult(i, 12345);
                }
            }
        };
        adapter = new FieldAdapter(fieldDataList, onClickListener);
        recyclerView.setAdapter(adapter);
        loadFields();
    }

    public void loadFields() {
        int id = 1;
        fieldDataList.clear();
        FieldData data;
        int border = 0;
        while (true) {
            data = database.do_select(id);
            id++;
            if (data.id == -1) {
                border++;
                if (border == 10)  break;
                continue;
            }
            fieldDataList.add(data);
            data = database.do_select(id);
        }
        adapter.notifyDataSetChanged();
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
            if (name.getText().toString().equals("") || width.getText().toString().equals("") || height.getText().toString().equals("")) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            for (FieldData fd : fieldDataList) {
                if (fd.name.equals(name.getText().toString())) {
                    Toast.makeText(this, "Field with this name already exists", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            database.do_insert(name.getText().toString(),
                    Integer.parseInt(width.getText().toString()),
                    Integer.parseInt(height.getText().toString()),
                    "0",
                    0);
            loadFields();
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
        super.onActivityResult(requestCode, resultCode, data);
        loadFields();
    }
}