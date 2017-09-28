package com.justacomm.gyungjobi.gyungjobi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.justacomm.gyungjobi.gyungjobi.common.CommandMap;

public class EventInfoActivity extends AppCompatActivity {
    private CommandMap map;
    private double calculation = 0;
    private double defaultCost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_info);

        Intent intent = getIntent();
        map = (CommandMap) intent.getSerializableExtra("map");
        if(map.get("calculation") != null){
            calculation = (double)map.get("calculation");
        }
        if(map.get("defaultCost") != null){
            defaultCost = (double)map.get("defaultCost");
        }


        TextView tv_b = (TextView) findViewById(R.id.tv_b);
        //tv_b.setText((String) map.get("relationship") +" "+ tv_b.getText());


        final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
        final RadioGroup rg_b = (RadioGroup)findViewById(R.id.rg_b);
        final TextView tv_d = (TextView)findViewById(R.id.tv_d);

        rg_a.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id_a = rg_a.getCheckedRadioButtonId();
                int id_b = rg_b.getCheckedRadioButtonId();

                if(id_a != -1 && id_b != -1){
                    RadioButton rb_a = (RadioButton) findViewById(id_a);
                    RadioButton rb_b = (RadioButton) findViewById(id_b);

                    tv_d.setText((String)map.get("relationshipCategory")+" "+ rb_a.getText().toString()+"의 "+ rb_b.getText().toString() + "\n경조사비의 대상자: "+ (String)map.get("relationship") + "(" + (String)map.get("relationshipCategory")+ ")" + "\n경조사의 당사자: "+ (String)map.get("relationshipCategory")+" "+ rb_a.getText().toString());
                    map.put("eventInfo",tv_d.getText().toString());
                }
            }
        });


        rg_b.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id_a = rg_a.getCheckedRadioButtonId();
                int id_b = rg_b.getCheckedRadioButtonId();

                if(id_a != -1 && id_b != -1){
                    RadioButton rb_a = (RadioButton) findViewById(id_a);
                    RadioButton rb_b = (RadioButton) findViewById(id_b);

                    tv_d.setText((String)map.get("relationshipCategory")+" "+ rb_a.getText().toString()+"의 "+ rb_b.getText().toString() + "\n경조사비의 대상자: "+ (String)map.get("relationship") + "(" + (String)map.get("relationshipCategory")+ ")" + "\n경조사의 당사자: "+ (String)map.get("relationshipCategory")+" "+ rb_a.getText().toString());
                    map.put("eventInfo",tv_d.getText().toString());
                }
            }
        });
    }

    public void onClick(View view){
        Toast toast = Toast.makeText(this, "모든 질문에 답을 선택하여 주시기 바랍니다.", Toast.LENGTH_SHORT);

        RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
        RadioGroup rg_b = (RadioGroup)findViewById(R.id.rg_b);
        int id_a = rg_a.getCheckedRadioButtonId();
        int id_b = rg_b.getCheckedRadioButtonId();
        if(id_a == -1 || id_b == -1){
            toast.show();
            return;
        }

        RadioButton rb_a = (RadioButton) findViewById(id_a);
        map.put("relationshipPerson",rb_a.getText().toString());
        RadioButton rb_b = (RadioButton) findViewById(id_b);
        map.put("event",rb_b.getText().toString());
        double vCalculation = defaultCost;
        switch(id_b) {
            case R.id.rb_b_3:
                vCalculation += -5000;
                break;
            case R.id.rb_b_4:
                vCalculation += -5000;
                break;
            case R.id.rb_b_5:
                vCalculation += -5000;
                break;
            case R.id.rb_b_6:
                vCalculation += -10000;
                break;
        }
        map.put("defaultCost", vCalculation);

        Intent intent = new Intent (this, ClosenessLevelActivity.class );
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
