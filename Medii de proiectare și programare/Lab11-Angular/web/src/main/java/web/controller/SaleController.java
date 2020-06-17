package web.controller;

import core.domain.Client;
import core.domain.Sale;
import core.service.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.BaseConverter;
import web.dto.ClientDto;
import web.dto.SaleDto;
import web.dto.SalesDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SaleController {
    @Autowired
    private SaleService saleService;

    @Autowired
    BaseConverter<Sale, SaleDto> saleConverter;

    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    List<SaleDto> getSales(){

        List<Sale> sales = saleService.getAllSales();
        return new ArrayList<>(saleConverter.convertModelsToDtos(sales));
    }

    @RequestMapping(value = "/sales", method = RequestMethod.POST)
    void save(@RequestBody SaleDto saleDto){
        saleService.saveSale(saleConverter.convertDtoToModel(saleDto));
    }

    @RequestMapping(value = "/sales/{id}",method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        saleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
