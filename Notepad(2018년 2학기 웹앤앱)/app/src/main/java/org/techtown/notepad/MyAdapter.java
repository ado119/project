package org.techtown.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext;
    int mResource;
    ArrayList<MemoItem> itemList = new ArrayList<MemoItem>();

    public MyAdapter(Context context,int resource,ArrayList<MemoItem> items){
        mContext=context;
        mResource=resource;
        itemList=items;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(mResource,parent,false);
        }
        TextView category=convertView.findViewById(R.id.category);
        category.setText(itemList.get(position).category);


        TextView memo=convertView.findViewById(R.id.memo);
        memo.setText(itemList.get(position).memo);

        TextView regdate=convertView.findViewById(R.id.regdate);
        regdate.setText(itemList.get(position).regDate);

        return convertView;
    }
}
