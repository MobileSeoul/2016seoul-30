package com.justacomm.gyungjobi.gyungjobi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.justacomm.gyungjobi.gyungjobi.common.CommandMap;


public class ImportanceActivity  extends AppCompatActivity {
    private CommandMap map;
    private double calculation;
    private double defaultCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.importance);

        Intent intent = getIntent();
        map = (CommandMap) intent.getSerializableExtra("map");
        if(map.get("defaultCost") != null){
            defaultCost = (double)map.get("defaultCost");
        }
        if(map.get("calculation") != null){
            calculation = (double)map.get("calculation");
        }



    }
    public void onClick(View view){

        final CheckBox cb_a = (CheckBox) findViewById(R.id.cb_a);
        final CheckBox cb_b = (CheckBox) findViewById(R.id.cb_b);
        final CheckBox cb_c = (CheckBox) findViewById(R.id.cb_c);
        final CheckBox cb_d = (CheckBox) findViewById(R.id.cb_d);
        final CheckBox cb_e = (CheckBox) findViewById(R.id.cb_e);
        final CheckBox cb_f = (CheckBox) findViewById(R.id.cb_f);
        final CheckBox cb_g = (CheckBox) findViewById(R.id.cb_g);
        final CheckBox cb_h = (CheckBox) findViewById(R.id.cb_h);
        final CheckBox cb_i = (CheckBox) findViewById(R.id.cb_i);
        final CheckBox cb_j = (CheckBox) findViewById(R.id.cb_j);

        double vCalculation = calculation;
        if (cb_a.isChecked()) {
            vCalculation = vCalculation - (defaultCost * 0.03);
        }
        if (cb_b.isChecked()) {
            vCalculation = vCalculation - (defaultCost * 0.03);
        }
        if (cb_c.isChecked()) {
            vCalculation = vCalculation + (defaultCost * 0.03);
        }
        if (cb_d.isChecked()) {
            vCalculation = vCalculation - (defaultCost * 0.03);
        }
        if (cb_e.isChecked()) {
            vCalculation = vCalculation + (defaultCost * 0.03);
        }
        if (cb_f.isChecked()) {
            vCalculation = vCalculation - (defaultCost * 0.03);
        }
        if (cb_g.isChecked()) {
            vCalculation = vCalculation - (defaultCost * 0.03);
        }
        if (cb_h.isChecked()) {
            vCalculation = vCalculation + (defaultCost * 0.03);
        }
        if (cb_i.isChecked()) {
            vCalculation = vCalculation - (defaultCost * 0.03);
        }
        if (cb_j.isChecked()) {
            vCalculation = vCalculation - (defaultCost * 0.03);
        }

        map.put("calculation", vCalculation);

        Intent intent = new Intent (this, ResultActivity.class );
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
