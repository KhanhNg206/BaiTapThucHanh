package core.service.Impl;

import core.dto.SupplierDTO;
import core.entity.Product;
import core.entity.Supplier;
import core.repository.SupplierRepository;
import core.service.SupplierService;
import infrastructure.db.Neo4jConnection;
import infrastructure.mappper.GenericDataMapper;
import infrastructure.persistence.SupplierRepositoryyImpl;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    private SupplierRepository supplierRepositoryy;

    public SupplierServiceImpl(SupplierRepository supplierRepositoryy) {
        this.supplierRepositoryy = supplierRepositoryy;
    }

    @Override
    public List<Product> listProductsBySupplier(String companyName, int page, int size) {
        if(companyName == null || companyName.equals("")){
            throw new IllegalArgumentException("companyName: Không được null hoặc rỗng  ");
        }
        if(page < 1 || size < 1){
            throw new IllegalArgumentException("page và size: Phải là số nguyên dương (page ≥ 1)  ");
        }
        return supplierRepositoryy.listProductsBySupplier(companyName,page,size);
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) {
        if(supplierDTO == null) {
            throw new IllegalArgumentException("supplier: Không được null");
        }
        if(supplierDTO.getId() == null || supplierDTO.getId().equals("") || supplierDTO.getCompanyName().equals("")) {
            throw new IllegalArgumentException("SupplierID và CompanyName: Không được null hoặc rỗng  ");
        }
        boolean result = supplierRepositoryy.updateSupplier(supplierDTO);
        if(!result){
            throw new IllegalArgumentException("Supplier không tồn tại");
        }return result;
    }

    @Override
    public double calculateTotalOrder(String orderID) {
        if(orderID.equals("")){
            throw new IllegalArgumentException("Đơn hàng không được rỗng");
        }
        return supplierRepositoryy.calculateTotalOrder(orderID);
    }

    @Override
    public List<Supplier> findByCountry(String country) {
        if(country.equals("")){
            throw new IllegalArgumentException("country không được rỗng");
        }
        return supplierRepositoryy.findByCountry(country);
    }

    @Override
    public boolean createNewProduct(Product product) {
        if(product == null){
            throw new IllegalArgumentException("Product kh dc null");
        }
        return supplierRepositoryy.createNewProduct(product);
    }
}
