package Bean;

/**
 * Created by Prince on 7/22/2016.
 */
public class Bean_Master {

    String id;
    String title;
    String desc;
    String image_url;
    String video_url;
    String author;
    String source_name;
    String read_more_link;
    String news_type;

    public Bean_Master() {
    }

    public Bean_Master(String id, String title, String desc, String image_url, String video_url, String author, String read_more_link) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.image_url = image_url;
        this.video_url = video_url;
        this.author = author;
        this.read_more_link = read_more_link;
    }


    public Bean_Master(String id, String title, String desc, String image_url, String video_url, String author, String source_name, String read_more_link) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.image_url = image_url;
        this.video_url = video_url;
        this.author = author;
        this.source_name = source_name;
        this.read_more_link = read_more_link;
    }

    public Bean_Master(String id, String title, String desc, String image_url, String author, String read_more_link) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.image_url = image_url;
        this.author = author;
        this.read_more_link = read_more_link;
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

    public String   getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRead_more_link() {
        return read_more_link;
    }

    public void setRead_more_link(String read_more_link) {
        this.read_more_link = read_more_link;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }
}
