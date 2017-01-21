package apps.database.dnr2i.rssfeedreader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.*;


/**
 * Created by Alexandre DUCREUX on 18/01/2017.
 * Class Settings of Sqlite database
 */

public class DBOpener extends SQLiteOpenHelper {

    //static attributes
    private static final String DB_CREATE = "CREATE TABLE " + TABLE_NAME + "( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"  +
            COLUMN_NAME_TITLE + " TEXT NOT NULL , " +
            COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL , " +
            COLUMN_NAME_LINK + " TEXT NOT NULL , " +
            COLUMN_NAME_DATE + " DATETIME NOT NULL " + ")";

    //dropping base
    private static final String SQL_DELETE_ENTRIES = " DROP TABLE  IF EXISTS " + TABLE_NAME;

    //versioning
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "rssfeed.db";


    public DBOpener(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version){
        super(context, dbName, factory, version);
    }

    /**
     * creating database
     * @param db
     */
    public void onCreate(SQLiteDatabase db){
        Log.i("AD", "Création des tables la base de données"+DB_CREATE);
        db.execSQL(DB_CREATE);
    }

    /**
     * Delete database on upgrade and recreate it
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


}
