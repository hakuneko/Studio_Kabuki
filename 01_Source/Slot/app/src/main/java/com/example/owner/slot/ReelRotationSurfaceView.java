package com.example.owner.slot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Owner on 2016/10/30.
 */

public class ReelRotationSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
    private SurfaceHolder holder = null;
    private Thread thread = null;
    private boolean isAttached = true;
    private Bitmap reel_01;
    private Bitmap reel_02;
    private Bitmap reel_03;
    private int iReel01Y = -1460;
    private int iReel02Y = -1460;
    private int iReel03Y = -1460;
    private Timer timer;

    public ReelRotationSurfaceView(Context context) {
        super(context);
        // Surfaceホルダー生成
        holder = getHolder();
        holder.addCallback(this);
        reel_01 = BitmapFactory.decodeResource(getResources(), R.drawable.reel_01);
        reel_02 = BitmapFactory.decodeResource(getResources(), R.drawable.reel_02);
        reel_03 = BitmapFactory.decodeResource(getResources(), R.drawable.reel_03);
        timer = new Timer();

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.holder = holder;
        thread = new Thread(this);
        // スレッドを開始
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2)
    {
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                doTask();
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 1000 / 60);//60FPS
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        timer.cancel();
    }

    @Override
    public void run()
    {

    }

    public void doTask()
    {
        // ホルダー取得とロック
        Canvas canvas = holder.lockCanvas();

        // 情報更新
        //update();
        // 描画
        if (canvas != null) {
            doDraw(canvas);
            // アンロック
            holder.unlockCanvasAndPost(canvas);
        }
    }
    public void doDraw(Canvas canvas)
    {
        if( iReel01Y >= 0 )
        {
            iReel01Y = -1460;
        }
        else if( iReel02Y >= 0 )
        {
            iReel02Y = -1460;
        }
        else if( iReel03Y >= 0)
        {
            iReel03Y = -1460;
        }
        canvas.drawBitmap(reel_01, 100, iReel01Y, null);
        canvas.drawBitmap(reel_02, 270, iReel02Y, null);
        canvas.drawBitmap(reel_03, 430, iReel03Y, null);

        iReel01Y += 30;
        iReel02Y += 30;
        iReel03Y += 30;
    }
}
//public class ReelRotationSurfaceView extends SurfaceView
//{
//    private ReelRotationCallBack cb;
//
//    public ReelRotationSurfaceView(Context context)
//    {
//        super(context);
//        Bitmap reel_01 = BitmapFactory.decodeResource(getResources(), R.drawable.reel_01);
//        SurfaceHolder holder = getHolder();
//        cb = new ReelRotationCallBack();
//        holder.addCallback(cb);
//    }
//}
