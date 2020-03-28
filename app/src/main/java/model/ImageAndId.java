package model;

import java.io.Serializable;

/**
 * Created by Dhanu on 19-08-2015.
 */
public class ImageAndId implements Serializable {
    int pid;
    byte[] image;

    public ImageAndId() {

    }
    public ImageAndId(int pid, byte[] image) {
        this.pid = pid;
        this.image = image;
    }
    public ImageAndId(byte[] image) {

        this.image = image;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
