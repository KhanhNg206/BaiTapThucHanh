create CONSTRAINT supplier_id IF NOT EXISTS
FOR (s : Supplier) REQUIRE s.id IS UNIQUE

create CONSTRAINT product_id IF NOT EXISTS
FOR (p : Product) REQUIRE p.id IS UNIQUE

create CONSTRAINT order_id IF NOT EXISTS
FOR (o : Order) REQUIRE o.id IS UNIQUE

LOAD CSV WITH HEADERS FROM 'file:///northwind/suppliers.csv' AS row
MERGE (s:Supplier {
supplier_id : row.supplier_id,
company_name : row.company_name,
contact_name : row.contact_name,
country : row.country
})

LOAD CSV WITH HEADERS FROM 'file:///northwind/products.csv' AS row
MERGE (p:Product {
product_id : row.product_id,
product_name : row.product_name,
unit : row.unit,
unit_price : toFloat(row.unit_price),
units_in_stock : toInteger(row.units_in_stock),
supplier_id : row.supplier_id
})

LOAD CSV WITH HEADERS FROM 'file:///northwind/orders.csv' AS row
MERGE (o:Order {
order_id : row.order_id,
customer_name : row.customer_name,
employee_name : row.employee_name,
order_date : date(row.order_date),
status : row.status
})

LOAD CSV WITH HEADERS FROM 'file:///northwind/order_details.csv' AS row
MERGE (od:OrderDetail {
order_id : row.order_id,
product_id : row.product_id,
quantity : toInteger(row.quantity),
unit_price : toFloat(row.unit_price),
discount : toFloat(row.discount)
})

match (s : Supplier) , (p : Product)
where s.supplier_id = p.supplier_id
MERGE (s) -[:SUPPLIES]->(p)

MATCH (o : Order) , (p : Product), (od : OrderDetail)
where o.order_id = od.order_id and p.product_id = od.product_id
MERGE (o) -[:ORDERS{quantity : od.quantity , unit_price : od.unit_price
,discount : od.discount
}]->(p)


CREATE RANGE INDEX supplier_index_companyName
for(s : Supplier)
ON (s.company_name)

MATCH (s : Supplier) -[:SUPPLIES]-> (p : Product)
where s.company_name = "Tokyo Traders"
SKIP 0 LIMIT 3
RETURN p

MATCH (s : Supplier)
where s.supplier_id = 'S005'
set s.company_name = 'newName',
s.contact_name = 'new name',
s.country = 'new country'
return s
