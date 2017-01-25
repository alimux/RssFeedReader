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

import static apps.database.dnr2i.rssfeedreader.FeedSelectorActivity.DEFAULT_ENTRIES;
import  apps.database.dnr2i.rssfeedreader.FeedSelectorActivity;
import apps.database.dnr2i.rssfeedreader.ModifyFeedRssActivity;
import apps.database.dnr2i.rssfeedreader.R;
import apps.database.dnr2i.rssfeedreader.SelectedFeedActivity;


/**
 * Class RSS adapter, for Display of Recycler view
 * Created by Alexandre DUCREUX on 21/01/2017.
 */

public class RSSListAdapter extends RecyclerView.Adapter<FeedListViewHolder> {


    private ArrayList<RSSFeedList> rssFL = new ArrayList<>();
    private Context context;
    private RssEntity rssEntity;
    private FeedListViewHolder holder;


    /**
     * construct the list of the available feed
     * @param rssFL
     * @param context
     */
    public RSSListAdapter(ArrayList<RSSFeedList> rssFL, Context context){
        this.rssFL = rssFL;
        this.context = context;
    }

    /**
     * Display or not modify and delete button only if record is found
     * @param holder
     */
    public void displayButton(FeedListViewHolder holder){
        if(rssFL.size()==1 && rssFL.get(0).getId()==-1){
            holder.displaySelectorButton();
        }

    }

    /**
     * Recycle view, create layout
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public FeedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rss_feed_details_selector, parent, false);
        this.holder = new FeedListViewHolder(view);
        displayButton(holder);
        return holder;
    }

    /**
     * adding elements on recycle view and manage clicks on interface
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(FeedListViewHolder holder, final int position) {

        final RSSFeedList rssFeedList = rssFL.get(position);
        holder.getTitleFeed().setText(rssFeedList.getTitleFeed());
        holder.getDescriptionFeed().setText(rssFeedList.getDescriptionFeed());
        holder.getUrlFeed().setText(rssFeedList.getUrlFeed());

        //display feed

            holder.getUrlFeed().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SelectedFeedActivity.class);
                    int id = rssFeedList.getId();
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                }
            });


        //delete item
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
                    //delete record
                    rssFL.remove(position);
                    //notify recycler view
                    notifyItemRemoved(position);
                    //verifying size of ArrayList and if list=0 push default entry
                    if(rssFL.size()==0){
                        rssFeedList.setId(-1);
                        rssFeedList.setTitleFeed("");
                        rssFeedList.setUrlFeed("");
                        rssFeedList.setDescriptionFeed(DEFAULT_ENTRIES);
                        rssFL.add(rssFeedList);
                        notifyItemInserted(position+1);
                    }

                }
                else{
                    Toast toast = Toast.makeText(context, message+error, Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
        //modify item
        holder.getModifyButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ModifyFeedRssActivity.class);
                int id = rssFeedList.getId();
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });

    }

    /**
     * method which count the # of item
     * @return itemCount
     */
    @Override
    public  int getItemCount(){
        int recordNumber =  rssFL.size();
        //Log.i("AD", "Nombre d'enregistrement en base : "+recordNumber);
        return recordNumber;
    }


}



