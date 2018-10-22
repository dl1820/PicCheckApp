package com.lgd.piccheck;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    char a;
    private static final int REQUEST_STORAGE = 1;
    private static String[] PERMISSION_STORAGE= {
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (checkPermissions(this, Manifest.permission.CAMERA)){
            Log.v("log", "hi");
        } else {
            requestPermissions(this, PERMISSION_STORAGE, REQUEST_STORAGE);
        }
    }

    private static boolean checkPermissions(Activity activity, String permission){
        int permissionResult = ActivityCompat.checkSelfPermission(activity, permission);
        if (permissionResult == PackageManager.PERMISSION_GRANTED ) return true;
        else return false;
    }

    private static void requestPermissions(final @NonNull Activity activity,
                                          final @NonNull String[] permissions, final int requestCode){
        if (activity instanceof ActivityCompat.OnRequestPermissionsResultCallback) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    final int[] grantResults = new int[permissions.length];

                    PackageManager packageManager = activity.getPackageManager();
                    String packageName = activity.getPackageName();

                    final int permissionCount = permissions.length;
                    for (int i = 0; i < permissionCount; i++){
                        grantResults[i] = packageManager.checkPermission(permissions[i], packageName);
                    }

                    ((ActivityCompat.OnRequestPermissionsResultCallback)activity).onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            });
        }
    }

    private static boolean verifyPermission(int[] grantresults){
        if (grantresults.length < 1){
            return false;
        }

        for (int result : grantresults){
            if (result != PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    private void showRequestAgainDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("이 권한은 꼭 필요한 권한이므로, 활성화 부탁드립니다.");
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            .setData(Uri.parse("package:"+getPackageName()));
                    startActivity(intent);
                } catch (ActivityNotFoundException e){
                  e.printStackTrace();
                  Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                  startActivity(intent);
                }
            }
        });

        builder.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        builder.create();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE){
            if (verifyPermission(grantResults)){

            } else{
                showRequestAgainDialog();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
