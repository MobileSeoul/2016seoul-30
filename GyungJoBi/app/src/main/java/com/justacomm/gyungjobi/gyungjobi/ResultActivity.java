package com.justacomm.gyungjobi.gyungjobi;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.justacomm.gyungjobi.gyungjobi.common.CommandMap;
import com.justacomm.gyungjobi.gyungjobi.common.FamillyEventDBHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultActivity extends AppCompatActivity {
    FamillyEventDBHelper helper;
    SQLiteDatabase db;
    private CommandMap map;
    private double calculation = 0;
    private double defaultCost = 0;
    private long costLong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Intent intent = getIntent();
        map = (CommandMap) intent.getSerializableExtra("map");
        if(map.get("defaultCost") != null){
            defaultCost = (double)map.get("defaultCost");
        }
        if(map.get("calculation") != null){
            calculation = (double)map.get("calculation");
        }


        costLong = Math.round(defaultCost+calculation);
        if(costLong > 100000){
            costLong = 100000;
        }

        map.put("resultCost", Long.toString(costLong));


        final TextView tv_a = (TextView)findViewById(R.id.tv_a);
        tv_a.setText(String.format("%,d", costLong) +"원");
        final TextView tv_b = (TextView)findViewById(R.id.tv_b);
        tv_b.setText("\"금액으로 참석자의 마음을\n" +
                "판단하기보다\n" +
                "초대와 참석에 의의를 두는\n" +
                "새로운 경조사 문화를 만듭시다.\""
        );


    }

    public void onClick(View view){
        final EditText et_a = (EditText)findViewById(R.id.et_a);
        //Toast.makeText(this, et_a.getText().toString(), Toast.LENGTH_SHORT).show();
        String title = et_a.getText().toString();

        Pattern titlePattern = Pattern.compile("^[\\wㄱ-ㅎㅏ-ㅣ가-힣]+$");
        Matcher titleMatch = titlePattern.matcher(title);
        if(titleMatch.matches() == false){
            Toast.makeText(this, "제목은 한글자 이상의 한글, 영문, 숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show();
            return;
        }


        helper = new FamillyEventDBHelper(this);
        db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO SAVED_EVENT VALUES (null, '"+ title + "', '" + (String)map.get("eventInfo") +"', '" + Integer.parseInt((String) map.get("resultCost")) + "');");
        helper.close();




        AlertDialog.Builder alert = new AlertDialog.Builder(ResultActivity.this);
        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent (ResultActivity.this, MainActivity.class );
                startActivity(intent);
            }
        });
        alert.setMessage("저장되었습니다.");
        alert.show();
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

