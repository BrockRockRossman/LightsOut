package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 3;
    private GridLayout grid;
    private boolean cellState [][];
    private int count = 0;
    private TextView lightNum;
    private Button offButton;
    private Button randomButton;



    // Grid Event Listener
    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Button current = (Button) v;
            for (int i = 0; i < grid.getChildCount(); i++) {
                Button gridButton = (Button) grid.getChildAt(i);

                if (gridButton == current){
                    int row = i / GRID_SIZE;
                    int col = i % GRID_SIZE;

                    if (cellState[row][col] == true) {
                        cellState[row][col] = false;
                    } else {
                        cellState[row][col] = true;
                    }

                }
            }

            count += 1;

            recolor();



        }


    };

    // Off Button Listener
    View.OnClickListener offbuttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("offButton","offButtonclicked");
            for(int i =0; i< GRID_SIZE; i++){
                for(int j =0; j< GRID_SIZE; j++){
                    cellState[i][j] = false;
                }
            }
            recolor();

        }
    };

    // Randomize Button Listener
    View.OnClickListener randomizebuttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            randomize();

            recolor();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cellState = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};

        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.light_grid);

        lightNum = findViewById(R.id.lightNum);

        offButton = findViewById(R.id.offButton);
        randomButton = findViewById(R.id.randomButton);



        randomize();

        recolor();

        // Registerring the listener

        for (int i = 0; i < grid.getChildCount(); i++) {

            Button currButton = (Button) grid.getChildAt(i);
            currButton.setOnClickListener(buttonListener);

        }

        offButton.setOnClickListener(offbuttonListener);
        randomButton.setOnClickListener((randomizebuttonListener));

    }

    public void recolor(){
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col] == true) {
                gridButton.setBackgroundColor(getColor(R.color.red));
            } else {
                gridButton.setBackgroundColor(getColor(R.color.black));
            }
        }

        lightCount();
    }

    public void randomize(){
        Random random = new Random();
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                cellState[i][j] = random.nextBoolean();
            }
        }
    }

    public void lightCount(){
        int lights = 0;
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                if(cellState[i][j])
                {
                    lights += 1;
                }
            }
        }

        String light = "" + lights;
        lightNum.setText(light);

        if(lights == 9)
        {
            Intent intent = new Intent(MainActivity.this, WinActivity.class);
            intent.putExtra("ClickCount", "" + count);
            startActivity(intent);
        }
    }





}
