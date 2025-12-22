package coeait.g333.orlov.lab07apicalculator;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.converter.scalars.ScalarsConverterFactory;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText edit_a, edit_b, edit_ip;
    TextView res_txt;

    Button btn_add, btn_sub, btn_mul, btn_div,
            btn_sqrt, btn_pow, btn_sin, btn_cos;

    CalculatorService service;

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

        edit_a = findViewById(R.id.editNumA);
        edit_b = findViewById(R.id.editNumB);
        edit_ip = findViewById(R.id.editip);
        res_txt = findViewById(R.id.txt_result);
        res_txt.setText(res_txt.getText()+"0.0");

        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);
        btn_sqrt = findViewById(R.id.btn_sqrt);
        btn_pow = findViewById(R.id.btn_pow);
        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }


    public void on_ip_update(View v)
    {
        String ip = edit_ip.getText().toString().trim();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + ip + ":1880/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        service = retrofit.create(CalculatorService.class);
    }
    public void on_btn_click(View v)
    {
        String a = edit_a.getText().toString();
        String b = edit_b.getText().toString();

        res_txt.setText(a);

        if (edit_a.getText().isEmpty() || edit_b.getText().isEmpty())
        {
            res_txt.setText("enter smth please");
            return;
        }


        Call <String> func;
        Button btn = (Button) v;
        String oper = btn.getText().toString().toLowerCase();
        func = service.calculator(a, b, oper);

        try {
            res_txt.setText(func.execute().body());
        } catch (IOException e) {}
    }
}