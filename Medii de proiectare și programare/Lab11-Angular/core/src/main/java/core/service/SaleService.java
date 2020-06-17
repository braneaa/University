package core.service;

import core.domain.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> getAllSales();
    void saveSale(Sale sale);
    void deleteById(Long id);
}
