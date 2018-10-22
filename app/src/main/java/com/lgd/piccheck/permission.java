package com.lgd.piccheck;

import android.Manifest;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class permission extends AppCompatActivity {
    ListView listView = null;
    ListViewAdapter listViewAdapter;
    boolean[] granted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        Intent intent = getIntent();
        String[] PermissionList = intent.getStringArrayExtra("Permission");
        granted = new boolean[PermissionList.length];

        for (int i = 0; i < granted.length; i++)
            granted[i] = false;

        listView = (ListView)findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter(PermissionList);
        listView.setAdapter(listViewAdapter);
    }

    class ListViewAdapter extends BaseAdapter {
        private ArrayList<PermissionListItem> arrayList = new ArrayList<PermissionListItem>();
        private final String[] permissionList;
        public ListViewAdapter(String[] permissionList) {
            int[] color = new int[]{0xffF44336, 0xffe91e63, 0xff9c27b0, 0xff673ab7, 0xff3f51b5, 0xff2196f3, 0xff03a9f4, 0xff00bcd4, 0xff009688, 0xff4caf50, 0xff8bc34a, 0xffcddc39, 0xffffeb3b, 0xffffc107, 0xffff9800, 0xffff5722, 0xff795548};
            this.permissionList = permissionList;

            for(int i = 0; i<this.permissionList.length; i++){
                PermissionListItem listItem = new PermissionListItem();

                switch (this.permissionList[i]){
                    case Manifest.permission.CAMERA :
                        listItem.setColor(color[i%color.length]);
                        listItem.setTitle(this.permissionList[i]);
                        listItem.setDes("I need "+ this.permissionList[i]);
//                        listItem.setIcon(getResources().getDrawable(R.drawable.camara));
                        break;

                    case Manifest.permission.ACCESS_COARSE_LOCATION:
                    case Manifest.permission.ACCESS_FINE_LOCATION:
                        listItem.setColor(color[i%color.length]);
                        listItem.setTitle(this.permissionList[i]);
                        listItem.setDes("I need "+ this.permissionList[i]);
//                        listItem.setIcon(getResources().getDrawable(R.drawable.location));
                        break;

                    case Manifest.permission.WRITE_CONTACTS:
                    case Manifest.permission.READ_CONTACTS:
                    case Manifest.permission.GET_ACCOUNTS:
                        listItem.setColor(color[i%color.length]);
                        listItem.setTitle(this.permissionList[i]);
                        listItem.setDes("I need "+ this.permissionList[i]);
//                        listItem.setIcon(getResources().getDrawable(R.drawable.contact));
                        break;

                    case Manifest.permission.RECORD_AUDIO:
                        listItem.setColor(color[i%color.length]);
                        listItem.setTitle(this.permissionList[i]);
                        listItem.setDes("I need "+ this.permissionList[i]);
//                        listItem.setIcon(getResources().getDrawable(R.drawable.mic));
                        break;

                    case Manifest.permission.READ_PHONE_STATE:
                    case Manifest.permission.CALL_PHONE:
                    case Manifest.permission.READ_CALL_LOG:
                    case Manifest.permission.WRITE_CALL_LOG:
                    case Manifest.permission.ADD_VOICEMAIL:
                    case Manifest.permission.USE_SIP:
                    case Manifest.permission.PROCESS_OUTGOING_CALLS:
                        listItem.setColor(color[i%color.length]);
                        listItem.setTitle(this.permissionList[i]);
                        listItem.setDes("I need "+ this.permissionList[i]);
//                        listItem.setIcon(getResources().getDrawable(R.drawable.phone));
                        break;
                    case Manifest.permission.BODY_SENSORS:
                        listItem.setColor(color[i%color.length]);
                        listItem.setTitle(this.permissionList[i]);
                        listItem.setDes("I need "+ this.permissionList[i]);
//                        listItem.setIcon(getResources().getDrawable(R.drawable.sensor));
                        break;

                    case Manifest.permission.SEND_SMS:
                    case Manifest.permission.RECEIVE_SMS:
                    case Manifest.permission.READ_SMS:
                    case Manifest.permission.RECEIVE_WAP_PUSH:
                    case Manifest.permission.RECEIVE_MMS:
                        listItem.setColor(color[i%color.length]);
                        listItem.setTitle(this.permissionList[i]);
                        listItem.setDes("Set Des");
//                        listItem.setIcon(getResources().getDrawable(R.drawable.sms));
                        break;

                    case Manifest.permission.READ_EXTERNAL_STORAGE:
                    case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                        listItem.setColor(color[i%color.length]);
                        listItem.setTitle(this.permissionList[i]);
                        listItem.setDes("Set Des");
//                        listItem.setIcon(getResources().getDrawable(R.drawable.storage));
                        break;
                }
                arrayList.add(listItem);
            }
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

            if(convertView == null){
                LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.permission_list_view_item, parent, false);
            }

            LinearLayout ll = (LinearLayout)convertView.findViewById(R.id.layout0);

            ImageView iv = (ImageView)convertView.findViewById(R.id.imageview0);
            TextView tv = (TextView)convertView.findViewById(R.id.textview0);
            TextView dv = (TextView)convertView.findViewById(R.id.textview1);
            final Button bt = (Button)convertView.findViewById(R.id.button0);

            //퍼미션 허가시 버튼 비활성화
            int Permissioncheck = ContextCompat.checkSelfPermission(permission.this,permissionList[pos]);

            if(Permissioncheck != PackageManager.PERMISSION_DENIED || granted[pos] == true){
                bt.setEnabled(false);
                granted[pos] = true;
            }

            else{
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(permission.this, new String[]{permissionList[pos]},pos);
                    }
                });
            }


            PermissionListItem listItem = arrayList.get(pos);

            ll.setBackgroundColor(listItem.getColor());
            iv.setImageDrawable(listItem.getIcon());
            tv.setText(listItem.getTitle());
            dv.setText(listItem.getDes());

            return convertView;
        }

        public void addItem(int color, Drawable icon, String title, String des){
            PermissionListItem listItem = new PermissionListItem();

            listItem.setColor(color);
            listItem.setDes(des);
            listItem.setTitle(title);
            listItem.setIcon(icon);

            arrayList.add(listItem);
        }
    }
}
