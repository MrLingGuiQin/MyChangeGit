package com.linguiqing.mychanage.ui.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.app.UrlManager;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.recylerView.GetDataApi;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * ***************************************
 * statement: 学习Retrofit的使用
 * auther: lingguiqin
 * date created : 2017/8/16 0028
 * ***************************************
 */

public class StudyRetrofitActivity extends BaseActivity {


    private GetDataApi mApi;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_retrofit;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(GetDataApi.class);
    }

    private void getBannerData() {
        Call<BannerData> bannerData = mApi.getBannerData();
        bannerData.enqueue(new Callback<BannerData>() {
            @Override
            public void onResponse(Call<BannerData> call, Response<BannerData> response) {
                BannerData bannerData = response.body();
                showToast(bannerData.getData().getAdInfoMap().get(0).getAdHrefUrl(),
                        Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<BannerData> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.btn_study_retorfit_get)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_study_retorfit_get:
                getBannerData();
                break;
        }

    }
}
