DROP DATABASE IF EXISTS RentABike;
CREATE DATABASE IF NOT EXISTS RentABike;
USE RentABike;

DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer (
                                        customerId VARCHAR(15) NOT NULL,
                                        name VARCHAR(50),
                                        age INT,
                                        contact VARCHAR(20),
                                        email VARCHAR(50),
                                        address VARCHAR(100),
                                        city VARCHAR(50),
                                        country VARCHAR(50),
                                        zip_code VARCHAR(20),
                                        CONSTRAINT PRIMARY KEY (customerId)
);

DROP TABLE IF EXISTS Booking;
CREATE TABLE IF NOT EXISTS Booking (
                                       bookingId VARCHAR(15) NOT NULL,
                                       chooseBike VARCHAR(50),
                                       PickUpLocation VARCHAR(100),
                                       pickUpDate DATE,
                                       pickUpTime VARCHAR(10),
                                       dropOffLocation VARCHAR(100),
                                       dropOffDate DATE,
                                       dropOffTime VARCHAR(10),
                                       bookingStatus VARCHAR(10),
                                      /* cid VARCHAR(15),*/
                                       CONSTRAINT PRIMARY KEY (bookingId)
                                       /*CONSTRAINT FOREIGN KEY(cId) REFERENCES Customer(customerId) on Delete Cascade on Update Cascade*/
);



DROP TABLE IF EXISTS Payment;
CREATE TABLE IF NOT EXISTS Payment (
                                       paymentId VARCHAR(15) NOT NULL,
                                       paymentAmount DOUBLE,
                                       paymentDescription VARCHAR(100),
                                       paymentDate VARCHAR(15),
                                       cId VARCHAR(15),
                                       bId VARCHAR(15),
                                       CONSTRAINT PRIMARY KEY (paymentId),
                                       CONSTRAINT FOREIGN KEY(cId) REFERENCES Customer(customerId) on Delete Cascade on Update Cascade,
                                       CONSTRAINT FOREIGN KEY(bId) REFERENCES Booking(bookingId) on Delete Cascade on Update Cascade
);

DROP TABLE IF EXISTS Vehicle;
CREATE TABLE IF NOT EXISTS Vehicle (
                                       vehicleId VARCHAR(15) NOT NULL,
                                       name VARCHAR(50),
                                       type VARCHAR(50),
                                       rent_price DOUBLE,
                                       mileage VARCHAR(50),
                                       first_aid_kit VARCHAR(50),
                                       transmission VARCHAR(50),
                                       roadside_assistance VARCHAR(50),
                                       available VARCHAR(50),
                                       CONSTRAINT PRIMARY KEY (vehicleId)
);


DROP TABLE IF EXISTS VehicleStatus;
CREATE TABLE IF NOT EXISTS VehicleStatus (
                                       vehicleId VARCHAR(15) NOT NULL,
                                       name VARCHAR(50),
                                       type VARCHAR(50),
                                       rent_price DOUBLE,
                                       customer_id VARCHAR(50),
                                       booking_id VARCHAR(50),
                                       available VARCHAR(50),
                                       CONSTRAINT PRIMARY KEY (vehicleId)
);



DROP TABLE IF EXISTS BookingDetails;
CREATE TABLE IF NOT EXISTS BookingDetails (
                                              vId VARCHAR(15),
                                              bId VARCHAR(15),
                                              CONSTRAINT PRIMARY KEY (vId, bId),
                                              CONSTRAINT FOREIGN KEY (vId) REFERENCES Vehicle(vehicleId) on Delete Cascade on Update Cascade,
                                              CONSTRAINT FOREIGN KEY (bId) REFERENCES Booking(bookingId) on Delete Cascade on Update Cascade
);


/*employee table*/

CREATE TABLE User (
                      user_name VARCHAR(50) NOT NULL,
                      password VARCHAR(50) NOT NULL,
                      CONSTRAINT PRIMARY KEY (user_name)
);

CREATE TABLE Employee (
                          employeeId VARCHAR(50),
                          employeeTyped VARCHAR(50),
                          name VARCHAR(50) NOT NULL,
                          nic VARCHAR(20) NOT NULL,
                          address VARCHAR(100),
                          contact VARCHAR(50),
                          email VARCHAR(50),
                          CONSTRAINT PRIMARY KEY (employeeId)
);

CREATE TABLE Salary (
                          salaryId VARCHAR(50),
                          description VARCHAR(100),
                          amount DOUBLE,
                          type VARCHAR(50),
                          month VARCHAR(50),
                          employeeId VARCHAR(50),
                          CONSTRAINT PRIMARY KEY (salaryId)
);

