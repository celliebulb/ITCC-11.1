import java.util.*;

public class User {
    String name;
    List<Movie> watchHistory = new ArrayList<>();
    Map<String, Integer> genreWatchTime = new HashMap<>();

    public User(String name) {
        this.name = name;
    }

    public void watchMovie(Movie movie, int watchTime) {
        watchHistory.add(movie);
        genreWatchTime.put(movie.genre, genreWatchTime.getOrDefault(movie.genre, 0) + watchTime);
        System.out.println("You watched " + watchTime + " minutes of '" + movie.title + "'.");
    }

    public String getFavoriteGenre() {
        return genreWatchTime.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public List<Movie> getRecommendations(List<Movie> movies) {
        String favoriteGenre = getFavoriteGenre();
        if (favoriteGenre == null) {
            Collections.shuffle(movies);
            return movies.subList(0, Math.min(3, movies.size()));
        }
        List<Movie> recommendations = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.genre.equals(favoriteGenre)) {
                recommendations.add(movie);
            }
            if (recommendations.size() >= 3) break;
        }
        return recommendations;
    }
}