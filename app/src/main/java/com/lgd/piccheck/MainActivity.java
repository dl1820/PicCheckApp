package com.lgd.piccheck;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissionArray = new String[]{Manifest.permission.CAMERA};

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            for (int i = 0; i < permissionArray.length; i++){
                int permissionCheck = ContextCompat.checkSelfPermission(this, permissionArray[i]);

                if (permissionCheck == PackageManager.PERMISSION_DENIED){
                    Intent intent = new Intent(this, permission.class);
                    intent.putExtra("permission", permissionArray);
                    startActivity(intent);
                    break;
                }
            }
        }
    }
}
