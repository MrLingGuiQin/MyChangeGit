package com.linguiqing.mychanage.ui.eventbus;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseViewHolder;
import com.linguiqing.mychanage.base.CommonAdapter;
import com.linguiqing.mychanage.util.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/5/3 0003
 * ***************************************
 */

public class EventBusAdapter extends CommonAdapter<String> {
    public EventBusAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(final BaseViewHolder helper, String item) {
        helper.setText(R.id.txt_study_event_bus_item, item);
        helper.getView(R.id.txt_study_event_bus_item)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBus.getDefault().post(new EventDataBean(helper.getPosition()));
                    }
                });
    }
}
