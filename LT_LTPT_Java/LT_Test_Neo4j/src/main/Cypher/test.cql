CREATE
  (p1:Person {name: 'Alice', age: 25}),
  (p2:Person {name: 'Bob', age: 30}),
  (p3:Person {name: 'Charlie', age: 22}),
  (p4:Person {name: 'David', age: 35}),
  (p5:Person {name: 'Eve', age: 28}),
  (p6:Person {name: 'Frank', age: 40}),
  (p7:Person {name: 'Grace', age: 27}),
  (p8:Person {name: 'Henry', age: 32}),
  (p9:Person {name: 'Ivy', age: 29}),
  (p10:Person {name: 'Jack', age: 31}),

  (a1:Address {street: '123 Main St', city: 'New York', state: 'NY'}),
  (a2:Address {street: '456 Elm Ave', city: 'London', country: 'UK'}),
  (a3:Address {street: '789 Oak Ln', city: 'Paris', country: 'France'}),
  (a4:Address {street: '101 Pine Ct', city: 'Sydney', state: 'NSW'}),
  (a5:Address {street: '222 Maple Dr', city: 'Rome', country: 'Italy'}),
  (a6:Address {street: '333 Willow Rd', city: 'Berlin', country: 'Germany'}),
  (a7:Address {street: '444 Birch Pl', city: 'Madrid', country: 'Spain'}),
  (a8:Address {street: '555 Cedar Cir', city: 'Tokyo', country: 'Japan'}),
  (a9:Address {street: '666 Redwood St', city: 'Toronto', country: 'Canada'}),
  (a10:Address {street: '777 Oak Ave', city: 'Sao Paulo', country: 'Brazil'}),

  (p1)-[:FRIENDS_WITH]->(p2),
  (p1)-[:FRIENDS_WITH]->(p3),
  (p1)-[:FRIENDS_WITH]->(p6),
  (p2)-[:FRIENDS_WITH]->(p4),
  (p3)-[:FRIENDS_WITH]->(p5),
  (p4)-[:FRIENDS_WITH]->(p6),
  (p4)-[:FRIENDS_WITH]->(p9),
  (p5)-[:FRIENDS_WITH]->(p7),
  (p6)-[:FRIENDS_WITH]->(p8),
  (p7)-[:FRIENDS_WITH]->(p3),
  (p7)-[:FRIENDS_WITH]->(p1),
  (p7)-[:FRIENDS_WITH]->(p9),
  (p7)-[:FRIENDS_WITH]->(p10),
  (p8)-[:FRIENDS_WITH]->(p10),
  (p9)-[:FRIENDS_WITH]->(p1),
  (p9)-[:FRIENDS_WITH]->(p6),
  (p10)-[:FRIENDS_WITH]->(p4),

  (p1)-[:LIVES_AT]->(a1),
  (p2)-[:LIVES_AT]->(a2),
  (p3)-[:LIVES_AT]->(a3),
  (p4)-[:LIVES_AT]->(a4),
  (p5)-[:LIVES_AT]->(a5),
  (p6)-[:LIVES_AT]->(a6),
  (p7)-[:LIVES_AT]->(a7),
  (p8)-[:LIVES_AT]->(a8),
  (p9)-[:LIVES_AT]->(a9),
  (p10)-[:LIVES_AT]->(a10)

MATCH (p:Person)
WHERE (p.name STARTS WITH 'A' OR p.name STARTS WITH 'E')
RETURN p;

//tim ds ban cua ai do
MATCH (p:Person)
RETURN p.name, [(p)-[:FRIENDS_WITH]->(f:Person) | f.name] AS friends;

//ban >= 3
MATCH (p:Person) WHERE COUNT { (p)-[:FRIENDS_WITH]->(:Person) } >= 3
RETURN p.name;

MATCH (p:Person {name: 'Khanh'})
DETACH DELETE p;

CREATE INDEX person_name_idx IF NOT EXISTS
FOR (p:Person)
ON (p.name)

SHOW INDEXES

CREATE CONSTRAINT person_email_uq IF NOT EXISTS
FOR (n:Person)
REQUIRE n.email IS UNIQUE

CREATE (p1:Person {name: 'Abc', age: 25, email: 'tommy@gmail.com'})

SHOW CONSTRAINTS

CREATE CONSTRAINT person_name_key IF NOT EXISTS
FOR (n:Person)
REQUIRE n.name IS NODE KEY

CREATE CONSTRAINT person_email_uq IF NOT EXISTS
FOR (n:Person)
REQUIRE n.email IS UNIQUE

CREATE (p1:Person {name: 'Abc', age: 25, email: 'x@gmail.com'})

SHOW CONSTRAINTS

CREATE CONSTRAINT student_id_key IF NOT EXISTS
FOR (n:Student)
REQUIRE n.student_id IS NODE KEY

CREATE CONSTRAINT course_id_key IF NOT EXISTS
FOR (n:Course)
REQUIRE n.course_id IS NODE KEY

CREATE CONSTRAINT dept_id_key IF NOT EXISTS
FOR (n:Department)
REQUIRE n.dept_id IS NODE KEY

LOAD CSV WITH HEADERS
FROM 'file:///courses/courses.csv' AS row
CREATE (c:Course {course_id: row.course_id,name: row.name, hours: toInteger(row.hours)
})

LOAD CSV WITH HEADERS
FROM 'file:///courses/students.csv' AS row
CREATE (c:Student{student_id:row.student_id, name:row.name, gpa:toFloat(row.gpa)})

LOAD CSV WITH HEADERS
FROM 'file:///courses/departments.csv' AS row
CREATE (c:Deparment{department_id:row.department_id, name:row.name, dean: row.dean, building: row.building, room:row.room})


LOAD CSV WITH HEADERS
FROM 'file:///courses/courses.csv' AS row
MATCH (c:Course {course_id: row.course_id})
MATCH (d:Department {dept_id: row.dept_id})
CREATE (c)-[:BELONGS_TO]->(d);

LOAD CSV WITH HEADERS
FROM 'file:///courses/students.csv' AS row
CREATE (c:Student{student_id:row.student_id, name:row.name, gpa:toFloat(row.gpa)})

LOAD CSV WITH HEADERS
FROM 'file:///courses/departments.csv' AS row
CREATE (c:Deparment{department_id:row.department_id, name:row.name, dean: row.dean, building: row.building, room:row.room})


LOAD CSV WITH HEADERS
FROM 'file:///courses/courses.csv' AS row
MATCH (c:Course {course_id: row.course_id})
MATCH (d:Department {dept_id: row.dept_id})
CREATE (c)-[:BELONGS_TO]->(d);

LOAD CSV WITH HEADERS
FROM 'file:///courses/enrollments.csv' AS row
MATCH (c:Course {course_id: row.course_id})
MATCH (s:Student {student_id: row.student_id})
CREATE (s)-[:ENROLLED_IN {grade: toFloat(row.grade)}]->(c);

MATCH ()-[r:ENROLLED_IN]->()














