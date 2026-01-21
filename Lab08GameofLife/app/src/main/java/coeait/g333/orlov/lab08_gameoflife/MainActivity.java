package coeait.g333.orlov.lab08_gameoflife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FieldAdapter adapter;
    ArrayList<FieldData> fieldDataList;
    ImageView btn;

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

        recyclerView = findViewById(R.id.rv_fields);
        btn = findViewById(R.id.addField_btn);

        fieldDataList = new ArrayList<>();
        fieldDataList.add(new FieldData("Test", 10, 10));
        fieldDataList.add(new FieldData("Test2", 20, 20));
        fieldDataList.add(new FieldData("Test3", 30, 30));
        fieldDataList.add(new FieldData("Test4", 40, 40));
        fieldDataList.add(new FieldData("Test5", 50, 50));
        fieldDataList.add(new FieldData("Test6", 60, 60));
        fieldDataList.add(new FieldData("Test7", 70, 70));
        fieldDataList.add(new FieldData("Test8", 80, 80));
        fieldDataList.add(new FieldData("Test9", 90, 90));
        fieldDataList.add(new FieldData("Test10", 100, 100));

        adapter = new FieldAdapter(fieldDataList);
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
            fieldDataList.add(new FieldData(name.getText().toString(),
                    Integer.parseInt(width.getText().toString()),
                    Integer.parseInt(height.getText().toString())));
            adapter = new FieldAdapter(fieldDataList);
            recyclerView.setAdapter(adapter);
            dlg.dismiss();
        });

        btnCancel.setOnClickListener(v1 -> {
            dlg.dismiss();
        });

        dlg.setView(view);
        dlg.setTitle("Add field");
        dlg.show();
    }
}