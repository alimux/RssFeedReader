package apps.database.dnr2i.rssfeedreader;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import apps.database.dnr2i.rssfeedreader.model.FeedItems;
import apps.database.dnr2i.rssfeedreader.model.ItemEntity;
import apps.database.dnr2i.rssfeedreader.model.RSSFeedList;
import apps.database.dnr2i.rssfeedreader.model.RSSAdapter;
import apps.database.dnr2i.rssfeedreader.model.ItemEntity;
import apps.database.dnr2i.rssfeedreader.model.RSSListAdapter;
import apps.database.dnr2i.rssfeedreader.model.RssEntity;

import static android.provider.BaseColumns._ID;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DATE;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DESCRIPTION;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_LINK;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_TITLE;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_FEED_ID;

/**
 * Created by flolaptop on 21/01/2017.
 */

public class SelectedFeedActivity extends AppCompatActivity {
    private ItemEntity feedItems;
    private ArrayList<FeedItems> arrayFeedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_feed);
        setTitle("Liste des articles du flux ...");

        feedItems = new ItemEntity(SelectedFeedActivity.this);

        //TODO recupérer l'id du feed sur lequel on a cliqué
        arrayFeedItems = feedItemList();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feedsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RSSAdapter(arrayFeedItems));
    }

    /**
     * Adding menu on the top of the apps
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public ArrayList<FeedItems> feedItemList(int feedId){
        Cursor cursor = feedItems.getAllItems(feedId);
        Log.i("AD", "Connexion à la base ok");
        ArrayList<FeedItems> feedItems = new ArrayList<>();
        String[] columns = new String[] { _ID, COLUMN_NAME_TITLE, COLUMN_NAME_DESCRIPTION, COLUMN_NAME_LINK, COLUMN_NAME_DATE };
        if(cursor !=null && cursor.moveToFirst()){
            do {
                FeedItems rssFI = new FeedItems();
                rssFI.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                rssFI.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)));
                rssFI.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPTION)));
                Log.i("AD", "link : "+cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPTION)));
                rssFI.setLink(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LINK)));
                rssFI.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE)));
                rssFI.setFeedId(cursor.getInt(cursor.getColumnIndex(COLUMN_FEED_ID)));

                arrayFeedItems.add(rssFI);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return arrayFeedItems;
    }
}
