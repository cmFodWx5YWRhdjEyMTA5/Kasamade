package model;


/**
 * Created by Dhanu on 03-08-2015.
 */
public class MessageDetail {
    // private variables
    int pid;

    int webpid;

    String name;

    String image;

    String description;

    String msg_from;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    String link;

    public String getMsg_from() {
        return msg_from;
    }

    public void setMsg_from(String msg_from) {
        this.msg_from = msg_from;
    }

    int del;

    int isread;

    public int getIsread() {
        return isread;
    }

    public void setIsread(int isread) {
        this.isread = isread;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public int getWebpid() {
        return webpid;
    }

    public void setWebpid(int webpid) {
        this.webpid = webpid;
    }


//int del;

    // Empty constructor
    public MessageDetail() {

    }

    // constructor
    public MessageDetail(int keyId, int webpid, String name, String image, String details, String msg_from, int del, int isread) {
        this.pid = keyId;
        this.webpid=webpid;
        this.name = name;
        this.image = image;
        this.description=details;
        this.msg_from=msg_from;
        this.del=del;
        this.isread=isread;

    }

    // constructor
    public MessageDetail(String name, String image) {
        this.name = name;
        this.image = image;
    }
    public MessageDetail(int webpid, String name, String image, String details, String msg_from, int del, int isread) {
        this.webpid=webpid;
        this.name = name;
        this.image = image;
        this.description=details;
        this.msg_from=msg_from;
        this.del=del;
        this.isread=isread;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
