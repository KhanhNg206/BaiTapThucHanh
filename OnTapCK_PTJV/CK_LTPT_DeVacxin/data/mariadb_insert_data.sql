-- Insert data into the persons table
INSERT INTO persons(person_id, dob, full_name, gender, height, weight)
VALUES ('P001', '1990-05-12', 'Nguyen Van Anh', 'Male', 1.72, 68.5);

INSERT INTO persons(person_id, dob, full_name, gender, height, weight)
VALUES ('P002', '1985-10-23', 'Tran Thi Thu Huong', 'Female', 1.60, 54.2);

INSERT INTO persons(person_id, dob, full_name, gender, height, weight)
VALUES ('P003', '2017-07-15', 'Le Hoang Chung', 'Male', 1.28, 45.0);

INSERT INTO persons(person_id, dob, full_name, gender, height, weight)
VALUES ('P004', '2019-03-08', 'Pham Minh Duc', 'Female', 1.65, 28.1);

INSERT INTO persons(person_id, dob, full_name, gender, height, weight)
VALUES ('P005', '1982-12-01', 'Hoang Anh', 'Male', 1.65, 85.3);

INSERT INTO persons(person_id, dob, full_name, gender, height, weight)
VALUES ('P006', '1988-06-30', 'Nguyen Thi Le Hang', 'Female', 1.62, 59.0);

INSERT INTO persons(person_id, dob, full_name, gender, height, weight)
VALUES ('P007', '2024-11-21', 'Do Quoc Gioi', 'Male', 0.75, 10.4);

INSERT INTO persons(person_id, dob, full_name, gender, height, weight)
VALUES ('P008', '2020-01-17', 'Dang Thanh Ha', 'Female', 1.28, 31.8);

INSERT INTO persons(person_id, dob, full_name, gender, height, weight)
VALUES ('P009', '2016-08-05', 'Bui Van Huynh', 'Male', 1.30, 34.5);

INSERT INTO persons(person_id, dob, full_name, gender, height, weight)
VALUES ('P010', '2019-04-10', 'Trinh Thi Mo', 'Female', 1.33, 25.7);

-- Insert data into the adults table
INSERT INTO adults(identity_number, occupation, person_id)
VALUES ('123456789', 'Engineer', 'P001');

INSERT INTO adults(identity_number, occupation, person_id)
VALUES ('234567891', 'Teacher', 'P002');

INSERT INTO adults(identity_number, occupation, person_id)
VALUES ('345678912', 'Doctor', 'P005');

INSERT INTO adults(identity_number, occupation, person_id)
VALUES ('456789123', 'Accountant', 'P006');

-- Insert data into the children table
INSERT INTO children(guardian_name, person_id)
VALUES ('Nguyen Van Anh', 'P003');

INSERT INTO children(guardian_name, person_id)
VALUES ('Tran Thi Binh', 'P004');

INSERT INTO children(guardian_name, person_id)
VALUES ('Le Hoang Cuc', 'P007');

INSERT INTO children(guardian_name, person_id)
VALUES ('Pham Minh Dinh', 'P008');

INSERT INTO children(guardian_name, person_id)
VALUES ('Hoang Anh Em', 'P009');

INSERT INTO children(guardian_name, person_id)
VALUES ('Nguyen Thi Nga', 'P010');

-- Insert data into the vaccines table
INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V001', '2025-06-30', 'Pfizer Inc.', 'Comirnaty');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V002', '2025-09-15', 'Moderna Inc.', 'Spikevax');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V003', '2026-01-10', 'AstraZeneca', 'Vaxzevria');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V004', '2025-12-01', 'Johnson & Johnson', 'Janssen');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V005', '2025-11-20', 'Sinopharm', 'BBIBP-CorV');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V006', '2026-02-28', 'Sinovac Biotech', 'CoronaVac');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V007', '2024-10-15', 'Bharat Biotech', 'Covaxin');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V008', '2026-05-05', 'Pfizer Inc.', 'Comirnaty');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V009', '2026-03-22', 'Moderna Inc.', 'Spikevax');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V010', '2025-07-07', 'AstraZeneca', 'Vaxzevria');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V011', '2025-08-18', 'Novavax', 'Nuvaxovid');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V012', '2026-06-30', 'Valneva', 'Valneva COVID-19 Vaccine');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V013', '2024-12-31', 'CureVac', 'CVnCoV');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V014', '2025-03-11', 'Sanofi GSK', 'Vidprevtyn Beta');

INSERT INTO vaccines(vaccine_id, expiration_date, manufacturer, vaccine_name)
VALUES ('V015', '2025-04-25', 'Inovio Pharmaceuticals', 'INO-4800');

-- Insert data into the records table
INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (1,1, '2024-05-01', 'COMPLETED', 'P001', 'V001');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (2,2, '2024-06-01', 'COMPLETED', 'P001', 'V001');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (3,1, '2024-04-10', 'COMPLETED', 'P002', 'V002');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (4,2, '2025-06-10', 'SCHEDULED', 'P002', 'V002');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (5,1, '2024-03-05', 'COMPLETED', 'P003', 'V003');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (6,1, '2024-02-15', 'MISSED', 'P004', 'V004');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (7,1, '2025-07-02', 'SCHEDULED', 'P005', 'V005');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (8,2, '2025-06-02', 'SCHEDULED', 'P005', 'V005');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (9,1, '2024-01-20', 'COMPLETED', 'P006', 'V006');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (10,2, '2024-02-20', 'COMPLETED', 'P006', 'V006');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (11,1, '2024-03-01', 'CANCELLED', 'P007', 'V007');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (12,1, '2024-04-05', 'COMPLETED', 'P008', 'V008');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (13,2, '2024-05-05', 'COMPLETED', 'P008', 'V008');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (14,1, '2024-01-10', 'MISSED', 'P009', 'V009');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (15,1, '2024-03-10', 'COMPLETED', 'P010', 'V010');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (16,2, '2025-08-10', 'SCHEDULED', 'P010', 'V010');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (17,1, '2024-04-01', 'COMPLETED', 'P003', 'V011');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (18,1, '2024-04-08', 'COMPLETED', 'P004', 'V012');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (19,1, '2024-04-15', 'COMPLETED', 'P007', 'V013');

INSERT INTO records(record_id, dose_number, injection_date, status, person_id, vaccine_id)
VALUES (20,2, '2025-07-15', 'SCHEDULED', 'P007', 'V013');


select * from persons;
select * from adults;
select * from children;
select * from vaccines;
select * from records;
