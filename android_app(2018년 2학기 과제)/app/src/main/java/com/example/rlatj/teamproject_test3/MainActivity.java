package com.example.rlatj.teamproject_test3;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ListView mListView;

    ArrayList<Model> mList;
    RecordListAdapter mAdapter=null;
    public  static SQLiteHelper mSQLiteHelper;
    ImageView imageViewIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter(this,R.layout.row,mList);
        mListView.setAdapter(mAdapter);

        //Create database
        mSQLiteHelper =new SQLiteHelper(this,"RECORDSDB.sqlite",null,1);

        //create "Table" in db
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS RECORDS(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "place VARCHAR,people VARCHAR,latitude DOUBLE, longtitude DOUBLE,image BLOB)");
        //get all data from sqlite
        Cursor cursor=MainActivity.mSQLiteHelper.getData("SELECT * FROM RECORDS");
        mList.clear();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String place = cursor.getString(1);
            String people = cursor.getString(2);
            Double latitude= cursor.getDouble(3);
            Double longtitude=cursor.getDouble(4);
            byte[] image=cursor.getBlob(5);

            mList.add(new Model(id,place,people,latitude,longtitude,image));

        }

        mAdapter.notifyDataSetChanged();
        if(mList.size()==0){
            // if listview empty
            Toast.makeText(this,"No record",Toast.LENGTH_SHORT).show();
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String place=((Model)mAdapter.getItem(i)).getPlace();
                String people=((Model)mAdapter.getItem(i)).getPeople();
                Double latitude=((Model)mAdapter.getItem(i)).getLatitude();
                Double longitude=((Model)mAdapter.getItem(i)).getLongtitude();
                byte[] image= ((Model)mAdapter.getItem(i)).getImage();

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("place", place);
                intent.putExtra("people", people);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);

                Bitmap sendBitmap = BitmapFactory.decodeByteArray(image ,0, image.length);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                sendBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);

                startActivity(intent);

            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final CharSequence[] items = {"Update", "Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (position == 0){
                            //update
                        }
                        if (position == 1){
                            //delete
                            Cursor c = MainActivity.mSQLiteHelper.getData("SELECT id FROM RECORDS");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    private void updateRecordList() {
        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT * FROM RECORDS");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String place = cursor.getString(1);
            String people = cursor.getString(2);
            Double latitude= cursor.getDouble(3);
            Double longtitude=cursor.getDouble(4);
            byte[] image=cursor.getBlob(5);

            mList.add(new Model(id,place,people,latitude,longtitude,image));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void showDialogDelete(final Integer idRecord) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(MainActivity.this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("are you sure delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    MainActivity.mSQLiteHelper.deleteData(idRecord);
                    Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Log.e("error", e.getMessage());

                }
                updateRecordList();
            }
        });

        dialogDelete.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
            }
        });
        dialogDelete.show();
    }
//design row for the listview


    //메뉴바 추가
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //메뉴바 아이템 선택시(검색, add)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:
                Toast.makeText(getApplicationContext(), "Menu Test", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_add:
                Intent add_intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(add_intent);
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }

    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap= ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }
}
