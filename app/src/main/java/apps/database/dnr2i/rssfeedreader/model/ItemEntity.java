package apps.database.dnr2i.rssfeedreader.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import apps.database.dnr2i.rssfeedreader.db.DBOpener;

import static android.provider.BaseColumns._ID;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DATE;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DESCRIPTION;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_LINK;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_TITLE;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.ITEM_TABLE_NAME;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.TABLE_NAME;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_FEED_ID;
import static apps.database.dnr2i.rssfeedreader.db.DBOpener.DATABASE_NAME;
import static apps.database.dnr2i.rssfeedreader.db.DBOpener.DATABASE_VERSION;

/**
 * Created by flolaptop on 21/01/2017.
 */

public class ItemEntity {
    private DBOpener dbOpener;
    private SQLiteDatabase db;
    private long dateNow;

    public ItemEntity(Context context){
        //instanciate DBOpener
        dbOpener = new DBOpener(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Add new feed ressources on database
     * @param title
     * @param description
     * @param url
     * @param date
     * @return
     */

    public Cursor getAllItems(int feedId){
        //prepare query
        String[] columns = new String[] { _ID, COLUMN_NAME_TITLE, COLUMN_NAME_DESCRIPTION, COLUMN_NAME_LINK, COLUMN_NAME_DATE };
        String order = COLUMN_NAME_DATE + " DESC";
        String where = " feedId="+feedId+" ";
        db = dbOpener.getWritableDatabase();

        try {
            //execute query
            Cursor cursor = db.query(ITEM_TABLE_NAME, columns, where, null, null, null, order, null);
            return cursor;
        }catch (Exception ex){
            Log.e("AD", "pas de resultat recupérés", ex);
            return null;
        }


    }

    public Long setItem(String title, String description, String date, String link, int feedId){
        ContentValues values = new ContentValues();
        db = dbOpener.getWritableDatabase();
        //emptyItemsById(feedId);
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_DESCRIPTION, description);
        values.put(COLUMN_NAME_LINK,link);
        values.put(COLUMN_NAME_DATE,date);
        values.put(COLUMN_FEED_ID, feedId);
        return db.insertOrThrow(ITEM_TABLE_NAME, null, values);
    }

    public boolean emptyItemsById(int feedId){
        String where = " feedId = " +feedId+" ";
        db = dbOpener.getWritableDatabase();
        return db.delete(ITEM_TABLE_NAME,where,null)>0;
    }
}
