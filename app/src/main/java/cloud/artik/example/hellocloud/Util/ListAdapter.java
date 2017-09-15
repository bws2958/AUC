package cloud.artik.example.hellocloud.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cloud.artik.example.hellocloud.R;

/**
 * Created by HMS on 2017-08-27.
 */

public class ListAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<CustomList> list;
    LayoutInflater inflater;
    public ListAdapter(Context context, int layout, ArrayList<CustomList> list){
        this.context = context;
        this.layout = layout;
        this.list = list;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return list.size();
    }

    public Object getItem(int position){
        return position;
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View view, ViewGroup parent){
        if(view == null){
            view = inflater.inflate(layout, null);
        }
        TextView textView = (TextView)view.findViewById(R.id.main_after_left_textView1);
        CustomList customList = list.get(position);
        textView.setText(customList.getContent1());
        return view;
    }
}
