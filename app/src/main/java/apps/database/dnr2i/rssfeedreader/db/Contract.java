package apps.database.dnr2i.rssfeedreader.db;

import android.provider.BaseColumns;

/**
 * Created by Alexandre DUCREUX on 18/01/2017.
 * Class which sets Static attributes for database
 */

public class Contract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Contract() {}

    /* Inner class that defines the table contents */
    public static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "rssfeeds";
        public static final String ITEM_TABLE_NAME = "item";
        public static final String COLUMN_NAME_TITLE = "titleFeed";
        public static final String COLUMN_NAME_DESCRIPTION = "descriptionFeed";
        public static final String COLUMN_NAME_LINK = "urlFeed";
        public static final String COLUMN_NAME_DATE = "dateFeed";
        public static final String COLUMN_FEED_ID = "feedId";
    }
}
