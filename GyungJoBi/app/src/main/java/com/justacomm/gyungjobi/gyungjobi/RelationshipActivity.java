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

public class RelationshipActivity extends AppCompatActivity {

    private CommandMap map;
    private double defaultCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relationship);

        Intent intent = getIntent();
        map = (CommandMap) intent.getSerializableExtra("map");
        defaultCost = (double)map.get("defaultCost");

    }

    public void onClick(View view){
        Toast toast = Toast.makeText(this, "모든 질문에 답을 선택하여 주시기 바랍니다.", Toast.LENGTH_SHORT);
        final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);


        int id_a = rg_a.getCheckedRadioButtonId();


        if(id_a == -1 ){

            toast.show();
            return;
        }

        RadioButton rb_a = (RadioButton) findViewById(id_a);

        map.put("relationship",rb_a.getText().toString());
        map.put("relationshipCategory",rb_a.getHint().toString());

        double calculation = defaultCost;
        switch(id_a) {
            case R.id.rb_a_1:
                calculation += 5000;
                break;
            case R.id.rb_a_2:
                calculation += 5000;
                break;
            case R.id.rb_a_3:
                calculation += 5000;
                break;
            case R.id.rb_a_4:
                calculation += 5000;
                break;
            case R.id.rb_a_5:
                calculation += 5000;
                break;
            case R.id.rb_a_6:
                calculation += 5000;
                break;
            case R.id.rb_a_18:
                calculation += -5000;
                break;
            case R.id.rb_a_19:
                calculation += -5000;
                break;
        }

        map.put("defaultCost", calculation);

        Intent intent = new Intent (this, EventInfoActivity.class );
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