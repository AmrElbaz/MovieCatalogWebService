# Movie Catalog Web Service

This project provides a Movie Catalog Web Service using Jakarta JAX-WS. The web service retrieves movie details from an XML file using DOM and top-rated movies from a JSON file using TrAX.

## Features

- **getMovieDetails(String title):** Retrieve details for a specific movie.
- **getTopRatedMovies():** Retrieve a list of top-rated movies.

## Setup

1. Clone the repository.
2. Import the project into your preferred IDE.
3. Configure your Jakarta EE-compatible server (e.g., Apache Tomcat).
4. Deploy the project to the server.

## Data Sources

- **movie_details.xml:** Contains movie details in XML format. Customize the XML structure based on your needs.
- **top_rated_movies.json:** JSON file with top-rated movies. TrAX is used for transforming JSON to XML.

## Usage

Use a SOAP client or a Java client to test the provided web service methods.

1. **getMovieDetails(String title):** Retrieve details for a specific movie. Example request: `getMovieDetails("Inception")`.
2. **getTopRatedMovies():** Retrieve a list of top-rated movies.

Ensure to customize the XML and JSON files with actual data for testing.

## Customization

Feel free to expand the functionality and customize the project as needed. Consider adding more endpoints, improving error handling, or integrating additional data sources.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

---

**Note:** This README template is just a starting point. Customize it according to your project structure, features, and additional information you want to include.
