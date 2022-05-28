package com.nttdata.movies.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nttdata.movies.dao.ActorDAO;
import com.nttdata.movies.data.Actor;

@Controller
@RequestMapping("/actor")
public class ActorController {

	@Autowired
	private ActorDAO actorDao;
	
	public Actor actor = new Actor();
	public String titleButton = "Create";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("actors", this.actorDao.findAll());
		model.addAttribute("actor", actor);
		model.addAttribute("titleButton", titleButton);
		return "actor";
	}
	
	@RequestMapping(value = "/updateActor", method = RequestMethod.POST)
	public String addMovie(@ModelAttribute("actor") Actor actorNew) {
		Long id = actorNew.getId();
		if (Objects.nonNull(id) && id > 0) {
			actor = actorDao.findById(id).get();
		}
		actor.setName(actorNew.getName());
		actor.setAge(actorNew.getAge());
		actorDao.save(actorNew);
		reset();
	return "redirect:/";
	}
	
	@RequestMapping(value = "/view/{id}")
	public String viewMovie(@PathVariable Long id, Model model) {
		actor = actorDao.findById(id).get();
		this.titleButton = "Update";
		return "redirect:/";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteMovie(@PathVariable Long id, Model model) {
		Actor movieDelete = actorDao.findById(id).get();
		actorDao.delete(movieDelete);
		reset();
		return "redirect:/";
	}
	
	private void reset() {
		actor = new Actor();
		titleButton = "Create";
	}
}
