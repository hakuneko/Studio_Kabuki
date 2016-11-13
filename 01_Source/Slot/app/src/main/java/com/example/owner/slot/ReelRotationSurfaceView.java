package com.example.owner.slot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Owner on 2016/10/30.
 */

public class ReelRotationSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder = null;
    private Thread thread = null;
    private boolean isAttached = true;
    private Bitmap reel_01;               // 第1リールイメージ格納用
    private Bitmap reel_02;               // 第2リールイメージ格納用
    private Bitmap reel_03;               // 第3リールイメージ格納用
    private Bitmap machineImage;
    final static int REEL_ONE_X = 135;   // 第1リールＸ座標
    final static int REEL_TWO_X = 290;   // 第2リールＸ座標
    final static int REEL_THREE_X = 450; // 第3リールＸ座標
    private int iReel01Y = REEL_ZAHYOU; // 第1リールＹ座標
    private int iReel02Y = REEL_ZAHYOU; // 第2リールＹ座標
    private int iReel03Y = REEL_ZAHYOU; // 第3リールＹ座標
    final static int REEL_SPEED = 30;   // リール回転速度
    final static int FPS = 60;           // FPS
    final static int REEL_ZAHYOU = -1460; // リールの初期位置
    private Timer timer; // タイマークラスの宣言
    // SurfaceViewのサイズを指定(異なる画面サイズに対応するため)
    private static final int SURFACE_HEIGHT = 1180;
    private static final int SURFACE_WIDTH = 720;
    private static final Rect SURFACE_RECT = new Rect(0, 0, SURFACE_WIDTH, SURFACE_HEIGHT);
    private static int iTouchX;
    private static int iTouchY;
    private boolean isReelOneFlg = false; // 第1リール回転フラグ
    private boolean isReelTwoFlg = false; // 第2リール回転フラグ
    private boolean isReelThreeFlg = false; // 第3リール回転フラグ

    public ReelRotationSurfaceView(Context context) {
        super(context);
        // Surfaceホルダー生成
        holder = getHolder();
        holder.addCallback(this);
        machineImage = BitmapFactory.decodeResource(getResources(), R.drawable.machine_image);
        reel_01 = BitmapFactory.decodeResource(getResources(), R.drawable.reel_01);
        reel_02 = BitmapFactory.decodeResource(getResources(), R.drawable.reel_02);
        reel_03 = BitmapFactory.decodeResource(getResources(), R.drawable.reel_03);
        timer = new Timer();
        holder.setFixedSize(SURFACE_WIDTH, SURFACE_HEIGHT);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.holder = holder;
        thread = new Thread(this);
        // スレッドを開始
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                doTask();
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, 1000 / FPS);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        timer.cancel();
    }

    @Override
    public void run() {

    }

    public void doTask() {
        // ホルダー取得とロック
        Canvas canvas = holder.lockCanvas(SURFACE_RECT);

        // 情報更新
        //update();
        // 描画
        if (canvas != null) {
            doDraw(canvas);
            // アンロック
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void doDraw(Canvas canvas) {
        if (iReel01Y >= 0) {
            iReel01Y = REEL_ZAHYOU;
        } else if (iReel02Y >= 0) {
            iReel02Y = REEL_ZAHYOU;
        } else if (iReel03Y >= 0) {
            iReel03Y = REEL_ZAHYOU;
        }
        canvas.drawBitmap(reel_01, REEL_ONE_X, iReel01Y, null);
        canvas.drawBitmap(reel_02, REEL_TWO_X, iReel02Y, null);
        canvas.drawBitmap(reel_03, REEL_THREE_X, iReel03Y, null);
        canvas.drawBitmap(machineImage, 0, 0, null);

        if (isReelOneFlg) {
            iReel01Y += REEL_SPEED;
        }
        if (isReelTwoFlg) {
            iReel02Y += REEL_SPEED;
        }
        if (isReelThreeFlg) {
            iReel03Y += REEL_SPEED;
        }
    }

    // タッチイベント
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        // タッチされた時
        iTouchX = (int) event.getX() * SURFACE_WIDTH / getWidth();
        iTouchY = (int) event.getY() * SURFACE_HEIGHT / getHeight();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // タッチダウンの処理を記載
                reelAction(iTouchX, iTouchY);
                break;
        }
        return true;
    }

    public void reelAction(int iTouchX, int iTouchY)
    {
        if( ( 130 <= iTouchX &&  220 >= iTouchX) && ( 1070 <=iTouchY ))
        {
            // レバーオン
            isReelOneFlg = true;
            isReelTwoFlg = true;
            isReelThreeFlg = true;
        }
        else if( (iTouchX >= 250 && 325 >= iTouchX) && ( 1070 <=iTouchY ))
        { // 第1リールボタン押下
            isReelOneFlg = false;
        }
        else if( (iTouchX >= 326 && 405 >= iTouchX) && ( 1070 <=iTouchY ))
        { // 第2リールボタン押下
            isReelTwoFlg = false;
        }
        else if( (iTouchX >= 406 && 485 >= iTouchX) && ( 1070 <=iTouchY ))
        { // 第3リールボタン押下
            isReelThreeFlg = false;
        }
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
