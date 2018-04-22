package com.edmarkou.blackjacktrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText getNumberText;
    private Button startButton;
    private TextView helpingTextView;
    private Button backButton;

    private double multiplier;
    private int[] mNumbers;
    Random randomizer;
    int counter = 0;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNumberText = findViewById(R.id.getNumberText);
        startButton = findViewById(R.id.startButton);
        helpingTextView = findViewById(R.id.helpingTextView);
        backButton = findViewById(R.id.backButton);
        randomizer = new Random();

        backButton.setVisibility(View.GONE);
        mNumbers = new int[2000];
        mNumbers[0] = 0;
        for(int i = 1; i < 2000; i++){
            mNumbers[i] = mNumbers[i - 1] + 5;
        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counter < 1 && !(getNumberText.getText().toString().length() < 1)) {
                    backButton.setVisibility(View.VISIBLE);
                    multiplier = Double.parseDouble(getNumberText.getText().toString());
                    startButton.setText("Enter result");
                    num = randomizer.nextInt(2000);
                    helpingTextView.setText(mNumbers[num] + " x " + multiplier);
                    getNumberText.setText("");
                    counter++;
                }else if(!(getNumberText.getText().toString().length() < 1)){
                    double result;
                    double userResult;
                    result = mNumbers[num] * multiplier;
                    userResult = Double.parseDouble(getNumberText.getText().toString());
                    if(result == userResult){
                        Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                        num = randomizer.nextInt(2000);
                        helpingTextView.setText(mNumbers[num] + " x " + multiplier);
                        getNumberText.setText("");

                    } else {
                        Toast.makeText(MainActivity.this, "False :(", Toast.LENGTH_SHORT).show();
                        getNumberText.setText("");

                    }
                } else{
                    Toast.makeText(MainActivity.this, "You need to enter a number first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                helpingTextView.setText("Type in a number");
                startButton.setText("Start counting");
                getNumberText.setText("");
                backButton.setVisibility(View.GONE);
            }
        });
    }
}
