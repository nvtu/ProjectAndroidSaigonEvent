package com.example.tuvanninh.hcmcevent;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tu Van Ninh on 03/07/2016.
 */
public class CommonAdapter extends ArrayAdapter<Info>{

    private Context context;
    int resource;

    public CommonAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(resource, null);
        }

        Info info = getItem(position);
        if (info != null){
            TextView txView = (TextView) convertView.findViewById(R.id.txtView);
            ImageView imgView = (ImageView) convertView.findViewById(R.id.imgView);
            txView.setText(info.getName());
            imgView.setImageResource(info.getBmpId());
        }

        return convertView;
    }
}
