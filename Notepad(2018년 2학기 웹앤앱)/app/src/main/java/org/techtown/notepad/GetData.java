package org.techtown.notepad;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetData extends GetRequest {
    final static String defaultUrl = "http://54.180.117.57:3000/api";
    public GetData(Activity activity){
        super(activity);
    }

    @Override
    protected void onPreExecute(){
        try{
            url = new URL(defaultUrl+"/heroes");
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onPostExecute(String jsonString){
        if(jsonString == null)
            return;
        ArrayList<hero> arrayList = getArrayListFromJSONString(jsonString);

        ArrayAdapter adapter = new ArrayAdapter(activity,
                android.R.layout.simple_list_item_1,
                arrayList.toArray());
        ListView txtList = activity.findViewById(R.id.txtlist);
        txtList.setAdapter(adapter);
        txtList.setDividerHeight(10);

    }



    protected ArrayList<hero> getArrayListFromJSONString(String jsonString) {
        ArrayList<hero> output = new ArrayList<>();
        try {

            JSONObject root = new JSONObject(jsonString);
            JSONArray heroes = root.getJSONArray("data");


            for (int i = 0; i < heroes.length(); i++) {
                JSONObject jsonObject = (JSONObject) heroes.getJSONObject(i);
                //JSONObject data = (JSONObject) jsonObject.getJSONObject("data");
                hero hero_1 = new hero(/*jsonObject.getString("_id"),*/
                        jsonObject.getString("id"),
                        jsonObject.getString("name"));

                output.add(hero_1);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;
    }
}