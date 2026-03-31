package core.service;

import core.dto.SupplierDTO;
import core.entity.Product;
import core.entity.Supplier;

import java.util.List;

public interface SupplierService {
    List<Product> listProductsBySupplier(String companyName, int page, int size);
    boolean updateSupplier(SupplierDTO supplierDTO);
    double calculateTotalOrder(String orderID);
    List<Supplier> findByCountry(String country);
    boolean createNewProduct(Product product);
}
