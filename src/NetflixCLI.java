import java.util.*;

public class NetflixCLI {
    Scanner scanner = new Scanner(System.in);
    List<Movie> movies = Arrays.asList(
            new Movie("Inception", "Sci-Fi", 148),
            new Movie("Titanic", "Romance", 195),
            new Movie("Avengers", "Action", 143),
            new Movie("The Conjuring", "Horror", 112),
            new Movie("Interstellar", "Sci-Fi", 169),
            new Movie("The Notebook", "Romance", 123)
    );
    User user;

    public void start() {
        System.out.println("Welcome to Netflix CLI!");
        System.out.print("Enter your name: ");
        user = new User(scanner.nextLine());
        while (true) {
            System.out.println("\n1. View Movies\n2. Watch a Movie\n3. View Recommendations\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> showMovies();
                case 2 -> watchMovie();
                case 3 -> showRecommendations();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void showMovies() {
        System.out.println("\nAvailable Movies:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i).title + " (" + movies.get(i).genre + ") - " + movies.get(i).duration + " min");
        }
    }

    private void watchMovie() {
        showMovies();
        System.out.print("Select a movie number to watch: ");
        int choice = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        if (choice >= 0 && choice < movies.size()) {
            Movie movie = movies.get(choice);
            System.out.print("How many minutes did you watch of '" + movie.title + "'? ");
            int watchTime = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            user.watchMovie(movie, watchTime);
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private void showRecommendations() {
        List<Movie> recommendations = user.getRecommendations(movies);
        if (!recommendations.isEmpty()) {
            System.out.println("\nRecommended Movies for You:");
            for (Movie movie : recommendations) {
                System.out.println("- " + movie.title + " (" + movie.genre + ")");
            }
        } else {
            System.out.println("No recommendations yet. Watch more movies!");
        }
    }
}