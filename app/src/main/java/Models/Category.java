package Models;

public class Category {


    public String title;
    public String thumburl;

    public Category(String title, String thumburl) {

        this.title = title;
        this.thumburl = thumburl;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getTitle() {
        return title;

    }

    public String getThumburl() {
        return thumburl;
    }
}
