package com.julio.spring6restmvc.controllers;

import com.julio.spring6restmvc.model.Beer;
import com.julio.spring6restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer/";
    public static final String BEER_PATH_ID = BEER_PATH + "{beerId}";

    private final BeerService beerService;

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable UUID beerId){
        beerService.deleteById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("beerId") UUID id, @RequestBody Beer beer) throws Exception {
        beerService.updateById(id, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = BEER_PATH)
    public ResponseEntity handlePost(@RequestBody Beer newBeer){
        Beer saved = beerService.saveNewBeer(newBeer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + saved.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = BEER_PATH, method = RequestMethod.GET)
    private List<Beer> listBeers(){
        return beerService.listBeers();
    }

    @RequestMapping(value = BEER_PATH_ID, method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable UUID beerId){
        return beerService.getBeerById(beerId);
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity patchBeerById(@PathVariable UUID beerId, @RequestBody Beer beer){
        beerService.patchBeerById(beerId, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
