package com.julio.spring6restmvc.services;

import com.julio.spring6restmvc.model.Beer;
import com.julio.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Get beer id was called");
        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Heineken")
                .beerStyle(BeerStyle.IPA)
                .upc("1234")
                .price(BigDecimal.TEN)
                .quantityOnHand(12)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
