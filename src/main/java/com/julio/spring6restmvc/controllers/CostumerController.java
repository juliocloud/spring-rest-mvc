package com.julio.spring6restmvc.controllers;

import com.julio.spring6restmvc.model.Costumer;
import com.julio.spring6restmvc.services.CostumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/costumer")
@RequiredArgsConstructor
public class CostumerController {

    private final CostumerService costumerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Costumer> listCostumers(){
        return costumerService.listCostumers();
    }

    @RequestMapping(value = "/{costumerId}", method = RequestMethod.GET)
    public Costumer getById(@PathVariable("costumerId") UUID id){
        return costumerService.getCostumerById(id);
    }
}
