package apps.database.dnr2i.rssfeedreader.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import apps.database.dnr2i.rssfeedreader.FeedSelectorActivity;
import apps.database.dnr2i.rssfeedreader.ModifyFeedRssActivity;
import apps.database.dnr2i.rssfeedreader.R;


/**
 * Class RSS adapter, for Display of Recycler view
 * Created by Alexandre DUCREUX on 21/01/2017.
 */

public class RSSListAdapter extends RecyclerView.Adapter<FeedListViewHolder> {


    private ArrayList<RSSFeedList> rssFL = new ArrayList<>();
    private Context context;
    private RssEntity rssEntity;
    private FeedListViewHolder holder;



    public RSSListAdapter(ArrayList<RSSFeedList> rssFL, Context context){
        this.rssFL = rssFL;
        this.context = context;



    }
    public void displayButton(FeedListViewHolder holder){
        if(rssFL.size()==1 && rssFL.get(0).getId()==-1){
            holder.displaySelectorButton();
        }

    }

    @Override
    public FeedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rss_feed_details_selector, parent, false);
        this.holder = new FeedListViewHolder(view);
        displayButton(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedListViewHolder holder, final int position) {

        final RSSFeedList rssFeedList = rssFL.get(position);
        holder.getTitleFeed().setText(rssFeedList.getTitleFeed());
        holder.getDescriptionFeed().setText(rssFeedList.getDescriptionFeed());
        holder.getUrlFeed().setText(rssFeedList.getUrlFeed());

        holder.getDeleteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("AD", "position ; "+rssFeedList.getId());
                rssEntity = new RssEntity(context);
                String message = "L'enregistrement";
                String notError = " à été supprimé";
                String error = " n'a pas été supprimé, une erreur est survenue...";
                if(rssEntity.deleteFeed(rssFeedList.getId())){
                    Toast toast = Toast.makeText(context, message+notError, Toast.LENGTH_SHORT);
                    toast.show();
                    //refresh activity
                    rssFL.remove(position);
                    notifyDataSetChanged();


                }
                else{
                    Toast toast = Toast.makeText(context, message+error, Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
        holder.getModifyButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ModifyFeedRssActivity.class);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public  int getItemCount(){
        int recordNumber =  rssFL.size();
        Log.i("AD", "Nombre d'enregistrement en base : "+recordNumber);
        return recordNumber;
    }


}



