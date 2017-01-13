package com.co.myrappitest.DataContent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.co.myrappitest.Data.DataT5;
import com.co.myrappitest.Data.RappiDataBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuanCamilo on 12/01/2017.
 */

public class DataT5Content implements BaseColumns {
    public static final String TABLE_NAME = "json_table";


    public static final String JSON_STRUCTURE_USER_SR_THEME_ENABLED = "user_sr_theme_enabled";
    public static final String JSON_STRUCTURE_SUBMIT_TEXT_HTML = "submit_text_html";
    public static final String JSON_STRUCTURE_USER_IS_BANNED = "user_is_banned";
    public static final String JSON_STRUCTURE_WIKI_ENABLED = "wiki_enabled";
    public static final String JSON_STRUCTURE_SHOW_MEDIA = "show_media";
    public static final String JSON_STRUCTURE_ID = "id";
    public static final String JSON_STRUCTURE_DATA = "data";
    public static final String JSON_STRUCTURE_CHILDREN = "children";
    public static final String JSON_STRUCTURE_BANNER_IMG = "banner_img";
    public static final String JSON_STRUCTURE_SUBMIT_TEXT = "submit_text";
    public static final String JSON_STRUCTURE_DISPLAY_NAME = "display_name";
    public static final String JSON_STRUCTURE_HEADER_IMG = "header_img";
    public static final String JSON_CONTENT_DESCRIPTION = "description";
    public static final String JSON_STRUCTURE_DESCRIPTION_HTML = JSON_CONTENT_DESCRIPTION + "_html";
    public static final String JSON_CONTENT_TITLE = "title";
    public static final String JSON_CONTENT_COLLAPSE_DELETED_COMMENTS = "collapse_deleted_comments";
    public static final String JSON_CONTENT_OVER18 = "over18";
    public static final String JSON_CONTENT_PUBLIC_DESCRIPTION_HTML = "public_" + JSON_CONTENT_DESCRIPTION + "_html";
    public static final String JSON_CONTENT_SPOILERS_ENABLED = "spoilers_enabled";
    public static final String JSON_CONTENT_ICON_SIZE = "icon_size";
    public static final String JSON_CONTENT_CREATED_UTC = "created_utc";
    public static final String JSON_CONTENT_HEADER_SIZE = "header_size";
    public static final String JSON_CONTENT_KEY_COLOR = "key_color";
    public static final String JSON_CONTENT_BANNER_SIZE = "banner_size";
    public static final String[] COLUMNS = new String[]{
            JSON_STRUCTURE_DISPLAY_NAME,
            JSON_CONTENT_DESCRIPTION,
            JSON_STRUCTURE_DESCRIPTION_HTML,
            JSON_STRUCTURE_HEADER_IMG,
            JSON_CONTENT_KEY_COLOR,
            JSON_STRUCTURE_BANNER_IMG,
            JSON_CONTENT_BANNER_SIZE,
            JSON_CONTENT_TITLE,
            JSON_CONTENT_HEADER_SIZE,
    };
    private static final String TAG = DataT5Content.class.getSimpleName();

    public static void updateDataBase(String JSONString, Context context) throws JSONException {
        JSONObject root = new JSONObject(JSONString);
        JSONArray t5Objects = root.getJSONObject(JSON_STRUCTURE_DATA).getJSONArray(JSON_STRUCTURE_CHILDREN);
        JSONObject object;
        ContentValues values[] = new ContentValues[t5Objects.length()];
        for (int i = 0; i < t5Objects.length(); i++) {
            object = t5Objects.getJSONObject(i);
            values[i] = getData(object.getJSONObject(JSON_STRUCTURE_DATA));
        }
        RappiDataBase dataBase = new RappiDataBase(context);
        SQLiteDatabase db = dataBase.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        int count = insertAll(db, values);
        Log.d(TAG, "rows inserted " + count);
    }

    private static ContentValues getData(JSONObject data) {
        ContentValues cv = new ContentValues();
        for (String property : DataT5Content.COLUMNS) {
            cv.put(property, String.valueOf(data.opt(property)));
        }
        return cv;
    }

    private static int insertAll(SQLiteDatabase db, ContentValues[] values) {
        db.beginTransaction();
        int returnCount = 0;
        try {
            for (ContentValues value : values) {
                long _id = db.insert(DataT5Content.TABLE_NAME, null, value);
                if (_id != -1) {
                    returnCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return returnCount;
    }

    public static List<DataT5> getAllData(Context context) {
        List<DataT5> result = new ArrayList<>();
        RappiDataBase dataBase = new RappiDataBase(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
        if (!cursor.moveToFirst()) {
            Log.e(TAG, "error getting data");
            return result;
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            String title = cursor.getString(cursor.getColumnIndex(JSON_CONTENT_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(JSON_CONTENT_DESCRIPTION));
            String htmlDescr = cursor.getString(cursor.getColumnIndex(JSON_STRUCTURE_DESCRIPTION_HTML));
            String headerImg = cursor.getString(cursor.getColumnIndex(JSON_STRUCTURE_HEADER_IMG));
            String color = cursor.getString(cursor.getColumnIndex(JSON_CONTENT_KEY_COLOR));
            String bannerImg = cursor.getString(cursor.getColumnIndex(JSON_STRUCTURE_BANNER_IMG));
            result.add(new DataT5(title, description, htmlDescr, headerImg, color, bannerImg));
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
}
