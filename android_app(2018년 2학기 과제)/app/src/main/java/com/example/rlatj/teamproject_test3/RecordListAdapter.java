package com.example.rlatj.teamproject_test3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Model> recordList;

    public RecordListAdapter(Context context, int layout, ArrayList<Model> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
    }

    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private  class ViewHolder{
        ImageView imageView;
        TextView TxtPlace, TxtPeople, TxtLa,TxtLong;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row=  view;
        ViewHolder holder= new ViewHolder();

        if(row==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.TxtPlace=row.findViewById(R.id.txtPlace);
            holder.TxtPeople=row.findViewById(R.id.txtPeople);
            holder.TxtLa=row.findViewById(R.id.txtLa);
            holder.TxtLong=row.findViewById(R.id.txtLong);
            holder.imageView=row.findViewById(R.id.imageIcon);

            row.setTag(holder);
        }
        else{
            holder=(ViewHolder)row.getTag();

        }

        Model model=recordList.get(i);

        holder.TxtPlace.setText(model.getPlace());
        holder.TxtPeople.setText(model.getPeople());
        holder.TxtLa.setText(model.getLatitude().toString());
        holder.TxtLong.setText(model.getLongtitude().toString());

        //이미지값 넣어주는거 일단은 생략
        byte[] recordImage= model.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(recordImage,0,recordImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
