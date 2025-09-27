package com.example.exn820;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuadraticSolverActivity extends AppCompatActivity {

    TextView answer1,answer2;
    ImageView graphView;
    Intent getParams;
    double param1,param2,param3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratic_solver);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        graphView = findViewById(R.id.graphView);
        getParams = getIntent();
        param1 = getParams.getDoubleExtra("a", -9.999);
        param2 = getParams.getDoubleExtra("b", -9.999);
        param3 = getParams.getDoubleExtra("c", -9.999);
        solver(param1, param2, param3);

    }

    public void solver(double a, double b, double c) {
        if(b*b-4*a*c < 0)
        {
            answer1.setText("No solution");
            answer2.setText("No solution");
            if(a > 0)
            {
                graphView.setImageResource(R.drawable.graph3);
            }
            else
            {
                graphView.setImageResource(R.drawable.graph6);
            }
        }
        double solution1, solution2;
        solution1 = (-b + Math.sqrt(b*b-4*a*c))/(2*a);
        solution2 = (-b - Math.sqrt(b*b-4*a*c))/(2*a);
        if(solution1 == solution2)
        {
            answer1.setText("x = " +String.format("%.5f", solution1));
            answer2.setText("x = " +String.format("%.5f", solution2));
            if(a > 0)
            {
                graphView.setImageResource(R.drawable.graph2);
            }
            else
            {
                graphView.setImageResource(R.drawable.graph5);
            }
        }
        else
        {
            answer1.setText("x = " +String.format("%.5f", solution1));
            answer2.setText("x = " +String.format("%.5f", solution2));
            if(a>0)
            {
                graphView.setImageResource(R.drawable.graph1);
            }
            else
            {
                graphView.setImageResource(R.drawable.graph4);
            }
        }
    }

    public void goBack(View view) {
        finish();
    }
}