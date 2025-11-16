// Орлов 333
package koeait.g333.orlov.lab01_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText val1;
    EditText val2;
    TextView result;

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
        val1 = findViewById(R.id.txt_1);
        val2 = findViewById(R.id.txt_2);
        result = findViewById(R.id.result_outtxt);
    }

    public void add_click(View v)
    {
        float a = Float.parseFloat(val1.getText().toString());
        float b = Float.parseFloat(val2.getText().toString());
        result.setText(String.valueOf(a+b));
    }
    public void sub_click(View v)
    {
        float a = Float.parseFloat(val1.getText().toString());
        float b = Float.parseFloat(val2.getText().toString());
        result.setText(String.valueOf(a-b));
    }
    public void mul_click(View v)
    {
        float a = Float.parseFloat(val1.getText().toString());
        float b = Float.parseFloat(val2.getText().toString());
        result.setText(String.valueOf(a*b));
    }
    public void div_click(View v)
    {
        float a = Float.parseFloat(val1.getText().toString());
        float b = Float.parseFloat(val2.getText().toString());
        if (b==0){result.setText(getString(R.string.error_div_0)); return;}
        result.setText(String.valueOf(a/b));
    }

    public void pow_click(View v)
    {
        float a = Float.parseFloat(val1.getText().toString());
        float b = Float.parseFloat(val2.getText().toString());
        result.setText(String.valueOf(Math.pow(a, b)));
    }
    public void sqrt_click(View v)
    {
        float a = Float.parseFloat(val1.getText().toString());
        float b = Float.parseFloat(val2.getText().toString());
        result.setText(String.valueOf(Math.sqrt(a))+",\n"+String.valueOf(Math.sqrt(b)));
    }
    public void sin_click(View v)
    {
        float a = Float.parseFloat(val1.getText().toString());
        float b = Float.parseFloat(val2.getText().toString());
        result.setText(String.valueOf(Math.sin(a))+",\n"+String.valueOf(Math.sin(b)));
    }
    public void cos_click(View v)
    {
        float a = Float.parseFloat(val1.getText().toString());
        float b = Float.parseFloat(val2.getText().toString());
        result.setText(String.valueOf(Math.cos(a))+",\n"+String.valueOf(Math.cos(b)));
    }
    public void tan_click(View v)
    {
        float a = Float.parseFloat(val1.getText().toString());
        float b = Float.parseFloat(val2.getText().toString());
        result.setText(String.valueOf(Math.tan(a))+",\n"+String.valueOf(Math.tan(b)));
    }
}