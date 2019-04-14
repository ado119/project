package org.techtown.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Realmemoview2 extends AppCompatActivity {
    final String hero_id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realmemoview2);
        final Intent intent = getIntent();

        final EditText view = (EditText)findViewById(R.id.real_memo_test_2);

        String memo_view = intent.getStringExtra("name");
        final String hero_id =intent.getStringExtra("hero_id");
        final int int_hero = Integer.parseInt(hero_id);

        view.setText(memo_view);

        Button delete_bt = (Button)findViewById(R.id.Delete_hero);
        delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteHero(Realmemoview2.this, int_hero).execute();

            }
        });
    }
}
