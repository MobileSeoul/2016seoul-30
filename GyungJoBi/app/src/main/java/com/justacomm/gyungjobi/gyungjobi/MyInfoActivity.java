package com.justacomm.gyungjobi.gyungjobi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.justacomm.gyungjobi.gyungjobi.common.CommandMap;

public class MyInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info);


    }
    public void onClick(View view){
        Toast toast = Toast.makeText(this, "모든 질문에 답을 선택하여 주시기 바랍니다.", Toast.LENGTH_SHORT);
        CommandMap map = new CommandMap();

        final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
        final RadioGroup rg_b = (RadioGroup)findViewById(R.id.rg_b);

        int id_a = rg_a.getCheckedRadioButtonId();
        int id_b = rg_b.getCheckedRadioButtonId();

        if(id_a == -1 || id_b == -1){

            toast.show();
            return;
        }


        RadioButton rb_a = (RadioButton) findViewById(id_a);
        RadioButton rb_b = (RadioButton) findViewById(id_b);

        double defaultCost = 0;

        switch(id_b) {
            case R.id.rb_b_1:
                defaultCost = 20000;
                break;
            case R.id.rb_b_2:
                defaultCost = 60000;
                break;
            case R.id.rb_b_3:
                defaultCost = 70000;
                break;
            case R.id.rb_b_4:
                defaultCost = 80000;
                break;
            case R.id.rb_b_5:
                defaultCost = 100000;
                break;
        }

        if(id_a == R.id.rb_a_2){
            defaultCost = defaultCost * 0.5;
        }

        map.put("defaultCost", defaultCost);

        Intent intent = new Intent (this, RelationshipActivity.class );
        intent.putExtra("map", map);


        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main) {

            Intent intent = new Intent (this, MainActivity.class );
            startActivity(intent);

        }else if (id == R.id.saved) {

            Intent intent = new Intent (this, SavedEventActivity.class );
            startActivity(intent);

        }else if (id == R.id.finish) {

            this.finishAffinity();

        }

        return super.onOptionsItemSelected(item);
    }
}