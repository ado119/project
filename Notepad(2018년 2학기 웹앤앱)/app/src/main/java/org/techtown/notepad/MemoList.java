package org.techtown.notepad;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MemoList extends AppCompatActivity {

    public static final int REQUEST_CODE_INSERT = 1000;
    private MemoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        ListView listView=findViewById(R.id.list_view);

        Cursor cursor = getMemoCursor();
        adapter = new MemoAdapter(this, cursor);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MemoList.this, MemoView.class);

                Cursor cursor = (Cursor) adapter.getItem(position);
                String title = cursor.getString(cursor.getColumnIndexOrThrow(memo_warehouse.MemoEntry.COLUMN_TITLE));
                String contents = cursor.getString(cursor.getColumnIndexOrThrow(memo_warehouse.MemoEntry.COLUMN_CONTENTS));

                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("contents", contents);

                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });

        FloatingActionButton f_btn = (FloatingActionButton)findViewById(R.id.fb);
        f_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_new = new Intent(getApplicationContext(), MemoView.class);
                startActivityForResult(intent_new, REQUEST_CODE_INSERT);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final long deleteId = id;
                AlertDialog.Builder builder = new AlertDialog.Builder(MemoList.this);
                builder.setTitle("메모 삭제");
                builder.setMessage("메모를 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = MemoDBHelper.getsInstance(MemoList.this).getWritableDatabase();
                        int deletedCount = db.delete(memo_warehouse.MemoEntry.TABLE_NAME,
                                memo_warehouse.MemoEntry._ID + " = " + deleteId, null);
                        if (deletedCount == 0) {
                            Toast.makeText(MemoList.this, "삭제에 문제가 발생", Toast.LENGTH_SHORT).show();
                        }else{
                            adapter.swapCursor(getMemoCursor());
                            Toast.makeText(MemoList.this, "메모가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.local_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.local_logout_action:
                Intent logout_intent = new Intent(getApplicationContext(), LoginForm.class);
                startActivity(logout_intent);
                return true;
            case R.id.local_touch_memo:
                Intent touch_intent = new Intent(getApplicationContext(), Touch_memo.class);
                startActivity(touch_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private Cursor getMemoCursor() {
        MemoDBHelper dbHelper = MemoDBHelper.getsInstance(getApplicationContext());
        Cursor cursor = dbHelper.getReadableDatabase()
                .query(memo_warehouse.MemoEntry.TABLE_NAME,
                        null, null, null, null, null, memo_warehouse.MemoEntry._ID + " DESC");
        return cursor;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_INSERT && requestCode == RESULT_OK){
            adapter.swapCursor(getMemoCursor());
        }
    }

    private  static  class MemoAdapter extends CursorAdapter{

        public MemoAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView titleText = view.findViewById(android.R.id.text1);
            titleText.setText(cursor.getString(cursor.getColumnIndexOrThrow(memo_warehouse.MemoEntry.COLUMN_TITLE)));
        }


    }


}