CREATE TABLE Attendance (
                            attendenceId VARCHAR(50),
                            date DATE,
                            holiday VARCHAR(50),
                            signInTime VARCHAR(50),
                            signOutTime VARCHAR(50),
                            employeeId VARCHAR(50),
                            CONSTRAINT PRIMARY KEY (attendenceId)
                            /*CONSTRAINT FOREIGN KEY (employeeId) REFERENCES Employee(employeeId) on Delete Cascade on Update Cascade*/
);


INSERT INTO Customer VALUES('C00-001', 'John Doe', 28 ,'+94754789723','Danapala@gmail.com','123 Main St', 'New York', 'USA' , '10001');
INSERT INTO Customer VALUES('C00-002', 'Jane Smith', 35, '+44789562341', 'jane.smith@example.com', '456 Elm St', 'London', 'UK', '8006');
INSERT INTO Customer VALUES('C00-003', 'Robert Johnson', 42, '+16175551212', 'robert.johnson@example.com', '789 Maple Ave', 'Chicago', 'USA', '60611');
INSERT INTO Customer VALUES('C00-004', 'Maria Garcia', 29, '+34917654321', 'maria.garcia@example.com', '10 Calle de Alcala', 'Madrid', 'Spain', '28001');
INSERT INTO Customer VALUES('C00-005', 'Jens Schmidt', 46, '+49123456789', 'jens.schmidt@example.com', '321 Rosenstrasse', 'Berlin', 'Germany', '10115');
INSERT INTO Customer VALUES('C00-006', 'Yuji Tanaka', 31, '+81345678901', 'yuji.tanaka@example.com', '456 Shibuya Crossing', 'Tokyo', 'Japan', '0002');
INSERT INTO Customer VALUES('C00-007', 'Sophie Dubois', 25, '+33123456789', 'sophie.dubois@example.com', '789 Rue de Rivoli', 'Paris', 'France', '75001');
INSERT INTO Customer VALUES('C00-008', 'Miguel Rodriguez', 36, '+5215555555555', 'miguel.rodriguez@example.com', '123 Av. Reforma', 'Mexico City', 'Mexico', '06600');
INSERT INTO Customer VALUES('C00-009', 'Anastasia Ivanova', 27, '+74951234567', 'anastasia.ivanova@example.com', '456 Nevsky Prospekt', 'St. Petersburg', 'Russia', '191023');
INSERT INTO Customer VALUES('C00-010', 'Kim Min-Jae', 33, '+8221234567', 'kim.minjae@example.com', '789 Gangnam-gu', 'Seoul', 'South Korea', '06001');
INSERT INTO Customer VALUES('C00-011', 'Lucas Silva', 30, '+5511999999999', 'lucas.silva@example.com', '123 Avenida Paulista', 'Sao Paulo', 'Brazil', '01310');



INSERT INTO Booking VALUES('B00-001', 'Mountain Bike', 'Nugegoda', '2023-04-01','11.23', 'Nugegoda', '2023-04-03','20.54 pm', 'expired');
INSERT INTO Booking VALUES('B00-002', 'City Bike', 'Colombo 07', '2023-04-06', '09.30', 'Colombo 03', '2023-04-07', '17.45 pm' , 'expired');
INSERT INTO Booking VALUES('B00-003', 'Mountain Bike', 'Kandy', '2023-04-08', '14.00', 'Kandy', '2023-04-10', '18.30 pm' , 'expired');
INSERT INTO Booking VALUES('B00-004', 'Electric Bike', 'Negombo', '2023-04-11', '10.15', 'Negombo', '2023-04-13', '19.00 pm' , 'expired');
INSERT INTO Booking VALUES('B00-005', 'City Bike', 'Galle', '2023-04-15', '11.45', 'Galle', '2023-04-16', '16.30 pm' , 'expired');
INSERT INTO Booking VALUES('B00-006', 'Mountain Bike', 'Nuwara Eliya', '2023-04-19', '08.00', 'Nuwara Eliya', '2023-04-21', '12.15 pm' , 'expired');
INSERT INTO Booking VALUES('B00-007', 'Electric Bike', 'Matara', '2023-04-23', '13.30', 'Matara', '2023-04-25', '16.45 pm' , 'expired');
INSERT INTO Booking VALUES('B00-008', 'City Bike', 'Anuradhapura', '2023-04-27', '10.00', 'Anuradhapura', '2023-04-28', '15.15 pm', 'expired');
INSERT INTO Booking VALUES('B00-009', 'Mountain Bike', 'Polonnaruwa', '2023-04-01', '12.45', 'Polonnaruwa', '2023-04-03', '19.30 pm', 'expired');
INSERT INTO Booking VALUES('B00-010', 'Electric Bike', 'Trincomalee', '2023-04-05', '09.00', 'Trincomalee', '2023-04-06', '13.45 pm', 'expired');

