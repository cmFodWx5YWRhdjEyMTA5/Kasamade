package Bean;

/**
 * Created by Prince on 8/10/2016.
 */
public class Bean_id_title {

    String id;
    String title;

    public Bean_id_title() {
    }

    public Bean_id_title(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
