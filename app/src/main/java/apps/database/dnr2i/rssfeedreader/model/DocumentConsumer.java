package apps.database.dnr2i.rssfeedreader.model;

/**
 * Created by Alexandre DUCREUX on 21/01/2017.
 * Interface to receive a DOM Document
 */
import org.w3c.dom.Document;

public interface DocumentConsumer {

    void setXMLDocument(Document document);
}
