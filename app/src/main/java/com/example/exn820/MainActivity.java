package com.example.exn820;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText etFirstParam;
    EditText etSecondParam;
    EditText etThirdParam;
    TextView feedbackTv;
    TextView prevAnswer1;
    TextView prevAnswer2;
    double param1;
    double param2;
    double param3;
    double solution1;
    double solution2;
    final int REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFirstParam = findViewById(R.id.etFirstParam);
        etSecondParam = findViewById(R.id.etSecondParam);
        etThirdParam = findViewById(R.id.etThirdParam);
        feedbackTv = findViewById(R.id.feedbackTv);
        prevAnswer1 = findViewById(R.id.prevAnswer1);
        prevAnswer2 = findViewById(R.id.prevAnswer2);
    }

    public void solve(View view) {
        if(Double.parseDouble(etFirstParam.getText().toString()) == 0.0)
        {
            feedbackTv.setText("a can't be 0");
        }
        else {
            param1 = preGetNum(etFirstParam);
            param2 = preGetNum(etSecondParam);
            param3 = preGetNum(etThirdParam);
            Intent params = new Intent(this, QuadraticSolverActivity.class);
            params.putExtra("a", param1);
            params.putExtra("b", param2);
            params.putExtra("c", param3);
            startActivityForResult(params, REQUEST_CODE);
        }
    }

    public double preGetNum(EditText etParam)
    {
        String text = etParam.getText().toString();
        if(text.length() > 0) {
            if (text.charAt(0) >= '0' && text.charAt(0) <= '9') {
                feedbackTv.setText("");
                return getNum(text);
            } else {
                if(text.length() == 2 && text.indexOf('-') != -1 && text.indexOf('.') != -1)
                {
                    feedbackTv.setText("need to put valid number");
                }
                if(text.length() > 1 ) {
                    feedbackTv.setText("");
                    return getNum(text.substring(1, text.length())) * (-1);
                }
                feedbackTv.setText("need to put valid number");
            }
        }
        else
        {
            feedbackTv.setText("need to put valid number");
        }
        return -9.9;

    }
    public double getNum(String param)
    {
        double num1 = 0;
        int dot;
        int len;
        double num2 = 0;
        int multy = 1;
        dot = param.indexOf('.');
        if(dot == -1)
        {
            return Integer.parseInt(param.substring(0, param.length()));
        }
        num1 = Integer.parseInt(param.substring(0, dot));
        if(param.length() - 1 >= dot + 1)
        {
            len = param.length()-1-dot;
            for(int i = 0; i < len; i++)
            {
                num2 *= 10;
                num2 += (param.charAt(dot + i + 1)) - 48;
                multy *= 10;
            }

            num2 /= multy;
            num1 += num2;
        }
        return num1;
    }

    public void randomParams(View view) {
        param1 = randomParamNumbers();
        etFirstParam.setText(formatScientificNotation(param1, 9));
        param2 = randomParamNumbers();
        etSecondParam.setText(formatScientificNotation(param2, 9));
        param3 = randomParamNumbers();
        etThirdParam.setText(formatScientificNotation(param3, 9));
    }

    public static String formatScientificNotation(double value, int i) {
        if((""+value).length() <= i)
        {
            return ""+value;
        }
        return String.format("%.3E", value);
    }
    public double randomParamNumbers()
    {
        Random rnd = new Random();
        return -100 + (200) * Math.random();
    }

    @Override
    protected void onActivityResult(int source, int result, @Nullable Intent data_back)
    {
        super.onActivityResult(source, result, data_back);
        if(source == REQUEST_CODE)
        {
            if(result == MainActivity.RESULT_OK)
            {
                if(data_back != null)
                {
                    prevAnswer1.setText("x = " + formatScientificNotation(data_back.getDoubleExtra("prevSolution1", 0.0), 8));
                    prevAnswer2.setText("x = " + formatScientificNotation(data_back.getDoubleExtra("prevSolution2", 0.0), 8));

                }
            }
        }
    }
}