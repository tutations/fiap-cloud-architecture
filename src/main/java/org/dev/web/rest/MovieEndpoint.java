package org.dev.web.rest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.dev.entity.MovieEntity;
import org.dev.service.MovieService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieEndpoint {

    @Inject
    MovieService movieService;

    @GET
    public List<MovieEntity> getMovies() {
        return movieService.findAll();
    }

    @POST
    @Transactional
    public Response addMovie(MovieEntity movie) {
        movie = movieService
                .save(movie)
                .orElseThrow(NoSuchElementException::new);
        return Response.ok(movie).status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response findMovie(@PathParam("id") Long id) {
        Optional<MovieEntity> movie = movieService.findById(id);
        if (!movie.isEmpty()) {
            return Response.ok(movie.get())
                    .status(Response.Status.OK)
                    .build();
        } else {
            throw new WebApplicationException("Movie with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateMovie(@PathParam("id") Long id, MovieEntity c) {
        Optional<MovieEntity> movie = movieService.findById(id);
        if (movie.isPresent()) {
            movie.get().setDescription(c.getDescription());
            movie.get().setTitle(c.getTitle());
            movieService.save(movie.get());
            return Response.ok(movie.get())
                    .status(Response.Status.OK)
                    .build();
        } else {
            throw new WebApplicationException("Movie with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteMovie(@PathParam("id") Long id) {
        Optional<MovieEntity> movie = movieService.findById(id);
        if (movie.isPresent()) {
            movieService.delete(movie.get());
            return Response.status(Response.Status.NO_CONTENT)
                    .build();
        } else {
            throw new WebApplicationException("Movie with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
    }
}
