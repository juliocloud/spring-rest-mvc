package com.julio.spring6restmvc.services;

import com.julio.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);
}
