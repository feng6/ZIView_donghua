package com.example.lanouhn.ziview_donghua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * 可随意滑动放置的动画效果
 */
public class MainActivity extends AppCompatActivity {
private ImageView btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (ImageView) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayMetrics dm = getResources().getDisplayMetrics();
                final int screenWidth = dm.widthPixels;
                final int screenHeight = dm.heightPixels - 50;
                //悬浮按钮触摸移动监听事件
                btn.setOnTouchListener(new View.OnTouchListener() {

                    int translationX, translationY;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        // TODO Auto-generated method stub
                        int ea = event.getAction();
                        switch (ea) {
                            case MotionEvent.ACTION_DOWN:
                                translationX = (int) event.getRawX();//获取触摸事件触摸位置的原始X坐标
                                translationY = (int) event.getRawY();
                                break;

                            case MotionEvent.ACTION_MOVE:
                                int dx = (int) event.getRawX()- translationX;
                                int dy = (int) event.getRawY()- translationY;

                                int l = v.getLeft() + dx;
                                int b = v.getBottom() + dy;
                                int r = v.getRight() + dx;
                                int t = v.getTop() + dy;
                                //下面判断移动是否超出屏幕  　　　　　　
                                if (l < 0) {
                                    l = 0;
                                    r = l + v.getWidth();
                                }

                                if (t < 0) {
                                    t = 0;
                                    b = t + v.getHeight();
                                }

                                if (r > screenWidth) {
                                    r = screenWidth;
                                    l = r - v.getWidth();
                                }

                                if (b > screenHeight) {
                                    b = screenHeight;
                                    t = b - v.getHeight();
                                }
                                v.layout(l, t, r, b);

                                translationX = (int) event.getRawX();
                                translationY = (int) event.getRawY();
                                v.postInvalidate();
                                break;
                            case MotionEvent.ACTION_UP:
                                translationX = (int) event.getRawX() + translationX;
                                translationY = (int) event.getRawY() + translationY;
                                break;
                        }
                        return false;
                    }
                });

            }
        });
    }

}
