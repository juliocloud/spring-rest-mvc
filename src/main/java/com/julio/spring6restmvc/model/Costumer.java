package com.julio.spring6restmvc.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Costumer {
    private UUID id;
    private String name;
    private BigDecimal version;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
