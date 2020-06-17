package core.service;

import core.domain.Sale;
import core.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class SaleServiceImpl implements SaleService {
    public static final Logger log = LoggerFactory.getLogger(SaleService.class);
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> getAllSales() {
        log.trace("getAllSales called");
        Iterable<Sale> sales = saleRepository.findAll();
        log.trace("getAll - method finished");
        return StreamSupport.stream(sales.spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public void saveSale(Sale sale) {
        log.trace("saveSale called: sale={}",sale);
        saleRepository.save(sale);
        log.trace("saveSale successfully executed");
    }

    @Override
    public void deleteById(Long id) {
        log.trace("deleteById called: id={}",id);
        saleRepository.deleteById(id);
        log.trace("deleteById successfully executed");
    }
}