INSERT INTO Payment VALUES('P00-001', 4100.50, 'Payment Success!!', '2023-03-20', 'C00-001', 'B00-001');
INSERT INTO Payment VALUES('P00-002', 3375.00, 'Payment Success!!', '2023-03-21', 'C00-001', 'B00-002');
INSERT INTO Payment VALUES('P00-003', 5520.00, 'Payment Success!!', '2023-03-22', 'C00-001', 'B00-003');
INSERT INTO Payment VALUES('P00-004', 6550.00, 'Payment Success!!', '2023-03-22', 'C00-001', 'B00-004');
INSERT INTO Payment VALUES('P00-005', 4200.00, 'Payment Success!!', '2023-03-23', 'C00-001', 'B00-005');
INSERT INTO Payment VALUES('P00-006', 2150.00, 'Payment Success!!', '2023-03-23', 'C00-001', 'B00-006');
INSERT INTO Payment VALUES('P00-007', 2230.00, 'Payment Success!!', '2023-03-24', 'C00-001', 'B00-007');
INSERT INTO Payment VALUES('P00-008', 1250.00, 'Payment Success!!', '2023-03-24', 'C00-001', 'B00-008');
INSERT INTO Payment VALUES('P00-009', 2380.00, 'Payment Success!!', '2023-03-25', 'C00-001', 'B00-009');
INSERT INTO Payment VALUES('P00-010', 4100.00, 'Payment Success!!', '2023-03-25', 'C00-001', 'B00-010');/*
INSERT INTO Payment VALUES('p011', 3200.00, 'Payment Success!!', '2023-03-26', 'C00-001', 'B00-011');
INSERT INTO Payment VALUES('p012', 4700.00, 'Payment Success!!', '2023-03-27', 'C00-001', 'B00-012');
INSERT INTO Payment VALUES('p013', 1900.00, 'Payment Success!!', '2023-03-27', 'C00-001', 'B00-013');
INSERT INTO Payment VALUES('p014', 2600.00, 'Payment Success!!', '2023-03-28', 'C00-001', 'B00-014');
INSERT INTO Payment VALUES('p015', 5400.00, 'Payment Success!!', '2023-03-29', 'C00-001', 'B00-015');
INSERT INTO Payment VALUES('p016', 3100.00, 'Payment Success!!', '2023-03-30', 'C00-001', 'B00-016');
INSERT INTO Payment VALUES('p017', 1850.00, 'Payment Success!!', '2023-03-30', 'C00-001', 'B00-017');
INSERT INTO Payment VALUES('p018', 4250.00, 'Payment Success!!', '2023-04-01', 'C00-001', 'B00-018');
INSERT INTO Payment VALUES('p019', 4900.00, 'Payment Success!!', '2023-04-02', 'C00-001', 'B00-019');
INSERT INTO Payment VALUES('p020', 6300.00, 'Payment Success!!', '2023-04-03', 'C00-001', 'B00-020');
INSERT INTO Payment VALUES('p021', 3700.00, 'Payment Success!!', '2023-04-04', 'C00-001', 'B00-021');
INSERT INTO Payment VALUES('p022', 2800.00, 'Payment Success!!', '2023-04-05', 'C00-001', 'B00-022');
INSERT INTO Payment VALUES('p023', 2150.00, 'Payment Success!!', '2023-04-06', 'C00-001', 'B00-023');
INSERT INTO Payment VALUES('p024', 4150.00, 'Payment Success!!', '2023-04-07', 'C00-001', 'B00-024');
INSERT INTO Payment VALUES('p025', 4200.00, 'Payment Success!!', '2023-04-08', 'C00-001', 'B00-025');
INSERT INTO Payment VALUES('p026', 1800.00, 'Payment Success!!', '2023-04-09', 'C00-001', 'B00-026');
INSERT INTO Payment VALUES('p027', 1230.00, 'Payment Success!!', '2023-04-10', 'C00-001', 'B00-027');
INSERT INTO Payment VALUES('p028', 2980.00, 'Payment Success!!', '2023-04-11', 'C00-001', 'B00-028');
INSERT INTO Payment VALUES('p029', 3850.00, 'Payment Success!!', '2023-04-12', 'C00-001', 'B00-029');
INSERT INTO Payment VALUES('p030', 5100.00, 'Payment Success!!', '2023-04-12', 'C00-001', 'B00-030');
INSERT INTO Payment VALUES('p031', 3400.00, 'Payment Success!!', '2023-04-13', 'C00-001', 'B00-031');
INSERT INTO Payment VALUES('p032', 2400.00, 'Payment Success!!', '2023-04-13', 'C00-001', 'B00-032');
INSERT INTO Payment VALUES('p033', 1780.00, 'Payment Success!!', '2023-04-14', 'C00-001', 'B00-033');
INSERT INTO Payment VALUES('p034', 3650.00, 'Payment Success!!', '2023-04-15', 'C00-001', 'B00-034');
INSERT INTO Payment VALUES('p035', 4300.00, 'Payment Success!!', '2023-04-23', 'C00-001', 'B00-035');
INSERT INTO Payment VALUES('p036', 2100.00, 'Payment Success!!', '2023-04-24', 'C00-001', 'B00-036');
INSERT INTO Payment VALUES('p037', 1430.00, 'Payment Success!!', '2023-04-25', 'C00-001', 'B00-037');
INSERT INTO Payment VALUES('p038', 2750.00, 'Payment Success!!', '2023-04-25', 'C00-001', 'B00-038');
INSERT INTO Payment VALUES('p039', 4650.00, 'Payment Success!!', '2023-04-26', 'C00-001', 'B00-039');
INSERT INTO Payment VALUES('p040', 5700.00, 'Payment Success!!', '2023-04-27', 'C00-001', 'B00-040');*/



