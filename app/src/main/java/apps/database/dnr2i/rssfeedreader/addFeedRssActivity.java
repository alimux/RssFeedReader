package apps.database.dnr2i.rssfeedreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button btnAddRssFeed;
    private String feedName;
    private String feedDescription;
    private String feedUrl;
    private final static String ERROR_FORM = "Veuillez remplir tous les champs du formulaire, svp...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_feed_ressources);
        //instanciate Entity
        addRSSEntity =new RssEntity(addFeedRssActivity.this);

        //retrieve input
        inputFeedName = (EditText) findViewById(R.id.feedName);
        inputFeedDescription = (EditText) findViewById(R.id.feedDescription);
        inputFeedUrl = (EditText) findViewById(R.id.feedUrl);
        btnAddRssFeed = (Button) findViewById(R.id.addFeedRSSBtn);
        inputFeedUrl.setText("http://");
        inputFeedUrl.setSelection(7);
    }

    /**
     * adding new feed on database
     * @param view
     */
    public void addRssFeed(View view){




        feedName = inputFeedName.getText().toString();
        feedDescription = inputFeedDescription.getText().toString();
        feedUrl = inputFeedUrl.getText().toString();

        Log.i("AD", "valeurs des inputs : " + feedName + " ," + feedDescription + " ," + feedUrl+"\n valeur input : "+inputFeedName.getText());


        if(validate()) {
            Long result = addRSSEntity.addNewFeed(feedName, feedDescription, feedUrl);
            //test result
            CharSequence errOrNot = "";
            if (result == -1) {
                errOrNot = "L'enregistrement à rencontré un problème...";
            } else {
                errOrNot = "Le nouvel élément " + feedName + " est bien enregistré !";

            }
            //Display message
            Toast.makeText(getApplicationContext(), errOrNot, Toast.LENGTH_LONG).show();
        }

    }
    public boolean validate(){

        if(feedName.isEmpty() || feedDescription.isEmpty() || feedUrl.isEmpty()){
            Log.i("AD", "dans validate");
            Toast.makeText(getApplicationContext(), ERROR_FORM, Toast.LENGTH_SHORT).show();
            return false;
        }
        else {

            return true;
        }


    }
}
