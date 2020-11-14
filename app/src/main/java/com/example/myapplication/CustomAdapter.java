package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//import static androidx.core.graphics.drawable.IconCompat.getResources;

public class CustomAdapter extends BaseAdapter {//адаптер для курса валют

    Context ctx;
    LayoutInflater layoutInflater;
    ArrayList<Course> objects;

   public CustomAdapter(Context context, ArrayList<Course> courses){
        ctx = context;
        objects = courses;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//аналогично Bank
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.custom_item, parent, false);
        }
        Course c = getCourse(position);



        ((TextView) view.findViewById(R.id.charCode)).setText(c.getCharCode());
        ((TextView) view.findViewById(R.id.name)).setText(c.getName());
        ((TextView) view.findViewById(R.id.buy)).setText(c.getValue());
        ((TextView) view.findViewById(R.id.sell)).setText(c.getCell());
        int res = getResourseID(ctx, "_"+c.getNumCode(), "drawable", ctx.getPackageName());
        ((ImageView) view.findViewById(R.id.countryIcon)).setImageResource(res);

        return view;
    }
    Course getCourse(int position){
        return ((Course) getItem(position));
    }
    public static int getResourseID(Context context, String pVariableName, String pResourcename, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resourse ID", e);
        }
    }
}
