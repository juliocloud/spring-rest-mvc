package com.julio.spring6restmvc.services;

import com.julio.spring6restmvc.model.Costumer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CostumerService {

    //List<Costumer> listCostumers();

    List<Costumer> listCostumers();

    Optional<Costumer> getCostumerById(UUID costumerId);

    Costumer createCostumer(Costumer costumer);

    void updateById(UUID costumerId, Costumer costumer);

    void deleteById(UUID costumerId);

    void patchById(UUID costumerId, Costumer costumer);
}
