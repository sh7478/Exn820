package com.example.exn820;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    }

    public void solve(View view) {
        String text1 = etFirstParam.getText().toString();
        if(text1.charAt(0) >= '0' && text1.charAt(0) <= '9')
        {

        }
        else
        {

        }
    }
    public int getNum(EditText etParam)
    {
        String param = etParam.getText().toString();
        double num1 = 0;
        int dot;
        int len;
        int num2;
        int multy = 1;
        dot = param.indexOf('.');
        num1 = (int)(param.substring(0, dot));
        if(param.length() - 1 > dot + 1)
        {
            len = param.length()-1-dot;
            for(int i = 0; i < len; i++)
            {
                num2 *= 10;
                num2 += (int)(param.charAt(dot + i + 1));
                multy *= 10;
            }
            num2 /= multy;
            num1 += num2;
        }
        return num1;
    }
}