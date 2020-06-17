package Laboratory.Controller;

import Laboratory.Domain.SaleEntity;
import Laboratory.Repository.SaleEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class SaleEntityControllerImpl implements SaleEntityController{
    public static final Logger log= LoggerFactory.getLogger(SaleEntityController.class);
    @Autowired
    private SaleEntityRepository saleEntityRepository;

    @Override
    public void saveSale(SaleEntity saleEntity) {
        log.trace("addSale called: sale={}",saleEntity);
        saleEntityRepository.save(saleEntity);
        log.trace("addSale executed successfully");
    }

    @Override
    public List<SaleEntity> getAllSales() {
        log.trace("getAll called");
        return saleEntityRepository.findAll();
    }

    @Override
    public Integer mostSoldBooks() {
        log.trace("bookreport called");
        Iterable<SaleEntity> sales= saleEntityRepository.findAll();
        List<Integer> books= new ArrayList<>();
        sales.forEach(x->books.add(x.getClientID()));
        books.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey);
        return null;
    }
}
