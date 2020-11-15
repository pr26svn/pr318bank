package space.dorzhu.test;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Currency;
import java.util.zip.Inflater;

public class KAdapter extends BaseAdapter {

    //класс который поможет мне отобоазить валюты в LIstView

    //объявление переменных
    Context ctx;
    LayoutInflater layoutInflater;
    ArrayList<Course> objects;



    public KAdapter(Context context, ArrayList<Course> courses){
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


    //вывод на экран
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.activity_custom_list, parent, false);
        }
        Course c = getCourse(position);


        //Заполняем данные
        ((TextView) view.findViewById(R.id.charCode)).setText(c.getCharCode());
        ((TextView) view.findViewById(R.id.name)).setText(c.getName());
        ((TextView) view.findViewById(R.id.buy)).setText(c.getValue());
        ((TextView)view.findViewById(R.id.sell)).setText(c.getValue());



        return view;
    }
    Course getCourse(int position){
        return ((Course) getItem(position));
    }

}
