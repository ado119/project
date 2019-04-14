package org.techtown.notepad;

import android.content.Intent;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinForm extends AppCompatActivity {

    final static String TAG = "";
    final static String defaultUrl = "http://54.180.117.57:3000/api";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_form);
        final EditText id_new = (EditText) findViewById(R.id.new_id);
        final EditText pass_new = (EditText)findViewById(R.id.new_pass);
        final EditText pass_new_confirm = (EditText)findViewById(R.id.new_pass_confirm);
        final EditText mail_new = (EditText)findViewById(R.id.new_email);
        final EditText name_new = (EditText)findViewById(R.id.new_name);

        Button btn = (Button)findViewById(R.id.cancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginForm.class);
                startActivity(intent);
            }
        });
        Button btn_join = (Button)findViewById(R.id.new_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id_new.getText().toString().length()==0){
                    Toast.makeText(JoinForm.this, "아이디를 입력하시오.", Toast.LENGTH_SHORT).show();
                    id_new.requestFocus();
                    return;
                }
                if(pass_new.getText().toString().length()==0){
                    Toast.makeText(JoinForm.this, "비밀번호를 입력하시오.", Toast.LENGTH_SHORT).show();
                    pass_new.requestFocus();
                    return;
                }
                if(pass_new.getText().toString().length() != pass_new_confirm.getText().toString().length()){
                    Toast.makeText(JoinForm.this, "비밀번호와 비밀번호 확인은 동일해야합니다.", Toast.LENGTH_SHORT).show();
                    pass_new_confirm.requestFocus();
                    return;
                }

//                if(mail_new.getText().toString().length()==0){
//                    Toast.makeText(JoinForm.this, "이메일을 입력하시오.", Toast.LENGTH_SHORT).show();
//                    mail_new.requestFocus();
//                    return;
//                }
                if(name_new.getText().toString().length()==0){
                    Toast.makeText(JoinForm.this, "이름을 입력하시오.", Toast.LENGTH_SHORT).show();
                    name_new.requestFocus();
                    return;
                }
                JSONObject postDataParam = new JSONObject();
                try {
                    postDataParam.put("username", id_new.getText().toString());
                    postDataParam.put("password", pass_new.getText().toString());
                    postDataParam.put("email", mail_new.getText().toString());
                    postDataParam.put("name", name_new.getText().toString());
                    postDataParam.put("passwordConfirmation", pass_new_confirm.getText().toString());

                } catch (JSONException e){
                    Log.e(TAG, "JSONEXception");
                }
                new InsertData(JoinForm.this).execute(postDataParam);
//                new GetData(JoinForm.this).execute();
                Intent intent_2 = new Intent(getApplicationContext(), LoginForm.class);
                startActivity(intent_2);
            }
        });
    }
}
