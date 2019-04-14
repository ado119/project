package org.techtown.notepad;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class InsertData_2 extends PostRequest {
    String defaultUrl = "http://54.180.117.57:3000/api";

    public InsertData_2(Activity activity) {
        super(activity);
    }


    @Override
    protected void onPreExecute() {

        try {
            url = new URL(defaultUrl + "/auth/login"); //이거는 로그인 url
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

/*    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();

        Intent real_intent = new Intent(activity, RealMemoList.class);
        activity.startActivity(real_intent);
        //if문
    }*/

}
