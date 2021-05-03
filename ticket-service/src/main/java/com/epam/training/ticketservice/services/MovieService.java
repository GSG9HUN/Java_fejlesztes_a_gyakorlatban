package com.epam.training.ticketservice.services;

import com.epam.training.ticketservice.Modell.Movie;
import com.epam.training.ticketservice.exceptions.EmptyListException;
import com.epam.training.ticketservice.exceptions.CrudException;
import com.epam.training.ticketservice.repository.impl.JpaMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private JpaMovieRepository jpaMovieRepository;

    public MovieService(JpaMovieRepository jpaMovieRepository) {
        this.jpaMovieRepository=jpaMovieRepository;
    }

    public void createMovie(String movieName, String movieCategory, String moviesLength){

        Movie movie = new Movie(movieName,movieCategory,Integer.parseInt(moviesLength));
        jpaMovieRepository.saveMovie(movie);

    }

    public List<Movie> listMovies() throws EmptyListException{
        List<Movie> movies = jpaMovieRepository.getAll();
        if(movies.isEmpty()){
            throw new EmptyListException("Theres no movies at the moment");
        }
        return  movies;
    }


    public void deleteMovie(String movieName) throws CrudException {
        jpaMovieRepository.deleteMovie(movieName);
    }

    public void updateMovie(String movieName, String movieCategory, String moviesLength) throws CrudException {
        jpaMovieRepository.updateMovie(movieName,movieCategory,Integer.parseInt(moviesLength));
    }
}
