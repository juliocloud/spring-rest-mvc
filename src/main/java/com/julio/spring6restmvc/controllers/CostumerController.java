package com.julio.spring6restmvc.controllers;

import com.julio.spring6restmvc.model.Costumer;
import com.julio.spring6restmvc.services.CostumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CostumerController {

    public static final String COSTUMER_PATH = "/api/v1/costumer/";
    public static final String COSTUMER_PATH_ID = COSTUMER_PATH + "{costumerId}";

    private final CostumerService costumerService;

    @RequestMapping(value = COSTUMER_PATH, method = RequestMethod.GET)
    public List<Costumer> listCostumers(){
        return costumerService.listCostumers();
    }

    @RequestMapping(value = COSTUMER_PATH_ID, method = RequestMethod.GET)
    public Costumer getById(@PathVariable("costumerId") UUID id){
        return costumerService.getCostumerById(id);
    }

    @PostMapping(value = COSTUMER_PATH)
    public ResponseEntity<Costumer> createCostumer(@RequestBody Costumer costumer){
        Costumer created = costumerService.createCostumer(costumer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/costumer/" + created.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = COSTUMER_PATH_ID)
    public ResponseEntity<Costumer> updateCostumerById(@PathVariable("costumerId") String costumerId, @RequestBody Costumer costumer){
        UUID cId = UUID.fromString(costumerId);
        costumerService.updateById(cId, costumer);
        return new ResponseEntity<Costumer>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = COSTUMER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable UUID costumerId){
        costumerService.deleteById(costumerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = COSTUMER_PATH_ID)
    public ResponseEntity patchById(@PathVariable UUID costumerId, @RequestBody Costumer costumer){
        costumerService.patchById(costumerId, costumer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
