create constraint supplier_id IF NOT EXISTS
FOR (s : Supplier) REQUIRE s.id is unique

create constraint product_id IF NOT EXISTS
FOR (p : Product) REQUIRE p.id is unique

create constraint oder_id IF NOT EXISTS
FOR (o : Order) REQUIRE o.id is unique

load csv with headers from 'file:///northwind/suppliers.csv' as row
create (s : Supplier {
id : row.supplier_id,
company_name : row.company_name,
contact_name : row.contact_name,
country : row.country
});

load csv with headers from 'file:///northwind/products.csv' as row
create (p : Product{
id : row.product_id,
product_name : row.product_name,
unit : row.unit,
unit_price : toFloat(row.unit_price),
units_in_stock : toInteger(row.units_in_stock),
supplier_id : row.supplier_id
})

load csv with headers from 'file:///northwind/orders.csv' as row
create (o : Order{
id : row.order_id,
customer_name : row.customer_name,
employee_name : row.employee_name,
order_date : date(row.order_date),
status : row.status
})

load csv with headers from 'file:///northwind/order_details.csv' as row
create (od : OrderDetail{
order_id : row.order_id,
product_id : row.product_id,
quantity : toInteger(row.quantity),
unit_price : toFloat(row.unit_price),
discount : toFloat(row.discount)
})

create (s) -[:SUPPLIES]-> (p)

MATCH (o : Order), (p : Product), (od : OrderDetail)
where o.id = od.order_id and p.id = od.product_id
create (o) -[:ORDER{
quantity : toInteger(od.quantity),
unit_price : toFloat(od.unit_price),
discount : toFloat(od.discount)
}]-> (p)

CREATE RANGE INDEX supplier_companyName_index
FOR (s:Supplier)
ON (s.companyName);

match (s : Supplier),(p : Product)
where s.company_name = 'Tokyo Traders' and s.id = p.supplier_id
ORDER BY p.product_name
return p

match (s : Supplier)
where s.id = 'S008'
set s.company_name = 'new company name',
s.contact_name = 'new contact name',
s.country = 'Gò vấp'
return s