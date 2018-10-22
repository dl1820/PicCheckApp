package com.lgd.piccheck;

import android.graphics.drawable.Drawable;

public class PermissionListItem {
    private int color;
    private Drawable icon;
    private String title;
    private String des;

    public void setColor(int color){
        this.color = color;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getDes() {
        return des;
    }

    public String getTitle() {
        return title;
    }

    public int getColor() {
        return color;
    }

}
