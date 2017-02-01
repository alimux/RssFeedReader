package apps.database.dnr2i.rssfeedreader;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.util.ArrayList;

import apps.database.dnr2i.rssfeedreader.model.AsyncResponse;
import apps.database.dnr2i.rssfeedreader.model.FeedItems;
import apps.database.dnr2i.rssfeedreader.model.ItemEntity;
import apps.database.dnr2i.rssfeedreader.model.ParserRSS;
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

public class SelectedFeedActivity extends AppCompatActivity implements AsyncResponse {
    private int feedId;
    private ItemEntity feedItems;
    private ArrayList<FeedItems> arrayFeedItems;
    ParserRSS task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_feed);
        setTitle("Liste des articles du flux ...");
        feedItems = new ItemEntity(SelectedFeedActivity.this);

        //TODO recupérer l'id du feed sur lequel on a cliqué
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        Log.i("AD",""+id);
        arrayFeedItems = feedItemList(intent.getIntExtra("id",0));
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.itemsList);
        Log.i("AD",""+recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RSSAdapter adapter = new RSSAdapter(arrayFeedItems, getApplicationContext());
        recyclerView.setAdapter(adapter);
        task = new ParserRSS(adapter);

    }
    /**
     * Adding menu on the top of the apps
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.selected_feed_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //menu settings
        switch (id)
        {
            case R.id.action_refresh :
                getFeedDataInBackground(this.feedId);
                break;
            default: return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public ArrayList<FeedItems> feedItemList(int feedId){
        this.feedId = feedId;
        Cursor cursor = feedItems.getAllItems(feedId);
        Log.i("AD", "Connexion à la base ok");
        ArrayList<FeedItems> feedItems = new ArrayList<>();
        String[] columns = new String[] { _ID, COLUMN_NAME_TITLE, COLUMN_NAME_DESCRIPTION, COLUMN_NAME_LINK, COLUMN_NAME_DATE };
        if(cursor !=null && cursor.moveToFirst()) {
            do {
                FeedItems rssFI = new FeedItems();
                rssFI.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                rssFI.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)));
                rssFI.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPTION)));
                rssFI.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE)));
                Log.i("AD", "date : " + cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE)));
                rssFI.setLink(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LINK)));
                //rssFI.setFeedId(cursor.getInt(cursor.getColumnIndex(COLUMN_FEED_ID)));

                feedItems.add(rssFI);
            }
            while (cursor.moveToNext());
            cursor.close();
        }

        return feedItems;
    }

    public void getFeedDataInBackground(int feedId){
        RssEntity rssEntity = new RssEntity(SelectedFeedActivity.this);
        Cursor feed = rssEntity.getFeedById(feedId);
        feed.moveToFirst();
        task.delegate = this;
        try{
            task.execute(feed.getString(feed.getColumnIndex(COLUMN_NAME_LINK)), String.valueOf(feedId));
        }catch(Exception e){

        }
//        feedItemList(feedId);
    }

    @Override
    public void processFinish(){
        feedItemList(this.feedId);
        Log.i("AD","VIEW A JOUR");
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
