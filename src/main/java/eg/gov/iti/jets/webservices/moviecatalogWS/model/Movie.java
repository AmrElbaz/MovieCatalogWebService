package eg.gov.iti.jets.webservices.moviecatalogWS.model;

public class Movie {
    private String title;
    // Add more attributes as needed

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie{title='" + title + '\'' + '}';
    }
}
