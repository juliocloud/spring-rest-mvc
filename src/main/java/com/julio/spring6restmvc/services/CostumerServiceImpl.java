package com.julio.spring6restmvc.services;

import com.julio.spring6restmvc.model.Costumer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CostumerServiceImpl implements CostumerService{
    private Map<UUID, Costumer> costumerMap;

    public CostumerServiceImpl() {

    }

    @Override
    public List<Costumer> listCostumers(){
        return new ArrayList<>(costumerMap.values());
    }

    @Override
    public Costumer getCostumerById(UUID costumerId){
        return costumerMap.get(costumerId);
    }
}
