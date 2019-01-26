package saddam.razon.sos.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

import saddam.razon.sos.model.KeyValuePair;

public class DBWrapper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ceaemctrydata";
    private static final int DATABASE_VERSION = 1;
    private static final String TAB_D = "es_em_def";
    private static final String TAB_T = "es_em_tel";

    public DBWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table es_em_tel(country text not null, cat text not null, tel text not null);");
        database.execSQL("create table es_em_def(country text not null, region text not null);");
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(DBWrapper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        recreteTables(database);
    }

    public void recreteTables(SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS es_em_tel");
        database.execSQL("DROP TABLE IF EXISTS es_em_def");
        onCreate(database);
    }

    public long insertC(String c, String a, String t) {
        ContentValues values = new ContentValues();
        values.put("country", c);
        values.put("cat", a);
        values.put("tel", t);
        return getWritableDatabase().insert(TAB_T, null, values);
    }

    public long insertD(String c, String r) {
        ContentValues values = new ContentValues();
        values.put("country", c);
        values.put("region", r);
        return getWritableDatabase().insert(TAB_D, null, values);
    }

    public long deleteC(String c, String a, String t) {
        String[] values = new String[]{c, a, t};
        return (long) getWritableDatabase().delete(TAB_T, "country = ? and cat = ? and tel = ?", values);
    }

    public void setDefaultCR(String r, String c) {
        getWritableDatabase().delete(TAB_D, null, null);
        insertD(c, r);
    }

    public String getDefaultRegion() {
        String s = "";
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String str = TAB_D;
            String[] strArr = new String[DATABASE_VERSION];
            strArr[0] = "region";
            Cursor c = writableDatabase.query(str, strArr, null, null, null, null, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    s = c.getString(0);
                }
                c.close();
            }
        } catch (Exception e) {
            getWritableDatabase().execSQL("create table es_em_def(country text not null, region text not null);");
            insertD("", "");
        }
        return s;
    }

    public String getDefaultCountry() {
        String s = "";
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String str = TAB_D;
            String[] strArr = new String[DATABASE_VERSION];
            strArr[0] = "country";
            Cursor c = writableDatabase.query(str, strArr, null, null, null, null, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    s = c.getString(0);
                }
                c.close();
            }
        } catch (Exception e) {
            getWritableDatabase().execSQL("create table es_em_def(country text not null, region text not null);");
            insertD("", "");
        }
        return s;
    }

    public ArrayList<KeyValuePair> getTels(String ct) {
        Cursor c = getWritableDatabase().query(true, TAB_T, new String[]{"cat", "tel"}, "country = \"" + ct + "\"", null, null, null, null, null);
        ArrayList<KeyValuePair> s = new ArrayList();
        if (c != null) {
            if (c.moveToFirst()) {
                while (!c.isAfterLast()) {
                    s.add(new KeyValuePair(c.getString(0), c.getString(DATABASE_VERSION)));
                    c.moveToNext();
                }
            }
            c.close();
        }
        return s;
    }
}
