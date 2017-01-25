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

    /**
     * Static attributes of Database
     * String DB_CREATE -> to initialize two tables(rssfeeds & item)
     * String SQL_DELETE_ENTRIES -> to drop database
     * String DATABASE_VERSION -> to settings version
     * String DATABASE_NAME -> initialize database name
     */
    private static final String DB_CREATE = "CREATE TABLE " + TABLE_NAME + "( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"  +
            COLUMN_NAME_TITLE + " TEXT NOT NULL , " +
            COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL , " +
            COLUMN_NAME_LINK + " TEXT NOT NULL , " +
            COLUMN_NAME_DATE + " DATETIME NOT NULL " + ");";
    private static final String itemTableCreate =" CREATE TABLE " + ITEM_TABLE_NAME + "( " +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"  +
            COLUMN_NAME_TITLE + " TEXT NOT NULL , " +
            COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL , " +
            COLUMN_NAME_LINK + " TEXT NOT NULL , " +
            COLUMN_NAME_DATE + " TEXT NOT NULL , " +
            COLUMN_FEED_ID +" TEXT NOT NULL );";
    private static final String SQL_DELETE_ENTRIES = " DROP TABLE  IF EXISTS " + TABLE_NAME+" ; DROP TABLE  IF EXISTS "+ITEM_TABLE_NAME;
    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "rssfeedfinal.db";


    /**
     * constructor
     * @param context
     * @param dbName
     * @param factory
     * @param version
     */
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
        db.execSQL(itemTableCreate);
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
