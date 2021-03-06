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
 * Class which manage the Rss Entity
 */

public class RssEntity {

    private DBOpener dbOpener;
    private SQLiteDatabase db;
    private java.sql.Timestamp timestamp;
    private long dateNow;


    /**
     * construct initialize database
     * @param context
     */
    public RssEntity(Context context){

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

    /**
     * method which returns a selected feed
     * @param id
     * @return cursor
     */
    public Cursor getFeedById(int id){

        //prepare query
        String[] columns = new String[] { _ID, COLUMN_NAME_TITLE, COLUMN_NAME_DESCRIPTION, COLUMN_NAME_LINK, COLUMN_NAME_DATE };
        String whereClause = " _ID = " + id + " ";
        db = dbOpener.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, columns, whereClause, null, null, null, null, null);
        return cursor;
    }

    /**
     * method which returns all feeds records which are in database
     * @return cursor
     */
    public Cursor getAllFeeds(){
        //prepare query
        String[] columns = new String[] { _ID, COLUMN_NAME_TITLE, COLUMN_NAME_DESCRIPTION, COLUMN_NAME_LINK, COLUMN_NAME_DATE };
        String order = COLUMN_NAME_DATE + " DESC";
        db = dbOpener.getWritableDatabase();

        //execute query
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, order, null);
        return cursor;


    }

    /**
     * update selected feed in database
     * @param id
     * @param title
     * @param description
     * @param url
     * @return db.update
     */
    public int modifyFeed(int id, String title, String description, String url ){
        ContentValues values = new ContentValues();
        dateNow = System.currentTimeMillis();
        timestamp = new java.sql.Timestamp(dateNow);

        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_DESCRIPTION,description);
        values.put(COLUMN_NAME_LINK, url);
        values.put(COLUMN_NAME_DATE, timestamp.toString());

        String whereClause = " _ID = " + id + " ";
        SQLiteDatabase db = dbOpener.getWritableDatabase();

        return db.update(TABLE_NAME, values, whereClause, null);

    }

    /**
     * delete the selected record
     * @param i
     * @return db.delete
     */
    public boolean deleteFeed(int i){
        String where = " _ID = " + i + " ";
        //String whereArgs[] =  { Integer.toString(i) };
        //Log.i("AD", "whereArgs : "+whereArgs[0]);
        db = dbOpener.getWritableDatabase();
        //delete alls feeds attached to the original feed
        deleteItemsFeed(i);
        //delete reccord
        return db.delete(TABLE_NAME, where, null) > 0;
    }
    public  boolean deleteItemsFeed(int feedID){
        String where = " feedId = " + feedID + " ";
        db = dbOpener.getWritableDatabase();
        //delete reccord
        return db.delete(ITEM_TABLE_NAME, where, null) > 0;
    }

}
