package com.julio.spring6restmvc.services;

import com.julio.spring6restmvc.model.Costumer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CostumerServiceImpl implements CostumerService{
    private Map<UUID, Costumer> costumerMap;

    public CostumerServiceImpl() {
        this.costumerMap = new HashMap<>();

        Costumer costumer1 = Costumer.builder()
                .name("Claudio Seña")
                .id(UUID.randomUUID())
                .updatedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .version(BigDecimal.TEN)
                .build();

        Costumer costumer2 = Costumer.builder()
                .name("Leandra")
                .id(UUID.randomUUID())
                .updatedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .version(BigDecimal.TEN)
                .build();

        this.costumerMap.put(costumer1.getId(), costumer1);
        this.costumerMap.put(costumer2.getId(), costumer2);
    }

    @Override
    public List<Costumer> listCostumers(){
        return new ArrayList<>(costumerMap.values());
    }

    @Override
    public Costumer getCostumerById(UUID costumerId){
        return costumerMap.get(costumerId);
    }

    @Override
    public Costumer createCostumer(Costumer costumer){
        Costumer newCostumer = Costumer.builder()
                .id(UUID.randomUUID())
                .name(costumer.getName())
                .createdDate(LocalDateTime.now())
                .version(BigDecimal.TEN)
                .updatedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();

        costumerMap.put(newCostumer.getId(), newCostumer);

        return newCostumer;
    }
}
