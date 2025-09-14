package com.example.lightsout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WinActivity extends AppCompatActivity {


    Button playAgainButton;

    View.OnClickListener playAgainListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(WinActivity.this, MainActivity.class);
            startActivity(intent);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_win);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(playAgainListener);


    }


}