package com.linguiqing.mychanage.ui.Permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.util.SystemUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import butterknife.OnClick;

/**
 * ***************************************
 * statement: 处理 6.0 权限奔溃的问题
 * auther: lingguiqin
 * date created : 2017/3/3 0003
 * ***************************************
 */

public class StudyPermissionActivity extends BaseActivity {

    public static final int REQUEST_PERMISSION_CALL_PHONE = 1;
    public static final int REQUEST_PERMISSION_CALL_PHONE2 = 2;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_permission;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(this, root);
        titlebar.initTitlebar(true, "Android 6.0 权限的处理", "", null);
        return titlebar;
    }


    /**
     * 请求权限结果回调
     *
     * @param requestCode  请求权限请求码
     * @param permissions  权限集合
     * @param grantResults 权限请求结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CALL_PHONE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 请求授权，用户同意啦 ，可以放飞啦
                    showToast("请求授权，用户同意啦 ，可以放飞啦", Toast.LENGTH_LONG);
                    SystemUtil.call(this, "13657972950");
                    break;
                } else {
                    // 请求授权用户还是拒绝了
                    showToast("未授权拨打电话哦", Toast.LENGTH_LONG);
                }
        }


    }

    @OnClick({R.id.btn_call_phone, R.id.btn_call_phone2, R.id.btn_contacts, R.id.btn_location, R.id.btn_microp_phone, R.id.btn_storage, R.id.btn_sms, R.id.btn_sensors, R.id.btn_camera, R.id.btn_calendar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_call_phone: // 拨打电话
                handleCallPhone();
                break;
            case R.id.btn_call_phone2: // 拨打电话2
                handleCallPhone2();
                break;
            case R.id.btn_contacts:  // 联系人
                handleContacts();
                break;
            case R.id.btn_location: // 定位
                break;
            case R.id.btn_microp_phone: // 麦克风
                break;
            case R.id.btn_storage: // 内存卡
                break;
            case R.id.btn_sms: // 短信
                break;
            case R.id.btn_sensors: //传感器
                break;
            case R.id.btn_camera: //照相机
                break;
            case R.id.btn_calendar: //日历
                break;
        }
    }

    /**
     * 处理访问电话本联系人逻辑
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private void handleContacts() {
        PermissionUtil.request(this, PermissionUtil.REQUEST_CONTACTS_CODE, new IPermissionResult() {
            @Override
            public void OnSucceed(int requestCode, List<String> grantPermissions) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
            }
        }, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS);

    }

    /**
     * 手动管理请求拨打电话权限
     */
    private void handleCallPhone() {
        // 判断是否有拨打电话权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 判断用户上次是否拒绝过,弹出对话框告诉用户为什么需要该权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("拨打电话您需要授权哦，不然没法拨打")
                        .setTitle("温馨提示")
                        .setCancelable(false)
                        .setNegativeButton("拒绝", null)
                        .setPositiveButton("授权", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 去申请权限
                                ActivityCompat.requestPermissions(StudyPermissionActivity.this,
                                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION_CALL_PHONE);
                            }
                        }).create().show();
            } else {
                // 去申请权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION_CALL_PHONE);
            }
            return;
        }
        SystemUtil.call(this, "13657972950");
    }

    /**
     * 使用第三方 andPermission 库 来处理拨打电话权限
     */
    private void handleCallPhone2() {
        // 申请单个权限。
        PermissionUtil.request(this, PermissionUtil.REQUEST_PHONE_CODE, new IPermissionResult() {
            @Override
            public void OnSucceed(int requestCode, List<String> grantPermissions) {
                SystemUtil.call(StudyPermissionActivity.this, "13657972950");
            }
        }, Manifest.permission.CALL_PHONE);
    }

    @PermissionYes(REQUEST_PERMISSION_CALL_PHONE2)
    private void getPermissionYes(List<String> grantedPermissions) {
        showToast("电话权限授权成功", Toast.LENGTH_LONG);
        SystemUtil.call(this, "13657972950");
    }

    @PermissionNo(REQUEST_PERMISSION_CALL_PHONE2)
    private void getPermissionNo(List<String> deniedPermissions) {
        // TODO 申请权限失败。
        showToast("电话权限授权失败", Toast.LENGTH_LONG);
    }
}
