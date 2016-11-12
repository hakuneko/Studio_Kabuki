//package com.example.owner.slot;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.util.Log;
//import android.view.SurfaceHolder;
//
///**
// * Created by Owner on 2016/10/30.
// */
//
//public class ReelRotationCallBack implements SurfaceHolder.Callback, Runnable
//{
//    private SurfaceHolder holder = null;
//    private Thread thread = null;
//    private boolean isAttached = true;
//
//    @Override
//    public void surfaceCreated(SurfaceHolder surfaceHolder)
//    {
//        this.holder = holder;
//        thread = new Thread(this);
//        // スレッドを開始
//        thread.start();
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2)
//    {
//        // 何もしない
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder surfaceHolder)
//    {
//        // Flagを初期化
//        isAttached = false;
//        // スレッドを終了
//        thread = null;
//    }
//
//    @Override
//    public void run()
//    {
//        // リール回転（無限ループ）
//        while( isAttached )
//        {
//          Log.w("テスト", "ループなう");
//        }
//    }
//}
