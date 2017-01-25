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
import android.widget.Toast;
import java.util.ArrayList;
import apps.database.dnr2i.rssfeedreader.model.RSSFeedList;
import apps.database.dnr2i.rssfeedreader.model.RSSListAdapter;
import apps.database.dnr2i.rssfeedreader.model.RssEntity;

import static android.provider.BaseColumns._ID;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DATE;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DESCRIPTION;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_LINK;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_TITLE;

/**
 * ACTIVITY -> Main, display the feeds subscriptions of the user
 */
public class FeedSelectorActivity extends AppCompatActivity {

    private RssEntity rssEntity;
    private RSSListAdapter adapter;
    public static final String DEFAULT_ENTRIES = "Vous n'êtes abonné à aucun flux...";

    /**
     * create activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_selector);
        setTitle("Liste de vos flux favoris ...");
        initRecyclerView();
    }

    /**
     * onResume activity, update the recycler view
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("AD", "reprise de l'activité");
        initRecyclerView();
        adapter.notifyDataSetChanged();
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
                break;
            default: return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public ArrayList<RSSFeedList> RssFeedsList(){
        Cursor cursor = rssEntity.getAllFeeds();
        ArrayList<RSSFeedList> rssFeedList = new ArrayList<>();
        Log.i("AD", "curseur = "+cursor.getCount()+ " taille tableau : "+rssFeedList.size());

        String[] columns = new String[] { _ID, COLUMN_NAME_TITLE, COLUMN_NAME_DESCRIPTION, COLUMN_NAME_LINK, COLUMN_NAME_DATE };

        if(cursor.getCount()>0 && cursor.moveToFirst()){
            Log.i("AD", "curseur non null, remplissage liste");
            if(rssFeedList.size()>0 && rssFeedList.get(0).getId()==-1){
                Log.i("AD", "enregistrement par défaut à supprimer : "+rssFeedList.get(0).getId());
                rssFeedList.remove(0);
            }

            do {
                RSSFeedList rssFL = new RSSFeedList();
                //adding entries on list
                rssFL.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
                rssFL.setTitleFeed(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)));
                rssFL.setDescriptionFeed(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPTION)));
                rssFL.setUrlFeed(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LINK)));
                rssFL.setDateFeed(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DATE)));

                rssFeedList.add(rssFL);

            }
            while (cursor.moveToNext());
        }
       else if(cursor.getCount()==0 && rssFeedList.size()==0){
            Log.i("AD", "pas d'entrée en base, on ajoute une entrée défaut..");;
            RSSFeedList rssFL = new RSSFeedList();
            rssFL.setId(-1);
            rssFL.setDescriptionFeed(DEFAULT_ENTRIES);
            rssFeedList.add(rssFL);
        }
        cursor.close();
        Log.i("aD", "taille rssFeedlist : "+rssFeedList.size()+" Taille du curseur :"+cursor.getCount());

        return rssFeedList;
    }

    public void initRecyclerView(){

        rssEntity = new RssEntity(FeedSelectorActivity.this);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feedsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RSSListAdapter(RssFeedsList(), FeedSelectorActivity.this);
        recyclerView.setAdapter(adapter);
    }

}
