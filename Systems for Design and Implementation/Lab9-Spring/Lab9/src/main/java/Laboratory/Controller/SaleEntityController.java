package Laboratory.Controller;

import Laboratory.Domain.SaleEntity;

import java.util.List;

public interface SaleEntityController {

    void saveSale(SaleEntity saleEntity);
    List<SaleEntity> getAllSales();
    Integer mostSoldBooks();

}
