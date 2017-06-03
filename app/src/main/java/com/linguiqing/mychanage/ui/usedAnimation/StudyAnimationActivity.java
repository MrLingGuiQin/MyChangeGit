package com.linguiqing.mychanage.ui.usedAnimation;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class StudyAnimationActivity extends BaseActivity {

    @BindView(R.id.txt_quick_login_tab)
    TextView mTxtQuickLoginTab;
    @BindView(R.id.txt_username_login_tab)
    TextView mTxtUsernameLoginTab;
    @BindView(R.id.view_tab_left_line)
    View mViewTabLeftLine;
    @BindView(R.id.view_tab_right_line)
    View mViewTabRightLine;
    private Animation mLeftAnimation;
    private Animation mRightAnimation;
    private boolean isSelectQuickLogin = true;
    private boolean isLeftAnimationEnd = true;
    private boolean isRightAnimationEnd = true;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_animation;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initAnimation();
    }

    private void initAnimation() {
        // 向左移动的动画
        mLeftAnimation = AnimationUtils.loadAnimation(mContext, R.anim.tab_line_move_left);
        mLeftAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isLeftAnimationEnd = false;
                mTxtQuickLoginTab.setTextColor(getResources().getColor(R.color.orange));
                mTxtUsernameLoginTab.setTextColor(getResources().getColor(R.color.default_text_color));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewTabLeftLine.setVisibility(View.VISIBLE);
                mViewTabRightLine.setVisibility(View.INVISIBLE);
                isLeftAnimationEnd = true;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // 向右移动的动画
        mRightAnimation = AnimationUtils.loadAnimation(mContext, R.anim.tab_line_move_right);
        mRightAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isRightAnimationEnd = false;
                mTxtUsernameLoginTab.setTextColor(getResources().getColor(R.color.orange));
                mTxtQuickLoginTab.setTextColor(getResources().getColor(R.color.default_text_color));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewTabRightLine.setVisibility(View.VISIBLE);
                mViewTabLeftLine.setVisibility(View.INVISIBLE);
                isRightAnimationEnd = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @OnClick({R.id.txt_quick_login_tab, R.id.txt_username_login_tab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_quick_login_tab:
                if (isSelectQuickLogin == false && isRightAnimationEnd) {
                    mViewTabRightLine.startAnimation(mLeftAnimation);
                    isSelectQuickLogin = true;
                }
                break;
            case R.id.txt_username_login_tab:
                if (isSelectQuickLogin == true && isLeftAnimationEnd) {
                    mViewTabLeftLine.startAnimation(mRightAnimation);
                    isSelectQuickLogin = false;
                }
                break;
        }
    }
}
