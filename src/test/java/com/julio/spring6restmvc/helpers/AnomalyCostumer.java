package com.julio.spring6restmvc.helpers;

import com.julio.spring6restmvc.model.Costumer;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AnomalyCostumer extends Costumer {
    public static Boolean isAnomalyTesting;
}
