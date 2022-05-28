package com.nttdata.movies.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nttdata.movies.dao.MovieDAO;
import com.nttdata.movies.data.Movie;

@Controller
public class MovieController {
	
	@Autowired
	private MovieDAO movieDao;
	
	public Movie movie = new Movie();
	public String titleButton = "Create";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("movies", this.movieDao.findAll());
		model.addAttribute("movie", movie);
		model.addAttribute("titleButton", titleButton);
		return "movies";
	}
	
	@RequestMapping(value = "/updateMovie", method = RequestMethod.POST)
	public String addMovie(@ModelAttribute("movie") Movie movieNew) {
		Long id = movieNew.getId();
		if (Objects.nonNull(id) && id > 0) {
			movie = movieDao.findById(id).get();
		}
		movie.setTitle(movieNew.getTitle());
		movie.setDescription(movieNew.getDescription());
		movie.setDescriptionGenres(movieNew.getDescriptionGenres());
		movie.setReleaseYear(movieNew.getReleaseYear());
		movieDao.save(movie);
		reset();
	return "redirect:/";
	}
	
	@RequestMapping(value = "/view/{id}")
	public String viewMovie(@PathVariable Long id, Model model) {
		movie = movieDao.findById(id).get();
		this.titleButton = "Update";
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteMovie(@PathVariable Long id, Model model) {
		Movie movieDelete = movieDao.findById(id).get();
		movieDao.delete(movieDelete);
		reset();
		return "redirect:/";
	}
	
	private void reset() {
		movie = new Movie();
		titleButton = "Create";
	}
	
}
