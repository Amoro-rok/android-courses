//Орлов Роман 333
package coeait.g333.orlov.lab03_fruitsmarket;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    CheckBox[] chk = new CheckBox[4];
    EditText[] num = new EditText[4];
    EditText[] prices = new EditText[4];

    RadioButton[] outputChoice = new RadioButton[3];

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

        chk[0] = findViewById(R.id.checkBox_apple);
        chk[1] = findViewById(R.id.checkBox_strawberry);
        chk[2] = findViewById(R.id.checkBox_Blueberry);
        chk[3] = findViewById(R.id.checkBox_Cherry);

        num[0] = findViewById(R.id.editAmountApple);
        num[1] = findViewById(R.id.editAmountStrawberry);
        num[2] = findViewById(R.id.editAmountBlueberry);
        num[3] = findViewById(R.id.editAmountCherry);

        prices[0] = findViewById(R.id.editPriceApple);
        prices[1] = findViewById(R.id.editPriceStrawberry);
        prices[2] = findViewById(R.id.editPriceBlueberry);
        prices[3] = findViewById(R.id.editPriceCherry);

        outputChoice[0] = findViewById(R.id.toastButton);
        outputChoice[1] = findViewById(R.id.textButton);
        outputChoice[2] = findViewById(R.id.dialogButton);

        result = findViewById(R.id.resultText);
    }

    public void onCalc(View v)
    {
        String res = "";
        float sum = 0.0f;
        for (int i = 0; i < num.length; i++)
        {
            if (chk[i].isChecked()) {
                int q = Integer.parseInt(num[i].getText().toString());
                float val = q * Float.parseFloat(prices[i].getText().toString());
                sum += val;
                if (!outputChoice[0].isChecked()) {
                    res = res.concat(String.format("%s: %d x %.2f = %.2f\n",
                            chk[i].getText().toString(), q,
                            Float.parseFloat(prices[i].getText().toString()), val));
                }
            }
        }
        res += String.format("Total: %.2f", sum);
        if (outputChoice[0].isChecked()) Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        else if (outputChoice[1].isChecked()) result.setText(res);
        else {
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            AlertDialog dlg = bld.create();
            dlg.setIcon(R.drawable.img_lemon);
            dlg.setTitle("result");
            dlg.setMessage(res);
            dlg.show();
        };
    }

}