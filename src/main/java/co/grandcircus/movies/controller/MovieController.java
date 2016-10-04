/*
 * Source Material (c) 2016 GCD
 * All rights reserved
 */
package co.grandcircus.movies.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.movies.dao.MovieDao;
import co.grandcircus.movies.model.Movie;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieDao movieDao;

	/**
	 * List (READ) all movies
	 */
	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public String listMovies(Model model, @RequestParam(value="category", required=false) String category) {
		List<Movie> movies;
		if (category != null && movieDao.isValidCategory(category)) {
			movies = movieDao.getMoviesByCategory(category);
			model.addAttribute("category", category);
		} else {
			movies = movieDao.getAllMovies();
		}

		model.addAttribute("movies",movies);

		logger.info("/movies -> movie-list.jsp");
		return "movie-list";
	}

	/**
	 * Display one movie
	 */
	@RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
	public String displayMovie(@PathVariable int id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("movie", movieDao.getMovie(id));

		logger.info("GET /movies/" + id + " -> movie.jsp");
		return "movie";
	}

	/**
	 * Save one movie (UPDATE)
	 */
	@RequestMapping(value = "/movies/{id}", method = RequestMethod.POST)
	public String saveMovie(@PathVariable int id, Movie movie, Model model) {
		movieDao.updateMovie(id, movie);
		model.addAttribute("id", id);
		model.addAttribute("movie", movie);

		logger.info("POST /movies/" + id + " -> movie.jsp");
		return "movie";
	}

	/**
	 * Delete one movie (DELETE)
	 * 
	 */
	@RequestMapping(value = "/movies/{id}/delete", method = RequestMethod.POST)
	public String deleteMovie(@PathVariable int id, Model model) {
		movieDao.deleteMovie(id);
		model.asMap().clear();

		logger.info("POST /movies/" + id + "/delete -> redirect to /movies");
		return "redirect:/movies";
	}

	/**
	 * Display GET one movie to populate CREATE form
	 */
	@RequestMapping(value = "/movies/create", method = RequestMethod.GET)
	public String createMovieForm(Model model) {
		model.addAttribute("movie", new Movie());

		logger.info("GET /movies/create -> movie-create.jsp");
		return "movie-create";
	}

	/**
	 * Save new movie (CREATE)
	 */
	@RequestMapping(value = "/movies/create", method = RequestMethod.POST)
	public String createMovie(Movie movie, Model model) {
		movieDao.addMovie(movie);
		model.asMap().clear();

		logger.info("POST /movies/create -> redirect to /movies");
		return "redirect:/movies";
	}

}
