package apps.database.dnr2i.rssfeedreader.model;


import android.sax.Element;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import apps.database.dnr2i.rssfeedreader.R;

import org.w3c.dom.Text;

/**
 * Created by Alexandre DUCREUX on 21/01/2017.
 */


public class ArticleViewHolder extends RecyclerView.ViewHolder {



    private final TextView title;
    private final TextView description;
    private final TextView date;
    private final TextView link;

    public ArticleViewHolder(final View itemView){
    super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        date = (TextView) itemView.findViewById(R.id.date);
        link = (TextView) itemView.findViewById(R.id.link);
    }
    public TextView getTitle() {
        return title;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getLink() {
        return link;
    }

    public TextView getDate() {
        return date;
    }




}
