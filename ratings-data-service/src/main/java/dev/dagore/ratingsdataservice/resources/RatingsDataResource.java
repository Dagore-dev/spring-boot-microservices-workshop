package dev.dagore.ratingsdataservice.resources;

import dev.dagore.ratingsdataservice.models.Rating;
import dev.dagore.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("ratings-data")
public class RatingsDataResource {

    @GetMapping("{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("users/{userId}")
    public UserRating getRatingsOfUser(@PathVariable String userId) {
        return new UserRating(Arrays.asList(
                new Rating("1234", 4),
                new Rating("4321", 3),
                new Rating("asdw", 1)
        ));
    }
}