INSERT INTO Vehicle VALUES('V00-001', 'Hero XPluse 200 cc', 'Bikes', 15000, 'unlimited', 'yes', 'Manual', 'yes', 'available');
INSERT INTO Vehicle VALUES('V00-002', 'Honda X Blade', 'Bikes', 15000, 'unlimited', 'no', 'Manual', 'yes', 'booked');
INSERT INTO Vehicle VALUES('V00-003', 'Honda XR 250', 'Bikes', 15000, 'unlimited', 'yes', 'Manual', 'yes', 'available');
INSERT INTO Vehicle VALUES('V00-004', 'Honda Hornet 160 R', 'Bikes', 15000, 'unlimited', 'yes', 'Manual', 'no', 'available');
INSERT INTO Vehicle VALUES('V00-005', 'Honda Dio 110 CC', 'Scooter', 1000, 'unlimited', 'yes', 'Automatic', 'yes', 'available');
INSERT INTO Vehicle VALUES('V00-006', 'Honda Navi 110 CC', 'Scooter', 1000, 'unlimited', 'no', 'Automatic', 'yes', 'booked');
INSERT INTO Vehicle VALUES('V00-007', 'Ladies Cycle', 'Bicycle', 800, 'unlimited', 'yes', 'Manual', 'yes', 'booked');
INSERT INTO Vehicle VALUES('V00-008', 'Mountain Cycle', 'Bicycle', 800, 'unlimited', 'yes', 'Manual', 'no', 'booked');
INSERT INTO Vehicle VALUES('V00-009', 'Bajaj Qute', 'Car', 4000, 'unlimited', 'no' , 'Manual', 'yes', 'available');
INSERT INTO Vehicle VALUES('V00-010', 'Tuk Tuk 300 CC', 'Three Wheel', 3500, 'unlimited', 'yes', 'Manual', 'yes', 'available');

INSERT INTO VehicleStatus VALUES('v001', 'Hero XPluse 200 cc', 'Bikes', 15000, 'C00-004', 'B00-003', 'available');
INSERT INTO VehicleStatus VALUES('v002', 'Honda X Blade', 'Bikes', 15000, 'C00-002' ,'B00-002', 'available');
INSERT INTO VehicleStatus VALUES('v003', 'Honda XR 250', 'Bikes', 15000, 'C00-003','B00-001', 'available');
INSERT INTO VehicleStatus VALUES('v004', 'Honda Hornet 160 R', 'Bikes', 15000, 'C00-001' ,'B00-001', 'available');
INSERT INTO VehicleStatus VALUES('v005', 'Honda Dio 110 CC', 'Scooter', 1000, 'C00-005' ,'B00-001', 'available');
INSERT INTO VehicleStatus VALUES('v006', 'Honda Navi 110 CC', 'Scooter', 1000, 'C00-007' ,'B00-001', 'available');
INSERT INTO VehicleStatus VALUES('v007', 'Ladies Cycle', 'Bicycle', 800, 'C00-006' ,'B00-005', 'available');
INSERT INTO VehicleStatus VALUES('v008', 'Mountain Cycle', 'Bicycle', 800, 'C00-008' ,'B00-007', 'available');
INSERT INTO VehicleStatus VALUES('v009', 'Bajaj Qute', 'Car', 4000, 'C00-010','B00-009', 'available');
INSERT INTO VehicleStatus VALUES('v010', 'Tuk Tuk 300 CC', 'Three Wheel', 3500, 'C00-009' ,'B00-010', 'available');

