package space.dorzhu.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterBank extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Bankomat> objects;

    AdapterBank(Context context, ArrayList<Bankomat> bankomats) {
        ctx = context;
        objects = bankomats;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.activity_list_row, parent, false);
        }
        Bankomat p = getBankomats(position);
        ((TextView) view.findViewById(R.id.litv11)).setText(p.getAddress());
        ((TextView) view.findViewById(R.id.litv12)).setText(p.getType());
        ((TextView) view.findViewById(R.id.litv13)).setText(p.getWork());
        ((TextView) view.findViewById(R.id.litv14)).setText(p.getMon());
        return view;
    }

    Bankomat getBankomats(int position) {
        return ((Bankomat) getItem(position));
    }

}
