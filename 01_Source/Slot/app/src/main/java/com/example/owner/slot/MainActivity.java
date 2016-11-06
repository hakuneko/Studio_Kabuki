package com.example.owner.slot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playGame = (Button)findViewById(R.id.playGame);
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to Play screen
                Intent intent = new Intent();
                intent.setClassName("com.example.owner.slot","com.example.owner.slot.SubActivity");
                startActivity(intent);
            }
        });
    }
}
