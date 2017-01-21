package apps.database.dnr2i.rssfeedreader.model;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.sax.Element;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import apps.database.dnr2i.rssfeedreader.R;

/**
 * Class RSS adapter, for Display of Recycler view
 * Created by Alexandre DUCREUX on 21/01/2017.
 */

public class RSSListAdapter extends RecyclerView.Adapter<FeedListViewHolder> {


    private ArrayList<RSSFeedList> rssFL = new ArrayList<>();



    public RSSListAdapter(ArrayList<RSSFeedList> rssFL){
        this.rssFL = rssFL;

    }

    @Override
    public FeedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rss_feed_details_selector, parent, false);
        return new FeedListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedListViewHolder holder, int position) {

        RSSFeedList rssFeedList = rssFL.get(position);
        holder.getTitleFeed().setText(rssFeedList.getTitleFeed());
        holder.getDescriptionFeed().setText(rssFeedList.getDescriptionFeed());
        holder.getUrlFeed().setText(rssFeedList.getUrlFeed());

    }

    @Override
    public  int getItemCount(){
        int recordNumber =  rssFL.size();
        Log.i("AD", "Nombre d'enregistrement en base : "+recordNumber);
        return recordNumber;
    }
}



