package com.julio.spring6restmvc.helpers;

public class Helper {
    public static Integer costumerCount;
    public static Integer beerCount;

    public AnomalyBeer generateBeer(){
        return AnomalyBeer.builder()
                .beerName("AwesomeBeer" + beerCount++)
                .build();
    }

    public AnomalyCostumer generateCostumer(){
        return AnomalyCostumer.builder()
                .name("AwesomeCostumer" + costumerCount++)
                .build();
    }
}
