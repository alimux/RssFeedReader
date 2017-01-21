package apps.database.dnr2i.rssfeedreader.model;

/**
 * Created by alexandre DUCREUX on 21/01/2017.
 */

public class RSSFeedList {

    private  int id;
    private String titleFeed;
    private String descriptionFeed;
    private String urlFeed;
    private String dateFeed;

    public int getId() {
        return id;
    }

    public String getTitleFeed() {
        return titleFeed;
    }

    public String getDescriptionFeed() {
        return descriptionFeed;
    }

    public String getUrlFeed() {
        return urlFeed;
    }

    public String getDateFeed() {
        return dateFeed;
    }

    public void setTitleFeed(String titleFeed) {
        this.titleFeed = titleFeed;
    }

    public void setDescriptionFeed(String descriptionFeed) {
        this.descriptionFeed = descriptionFeed;
    }

    public void setUrlFeed(String urlFeed) {
        this.urlFeed = urlFeed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateFeed(String dateFeed) {
        this.dateFeed = dateFeed;
    }
}
