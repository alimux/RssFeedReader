package apps.database.dnr2i.rssfeedreader.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Alexandre DUCREUX on 21/01/2017.
 * Class consists of parsing xml feeds using DOM Method with AsyncTask
 */

public class ParserRSS extends AsyncTask<String, Void, Document> {


    private DocumentConsumer _consumer;
    private int feedId;
    public AsyncResponse delegate = null;

    public ParserRSS(DocumentConsumer consumer){
        _consumer = consumer;
    }

    /**
     * Method launching new thread to parsing rss file
     * @param params
     * @return
     */
    @Override
    protected Document doInBackground(String... params) {
        //launch new thread
        try{
            Log.i("FEEDURL",""+params[0]);
            //Feed RSS URL
            URL url = new URL(params[0]);
            feedId = Integer.parseInt(params[1]);
            Log.i("ID",""+feedId);

            //launching connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // get information into an InputStream
            InputStream stream = connection.getInputStream();

            try {
                //parsing
                Log.i("STREAM",""+stream);
                return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            }
            finally {
                stream.close();
            }

        }
        catch (Exception ex){
            Log.e("AD", "Problème pendant le téléchargement...", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     * @param result
     */
    @Override
    protected void onPostExecute(Document result){
        Log.e("AD", "Parsing terminé...");
        _consumer.setXMLDocument(result,feedId);
        delegate.processFinish();
    }


}
