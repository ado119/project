package org.techtown.notepad;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MemoView extends AppCompatActivity {
    private EditText title_name;
    private EditText memo_content;
    private long mMemoId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_view);
        title_name=findViewById(R.id.title_test);
        memo_content=findViewById(R.id.memo_test);
        Intent intent = getIntent();

        if(intent != null){
            mMemoId = intent.getLongExtra("id", -1);
            String title = intent.getStringExtra("title");
            String contents = intent.getStringExtra("contents");

            title_name.setText(title);
            memo_content.setText(contents);
        }


    }
    public void onBackPressed(){
            String title_back = title_name.getText().toString();
            String contents_back = memo_content.getText().toString();

            ContentValues contentValues = new ContentValues();
            contentValues.put(memo_warehouse.MemoEntry.COLUMN_TITLE, title_back);
            contentValues.put(memo_warehouse.MemoEntry.COLUMN_CONTENTS, contents_back);

            SQLiteDatabase db = MemoDBHelper.getsInstance(this).getWritableDatabase();
            if (mMemoId == -1) {
                long newRowId = db.insert(memo_warehouse.MemoEntry.TABLE_NAME,
                        null,
                        contentValues);
                if (newRowId == -1) {
                    Toast.makeText(this, "저장에 문제가 발생했습니다.", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent_list = new Intent(MemoView.this, MemoList.class);
                    startActivity(intent_list);
                    setResult(RESULT_OK);
                }

            }else{
                int count = db.update(memo_warehouse.MemoEntry.TABLE_NAME, contentValues,
                        memo_warehouse.MemoEntry._ID + " = " + mMemoId, null);
                if (count == 0){
                    Toast.makeText(this, "수정에 문제 발생", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "메모가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                }
            }



            super.onBackPressed();
    }
}
