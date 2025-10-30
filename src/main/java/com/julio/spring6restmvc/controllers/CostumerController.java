package com.julio.spring6restmvc.controllers;

import com.julio.spring6restmvc.model.Beer;
import com.julio.spring6restmvc.model.Costumer;
import com.julio.spring6restmvc.services.BeerService;
import com.julio.spring6restmvc.services.CostumerService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.ColorSupported;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/costumer")
@RequiredArgsConstructor
public class CostumerController {

    private final CostumerService costumerService;
    private final BeerService beerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Costumer> listCostumers(){
        return costumerService.listCostumers();
    }

    @RequestMapping(value = "/{costumerId}", method = RequestMethod.GET)
    public Costumer getById(@PathVariable("costumerId") UUID id){
        return costumerService.getCostumerById(id);
    }

    @PostMapping
    public ResponseEntity<Costumer> createCostumer(@RequestBody Costumer costumer){
        Costumer created = costumerService.createCostumer(costumer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/costumer/" + created.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("{costumerId}")
    public ResponseEntity<Costumer> updateCostumerById(@PathVariable("costumerId") String costumerId, @RequestBody Costumer costumer){
        UUID cId = UUID.fromString(costumerId);
        costumerService.updateById(cId, costumer);
        return new ResponseEntity<Costumer>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{costumerId}")
    public ResponseEntity deleteById(@PathVariable UUID costumerId){
        costumerService.deleteById(costumerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
