//package com.linguiqing.mychanage.ui.usedDataBinging;
//
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.RelativeLayout;
//
//import com.linguiqing.mychanage.R;
//import com.linguiqing.mychanage.base.BaseActivity;
//import com.linguiqing.mychanage.databinding.ActivityStudyDataBindingBinding;
//import com.linguiqing.mychanage.ui.coustomView.Titlebar;
//
//public class StudyDataBindingActivity extends BaseActivity {
//
//
//    private ActivityStudyDataBindingBinding mDataBinding;
//    private UserBean mUserBean;
//
//    @Override
//    public int getLayoutResId() {
//        return 0;
//    }
//
//    @Override
//    public void initView(Bundle savedInstanceState) {
//        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_study_data_binding);
//        mUserBean = new UserBean("凌桂钦", "安卓开发工程师", "18");
//        mDataBinding.setUser(mUserBean);
//        mDataBinding.setUserPresener(new UserPresenter());
//    }
//
//    public class UserPresenter {
//        // 方法的引用,方法参数名受系统api限制
//        public void onClickSwitchUser(View view) {
//            updateUserData(mUserBean);
//        }
//
//        // 使用lam 表达式 可以自定义参数 进行传递
//        public void onClickSwitchUser2(UserBean user) {
//            updateUserData(user);
//        }
//
//        // 高级绑定--动态变量 RecylerView
//        public void onClickBindingVariable(View view) {
//            goToCustomActivity(StudyDataBindingToRecylerViewActivity.class);
//        }
//    }
//
//    private void updateUserData(UserBean user) {
//        if (user.getUserName().equals("凌桂钦")) {
//            user.setAge("22");
//            user.setUserName("冰淇淋");
//            user.setJob("码农");
//        } else {
//            user.setAge("18");
//            user.setUserName("凌桂钦");
//            user.setJob("安卓开发工程师");
//        }
//        mDataBinding.setUser(user);
//    }
//}
