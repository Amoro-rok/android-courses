package coeait.g333.orlov.lab02converter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Spinner sp_From;
    Spinner sp_To;
    EditText etFrom;
    TextView resulttxt;

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
        sp_From = findViewById(R.id.spFrom);
        sp_To = findViewById(R.id.spTo);
        etFrom = findViewById(R.id.from_value);
        resulttxt = findViewById(R.id.result_text);

        ArrayAdapter <String> adp = new <String> ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adp.add("mm");
        adp.add("cm");
        adp.add("m");
        adp.add("km");

        sp_From.setAdapter(adp);
        sp_To.setAdapter(adp);
    }
    private void on_convert(View v)
    {
        float inp = Float.parseFloat(etFrom.getText().toString());

        String sFrom = (String) sp_From.getSelectedItem();
        String sTo = (String) sp_To.getSelectedItem();

        float to = 0.0f;

        if (sFrom.equals("mm"))
        {
            if (sTo.equals("mm")) to = inp * 1.0f;
            if (sTo.equals("cm")) to = inp / 10.0f;
            if (sTo.equals("m")) to = inp / 100.0f;
            if (sTo.equals("km")) to = inp / 1000000.0f;
        }
    }
}