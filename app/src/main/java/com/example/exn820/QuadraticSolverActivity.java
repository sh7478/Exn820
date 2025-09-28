package com.example.exn820;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuadraticSolverActivity extends AppCompatActivity {

    TextView answer1,answer2;
    ImageView graphView;
    Intent getParams;
    double param1,param2,param3,solution1,solution2;
    final int RESULT_OK = -1;
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
        if(((b*b)-(4*a*c)) < 0)
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
        else {
            solution1 = 0.0;
            solution2 = 0.0;
            solution1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
            solution2 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
            if (solution1 == solution2) {
                answer1.setText("x = " + formatScientificNotation(solution1, 10));
                answer2.setText("x = " + formatScientificNotation(solution2, 10));
                if (a > 0) {
                    graphView.setImageResource(R.drawable.graph2);
                } else {
                    graphView.setImageResource(R.drawable.graph5);
                }
            } else {
                answer1.setText("x = " + formatScientificNotation(solution1, 10));
                answer2.setText("x = " + formatScientificNotation(solution2, 10));
                if (a > 0) {
                    graphView.setImageResource(R.drawable.graph1);
                } else {
                    graphView.setImageResource(R.drawable.graph4);
                }
            }
        }
    }

    public static String formatScientificNotation(double value, int i) {
        if((""+value).length() <= i)
        {
            return ""+value;
        }
        return String.format("%.3E", value);
    }
    public void goBack(View view) {
        getParams.putExtra("prevSolution1", solution1);
        getParams.putExtra("prevSolution2", solution2);
        setResult(RESULT_OK, getParams);
        finish();
    }

}