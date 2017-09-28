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


public class ClosenessLevelActivity  extends AppCompatActivity {
    private CommandMap map;
    private double calculation;
    private double defaultCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        map = (CommandMap) intent.getSerializableExtra("map");
        if(map.get("defaultCost") != null){
            defaultCost = (double)map.get("defaultCost");
        }
        if(map.get("calculation") != null){
            calculation = (double)map.get("calculation");
        }




        String relation = (String)map.get("relationship");
        if( relation.equals("(증)조부모")
            || relation.equals("배우자")
            || relation.equals("부모")
            || relation.equals("형제자매")
            || relation.equals("자녀")
            || relation.equals("(증)손주")){

            map.put("closenessLevel", 1);
            setContentView(R.layout.closeness_level);

            final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
            final RadioGroup rg_b_1 = (RadioGroup)findViewById(R.id.rg_b_1);
            final RadioGroup rg_b_2 = (RadioGroup)findViewById(R.id.rg_b_2);
            rg_a.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int id_a = rg_a.getCheckedRadioButtonId();


                    if(id_a != -1 ){
                        RadioButton rb_a = (RadioButton) findViewById(id_a);
                        if(rb_a.getText().equals("예")){
                            rg_b_1.clearCheck();
                            rg_b_2.setVisibility(View.GONE);
                            rg_b_1.setVisibility(View.VISIBLE);

                        }else{
                            rg_b_2.clearCheck();
                            rg_b_1.setVisibility(View.GONE);
                            rg_b_2.setVisibility(View.VISIBLE);

                        }
                    }
                }
            });


        }else if( relation.equals("사촌 이내")
                || relation.equals("기타")){

            map.put("closenessLevel", 2);
            setContentView(R.layout.closeness_level_2);

            final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
            final RadioGroup rg_b_1 = (RadioGroup)findViewById(R.id.rg_b_1);
            final RadioGroup rg_b_2 = (RadioGroup)findViewById(R.id.rg_b_2);
            rg_a.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int id_a = rg_a.getCheckedRadioButtonId();


                    if(id_a != -1 ){
                        RadioButton rb_a = (RadioButton) findViewById(id_a);
                        if(rb_a.getText().equals("예")){
                            rg_b_1.clearCheck();
                            rg_b_2.setVisibility(View.GONE);
                            rg_b_1.setVisibility(View.VISIBLE);

                        }else{
                            rg_b_2.clearCheck();
                            rg_b_1.setVisibility(View.GONE);
                            rg_b_2.setVisibility(View.VISIBLE);

                        }
                    }
                }
            });

        }else if( relation.equals("학교")
                || relation.equals("이웃")
                || relation.equals("종교")
                || relation.equals("사회")
                || relation.equals("기타")){

            map.put("closenessLevel", 3);
            setContentView(R.layout.closeness_level_3);

        }else if( relation.equals("애인")){

            map.put("closenessLevel", 4);
            setContentView(R.layout.closeness_level_4);

        }else if(relation.equals("동기")
                || relation.equals("선배")
                || relation.equals("후배")){

            map.put("closenessLevel", 5);
            setContentView(R.layout.closeness_level_5);

        }else if(relation.equals("거래업체")
                || relation.equals("고객")){

            map.put("closenessLevel", 6);
            setContentView(R.layout.closeness_level_6);

        }else if(relation.equals("스승")
                || relation.equals("제자")
                || relation.equals("동네")
                || relation.equals("종교")
                || relation.equals("기타")){

            map.put("closenessLevel", 7);
            setContentView(R.layout.closeness_level_7);

        }


    }

    public void onClick(View view){
        Toast toast = Toast.makeText(this, "모든 질문에 답을 선택하여 주시기 바랍니다.", Toast.LENGTH_SHORT);
        int closenessLevel = (int)map.get("closenessLevel");

        if(closenessLevel == 1){

            final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
            final RadioGroup rg_b_1 = (RadioGroup)findViewById(R.id.rg_b_1);
            final RadioGroup rg_b_2 = (RadioGroup)findViewById(R.id.rg_b_2);

            int id_a = rg_a.getCheckedRadioButtonId();

            if(id_a != -1 ){
                RadioButton rb_a = (RadioButton) findViewById(id_a);
                if(rb_a.getText().equals("예")){
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:
                                vCalculation = vCalculation - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.04);
                                break;
                            case R.id.rb_b_1_3:
                                vCalculation = vCalculation - (defaultCost * 0.03);
                                break;
                            case R.id.rb_b_1_4:
                                vCalculation = vCalculation - (defaultCost * 0.02);
                                break;
                            case R.id.rb_b_1_5:
                                vCalculation = vCalculation - (defaultCost * 0.01);
                                break;
                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }


                }else{
                    int id_b_2 = rg_b_2.getCheckedRadioButtonId();
                    if(id_b_2 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_2) {
                            case R.id.rb_b_2_1:
                                vCalculation = vCalculation - (defaultCost * 0.05) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_2_2:
                                vCalculation = vCalculation - (defaultCost * 0.04) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_2_3:
                                vCalculation = vCalculation - (defaultCost * 0.03) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_2_4:
                                vCalculation = vCalculation - (defaultCost * 0.02) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_2_5:
                                vCalculation = vCalculation - (defaultCost * 0.01) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_2_6:
                                vCalculation = vCalculation - (defaultCost * 0.05);
                                break;
                        }
                        map.put("calculation", vCalculation);
                    }else{
                        toast.show();
                        return;
                    }
                }
            }else{
                toast.show();
                return;
            }

        }else if(closenessLevel == 2){

            final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
            final RadioGroup rg_b_1 = (RadioGroup)findViewById(R.id.rg_b_1);
            final RadioGroup rg_b_2 = (RadioGroup)findViewById(R.id.rg_b_2);

            int id_a = rg_a.getCheckedRadioButtonId();

            if(id_a != -1 ){
                RadioButton rb_a = (RadioButton) findViewById(id_a);
                if(rb_a.getText().equals("예")){
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:

                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.03);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }


                }else{
                    int id_b_2 = rg_b_2.getCheckedRadioButtonId();
                    if(id_b_2 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_2) {
                            case R.id.rb_b_2_1:
                                vCalculation = vCalculation - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_2_2:
                                vCalculation = vCalculation - (defaultCost * 0.03) - (defaultCost * 0.05);
                                break;

                        }
                        map.put("calculation", vCalculation);
                    }else{
                        toast.show();
                        return;
                    }
                }
            }else{
                toast.show();
                return;
            }

        }else if(closenessLevel == 3){

            final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
            final RadioGroup rg_b_1 = (RadioGroup)findViewById(R.id.rg_b_1);


            int id_a = rg_a.getCheckedRadioButtonId();

            if(id_a != -1 ){
                RadioButton rb_a = (RadioButton) findViewById(id_a);
                if(rb_a.getText().equals("예")){
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:
                                vCalculation = vCalculation - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.04);
                                break;
                            case R.id.rb_b_1_3:
                                vCalculation = vCalculation - (defaultCost * 0.03);
                                break;
                            case R.id.rb_b_1_4:
                                vCalculation = vCalculation - (defaultCost * 0.02);
                                break;
                            case R.id.rb_b_1_5:
                                vCalculation = vCalculation - (defaultCost * 0.01);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }


                }else{
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:
                                vCalculation = vCalculation - (defaultCost * 0.05) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.04) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_3:
                                vCalculation = vCalculation - (defaultCost * 0.03) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_4:
                                vCalculation = vCalculation - (defaultCost * 0.02) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_5:
                                vCalculation = vCalculation - (defaultCost * 0.01) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_6:
                                vCalculation = vCalculation - (defaultCost * 0.05);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }
                }
            }else{
                toast.show();
                return;
            }

        }else if(closenessLevel == 4){

            final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
            final RadioGroup rg_b_1 = (RadioGroup)findViewById(R.id.rg_b_1);


            int id_a = rg_a.getCheckedRadioButtonId();

            if(id_a != -1 ){
                RadioButton rb_a = (RadioButton) findViewById(id_a);
                if(rb_a.getText().equals("예")){
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:

                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.03);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }


                }else{
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:
                                vCalculation = vCalculation - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.05) - (defaultCost * 0.03);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }
                }
            }else{
                toast.show();
                return;
            }

        }else if(closenessLevel == 5){

            final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
            final RadioGroup rg_b_1 = (RadioGroup)findViewById(R.id.rg_b_1);


            int id_a = rg_a.getCheckedRadioButtonId();

            if(id_a != -1 ){
                RadioButton rb_a = (RadioButton) findViewById(id_a);
                if(rb_a.getText().equals("예")){
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:

                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.03);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }


                }else{
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:
                                vCalculation = vCalculation - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.05) - (defaultCost * 0.03);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }
                }
            }else{
                toast.show();
                return;
            }

        }else if(closenessLevel == 6){

            final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
            final RadioGroup rg_b_1 = (RadioGroup)findViewById(R.id.rg_b_1);


            int id_a = rg_a.getCheckedRadioButtonId();

            if(id_a != -1 ){
                RadioButton rb_a = (RadioButton) findViewById(id_a);
                if(rb_a.getText().equals("예")){
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:

                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.03);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }


                }else{
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:
                                vCalculation = vCalculation - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.05) - (defaultCost * 0.03);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }
                }
            }else{
                toast.show();
                return;
            }

        }else if(closenessLevel == 7){

            final RadioGroup rg_a = (RadioGroup)findViewById(R.id.rg_a);
            final RadioGroup rg_b_1 = (RadioGroup)findViewById(R.id.rg_b_1);


            int id_a = rg_a.getCheckedRadioButtonId();

            if(id_a != -1 ){
                RadioButton rb_a = (RadioButton) findViewById(id_a);
                if(rb_a.getText().equals("예")){
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:
                                vCalculation = vCalculation - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.04);
                                break;
                            case R.id.rb_b_1_3:
                                vCalculation = vCalculation - (defaultCost * 0.03);
                                break;
                            case R.id.rb_b_1_4:
                                vCalculation = vCalculation - (defaultCost * 0.02);
                                break;
                            case R.id.rb_b_1_5:
                                vCalculation = vCalculation - (defaultCost * 0.01);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }


                }else{
                    int id_b_1 = rg_b_1.getCheckedRadioButtonId();
                    if(id_b_1 != -1){
                        double vCalculation = calculation;
                        double defaultCost = (double)map.get("defaultCost");
                        switch(id_b_1) {
                            case R.id.rb_b_1_1:
                                vCalculation = vCalculation - (defaultCost * 0.05) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_2:
                                vCalculation = vCalculation - (defaultCost * 0.04) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_3:
                                vCalculation = vCalculation - (defaultCost * 0.03) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_4:
                                vCalculation = vCalculation - (defaultCost * 0.02) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_5:
                                vCalculation = vCalculation - (defaultCost * 0.01) - (defaultCost * 0.05);
                                break;
                            case R.id.rb_b_1_6:
                                vCalculation = vCalculation - (defaultCost * 0.05);
                                break;

                        }
                        map.put("calculation", vCalculation);

                    }else{
                        toast.show();
                        return;
                    }
                }
            }else{
                toast.show();
                return;
            }

        }


        Intent intent = new Intent (this, ImportanceActivity.class );
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
