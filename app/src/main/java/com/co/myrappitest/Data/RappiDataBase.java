package com.co.myrappitest.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.co.myrappitest.DataContent.DataT5Content;

/**
 * Created by JuanCamilo on 12/01/2017.
 */

public class RappiDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "rappiDB";
    private static final int DB_VERSION = 1;

    private static String CREATE_TABLE;

    static {
        StringBuilder query = new StringBuilder("CREATE TABLE " + DataT5Content.TABLE_NAME
                + " ( _ID INT, ");
        for (int i = 0; i < DataT5Content.COLUMNS.length - 1; i++) {
            query.append(DataT5Content.COLUMNS[i]);
            query.append(" VARCHAR, ");
        }
        query.append(DataT5Content.COLUMNS[DataT5Content.COLUMNS.length - 1]);
        query.append(" VARCHAR );");
        CREATE_TABLE = query.toString();
    }

    public RappiDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("creation query", CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists " + DataT5Content.TABLE_NAME);
        Log.d("update query", "done");
    }

}
