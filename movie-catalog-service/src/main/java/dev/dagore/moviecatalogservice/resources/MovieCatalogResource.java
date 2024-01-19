package dev.dagore.moviecatalogservice.resources;

import dev.dagore.moviecatalogservice.models.CatalogItem;
import dev.dagore.moviecatalogservice.models.Movie;
import dev.dagore.moviecatalogservice.models.Rating;
import dev.dagore.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratings-data/users/" + userId, UserRating.class);

        return ratings.userRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.movieId(), Movie.class);
            return new CatalogItem(movie.name(), "Description", rating.rating());
        }).collect(Collectors.toList());
    }
}
