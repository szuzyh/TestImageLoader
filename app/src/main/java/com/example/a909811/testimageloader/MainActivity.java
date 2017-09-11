package com.example.a909811.testimageloader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a909811.testimageloader.util.ImageLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mBtn_test;
    private ImageView mIv_test;
    private String imageUrl = "http://a.hiphotos.baidu.com/image/h%3D220/sign=907b8b39e324b899c13c7e3a5e061d59/2934349b033b5bb571a981c73cd3d539b600bc89.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn_test = (Button) findViewById(R.id.btn_test);
        mIv_test = (ImageView) findViewById(R.id.iv_test);
        mBtn_test.setOnClickListener(this);
    }

    private Bitmap bitmap = null;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_test:

                new Thread() {
                    @Override
                    public void run() {
                        // 需要执行的方法
                        // 执行完毕后给handler发送一个空消息
                          ImageLoader imageLoader = new ImageLoader();
                        //   imageLoader.downloadImage(imageUrl);
                        bitmap = imageLoader.downloadImage(imageUrl);
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    }
                }.start();
                break;
            default:
                break;
        }
    }
    private Handler handler = new Handler() {
        //当有消息发送出来的时候就执行Handler的这个方法来处理消息分发
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    mIv_test.setImageBitmap(bitmap);
                    break;
            }
            //处理UI
        }
    };
}
