package com.justacomm.gyungjobi.gyungjobi.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ngin1 on 2016-10-28.
 */

public class FamillyEventDBHelper extends SQLiteOpenHelper {

    public FamillyEventDBHelper(Context context){
        super(context, "FamillyEvent.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SAVED_EVENT( SN INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TITLE TEXT , EVENT_NM TEXT , COST INTEGER );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
