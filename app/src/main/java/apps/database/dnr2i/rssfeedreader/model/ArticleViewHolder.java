package apps.database.dnr2i.rssfeedreader.model;


import android.sax.Element;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import apps.database.dnr2i.rssfeedreader.R;

import org.w3c.dom.Text;

/**
 * Class to initialize the render of the recycle view
 * Created by Alexandre DUCREUX on 21/01/2017.
 */


public class ArticleViewHolder extends RecyclerView.ViewHolder {



    private final TextView title;
    private final TextView description;
    private final TextView date;
    private final TextView link;

    /**
     * construct view
     * @param itemView
     */
    public ArticleViewHolder(final View itemView){
    super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        date = (TextView) itemView.findViewById(R.id.date);
        link = (TextView) itemView.findViewById(R.id.link);
    }

    /**
     * getTitle
     * @return title
     */
    public TextView getTitle() {
        return title;
    }

    /**
     * getDescription
     * @return description
     */
    public TextView getDescription() {
        return description;
    }

    /**
     * getLink
     * @return link
     */
    public TextView getLink() {
        return link;
    }

    /**
     * getDate
     * @return date
     */
    public TextView getDate() {
        return date;
    }




}
