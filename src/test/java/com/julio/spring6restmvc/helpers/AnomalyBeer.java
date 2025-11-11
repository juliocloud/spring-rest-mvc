package com.julio.spring6restmvc.helpers;

import com.julio.spring6restmvc.model.Beer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AnomalyBeer extends Beer {
    public static Boolean isAnomalyTesting;
}
