package com.linguiqing.mychanage.ui.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ***************************************
 * statement: 学习 Handler Activity
 * auther: lingguiqin
 * date created : 2017/3/3 0003
 * ***************************************
 */

public class StudyHandlerActivity extends BaseActivity {

    @BindView(R.id.txt_study_handler_content)
    TextView mTxtContent;
    @BindView(R.id.btn_study_handler_start)
    Button mBtnStart;
    @BindView(R.id.btn_study_handler_end)
    Button mBtnEnd;
    @BindView(R.id.btn_study_handler_timer_start)
    Button mBtnTimerStart;
    @BindView(R.id.btn_study_handler_used_new_Thread)
    Button mBtnUsedNewThread;
    @BindView(R.id.btn_study_handler_handler_thread_start)
    Button mBtnHandlerThreadStart;
    private MyHandler mHandler;
    private MyHandler mTimerHandler;
    private MyHandler mThreadHandler;
    private Runnable runnable;
    private Timer mTimer;
    private MyHandler mHandlerThreadHandler;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_handler_layout;
    }

    @Override
    public void initView(Bundle savedInstanceState) {


        mTxtContent.setText("UpdateThread...");

        mHandler = new MyHandler();

        // 这里注释重写了run方法，并未开启子线程，所以他是运行在主线程的
        // 例子表面上看handler使用了post方法启动了runnbale，
        // 其实启动的线程和activity主线程是同一个线程，
        // 因为它只是运行了线程的run方法，而不是start方法
        runnable = new Runnable() {
            @Override
            public void run() {
                // 运行在主线程,2s回掉一次
                mTxtContent.append("\nUpdateThread...");
                mHandler.postDelayed(runnable, 2000);
            }
        };

        // 开启子线程的handler
        startNewThreadHandler();

        // 使用HandlerThread
        HandlerThread handlerThread = new HandlerThread("check-message-coming");
        handlerThread.start();
        mHandlerThreadHandler = new MyHandler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handlerInputNums(msg);
            }
        };

    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "学习Handler", "", null);
        return titlebar;
    }

    // 2s定时器handle
    private void startTimerHandler() {
        mTxtContent.setText("UpdateThread...");
        if (mTimerHandler == null) {
            mTimerHandler = new MyHandler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == 0x1233) {
                        mTxtContent.append("\nUpdateThread...");
                    }
                }
            };
        }

        // 定义一个定时器，让该计时器2s周期性的执行任务
        if (mTimer == null) {
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // 发送空消息
                    mTimerHandler.sendEmptyMessage(0x1233);
                }
            }, 0, 2000);
        } else {
            mTimer.cancel();
            mTimer = null;
        }

    }

    /**
     * 在子线程中使用handler
     * 用toast 打印出0-1000 的质数
     */
    private void startNewThreadHandler() {

        new Thread() {

            @Override
            public void run() {
                Looper.prepare(); // 子线程需要自己创建looper // Looper.myLooper()
                mThreadHandler = new MyHandler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        handlerInputNums(msg);
                    }
                };
                Looper.loop();
            }
        }.start();

    }

    private void handlerInputNums(Message msg) {
        if (msg.what == 0x123) {
            List<Integer> nums = new ArrayList<>();

            // 从 2 开始计算、到1000 的 所有质数
            outer:
            for (int i = 2; i <= 1000; i++) {
                for (int j = 2; j < Math.sqrt(i); j++) {
                    // 如果可以整除说明这个数不是质数
                    if (i != 2 && i % j == 0) {
                        continue outer;
                    }
                }

                nums.add(i);
            }

            // 使用Toast弹出所有质数
            showToast(nums.toString(), Toast.LENGTH_LONG);
        }
    }


    @OnClick({R.id.btn_study_handler_start, R.id.btn_study_handler_end, R.id.btn_study_handler_timer_start, R.id.btn_study_handler_used_new_Thread, R.id.btn_study_handler_handler_thread_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_study_handler_start:
                mHandler.post(runnable);
                break;
            case R.id.btn_study_handler_end:
                mHandler.removeCallbacks(runnable);
                break;
            case R.id.btn_study_handler_timer_start:
                startTimerHandler();
                break;
            case R.id.btn_study_handler_used_new_Thread:

                if (mThreadHandler != null) {
                    showToast("正在计算打印0~1000 的质数", Toast.LENGTH_SHORT);
                    mThreadHandler.sendEmptyMessage(0x123);
                }
                break;
            case R.id.btn_study_handler_handler_thread_start:
                showToast("正在计算打印0~1000 的质数", Toast.LENGTH_SHORT);
                mHandlerThreadHandler.sendEmptyMessage(0x123);
                break;
        }
    }

}
