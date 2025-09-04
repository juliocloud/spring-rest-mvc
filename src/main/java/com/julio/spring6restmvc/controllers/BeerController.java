package com.julio.spring6restmvc.controllers;

import com.julio.spring6restmvc.model.Beer;
import com.julio.spring6restmvc.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @PutMapping("{beerId}")
    public ResponseEntity updateById(@PathVariable("beerId") UUID id, @RequestBody Beer beer) throws Exception {
        beerService.updateById(id, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Beer newBeer){
        Beer saved = beerService.saveNewBeer(newBeer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + saved.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    private List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @RequestMapping(value = "{beerId}", method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable UUID beerId){
        return beerService.getBeerById(beerId);
    }
}
