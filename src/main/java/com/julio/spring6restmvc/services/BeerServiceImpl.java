package com.julio.spring6restmvc.services;

import com.julio.spring6restmvc.model.Beer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer_one = Beer.builder()
                .id(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"))
                .version(1)
                .beerName("Heineke")
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

    @Override
    public Beer saveNewBeer(Beer newBeer) {

        Beer saved = Beer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .version(10)
                .beerName(newBeer.getBeerName())
                .upc(newBeer.getUpc())
                .beerStyle(newBeer.getBeerStyle())
                .price(newBeer.getPrice())
                .quantityOnHand(newBeer.getQuantityOnHand())
                .build();
        beerMap.put(saved.getId() , saved);
        return saved;
    }

    @Override
    public void updateById(UUID id, Beer beer) {
        Beer existingBeer = beerMap.get(id);

        if(Objects.isNull(existingBeer)) {
            log.info("No beer existing with id " + id.toString());
            return;
        }
        existingBeer.setBeerName(beer.getBeerName() != null ? beer.getBeerName() : existingBeer.getBeerName());
        existingBeer.setBeerStyle(beer.getBeerStyle() != null ? beer.getBeerStyle() : existingBeer.getBeerStyle());
        existingBeer.setPrice(beer.getPrice() != null ? beer.getPrice() : existingBeer.getPrice());
        existingBeer.setUpc(beer.getUpc() != null ? beer.getUpc() : existingBeer.getUpc());
        existingBeer.setUpdatedDate(LocalDateTime.now());

        beerMap.put(existingBeer.getId(), existingBeer);
    }
}
