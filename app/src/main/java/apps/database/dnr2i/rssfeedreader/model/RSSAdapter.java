package apps.database.dnr2i.rssfeedreader.model;

import android.sax.Element;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Document;

import java.util.ArrayList;

import apps.database.dnr2i.rssfeedreader.FeedSelectorActivity;
import apps.database.dnr2i.rssfeedreader.R;

/**
 * Class Adpater to prepare Display
 * Created by Alexandre DUCREUX on 21/01/2017.
 */

public class RSSAdapter extends RecyclerView.Adapter<ArticleViewHolder>{

    private ArrayList<FeedItems> rssFI = new ArrayList<>();

    public RSSAdapter(ArrayList<FeedItems> rssFI) {
        this.rssFI = rssFI;
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
}
