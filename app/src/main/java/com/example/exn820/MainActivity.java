package com.example.exn820;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText etFirstParam;
    EditText etSecondParam;
    EditText etThirdParam;
    double param1;
    double param2;
    double param3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFirstParam = findViewById(R.id.etFirstParam);
        etSecondParam = findViewById(R.id.etSecondParam);
        etThirdParam = findViewById(R.id.etThirdParam);
    }

    public void solve(View view) {
        param1 = preGetNum(etFirstParam);
        param2 = preGetNum(etSecondParam);
        param3 = preGetNum(etThirdParam);
        Intent params = new Intent(this, QuadraticSolverActivity.class);
        params.putExtra("a", param1);
        params.putExtra("b", param2);
        params.putExtra("c", param3);
        startActivity(params);
    }

    public double preGetNum(EditText etParam)
    {
        String text = etParam.getText().toString();
        if(text.length() > 0) {
            if (text.charAt(0) >= '0' && text.charAt(0) <= '9') {
                return getNum(text);
            } else {
                if(text.length() == 2 && text.indexOf('-') != -1 && text.indexOf('.') != -1)
                {
                    return -9.9;
                }
                if(text.length() > 1 ) {
                    return getNum(text.substring(1, text.length())) * (-1);
                }
                return -9.9;
            }
        }
        else
        {
            return -9.9;
        }

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
        etFirstParam.setText("" + param1);
        param2 = randomParamNumbers();
        etSecondParam.setText("" + param2);
        param3 = randomParamNumbers();
        etThirdParam.setText("" + param3);
    }

    public double randomParamNumbers()
    {
        Random rnd = new Random();
        int num;
        double num2, param;
        param = -100 + (200) * Math.random();
        num = (int)param;
        param -= num;
        param *= 100;
        num2 = (int)param;
        num2 /= 100;
        param = num + num2;
        return param;
    }
}