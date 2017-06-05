package com.linguiqing.mychanage.ui.eventbus;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ***************************************
 * statement: 使用eventBus 优化的事件总线 框架
 * https://github.com/greenrobot/EventBus
 * auther: lingguiqin
 * date created : 2017/4/27 0027
 * ***************************************
 */

public class StudyEventBusActivity extends BaseActivity {


    @BindView(R.id.lsv_event_bus)
    ListView mLsvEventBus;
    private List<String> mList;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_event_bus;
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(this, root);
        titlebar.initTitlebar(true, "EventBusde的使用", "", null);
        return titlebar;
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mList.add("测试EventBus条目" + i);
        }
    }

    @Override

    public void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        EventBusAdapter mAdapter = new EventBusAdapter(mContext, mList, R.layout.item_study_event_bus);
        mLsvEventBus.setAdapter(mAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventDataBean eventDataBean) {
        showToast("EventBus 接收了第" + eventDataBean.position + "点击事件", Toast.LENGTH_LONG);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
