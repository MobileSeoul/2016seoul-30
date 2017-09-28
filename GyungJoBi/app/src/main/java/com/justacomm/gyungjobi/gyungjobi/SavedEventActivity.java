package com.justacomm.gyungjobi.gyungjobi;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.justacomm.gyungjobi.gyungjobi.common.FamillyEventDBHelper;
import com.justacomm.gyungjobi.gyungjobi.common.ListViewAdapter;
import com.justacomm.gyungjobi.gyungjobi.common.ListViewItem;

public class SavedEventActivity extends AppCompatActivity {
    FamillyEventDBHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_event);

        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        helper = new FamillyEventDBHelper(this);
        db = helper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM SAVED_EVENT", null);
        while(cursor.moveToNext()){
            // 리스트뷰 아이템 추가
            adapter.addItem(Integer.toString(cursor.getInt(0)), cursor.getString(1), cursor.getString(2), String.format("%,d" ,cursor.getInt(3)).trim()+"원");
        }
        helper.close();


        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                final ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
                final ListViewAdapter adt = (ListViewAdapter) parent.getAdapter();
                final int listPostion = position;

                AlertDialog.Builder alert = new AlertDialog.Builder(SavedEventActivity.this);
                alert.setTitle("삭제");
                alert.setMessage("선택한 내용을 삭제하시겠습니까?");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // get item


                        String sn = item.getSn();
                        String title = item.getTitle() ;
                        String eventInfo = item.getEventInfo() ;
                        String cost = item.getCost() ;




                        helper = new FamillyEventDBHelper(SavedEventActivity.this);
                        db = helper.getWritableDatabase();
                        db.execSQL("DELETE FROM SAVED_EVENT WHERE SN = "+ Integer.parseInt(sn) + ";");
                        helper.close();

                        adt.removeItem(listPostion);

                        adt.notifyDataSetChanged();

                        // TODO : use item data.

                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alert.show();





            }
        }) ;



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

            return true;

        }else if (id == R.id.finish) {

            this.finishAffinity();

        }

        return super.onOptionsItemSelected(item);
    }
}
