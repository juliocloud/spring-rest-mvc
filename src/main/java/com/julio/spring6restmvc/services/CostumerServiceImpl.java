package com.julio.spring6restmvc.services;

import com.julio.spring6restmvc.controllers.NotFoundException;
import com.julio.spring6restmvc.model.Costumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
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
    public Optional<Costumer> getCostumerById(UUID costumerId){
        return Optional.of(costumerMap.get(costumerId));
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

    @Override
    public void updateById(UUID costumerId, Costumer costumer){
        Costumer originalCostumer = this.getCostumerById(costumerId).orElseThrow(NotFoundException::new);
        if(Objects.isNull(originalCostumer)){
            log.info("No beer existing with id " + costumerId.toString());
            return;
        }
        originalCostumer.setName(costumer.getName());
        originalCostumer.setVersion(costumer.getVersion());
        originalCostumer.setUpdatedDate(LocalDateTime.now());
        costumerMap.put(originalCostumer.getId(), originalCostumer);
    }

    @Override
    public void deleteById(UUID costumerId) {
        costumerMap.remove(costumerId);
    }

    @Override
    public void patchById(UUID costumerId, Costumer costumer) {
        Costumer existing = costumerMap.get(costumerId);

        if(StringUtils.hasText(costumer.getName()))
            existing.setName(costumer.getName());
    }
}
