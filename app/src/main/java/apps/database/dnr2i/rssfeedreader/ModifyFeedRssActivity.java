package apps.database.dnr2i.rssfeedreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ModifyFeedRssActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modification d'un flux...");
        setContentView(R.layout.modify_feed_selector);
    }


}
