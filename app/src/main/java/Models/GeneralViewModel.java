package Models;

public class GeneralViewModel {

    public String url;

    public GeneralViewModel(String thumburl) {
        this.url = thumburl;
    }

    public String getThumburl() {
        return url;
    }

    public void setThumburl(String thumburl) {
        this.url = thumburl;
    }
}
