package org.techtown.notepad;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

public class DeleteHero extends DeleteRequest {
    final String defaultUrl = "http://54.180.117.57:3000/api/heroes";
    final int int_hero;

    public DeleteHero(Activity activity, int int_hero) {
        super(activity);
        this.int_hero = int_hero;
    }


    @Override
    protected void onPreExecute() {

        try {
            url = new URL(defaultUrl + "/" + int_hero); //삭제 될까?
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
