package coeait.g333.orlov.lab04_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText input;

    CheckBox input_button1;
    CheckBox input_button2;

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
        input = findViewById(R.id.txt_data);
        input_button1 = findViewById(R.id.input_btn1);
        input_button2 = findViewById(R.id.input_btn2);
    }

    public void on_exit_click(View v) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setPositiveButton("Yes", (dialog, which) -> {
            finish();
        });
        bld.setNegativeButton("No", null);
        AlertDialog dlg = bld.create();

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        dlg.setView(view);
        dlg.setTitle("Confirmation");
        dlg.setIcon(R.drawable.cute_cat);

        dlg.show();
    }

    public void on_dialog_click(View v) {
        String s = input.getText().toString();

        Intent i = new Intent(this, Activity2.class);
        i.putExtra("abc", s);
        i.putExtra("abc2", input_button1.isChecked());
        i.putExtra("abc3", input_button2.isChecked());

        startActivityForResult(i, 555);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 555) {
            if (data != null) {
                String s = data.getStringExtra("qwe");
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

                input.setText(s);
                input_button1.setChecked(data.getBooleanExtra("qwe2", false));
                input_button2.setChecked(data.getBooleanExtra("qwe3", false));
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void on_btndialog_click(View v) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        AlertDialog dlg = bld.create();

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_2, null);

        Button btnOk = view.findViewById(R.id.buttonOk);
        Button btnCancel = view.findViewById(R.id.buttonCancel);
        EditText editText = view.findViewById(R.id.txt_data_alt);
        Switch inpsw1 = view.findViewById(R.id.input_switch1);
        Switch inpsw2 = view.findViewById(R.id.input_switch2);

        editText.setText(input.getText());
        inpsw1.setChecked(input_button1.isChecked());
        inpsw2.setChecked(input_button2.isChecked());

        btnOk.setOnClickListener(l -> {
            String text = editText.getText().toString();
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            input.setText(text);
            input_button1.setChecked(inpsw1.isChecked());
            input_button2.setChecked(inpsw2.isChecked());
            dlg.dismiss();
        });

        btnCancel.setOnClickListener(l -> {
            dlg.dismiss();
        });

        dlg.setView(view);
        dlg.setTitle("Activity2");
        dlg.setIcon(R.drawable.cute_cat);

        dlg.show();
    }
}