package org.techtown.notepad;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONException;
import org.json.JSONObject;

public class LoginForm extends AppCompatActivity {

    final String TAG = "error";
    String defaultUrl = "https://54.180.117.57:80";
    String sb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        final EditText Login_ID = (EditText)findViewById(R.id.Login_ID);
        final EditText Login_Pass = (EditText)findViewById(R.id.Login_Pass);



        Button real_login = (Button)findViewById(R.id.real_login);
        real_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                JSONObject postDataParam = new JSONObject();
                try {
                    postDataParam.put("username", Login_ID.getText().toString());
                    postDataParam.put("password", Login_Pass.getText().toString());
                } catch (JSONException e){
                    Log.e(TAG, "JSONEXception");
                }
                new InsertData_2(LoginForm.this).execute(postDataParam);
                //Log.e("----->",executeData.toString());
                //핸들러 해보기


            }
        });

        Button btn = (Button)findViewById(R.id.go_Join);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinForm.class);
                startActivity(intent);
            }
        });

        Button btn_2 = (Button)findViewById(R.id.go_Login);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_2= new Intent(getApplicationContext(), MemoList.class);
                startActivity(intent_2);
            }
        });
    }
}
