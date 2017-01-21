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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

//import apps.database.dnr2i.rssfeedreader.model.RSSAdapter;
import apps.database.dnr2i.rssfeedreader.model.RSSFeedList;
import apps.database.dnr2i.rssfeedreader.model.RSSListAdapter;
import apps.database.dnr2i.rssfeedreader.model.RssEntity;

import static android.provider.BaseColumns._ID;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DATE;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DESCRIPTION;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_LINK;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_TITLE;


public class FeedSelectorActivity extends AppCompatActivity {

    private RssEntity rssEntity;
    private  ArrayList<RSSFeedList> arrayRssFeedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_selector);
        setTitle("Liste de vos flux favoris ...");

        rssEntity = new RssEntity(FeedSelectorActivity.this);

        arrayRssFeedList = RssFeedsList();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feedsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RSSListAdapter(arrayRssFeedList, FeedSelectorActivity.this));
        //displayRssFeeds();
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.i("AD", "reprise de l'activité");
        rssEntity = new RssEntity(FeedSelectorActivity.this);

        arrayRssFeedList = RssFeedsList();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feedsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RSSListAdapter(arrayRssFeedList, FeedSelectorActivity.this));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //menu settings
        switch (id)
        {
            case R.id.action_settings :
                Intent intent = new Intent(this, addFeedRssActivity.class);
                startActivity(intent);
                String selectedMenu = "Le menu sélectionné est : "+item.toString();
                //display temp message
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, selectedMenu, Toast.LENGTH_SHORT);
                toast.show();
                //finish();
                break;
            default: return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public ArrayList<RSSFeedList> RssFeedsList(){
        Cursor cursor = rssEntity.getAllFeeds();
        Log.i("AD", "Connexion à la base ok");
        ArrayList<RSSFeedList> rssFeedList = new ArrayList<>();
        String[] columns = new String[] { _ID, COLUMN_NAME_TITLE, COLUMN_NAME_DESCRIPTION, COLUMN_NAME_LINK, COLUMN_NAME_DATE };
        if(cursor !=null && cursor.moveToFirst()){
            do {
                RSSFeedList rssFL = new RSSFeedList();
                rssFL.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                rssFL.setTitleFeed(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)));
                rssFL.setDescriptionFeed(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPTION)));
                rssFL.setUrlFeed(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LINK)));
                rssFL.setDateFeed(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE)));

                rssFeedList.add(rssFL);

            }
            while (cursor.moveToNext());
        }
        cursor.close();

        return rssFeedList;
    }
    public void refreshView(){
        onResume();
    }

}
