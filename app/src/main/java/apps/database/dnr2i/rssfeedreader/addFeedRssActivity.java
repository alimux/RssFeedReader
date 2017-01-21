package apps.database.dnr2i.rssfeedreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import apps.database.dnr2i.rssfeedreader.model.RssEntity;

/**
 * Activity -> REGISTER new feed
 */

public class addFeedRssActivity extends AppCompatActivity {

    private RssEntity addRSSEntity;
    private EditText inputFeedName;
    private EditText inputFeedDescription;
    private EditText inputFeedUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_feed_ressources);
        //instanciate Entity
        addRSSEntity =new RssEntity(addFeedRssActivity.this);
    }

    /**
     * adding new feed on database
     * @param view
     */
    public void addRssFeed(View view){

        //retrieve input
        inputFeedName = (EditText) findViewById(R.id.feedName);
        inputFeedDescription = (EditText) findViewById(R.id.feedDescription);
        inputFeedUrl = (EditText) findViewById(R.id.feedUrl);

        String feedName = inputFeedName.getText().toString();
        String feedDescription = inputFeedDescription.getText().toString();
        String feedUrl = inputFeedUrl.getText().toString();

        Log.i("AD", "valeurs des inputs : " + feedName + " ," + feedDescription + " ," + feedUrl);

        Long result = addRSSEntity.addNewFeed(feedName,feedDescription,feedUrl);
        //test result
        CharSequence errOrNot = "";
        if(result==-1){
            errOrNot = "L'enregistrement à rencontré un problème...";
        }
        else{
            errOrNot = "Le nouvel élément " + feedName + " est bien enregistré !";

        }
        //Display message
        Toast.makeText(getApplicationContext(), errOrNot, Toast.LENGTH_LONG).show();

    }
}
