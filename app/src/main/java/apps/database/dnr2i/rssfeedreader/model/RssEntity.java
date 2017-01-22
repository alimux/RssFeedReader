package apps.database.dnr2i.rssfeedreader.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import apps.database.dnr2i.rssfeedreader.db.Contract;
import apps.database.dnr2i.rssfeedreader.db.DBOpener;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.*;
import static apps.database.dnr2i.rssfeedreader.db.DBOpener.*;

/**
 * Created by Alexandre DUCREUX on 18/01/2017.
 */

public class RssEntity {

    private DBOpener dbOpener;
    private SQLiteDatabase db;
    private java.sql.Timestamp timestamp;
    private long dateNow;




    public RssEntity(Context context){
        //instanciate DBOpener
        dbOpener = new DBOpener(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Add new feed ressources on database
     * @param title
     * @param description
     * @param url
     * @return
     */

    public Long addNewFeed(String title, String description, String url){

        SQLiteDatabase db = dbOpener.getWritableDatabase();
        ContentValues values =  new ContentValues();

        dateNow = System.currentTimeMillis();
        timestamp = new java.sql.Timestamp(dateNow);

        Log.i("AD", "valeurs addNewFeed :" +title +" ,"+description+" ," + " url: " + url);
        //put data in base
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_DESCRIPTION,description);
        values.put(COLUMN_NAME_LINK, url);
        values.put(COLUMN_NAME_DATE, timestamp.toString());

        //insertion des datas
        return db.insertOrThrow(TABLE_NAME, null, values);

    }

    public Cursor getAllFeeds(){
        //prepare query
        String[] columns = new String[] { _ID, COLUMN_NAME_TITLE, COLUMN_NAME_DESCRIPTION, COLUMN_NAME_LINK, COLUMN_NAME_DATE };
        String order = COLUMN_NAME_DATE + " DESC";
        db = dbOpener.getWritableDatabase();

        //execute query
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, order, null);
        return cursor;


    }
    public boolean deleteFeed(int i){
        String where = " _ID = " + i + " ";
        //String whereArgs[] =  { Integer.toString(i) };
        //Log.i("AD", "whereArgs : "+whereArgs[0]);
        db = dbOpener.getWritableDatabase();
        //delete reccord
        return db.delete(TABLE_NAME, where, null) > 0;
    }

}
