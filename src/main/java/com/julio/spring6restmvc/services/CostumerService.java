package com.julio.spring6restmvc.services;

import com.julio.spring6restmvc.model.Costumer;

import java.util.List;
import java.util.UUID;

public interface CostumerService {

    List<Costumer> listCostumers();

    Costumer getCostumerById(UUID costumerId);
}
