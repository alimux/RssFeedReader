package apps.database.dnr2i.rssfeedreader.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import apps.database.dnr2i.rssfeedreader.R;

import static android.provider.BaseColumns._ID;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DATE;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_DESCRIPTION;
import static apps.database.dnr2i.rssfeedreader.db.Contract.Entry.COLUMN_NAME_TITLE;

/**
 * Alexandre DUCREUX on 21/01/2017.
 */

public class FeedListViewHolder extends RecyclerView.ViewHolder {

    private final TextView titleFeed;
    private final TextView descriptionFeed;
    private final TextView urlFeed;
    private final ImageButton modifyButton;
    private final ImageButton deleteButton;

    public FeedListViewHolder(final View itemView){
        super(itemView);

        titleFeed = (TextView) itemView.findViewById(R.id.titleFeed);
        descriptionFeed = (TextView) itemView.findViewById(R.id.descriptFeed);
        urlFeed = (TextView) itemView.findViewById(R.id.urlFeed);
        modifyButton = (ImageButton) itemView.findViewById(R.id.modifyButton);
        deleteButton = (ImageButton) itemView.findViewById(R.id.deleteButton);

    }

    public TextView getTitleFeed() {
        return titleFeed;
    }

    public TextView getDescriptionFeed() {
        return descriptionFeed;
    }

    public TextView getUrlFeed() {
        return urlFeed;
    }

    public ImageButton getModifyButton() {
        return modifyButton;
    }

    public ImageButton getDeleteButton() {
        return deleteButton;
    }
}
