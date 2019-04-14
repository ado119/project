package org.techtown.notepad;

//import android.app.Activity;
//import android.widget.EditText;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class InsertData extends PostRequest {
//    String defaultUrl = "http://54.180.117.57:80";
//
//    public InsertData(Activity activity) {
//        super(activity);
//    }
//
//    @Override
//    protected void onPreExecute(){
//        try{
//            url = new URL(defaultUrl + "/insert");
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }
//    }
//}
import android.app.Activity;
import android.widget.EditText;

import java.net.MalformedURLException;
import java.net.URL;



public class InsertData extends PostRequest {
    String defaultUrl = "http://54.180.117.57:3000/api";
    public InsertData(Activity activity) {
        super(activity);
    }


    @Override
    protected void onPreExecute() {

        try {
            url = new URL(defaultUrl + "/users"); //이거는 회원가입 url
            SecuData.getToken();
            //conn.setRequestProperties("x-access-token",SecuData.getToken());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
