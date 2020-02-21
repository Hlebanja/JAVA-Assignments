package se.chalmers.dm;

public class WebPage {
    public int ID;
    public int author;
    public String URL;
    public String content;
    public int popularity;

    WebPage(int ID, int author, String URL, String content, int popularity) {
        this.ID = ID;
        this.author = author;
        this.URL = URL;
        this.content = content;
        this.popularity = popularity;
    }
}
