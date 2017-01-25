package apps.database.dnr2i.rssfeedreader;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import apps.database.dnr2i.rssfeedreader.model.RssEntity;

import static android.provider.BaseColumns._ID;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DATE;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DESCRIPTION;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_LINK;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_TITLE;

/**
 * ACTIVITY -> modify the selected feed, previously selected in the FeedSelectorActivity
 * @author Alexandre DUCREUX 2017
 */
public class ModifyFeedRssActivity extends AppCompatActivity {

    private RssEntity rssEntity;
    private EditText inputFeedName;
    private EditText inputFeedDescription;
    private EditText inputFeedUrl;
    private Button btnModifyRssFeed;
    private String feedName;
    private String feedDescription;
    private String feedUrl;
    private final static String ERROR_FORM = "Veuillez remplir tous les champs du formulaire, svp...";
    private int id;

    /**
     * create Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modification d'un flux...");
        setContentView(R.layout.modify_feed_selector);
        //instanciate Entity
        rssEntity = new RssEntity(ModifyFeedRssActivity.this);

        //retrieve input
        inputFeedName = (EditText) findViewById(R.id.feedName);
        inputFeedDescription = (EditText) findViewById(R.id.feedDescription);
        inputFeedUrl = (EditText) findViewById(R.id.feedUrl);
        btnModifyRssFeed = (Button) findViewById(R.id.modifyFeedRSS);
        inputFeedUrl.setText("http://");
        inputFeedUrl.setSelection(7);


        //retrieve id
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        Log.i("AD", "recup id = "+id);
        Cursor cursor = rssEntity.getFeedById(id);
        if(cursor!=null && cursor.moveToFirst()){
            Log.i("AD", cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)));
            inputFeedName.setText(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)));
            inputFeedDescription.setText(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DESCRIPTION)));
            inputFeedUrl.setText(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LINK)));
        }

    }

    /**
     * method which consists of modifying the selection, and update the record in database
     * @call validate to check the form
     * @param view
     */
    public void modifyRssFeed(View view){


        feedName = inputFeedName.getText().toString();
        feedDescription = inputFeedDescription.getText().toString();
        feedUrl = inputFeedUrl.getText().toString();


        if(validate()) {
            int result = rssEntity.modifyFeed(id, feedName, feedDescription, feedUrl);
            //test result
            CharSequence errOrNot = "";
            if (result == -1) {
                errOrNot = "L'enregistrement à rencontré un problème...";
            } else {
                errOrNot = "Le nouvel élément " + feedName + " est bien modifié !";

            }
            //Display message
            Toast.makeText(getApplicationContext(), errOrNot, Toast.LENGTH_LONG).show();
            //@back on main Activity
            Intent intent = new Intent(this, FeedSelectorActivity.class);
            startActivity(intent);
        }

    }

    /**
     * Check form
     * @return
     */
    public boolean validate() {

        if (feedName.isEmpty() || feedDescription.isEmpty() || feedUrl.isEmpty()) {
            Log.i("AD", "dans validate");
            Toast.makeText(getApplicationContext(), ERROR_FORM, Toast.LENGTH_SHORT).show();
            return false;
        } else {

            return true;
        }
    }




}
