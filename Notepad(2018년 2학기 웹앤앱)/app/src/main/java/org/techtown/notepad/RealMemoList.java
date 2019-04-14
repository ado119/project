package org.techtown.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RealMemoList extends AppCompatActivity {
    private ArrayList<hero> arrayList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_memo_list);
        new GetData(RealMemoList.this).execute();
/*        Button test_button = (Button)findViewById(R.id.test_button);
        test_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new GetData(RealMemoList.this).execute();
            }
        });*/
        arrayList = new ArrayList<hero>();
 /*       ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                arrayList.toArray());*/
        ListView real_list = (ListView)findViewById(R.id.txtlist);
//        real_list.setAdapter(adapter);

       real_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               Adapter adapter = adapterView.getAdapter();
               String name = ((hero)adapter.getItem(position)).getName();
               String hero_id = ((hero)adapter.getItem(position)).getId();
               Intent intent_view = new Intent(getApplicationContext(), Realmemoview2.class);

               intent_view.putExtra("name", name);
               intent_view.putExtra("hero_id", hero_id);
               startActivity(intent_view);
           }
       });

      /* real_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               return false;
           }
       });*/

        FloatingActionButton real_fb=(FloatingActionButton)findViewById(R.id.real_fb);
        real_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RealMemoView.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.logout_action:
                Intent logout_intent = new Intent(getApplicationContext(), LoginForm.class);
                startActivity(logout_intent);
                return true;
            case R.id.change_action:
                Intent modify_intent = new Intent(getApplicationContext(), Modify_form.class);
                startActivity(modify_intent);
                return true;
            case R.id.real_touch_memo:
                Intent touch_intent = new Intent(getApplicationContext(), Touch_memo.class);
                startActivity(touch_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
