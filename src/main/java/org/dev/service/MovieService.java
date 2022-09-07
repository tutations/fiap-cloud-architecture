package org.dev.service;

import javax.inject.Inject;

import org.dev.entity.MovieEntity;
import org.dev.repository.MovieRepository;

import javax.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieService implements CrudService<MovieEntity> {

    @Inject
    MovieRepository movieRepository;

    public List<MovieEntity> findAll() {
        return movieRepository.findAll();
    }

    public Optional<MovieEntity> save(MovieEntity entity) {
        return Optional.ofNullable(movieRepository.save(entity));
    }

    public Optional<MovieEntity> findById(long id) {
        return movieRepository.findById(id);
    }

    public void delete(MovieEntity entity) {
        movieRepository.delete(entity);
    }

    public void deleteById(long id) {
        movieRepository.deleteById(id);
    }

    public long count() {
        return movieRepository.count();
    }
}