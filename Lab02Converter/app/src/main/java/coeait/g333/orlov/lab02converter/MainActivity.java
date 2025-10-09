package coeait.g333.orlov.lab02converter;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    RadioButton l_btn, m_btn, s_btn;

    public class Unit
    {
        public String name;
        public double coeff;

        public Unit(String n, double c)
        {
            name = n;
            coeff = c;
        }

        public String toString()
        {
            return name;
        }
        public double getCoeff()
        {
            return coeff;
        }
    }



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
        l_btn = findViewById(R.id.length_btn);
        m_btn = findViewById(R.id.mass_btn);
        s_btn = findViewById(R.id.speed_btn);

        ArrayAdapter <Unit> length = new <Unit> ArrayAdapter(this, android.R.layout.simple_list_item_1);
        ArrayAdapter <Unit> speed = new <Unit> ArrayAdapter(this, android.R.layout.simple_list_item_1);
        ArrayAdapter <Unit> mass = new <Unit> ArrayAdapter(this, android.R.layout.simple_list_item_1);

        length.add(new Unit("mm",0.001f));
        length.add(new Unit("cm", 0.01f));
        length.add(new Unit("m", 1.0f));
        length.add(new Unit("km", 1000.0f));

        speed.add(new Unit("m/s", 0.27777777778f));
        speed.add(new Unit("km/h", 1.0f));
        speed.add(new Unit("miles/h", 0.62137119f));

        mass.add(new Unit("mg", 0.001f));
        mass.add(new Unit("g", 1.0f));
        mass.add(new Unit("kg", 1000.0f));

        sp_From.setAdapter(length);
        sp_To.setAdapter(length);
    }
    public void on_convert(View v)
    {
        if (sp_From.toString().isBlank())
        {
            Toast.makeText(this, "Error!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        double inp = Double.parseDouble(etFrom.getText().toString());

        String sFrom = (String) sp_From.getSelectedItem();
        String sTo = (String) sp_To.getSelectedItem();

        double to = 0.0f;

        if (l_btn.isChecked())
        {

        } else if (s_btn.isChecked())
        {

        }
    }

    public void on_switch(View v)
    {

        ArrayAdapter <Unit> length = new <Unit> ArrayAdapter(this, android.R.layout.simple_list_item_1);
        ArrayAdapter <Unit> speed = new <Unit> ArrayAdapter(this, android.R.layout.simple_list_item_1);
        ArrayAdapter <Unit> mass = new <Unit> ArrayAdapter(this, android.R.layout.simple_list_item_1);

        length.add(new Unit("mm",0.001f));
        length.add(new Unit("cm", 0.01f));
        length.add(new Unit("m", 1.0f));
        length.add(new Unit("km", 1000.0f));

        speed.add(new Unit("m/s", 0.27777777778f));
        speed.add(new Unit("km/h", 1.0f));
        speed.add(new Unit("miles/h", 0.62137119f));

        mass.add(new Unit("mg", 0.001f));
        mass.add(new Unit("g", 1.0f));
        mass.add(new Unit("kg", 1000.0f));

        if (l_btn.isChecked())
        {
            sp_From.setAdapter(length);
            sp_To.setAdapter(length);
        } else if (s_btn.isChecked())
        {
            sp_From.setAdapter(speed);
            sp_To.setAdapter(speed);
        } else if (m_btn.isChecked())
        {
            sp_From.setAdapter(mass);
            sp_To.setAdapter(mass);
        }
    }
}