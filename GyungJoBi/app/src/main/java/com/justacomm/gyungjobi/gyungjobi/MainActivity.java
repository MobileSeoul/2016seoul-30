package com.justacomm.gyungjobi.gyungjobi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity {
    private long lastTimeBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onClick(View view){
        Intent intent = new Intent (this, MyInfoActivity.class );
        switch(view.getId()){
            case R.id.btn_a:
                intent = new Intent (this, MyInfoActivity.class );
                break;
            case R.id.btn_b:
                intent = new Intent (this, SavedEventActivity.class );
                break;
        }

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

            return true;

        }else if (id == R.id.saved) {

            Intent intent = new Intent (this, SavedEventActivity.class );
            startActivity(intent);

        }else if (id == R.id.finish) {

            this.finishAffinity();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(currentTimeMillis() - lastTimeBackPressed < 1500){
            this.finishAffinity();
            return;
        }
        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르시면 종료됩니다. ", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}
