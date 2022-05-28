package com.nttdata.movies.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.movies.data.Actor;

@Repository
public interface ActorDAO extends CrudRepository<Actor, Long> {

}
