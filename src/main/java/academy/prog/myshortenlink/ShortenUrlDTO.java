package academy.prog.myshortenlink;

public class ShortenUrlDTO extends UrlDTO {

    protected String shortUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
