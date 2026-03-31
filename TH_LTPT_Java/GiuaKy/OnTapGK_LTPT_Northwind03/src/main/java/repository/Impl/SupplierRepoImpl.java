package repository.Impl;

import db.Neo4jConnection;
import dto.ProductDTO;
import entity.Product;
import entity.Supplier;
import mapper.GenericDataMapper;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import repository.SupplierRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SupplierRepoImpl implements SupplierRepository {
    private Neo4jConnection connection;
    private GenericDataMapper mapper;

    public SupplierRepoImpl(Neo4jConnection connection, GenericDataMapper mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }

    @Override
    public double calculateTotalOrder(String orderID) {
        String cypher = """
                MATCH (o : Order {order_id : $orderId}) -[od:ORDERS]-> (p : Product)
                RETURN sum(od.quantity * od.unit_price * (1 -od.discount)) as total
                """;
        var param = Values.parameters("orderId",orderID);
        try(Session session = connection.OpenSession()){
            return session.executeRead(tx -> {
                Record record =  tx.run(cypher,param).single();
                if(record.get("total").isNull()){
                    return 0.0;
                }return record.get("total").asDouble();
            });
        }
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        String cypher = """
                MATCH (s : Supplier)
                where s.supplier_id = $id
                set s.company_name = $comName,
                 s.contact_name = $contactName,
                s.country = $country
                return count(s) as update
                """;
        var params = Values.parameters("id",supplier.getId(),
                "comName",supplier.getCompanyName(),"contactName",supplier.getContactName(),
        "country",supplier.getCountry());

        try(Session session = connection.OpenSession()){
            return session.executeWrite(tx ->
                   tx.run(cypher,params).single().get("update").asInt() > 0);
        }
    }

    @Override
    public List<Product> listproductsBySupplier(String companyName, int page, int size) {
        String cypher = """
                MATCH (s : Supplier) -[:SUPPLIES]-> (p : Product)
                where s.company_name = $comName
                ORDER BY p.product_name
                SKIP $skip LIMIT $size
                RETURN p
                """;
        int skip = (1-page) * size;
        var params = Values.parameters("comName",companyName,
        "skip",skip,"size",size);

        try(Session session = connection.OpenSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(cypher,params);
                return result.list(record -> {
                    return mapper.toObject(record.get("p").asNode().asMap(),Product.class);
                });
            });
        }
    }

    @Override
    public boolean createProduct(ProductDTO productDTO) {
        String cypher = """
                create (p : Product {
                product_id : $id,
                product_name : $pname,
                unit : $unit,
                unit_price : $uprice,
                units_in_stock : $uis,
                supplier_id : $supId
                })
                return count(p) as create
                """;
        var param = Values.parameters("id",productDTO.getId(),
        "pname",productDTO.getProductName(),"unit",productDTO.getUnit(),
                "uprice",productDTO.getUnitPrice(),"uis",productDTO.getUnitInStock(),
                "supId",productDTO.getSupplierID()
        );
        try(Session session = connection.OpenSession()){
            return session.executeWrite(tx ->
                    tx.run(cypher,param).single().get("create").asInt() > 0);
        }
    }

    @Override
    public Map<String, Integer> getNumberOfProductBySupplier() {
        String cypher = """
                MATCH (s : Supplier)-[:SUPPLIES]->(p : Product)
                return s.company_name as CompanyyName , count(p) as totalProduct
                ORDER BY CompanyyName ASC
                """;
        try(Session session = connection.OpenSession()){
            return session.executeRead(tx -> {
               Result result = tx.run(cypher);
               Map<String,Integer> map = new LinkedHashMap<>();
               while (result.hasNext()){
                   Record record = result.next();
                   String name = record.get("CompanyyName").asString();
                   int total = record.get("totalProduct").asInt();
                   map.put(name,total);
               }
               return map;
            });
        }
    }

    @Override
    public boolean deleteProduct(String productId) {
        String cypher = """
                MATCH (p : Product {product_id : $id})
                DETACH DELETE p
                return count(p) as total
                """;
        var params = Values.parameters("id",productId);
        try(Session session = connection.OpenSession()){
            return session.executeWrite(tx ->
                    tx.run(cypher,params).single().get("total").asInt() < 1);
        }
    }
}
