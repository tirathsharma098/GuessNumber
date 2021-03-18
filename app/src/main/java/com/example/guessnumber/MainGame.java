package com.example.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class MainGame extends AppCompatActivity {

    private TextView messageView;
    private EditText  takeInput;
    private Button submitButton, mainMenu, playAgain;
    private int guessed_Number;
    private int randomNumber = (int) ( Math.random()*101 );
    private int temp_random;
    private int i = 1;
    private int j = 10;
    private String userStringInput;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Log.d("MainGame", "Oncreate method executed");
        Log.d("Main Game","Random number is :  "+ randomNumber);

        messageView = findViewById(R.id.message_View);
        takeInput = findViewById(R.id.take_Input);
        submitButton = findViewById(R.id.submit_Button);
        playAgain = findViewById(R.id.playAgain_Button);
        mainMenu = findViewById(R.id.mainMenu_Button);


        messageView.setText("Guess a Number Beetween 0 Included and 100 Included." + " \n Remember You have only 5 chances.");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Main Game","Random number is :  "+ randomNumber);
                Log.d("Main Game","On click listener executed.");


                userStringInput = takeInput.getText().toString();
                guessed_Number = Integer.parseInt(userStringInput);

                i++; // First chance is incorrect therefore chances increased.


                if (randomNumber == guessed_Number) {

                    Log.d("Main Game", "If statement executed");

                    messageView.setText("You Won, The number was : " + randomNumber + " \n Your Score is : " + j + "/10" );

                    i=1;

                    takeInput.setEnabled(false);

                    submitButton.setVisibility(View.GONE);
                    playAgain.setVisibility(View.VISIBLE);
                    mainMenu.setVisibility(View.VISIBLE);

                } else if(i<=5){

                    Log.d("Main game", "Else statement executed."+" Chances is  = " + i);

                    temp_random = (int) (Math.random() * 11);
                    messageView.setText("Your Entered Number is Incorrect. \n \t Try Again." + "Hint Number is smaller than : " + (randomNumber + temp_random) + " Included");
                    j--;
                    j--;

                }else if(i>=6){

                    j--;
                    j--;

                    Log.d("Main game","You lose executed");
                    takeInput.setEnabled(false);
                    messageView.setText("You Loose, The number was : " + randomNumber + " \n Your Score is : " + j + "/10" );

                    submitButton.setVisibility(View.GONE);

                    submitButton.setVisibility(View.GONE);
                    playAgain.setVisibility(View.VISIBLE);
                    mainMenu.setVisibility(View.VISIBLE);

                }

            }
        });


        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainGame.this, MainGame.class);
                startActivity(intent);
            }
        });


        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



}