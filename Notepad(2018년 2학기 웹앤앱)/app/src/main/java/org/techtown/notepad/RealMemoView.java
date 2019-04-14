package org.techtown.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class RealMemoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_memo_view);

        final EditText view = (EditText)findViewById(R.id.real_memo_test);


        Button save_bt = findViewById(R.id.save_hero);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "메모를 입력하시요.", Toast.LENGTH_SHORT).show();
                    view.requestFocus();
                    return;
                }
                JSONObject postDataParam = new JSONObject();
                try {
                    postDataParam.put("name", view.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new InsertHero(RealMemoView.this).execute(postDataParam);

                Intent intent_save = new Intent(getApplicationContext(), RealMemoList.class);
                startActivity(intent_save);
            }
        });


    }
}
