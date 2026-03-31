package test;

import db.Neo4jConnection;
import dto.ProductDTO;
import entity.Product;
import entity.Supplier;
import mapper.GenericDataMapper;
import mapper.Impl.JacksonDataMapper;
import repository.Impl.SupplierRepoImpl;
import repository.SupplierRepository;
import service.Impl.SupplierServiceImpl;
import service.SuppierService;

import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        Neo4jConnection connection = new Neo4jConnection("neo4j","sapassword","neo4j://127.0.0.1:7687","northwinddb03");
        GenericDataMapper mapper = new JacksonDataMapper();

        SupplierRepository supRes = new SupplierRepoImpl(connection,mapper);
        SuppierService service = new SupplierServiceImpl(supRes);

//        List<Product> cauA = service.listproductsBySupplier("Tokyo Traders",1,3);
//        cauA.forEach(System.out::println);
//
//        Supplier supplier = new Supplier();
//        supplier.setId("S004");
//        supplier.setCompanyName("123");
//        supplier.setCountry("123");
//        supplier.setContactName("123");
//        boolean cauB = service.updateSupplier(supplier);
//        if(cauB){
//            System.out.print("Thành công");
//        }else System.out.print("Thất bai");

//        double cauC = service.calculateTotalOrder("O002");
//        System.out.print(cauC);

//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setId("124");
//        productDTO.setProductName("124");
//        productDTO.setUnit("124");
//        productDTO.setUnitPrice(124);
//        productDTO.setUnitInStock(124);
//        productDTO.setSupplierID("S124");
//        boolean cauD = service.createProduct(productDTO);
//        if(cauD){
//            System.out.print("Thành công");
//        }else System.out.print("Thất bai");

//        Map<String,Integer> map = service.getNumberOfProductBySupplier();
//        map.forEach((name,total) ->
//                System.out.print("Supplier : "+name+" totalProduct : "+total+"\n"));


          boolean dele = service.deleteProduct("P001");
          if(dele){
              System.out.print("thành cộng");
          }System.out.print("sai");
    }
}
