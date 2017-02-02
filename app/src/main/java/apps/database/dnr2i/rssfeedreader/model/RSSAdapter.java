package apps.database.dnr2i.rssfeedreader.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

import apps.database.dnr2i.rssfeedreader.FeedSelectorActivity;
import apps.database.dnr2i.rssfeedreader.R;

/**
 * Class Adpater to prepare Display
 * Created by Alexandre DUCREUX on 21/01/2017.
 */

public class RSSAdapter extends RecyclerView.Adapter<ArticleViewHolder> implements DocumentConsumer{

    private ArrayList<FeedItems> rssFI = new ArrayList<>();
    private Document _document;
    private Context context;

    public RSSAdapter(ArrayList<FeedItems> rssFI, Context context) {
        this.rssFI = rssFI;
        this.context = context;
    }

    @Override
    public  int getItemCount(){
        int recordNumber = rssFI.size();
        Log.i("AD", "Nombre d'enregistrement en base : "+recordNumber);
        return recordNumber;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rss_selected_feed_details, parent, false);
        return new ArticleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position){
        FeedItems feedItemsList = rssFI.get(position);
        holder.getTitle().setText(feedItemsList.getTitle());
        holder.getDescription().setText(feedItemsList.getDescription());
        holder.getLink().setText(feedItemsList.getLink());
        holder.getDate().setText(feedItemsList.getDate());


    }
    @Override
    public void setXMLDocument(Document document, int feedId) {
        _document = document;
        ItemEntity item = new ItemEntity(this.context);
        if (document.getElementsByTagName("item").getLength() > 0)
        {
            item.emptyItemsById(feedId);
        }
        for (int i=0; i<document.getElementsByTagName("item").getLength(); i++)
        {
            Element element = (Element) _document.getElementsByTagName("item").item(i);
            String title = element.getElementsByTagName("title").item(0).getTextContent();
            String description = element.getElementsByTagName("description").item(0).getTextContent();
            String date = element.getElementsByTagName("pubDate").item(0).getTextContent();
            String link = element.getElementsByTagName("link").item(0).getTextContent();
            item.setItem(title,description,date,link,feedId);
            Log.i("valeur de title"," title = " +title);

        }

        notifyDataSetChanged();
        Log.i("FIN","FIN");
    }
}
