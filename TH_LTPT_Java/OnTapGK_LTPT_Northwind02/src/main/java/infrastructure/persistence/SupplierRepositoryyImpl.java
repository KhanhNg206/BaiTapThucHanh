package infrastructure.persistence;

import core.dto.SupplierDTO;
import core.entity.Product;
import core.entity.Supplier;
import core.repository.SupplierRepository;
import infrastructure.db.Neo4jConnection;
import infrastructure.mappper.GenericDataMapper;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;

import java.util.List;

public class SupplierRepositoryyImpl implements SupplierRepository {
    private Neo4jConnection connection;
    private GenericDataMapper mapper;

    public SupplierRepositoryyImpl(Neo4jConnection connection, GenericDataMapper mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }

    @Override
    public double calculateTotalOrder(String orderID) {
        String cypher = """
                match (o : Order {id : $orderId}) -[r:ORDERS]->(p:Product)
                return SUM(r.quantity * r.unit_price * (1 - r.discount)) AS total
                """;
        var params = Values.parameters("orderId",orderID);
        try(Session session = connection.openSession()){
            return session.executeRead(tx -> {
                Record record = tx.run(cypher,params).single();
                if(record.get("total").isNull()){
                    return 0.0;
                }
                return record.get("total").asDouble();
            });
        }
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) {
        String cypher = """
                match (s : Supplier)
                where s.id = $id
                set s.company_name = $companyName,
                s.contact_name = $contactName,
                s.country = $country
                return count(s) as update
                """;
        var params = Values.parameters("id",supplier.getId(),
                "companyName",supplier.getCompanyName(),
                "contactName",supplier.getContactName(),
                "country",supplier.getCountry());
        try(Session session = connection.openSession()){
            return session.executeWrite(tx ->
                    tx.run(cypher,params).single().get("update").asInt() > 0);
        }
    }

    @Override
    public  List<Product> listProductsBySupplier(String companyName, int page, int size) {
        String cypher = """
                match (s : Supplier),(p : Product)
                where s.company_name = $companyName and s.id = p.supplier_id
                ORDER BY p.product_name
                SKIP $skip LIMIT $size
                return p
                """;

        int skip = (1 -page) * size;
        var params = Values.parameters("companyName",companyName,
                "skip",skip,"size",size);

        try(Session session = connection.openSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher, params);
                return result.list(record -> {
                    return mapper.toObject(record.get("p").asNode().asMap(), Product.class);
                });
            });
        }
    }

    @Override
    public List<Supplier> findByCountry(String country) {
        String cypher = """
                MATCH (s : Supplier)
                WHERE s.country = $country
                return s
                """;
        var params = Values.parameters("country",country);
        try (Session session = connection.openSession()){
            return  session.executeRead(tx -> {
                Result result = tx.run(cypher,params);
                return result.list(record -> {
                    return mapper.toObject(record.get("s").asNode().asMap(), Supplier.class);
                });
            });
        }
    }

    @Override
    public boolean createNewProduct(Product product) {
        String cypher = """
                CREATE (p : Product)
                SET p.id = $id,
                p.product_name = $productName,
                p.supplier_id = $supplierId,
                p.unit = $unit,
                p.unit_price = $unitPrice,
                p.units_in_stock = $unitInStock
                return p
                """;

        var params = Values.parameters("id",product.getId(),
               "productName",product.getProductName(),"supplierId",product.getSupplier_id(),
                "unit",product.getUnit(),"unitPrice",product.getUnitPrice(),
                "unitInStock",product.getUnitInStock());

        try(Session session = connection.openSession()){
           return session.executeWrite(tx -> {
               Result result = tx.run(cypher,params);
               return result.consume().counters().nodesCreated() > 0;
           });
        }
    }
}
