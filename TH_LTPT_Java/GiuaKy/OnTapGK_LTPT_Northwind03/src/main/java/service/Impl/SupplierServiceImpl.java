package service.Impl;

import dto.ProductDTO;
import entity.Product;
import entity.Supplier;
import repository.SupplierRepository;
import service.SuppierService;

import java.util.List;
import java.util.Map;

public class SupplierServiceImpl implements SuppierService {
    private SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Product> listproductsBySupplier(String companyName, int page, int size) {
        if(companyName == null || companyName.equals("")){
            throw new IllegalArgumentException("companyName: Không được null hoặc rỗng ");
        }
        if(page < 1 || size < 1){
            throw new IllegalArgumentException("page và size : Phải là số nguyên dương (page ≥ 1) ");
        }
        return supplierRepository.listproductsBySupplier(companyName,page,size);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        if(supplier == null){
            throw new IllegalArgumentException("supplier: Không được null ");
        }
        if(supplier.getId() == null){
            throw new IllegalArgumentException("supplierID: Không được null ");
        }
        boolean temp = supplierRepository.updateSupplier(supplier);
        if(!temp){
            throw  new IllegalArgumentException("nhà cung cấp không tồn tại.");
        }return  temp;
    }

    @Override
    public double calculateTotalOrder(String orderID) {
        return supplierRepository.calculateTotalOrder(orderID);
    }

    @Override
    public boolean createProduct(ProductDTO productDTO) {
        if(productDTO == null){
            throw new IllegalArgumentException("Product không được rỗng");
        }
        return supplierRepository.createProduct(productDTO);
    }

    @Override
    public Map<String, Integer> getNumberOfProductBySupplier() {
        return supplierRepository.getNumberOfProductBySupplier();
    }

    @Override
    public boolean deleteProduct(String productId) {
        return supplierRepository.deleteProduct(productId);
    }
}
