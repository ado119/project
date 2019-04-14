package org.techtown.notepad;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

public class InsertHero extends PostRequest {
    final String defaultUrl = "http://54.180.117.57:3000/api/heroes";

    public InsertHero(Activity activity) {
        super(activity);
    }


    @Override
    protected void onPreExecute() {

        try {
            url = new URL(defaultUrl); //메모 추가...
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
