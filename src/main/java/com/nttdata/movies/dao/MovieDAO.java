package com.nttdata.movies.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.movies.data.Movie;

@Repository
public interface MovieDAO extends CrudRepository<Movie, Long> {

}

