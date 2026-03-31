package repository;

import dto.ProductDTO;
import entity.Product;
import entity.Supplier;

import java.util.List;
import java.util.Map;

public interface SupplierRepository {
    List<Product> listproductsBySupplier(String companyName,int page,int size);
    boolean updateSupplier(Supplier supplier);
    double calculateTotalOrder(String orderID);
    boolean createProduct(ProductDTO productDTO);
    Map<String,Integer> getNumberOfProductBySupplier();
    boolean deleteProduct(String productId);
}
