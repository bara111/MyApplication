package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class Myadapter extends ArrayAdapter<Todo> {
    public Myadapter(Context context, List<Todo> object){
        super(context,0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_layout,parent,false);
        }

        TextView titleTextView = convertView.findViewById(R.id.task);
        ImageView imageView=convertView.findViewById(R.id.status);

        Drawable myDrawable =convertView.getResources().getDrawable(R.drawable.checked);
        Drawable myDrawable1 =convertView.getResources().getDrawable(R.drawable.cancel);

        Todo mission = getItem(position);
if(mission.isCompleted()){
    imageView.setImageDrawable(myDrawable);}
else {
    imageView.setImageDrawable(myDrawable1);}


        titleTextView.setText(mission.getName());



        return convertView;
    }

}