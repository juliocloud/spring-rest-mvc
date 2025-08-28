package com.julio.spring6restmvc.services;

import com.julio.spring6restmvc.model.Beer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer_one = Beer.builder()
                .id(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"))
                .version(1)
                .beerName("Heineken")
                .build();

        Beer beer_two = Beer.builder()
                .id(UUID.randomUUID())
                .version(2)
                .beerName("Brahma")
                .build();

        this.beerMap.put(beer_one.getId(), beer_one);
        this.beerMap.put(beer_two.getId(), beer_two);
    }

    private Map<UUID, Beer> beerMap;

    @Override
    public List<Beer> listBeers(){
        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Get beer id was called");
        return beerMap.get(id);
    }
}
