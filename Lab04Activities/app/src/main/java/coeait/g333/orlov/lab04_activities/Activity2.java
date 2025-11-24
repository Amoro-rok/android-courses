package coeait.g333.orlov.lab04_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {

    EditText txt;
    Switch input_switch1;
    Switch input_switch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt = findViewById(R.id.txt_data_alt);
        input_switch1 = findViewById(R.id.input_switch1);
        input_switch2 = findViewById(R.id.input_switch2);

        Intent i = getIntent();

        String s = i.getStringExtra("abc");
        txt.setText(s);
        input_switch1.setChecked(i.getBooleanExtra("abc2", false));
        input_switch2.setChecked(i.getBooleanExtra("abc3", false));
    }

    public void on_ok_click(View v) {
        Intent i = new Intent();

        String s = txt.getText().toString();
        i.putExtra("qwe", s);
        i.putExtra("qwe2", input_switch1.isChecked());
        i.putExtra("qwe3", input_switch2.isChecked());

        setResult(RESULT_OK, i);
        finish();
    }

    public void on_cancel_click(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}