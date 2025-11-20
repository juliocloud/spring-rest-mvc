package com.julio.spring6restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beer {
    private UUID id;
    private Integer version;
    private String beerName;
    private BeerStyle beerStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