INSERT INTO BookingDetails VALUES('V00-001','B00-001');
INSERT INTO BookingDetails VALUES('V00-002','B00-002');
INSERT INTO BookingDetails VALUES('V00-003','B00-003');
INSERT INTO BookingDetails VALUES('V00-004','B00-004');
INSERT INTO BookingDetails VALUES('V00-005','B00-005');
INSERT INTO BookingDetails VALUES('V00-006','B00-006');
INSERT INTO BookingDetails VALUES('V00-007','B00-007');
INSERT INTO BookingDetails VALUES('V00-008','B00-008');
INSERT INTO BookingDetails VALUES('V00-009','B00-009');
INSERT INTO BookingDetails VALUES('V00-010','B00-010');

INSERT INTO User VALUES('gayan', 'letmeIn');
INSERT INTO User VALUES('hello','1234');
INSERT INTO User VALUES('niko','bye');
INSERT INTO User VALUES('1','1');

INSERT INTO Employee VALUES('E00-001','receptionist','nimal Perera' , '946101273V', 'No. 123, Galle Road, Colombo', 0112345678, 'chathura@gmail.com');
INSERT INTO Employee VALUES('E00-002', 'mechanic' ,'gayan Silva' , '947691234V', 'No. 456, Kandy Road, Kadawatha', 0113456789, 'nimasha@yahoo.com');

INSERT INTO Salary VALUES('S00-001','Monthly Salary', 75000.00, 'Basic', 'January' , 'E00-001');
INSERT INTO Salary VALUES('S00-002','Monthly Salary', 65000.00, 'Basic', 'January' , 'E00-002');

INSERT INTO Attendance VALUES('A00-001','2023-03-01', 'workday', '08:30:00', '17:00:00', 'E00-001');
INSERT INTO Attendance VALUES('A00-002','2023-03-01', 'workday', '08:30:00', '17:00:00', 'E00-002');
INSERT INTO Attendance VALUES('A00-003','2023-03-02', 'workday', '08:45:00', '17:15:00', 'E00-001');
INSERT INTO Attendance VALUES('A00-004','2023-03-02', 'workday', '08:45:00', '17:15:00', 'E00-002');
INSERT INTO Attendance VALUES('A00-005','2023-03-03', 'workday', '08:15:00', '17:30:00', 'E00-001');
INSERT INTO Attendance VALUES('A00-006','2023-03-03', 'workday', '08:15:00', '17:30:00', 'E00-002');
INSERT INTO Attendance VALUES('A00-007','2023-03-04', 'workday', '08:00:00', '17:00:00', 'E00-001');
INSERT INTO Attendance VALUES('A00-008','2023-03-04', 'workday', '08:00:00', '17:00:00', 'E00-002');
INSERT INTO Attendance VALUES('A00-009','2023-03-05', 'workday', '08:30:00', '17:00:00', 'E00-001');
INSERT INTO Attendance VALUES('A00-010','2023-03-05', 'workday', '08:30:00', '17:00:00', 'E00-002');
INSERT INTO Attendance VALUES('A00-011','2023-03-06', 'holiday', NULL, NULL, 'E00-001');
INSERT INTO Attendance VALUES('A00-012','2023-03-06', 'holiday', NULL, NULL, 'E00-002');
INSERT INTO Attendance VALUES('A00-013','2023-03-07', 'workday', '08:15:00', '17:30:00', 'E00-001');
INSERT INTO Attendance VALUES('A00-014','2023-03-07', 'workday', '08:15:00', '17:30:00', 'E00-002');
INSERT INTO Attendance VALUES('A00-015','2023-03-08', 'workday', '09:00:00', '17:00:00', 'E00-001');
INSERT INTO Attendance VALUES('A00-016','2023-03-08', 'workday', '09:00:00', '17:00:00', 'E00-002');
INSERT INTO Attendance VALUES('A00-017','2023-03-09', 'workday', '08:30:00', '17:00:00', 'E00-001');
INSERT INTO Attendance VALUES('A00-018','2023-03-09', 'workday', '08:30:00', '17:00:00', 'E00-002');
INSERT INTO Attendance VALUES('A00-019','2023-04-27', 'workday', '08:00:00', '17:00:00', 'E00-001');
INSERT INTO Attendance VALUES('A00-020','2023-04-27', 'workday', '08:00:00', '17:00:00', 'E00-002');






