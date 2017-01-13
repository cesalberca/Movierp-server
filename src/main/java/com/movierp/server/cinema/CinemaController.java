package com.movierp.server.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/cinemas")
public class CinemaController {
    private final CinemaDao cinemaDao;

    @Autowired
    public CinemaController(CinemaDao cinemaDao) {
        this.cinemaDao = cinemaDao;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity create(@RequestBody Cinema cinema) {
        cinemaDao.save(cinema);
        return new ResponseEntity(cinema, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Cinema> getCinemas() {
        return (List<Cinema>) cinemaDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Cinema getCinema(@PathVariable("id") long id) {
        return cinemaDao.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        cinemaDao.delete(id);
    }
}
