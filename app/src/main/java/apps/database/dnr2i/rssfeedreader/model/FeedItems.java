package apps.database.dnr2i.rssfeedreader.model;

/**
 * Created by flolaptop on 21/01/2017.
 */

public class FeedItems {
    private  int id;
    private String title;
    private String description;
    private String link;
    private String date;
    private int feedId;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public int getFeedId(){ return this.feedId; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String dateFeed) {
        this.date = dateFeed;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }
}
