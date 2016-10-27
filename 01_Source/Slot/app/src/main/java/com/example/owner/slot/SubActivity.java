package com.example.owner.slot;

/**
 * Created by Owner on 2016/10/28.
 */
import android.app.Activity;
import android.os.Bundle;

public class SubActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_game);
    }

}
