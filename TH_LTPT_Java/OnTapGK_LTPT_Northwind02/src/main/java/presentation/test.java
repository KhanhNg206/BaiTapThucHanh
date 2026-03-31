package presentation;

import core.dto.SupplierDTO;
import core.entity.Product;
import core.entity.Supplier;
import core.repository.SupplierRepository;
import core.service.Impl.SupplierServiceImpl;
import core.service.SupplierService;
import infrastructure.db.Neo4jConnection;
import infrastructure.mappper.GenericDataMapper;
import infrastructure.mappper.Impl.JacksonDataMapper;
import infrastructure.persistence.SupplierRepositoryyImpl;

import java.util.List;

public class test {
    public static void main(String[] args) {
        Neo4jConnection neo4jConnection = new Neo4jConnection("neo4j://127.0.0.1:7687","neo4j",
                "sapassword","northwinddb02");

        GenericDataMapper dataMapper = new JacksonDataMapper();

        SupplierRepository res = new SupplierRepositoryyImpl(neo4jConnection,dataMapper);
        SupplierService supplierService = new SupplierServiceImpl(res);

//        List<Product> list = supplierService.listProductsBySupplier("Tokyo Traders",1,2);
//        list.forEach(System.out::println);

//        SupplierDTO supplierDTO = new SupplierDTO();
//        supplierDTO.setId("S004");
//        supplierDTO.setCompanyName("test");
//        supplierDTO.setContactName("test");
//        supplierDTO.setCountry("test");
//        boolean test = supplierService.updateSupplier(supplierDTO);
//        if(test){
//            System.out.print("update thành công");
//        }else System.out.print("Thất bại");

//        double total = supplierService.calculateTotalOrder("O00345");
//        System.out.print(total);

//        List<Supplier> supplierList = supplierService.findByCountry("USA");
//        supplierList.forEach(System.out::println);

        Product product = new Product();
        product.setId("P124");
        product.setProductName("MẠNH kahng");
        product.setUnit("Ccầu kè");
        product.setUnitPrice(10);
        product.setUnitInStock(20);

        boolean manhKhang = supplierService.createNewProduct(product);
        if(manhKhang){
            System.out.print("Thành công");
        }else System.out.print("Thất bại");
    }
}
