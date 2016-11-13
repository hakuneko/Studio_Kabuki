package com.example.owner.slot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


public class GameMainActivity extends AppCompatActivity implements View.OnTouchListener {
    // リール回転クラスを宣言
    ReelRotationSurfaceView reelRoationSV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game_main);
        reelRoationSV = new ReelRotationSurfaceView(this);
        // SurfaceViewをセット
        setContentView(reelRoationSV);
        // OnTouchListenerをセット
        reelRoationSV.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        return false;
    }
}
