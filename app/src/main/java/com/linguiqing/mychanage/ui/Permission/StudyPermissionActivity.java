package com.linguiqing.mychanage.ui.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.util.SystemUtil;

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
                handleLocation();
                break;
            case R.id.btn_microp_phone: // 麦克风
                handleMicrop();
                break;
            case R.id.btn_storage: // 内存卡
                handleStorage();
                break;
            case R.id.btn_sms: // 短信
                handleSMS();
                break;
            case R.id.btn_sensors: //传感器
                handleSensors();
                break;
            case R.id.btn_camera: //照相机
                handleCamera();
                break;
            case R.id.btn_calendar: //日历
                handleCalendar();
                break;
        }
    }

    /**
     * 处理传感器权限
     */
    private void handleCamera() {
        PermissionUtil.request(this, PermissionUtil.REQUEST_CAMERA_CODE,
                new IPermissionSuccessCallback() {
                    @Override
                    public void OnSucceed(int requestCode, List<String> grantPermissions) {
                        showToast("获取相机权限成功", Toast.LENGTH_LONG);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(intent);
                    }
                }, Manifest.permission.CAMERA);
    }

    /**
     * 处理日历权限
     */
    private void handleCalendar() {
        PermissionUtil.request(this, PermissionUtil.REQUEST_SENSORS_CODE,
                new IPermissionSuccessCallback() {
                    @Override
                    public void OnSucceed(int requestCode, List<String> grantPermissions) {
                        showToast("获取传感器权限成功", Toast.LENGTH_LONG);
                    }
                }, Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR);

    }

    /**
     * 处理传感器权限
     */
    private void handleSensors() {
        PermissionUtil.request(this, PermissionUtil.REQUEST_SENSORS_CODE,
                new IPermissionSuccessCallback() {
                    @Override
                    public void OnSucceed(int requestCode, List<String> grantPermissions) {
                        showToast("获取传感器权限成功", Toast.LENGTH_LONG);
                    }
                }, Manifest.permission.BODY_SENSORS);

    }

    /**
     * 处理短信权限
     */
    private void handleSMS() {
        PermissionUtil.request(this, PermissionUtil.REQUEST_SMS_CODE,
                new IPermissionSuccessCallback() {
                    @Override
                    public void OnSucceed(int requestCode, List<String> grantPermissions) {
                        showToast("获取短信权限成功", Toast.LENGTH_LONG);
                    }
                }, Manifest.permission.BROADCAST_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS
        );
    }

    /**
     * 处理读写内存卡权限
     * 跳转到系统图库
     */
    private void handleStorage() {
        PermissionUtil.request(this, PermissionUtil.REQUEST_STORAGE_CODE,
                new IPermissionSuccessCallback() {
                    @Override
                    public void OnSucceed(int requestCode, List<String> grantPermissions) {
                        showToast("获取内存卡权限成功", Toast.LENGTH_LONG);
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        startActivityForResult(intent, 0);
                    }
                }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 处理麦克风权限
     */
    private void handleMicrop() {
        PermissionUtil.request(this, PermissionUtil.REQUEST_MICROP_CODE,
                new IPermissionSuccessCallback() {
                    @Override
                    public void OnSucceed(int requestCode, List<String> grantPermissions) {
                        Intent mi = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                        startActivity(mi);
                    }
                }, Manifest.permission.RECORD_AUDIO);
    }

    /**
     * 处理定位权限
     */
    private void handleLocation() {
        PermissionUtil.request(this, PermissionUtil.REQUEST_LOCATION_CODE,
                new IPermissionSuccessCallback() {
                    @Override
                    public void OnSucceed(int requestCode, List<String> grantPermissions) {
                        showToast("获取定位权限成功啦", Toast.LENGTH_LONG);
                    }
                }, Manifest.permission.ACCESS_FINE_LOCATION);

    }

    /**
     * 处理访问电话本联系人逻辑
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private void handleContacts() {
        PermissionUtil.request(this, PermissionUtil.REQUEST_CONTACTS_CODE, new IPermissionSuccessCallback() {
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
        PermissionUtil.request(this, PermissionUtil.REQUEST_PHONE_CODE, new IPermissionSuccessCallback() {
            @Override
            public void OnSucceed(int requestCode, List<String> grantPermissions) {
                SystemUtil.call(StudyPermissionActivity.this, "13657972950");
            }
        }, Manifest.permission.CALL_PHONE);
    }

}
