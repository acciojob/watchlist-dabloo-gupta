package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    Map<String,Movie> mv = new HashMap<>();
    Map<String, Director> di = new HashMap<>();
    Map<String, List<String>> dr = new HashMap<>();

    public void saveMovie(Movie movie) {
        mv.put(movie.getName(), movie);
    }

    public void saveDiector(Director director) {
        di.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director) {
        if(mv.containsKey(movie) && di.containsKey(director)){
            List<String> currentMovies = new ArrayList<>();
            if(dr.containsKey(director)) currentMovies = dr.get(director);
            currentMovies.add(movie);
            dr.put(director, currentMovies);
        }
    }

    public List<String> findMoviesFromDirector(String director) {
        List<String> moviesList = new ArrayList<>();
        if(dr.containsKey(director)) moviesList = dr.get(director);
        return moviesList;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(mv.keySet());
    }

    public void deleteDirector(String director) {
        List<String> movies = new ArrayList<>();
        if(dr.containsKey(director)){
            movies = dr.get(director);
            for(String movie: movies){
                if(mv.containsKey(movie)){
                    mv.remove(movie);
                }
            }
            dr.remove(director);
        }

        if(dr.containsKey(director)){
            dr.remove(director);
        }
    }

    public void deleteAllDirectors() {
        HashSet<String> movieSet = new HashSet<>();
        for(String director: dr.keySet()){
            for(String movie: dr.get(director)){
                movieSet.add(movie);
            }
        }
        for(String movie: movieSet){
            if(mv.containsKey(movie)){
                mv.remove(movie);
            }
        }
    }

    public String getDirectorName(String movie) {
           HashSet<String> movieSet = new HashSet<>();
           for(String director: dr.keySet()){
               if(dr.get(director).contains(movie))
                   return director;
           }
           return "NoMovieSuchFound";
    }

    public Movie findMovie(String movieName) {
        return mv.get(movieName);
    }

    public Director findDiector(String directorName) {
        return di.get(directorName);
    }
}
