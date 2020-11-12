package space.dorzhu.test;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.zip.Inflater;

public class Adapter extends BaseAdapter {
    Context ctx;
    LayoutInflater inflater;
    ArrayList<Currency> objects;

    Adapter(Context context,ArrayList<Currency> product){
        ctx=context;
        objects= product;
        inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }
    //по позиции элемент
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.activity_custom_list, parent, false);
        }
        Course p = getCourse(position);

        ((TextView) view.findViewById(R.id.litv1)).setText(p.getCharCode());
        ((TextView) view.findViewById(R.id.litv2)).setText(p.getName());
        ((TextView) view.findViewById(R.id.litv3)).setText(p.getNominal());
        ((TextView) view.findViewById(R.id.litv4)).setText(p.getNominal());

        
        return view;
    }
    ArrayList<Currency> getBox(){
        ArrayList<Currency>box=new ArrayList<Currency>();
        for (Currency p : objects) {

        }
        return box;
    }

    private Course getCourse(int position) {
        return ((Course)getItem(position));
    }

}
