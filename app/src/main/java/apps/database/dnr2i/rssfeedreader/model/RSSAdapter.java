package apps.database.dnr2i.rssfeedreader.model;

import android.sax.Element;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Document;

import apps.database.dnr2i.rssfeedreader.FeedSelectorActivity;
import apps.database.dnr2i.rssfeedreader.R;

/**
 * Class Adpater to prepare Display
 * Created by Alexandre DUCREUX on 21/01/2017.
 */
/*
public class RSSAdapter extends RecyclerView.Adapter<ArticleViewHolder> implements DocumentConsumer{

    private Document document = null;



    @Override
    public  int getItemCount(){
        if(document!=null){
            return document.getElementsByTagName("item").getLength();
        }
        else {
            return 0;
        }
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_feed_selector, parent, false);
        return new ArticleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position){
        Element item =(Element) document.getElementsByTagName("item").item(position);
        holder.setElement(item);
    }

    @Override
    public void setXMLDocument(Document document) {
        this.document = document;
        notifyDataSetChanged();
    }
}
*/