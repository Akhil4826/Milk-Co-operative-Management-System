-- Database creation and selection
drop database milk;
CREATE DATABASE IF NOT EXISTS milk;
USE milk;


				
-- Table creation for employee
CREATE TABLE IF NOT EXISTS employee (
  eid INT NOT NULL AUTO_INCREMENT,
  ename VARCHAR(30) NOT NULL,
  phno VARCHAR(10) NOT NULL,
  salary INT NOT NULL,
  designation VARCHAR(50) NOT NULL,
  address VARCHAR(50) NOT NULL,
  e_vid INT DEFAULT 1,
  PRIMARY KEY (eid)
);

select * from employee;
-- Data insertion for employee table
INSERT INTO employee (eid, ename, phno, salary, designation, address, e_vid) VALUES
(12336, 'govind', '9441733995', 3000, 'staff', 'kurnool', 2),
(12337, 'gopal', '9441733996', 3000, 'staff', 'kurnool', 2),
(12338, 'ramya', '9441733998', 3000, 'staff', 'kurnool', 2),
(12339, 'rashi', '9441733999', 3000, 'staff', 'kurnool', 1),
(12340, 'priya', '9441733910', 3000, 'staff', 'kurnool', 1),
(12341, 'sudheeer', '9441733912', 3000, 'staff', 'kurnool', 1),
(12342, 'girish', '9441733913', 3000, 'staff', 'kurnool', 3),
(12344, 'rehman', '9441733914', 3000, 'staff', 'kurnool', 4),
(12345, 'ravi', '9908407185', 20000, 'staff', 'kurnool', 5),
(12346, 'mani', '8688764855', 30000, 'manager', 'ndk', 6),
(12347, 'rasi', '9908407285', 3000, 'manager', 'kudapa', 7),
(12348, 'nani', '9666613357', 3000, 'manager', 'kurnool', 8),
(12349, 'Kumar', '9876543210', 5000, 'Staff', 'Hyderabad', 1),
(12350, 'Swetha', '9876543211', 5000, 'Staff', 'Hyderabad', 1),
(12351, 'Anusha', '9876543212', 5000, 'Staff', 'Hyderabad', 1),
(12352, 'Praveen', '9876543213', 5000, 'Staff', 'Hyderabad', 1),
(12353, 'Nikhil', '9876543214', 5000, 'Staff', 'Hyderabad', 1),
(12354, 'Deepika', '9876543215', 5000, 'Staff', 'Hyderabad', 1),
(12355, 'Suresh', '9876543216', 5000, 'Staff', 'Hyderabad', 1),
(12356, 'Sneha', '9876543217', 5000, 'Staff', 'Hyderabad', 1),
(12357, 'Arjun', '9876543218', 5000, 'Staff', 'Hyderabad', 1),
(12358, 'Manju', '9876543219', 5000, 'Staff', 'Hyderabad', 1),
(12359, 'Sanjay', '9876543220', 5000, 'Staff', 'Hyderabad', 1),
(12360, 'Divya', '9876543221', 5000, 'Staff', 'Hyderabad', 1),
(12361, 'Amit', '9876543222', 5000, 'Staff', 'Hyderabad', 1),
(12362, 'Anita', '9876543223', 5000, 'Staff', 'Hyderabad', 1),
(12363, 'Sudha', '9876543224', 5000, 'Staff', 'Hyderabad', 1),
(12364, 'Ganesh', '9876543225', 5000, 'Staff', 'Hyderabad', 1),
(12365, 'Madhu', '9876543226', 5000, 'Staff', 'Hyderabad', 1),
(12366, 'Asha', '9876543227', 5000, 'Staff', 'Hyderabad', 1),
(12367, 'Vijay', '9876543228', 5000, 'Staff', 'Hyderabad', 1),
(12368, 'Suma', '9876543229', 5000, 'Staff', 'Hyderabad', 1),
(12369, 'Rajesh', '9876543230', 5000, 'Staff', 'Hyderabad', 1),
(12370, 'Varun', '9876543231', 5000, 'Staff', 'Hyderabad', 1),
(12371, 'Pooja', '9876543232', 5000, 'Staff', 'Hyderabad', 1),
(12372, 'Kiran', '9876543233', 5000, 'Staff', 'Hyderabad', 1),
(12373, 'Shiva', '9876543234', 5000, 'Staff', 'Hyderabad', 1),
(12374, 'Prasad', '9876543235', 5000, 'Staff', 'Hyderabad', 1),
(12375, 'Rama', '9876543236', 5000, 'Staff', 'Hyderabad', 1),
(12376, 'Shyam', '9876543237', 5000, 'Staff', 'Hyderabad', 1),
(12377, 'Latha', '9876543238', 5000, 'Staff', 'Hyderabad', 1),
(12378, 'Teja', '9876543239', 5000, 'Staff', 'Hyderabad', 1),
(12379, 'Rohit', '9876543240', 5000, 'Staff', 'Hyderabad', 1),
(12380, 'Vishnu', '9876543241', 5000, 'Staff', 'Hyderabad', 1),
(12381, 'Anjali', '9876543242', 5000, 'Staff', 'Hyderabad', 1),
(12382, 'Raj', '9876543243', 5000, 'Staff', 'Hyderabad', 1),
(12383, 'Kavitha', '9876543244', 5000, 'Staff', 'Hyderabad', 1),
(12384, 'Akash', '9876543245', 5000, 'Staff', 'Hyderabad', 1),
(12385, 'Lakshmi', '9876543246', 5000, 'Staff', 'Hyderabad', 1),
(12386, 'Venu', '9876543247', 5000, 'Staff', 'Hyderabad', 1),
(12387, 'Swathi', '9876543248', 5000, 'Staff', 'Hyderabad', 1),
(12388, 'Sai', '9876543249', 5000, 'Staff', 'Hyderabad', 1),
(12389, 'Sandeep', '9876543250', 5000, 'Staff', 'Hyderabad', 1),
(12390, 'Deepak', '9876543251', 5000, 'Staff', 'Hyderabad', 1),
(12391, 'Jyothi', '9876543252', 5000, 'Staff', 'Hyderabad', 1),
(12392, 'Arvind', '9876543253', 5000, 'Staff', 'Hyderabad', 1),
(12393, 'Sowmya', '9876543254', 5000, 'Staff', 'Hyderabad', 1),
(12394, 'Mohan', '9876543255', 5000, 'Staff', 'Hyderabad', 1),
(12395, 'Kalyan', '9876543256', 5000, 'Staff', 'Hyderabad', 1),
(12396, 'Nandini', '9876543257', 5000, 'Staff', 'Hyderabad', 1),
(12397, 'Srikanth', '9876543258', 5000, 'Staff', 'Hyderabad', 1),
(12398, 'Shravani', '9876543259', 5000, 'Staff', 'Hyderabad', 1),
(12399, 'Giri', '9876543260', 5000, 'Staff', 'Hyderabad', 1),
(12400, 'Anand', '9876543261', 5000, 'Staff', 'Hyderabad', 1),
(12401, 'Sravani', '9876543262', 5000, 'Staff', 'Hyderabad', 1),
(12402, 'Bhaskar', '9876543263', 5000, 'Staff', 'Hyderabad', 1),
(12403, 'Rajini', '9876543264', 5000, 'Staff', 'Hyderabad', 1),
(12404, 'Naveen', '9876543265', 5000, 'Staff', 'Hyderabad', 1),
(12405, 'Pranitha', '9876543266', 5000, 'Staff', 'Hyderabad', 1),
(12406, 'Pavan', '9876543267', 5000, 'Staff', 'Hyderabad', 1),
(12407, 'Suman', '9876543268', 5000, 'Staff', 'Hyderabad', 1),
(12408, 'Jeevan', '9876543269', 5000, 'Staff', 'Hyderabad', 1),
(12409, 'Nikhila', '9876543270', 5000, 'Staff', 'Hyderabad', 1),
(12410, 'Ravi Kumar', '9876543271', 5000, 'Staff', 'Hyderabad', 1),
(12411, 'Satish', '9876543272', 5000, 'Staff', 'Hyderabad', 1),
(12412, 'Poojitha', '9876543273', 5000, 'Staff', 'Hyderabad', 1),
(12413, 'Vinod', '9876543274', 5000, 'Staff', 'Hyderabad', 1),
(12414, 'Sai Kumar', '9876543275', 5000, 'Staff', 'Hyderabad', 1),
(12415, 'Harini', '9876543276', 5000, 'Staff', 'Hyderabad', 1),
(12416, 'Madhavi', '9876543277', 5000, 'Staff', 'Hyderabad', 1),
(12417, 'Ravali', '9876543278', 5000, 'Staff', 'Hyderabad', 1),
(12418, 'Kiran Kumar', '9876543279', 5000, 'Staff', 'Hyderabad', 1),
(12419, 'Sravan', '9876543280', 5000, 'Staff', 'Hyderabad', 1),
(12420, 'Kavitha', '9876543281', 5000, 'Staff', 'Hyderabad', 1);

-- Table creation for farmer
CREATE TABLE IF NOT EXISTS farmer (
  id INT NOT NULL AUTO_INCREMENT,
  fname VARCHAR(20) NOT NULL,
  ph VARCHAR(10) NOT NULL,
  f_vid INT NOT NULL,
  milk_type VARCHAR(30) DEFAULT 'cow',
  milk_price DECIMAL(7,4) DEFAULT 27,
  reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  animalID INT NOT NULL,
  balance DECIMAL(10,3) DEFAULT 0,
  PRIMARY KEY (id)
);


INSERT INTO farmer (id, fname, ph, f_vid, milk_type, milk_price, reg_date, animalID) VALUES
(1, 'Ravi kumar', '2147483647', 1, 'cow', 27, '2021-07-23', 98123),
(2, 'Kishore', '9908407281', 2, 'buffalo', 30, '2021-07-23', 98124),
(3, 'Rahul', '9908407286', 4, 'cow', 27, '2021-07-23', 98125),
(4, 'Lokesh ', '9908407288', 3, 'buffalo', 30, '2021-07-23', 98126),
(5, 'Seshi', '9848870676', 6, 'cow', 27, '2021-07-23', 98127),
(6, 'Nivesh', '9848870673', 5, 'buffalo', 30, '2021-07-23', 98128),
(7, 'Bhargav', '9908407289', 9, 'buffalo', 30, '2021-07-23', 98131),
(8, 'Charan', '9666613257', 7, 'buffalo', 30, '2021-07-23', 98132),
(9, 'Dharma', '9666613258', 0, 'buffalo', 31, '2021-07-23', 98133),
(10, 'Niranjan', '9848870987', 8, 'cow', 27, '2021-07-23', 98134),
(11, 'ravindra', '9848870612', 10, 'buffalo', 30, '2021-07-25', 98145),
(12, 'ramya', '9908407256', 6, 'cow', 29, '2021-07-28', 12378),
(13, 'Anand', '9876543210', 11, 'cow', 28, '2021-07-29', 98146),
(14, 'Rajesh', '9876543211', 12, 'buffalo', 30, '2021-08-02', 98147),
(15, 'Suresh', '9876543212', 13, 'cow', 28, '2021-08-05', 98148),
(16, 'Kiran', '9876543213', 14, 'buffalo', 30, '2021-08-10', 98149),
(17, 'Vijay', '9876543214', 15, 'cow', 28, '2021-08-15', 98150),
(18, 'Vinod', '9876543215', 16, 'buffalo', 30, '2021-08-20', 98151),
(19, 'Sumanth', '9876543216', 17, 'buffalo', 30, '2021-08-25', 98152),
(20, 'Arjun', '9876543217', 18, 'cow', 28, '2021-08-30', 98153),
(21, 'Naveen', '9876543218', 19, 'buffalo', 30, '2021-09-05', 98154),
(22, 'Praveen', '9876543219', 20, 'cow', 28, '2021-09-10', 98155),
(23, 'Manish', '9876543220', 21, 'buffalo', 30, '2021-09-15', 98156),
(24, 'Harish', '9876543221', 22, 'cow', 28, '2021-09-20', 98157),
(25, 'Ashish', '9876543222', 23, 'buffalo', 30, '2021-09-25', 98158),
(26, 'Ramesh', '9876543223', 24, 'cow', 28, '2021-09-30', 98159),
(27, 'Ganesh', '9876543224', 25, 'buffalo', 30, '2021-10-05', 98160),
(28, 'Vishal', '9876543225', 26, 'cow', 28, '2021-10-10', 98161),
(29, 'Sachin', '9876543226', 27, 'buffalo', 30, '2021-10-15', 98162),
(30, 'Nitin', '9876543227', 28, 'cow', 28, '2021-10-20', 98163),
(31, 'Amit', '9876543228', 29, 'buffalo', 30, '2021-10-25', 98164),
(32, 'Rahul', '9876543229', 30, 'cow', 28, '2021-10-30', 98165),
(33, 'Vivek', '9876543230', 31, 'buffalo', 30, '2021-11-05', 98166),
(34, 'Rohit', '9876543231', 32, 'cow', 28, '2021-11-10', 98167),
(35, 'Arun', '9876543232', 33, 'buffalo', 30, '2021-11-15', 98168),
(36, 'Pavan', '9876543233', 34, 'cow', 28, '2021-11-20', 98169),
(37, 'Shiva', '9876543234', 35, 'buffalo', 30, '2021-11-25', 98170),
(38, 'Vikram', '9876543235', 36, 'cow', 28, '2021-11-30', 98171),
(39, 'Gopal', '9876543236', 37, 'buffalo', 30, '2021-12-05', 98172),
(40, 'Sanjay', '9876543237', 38, 'cow', 28, '2021-12-10', 98173),
(41, 'Sunil', '9876543238', 39, 'buffalo', 30, '2021-12-15', 98174),
(42, 'Rajiv', '9876543239', 40, 'cow', 28, '2021-12-20', 98175),
(43, 'Santosh', '9876543240', 41, 'buffalo', 30, '2021-12-25', 98176),
(44, 'Ajay', '9876543241', 42, 'cow', 28, '2021-12-30', 98177),
(45, 'Akhil', '9876543242', 43, 'buffalo', 30, '2022-01-05', 98178),
(46, 'Akash', '9876543243', 44, 'cow', 28, '2022-01-10', 98179),
(47, 'Sudhir', '9876543244', 45, 'buffalo', 30, '2022-01-15', 98180),
(48, 'Rajesh', '9876543245', 46, 'cow', 28, '2022-01-20', 98181),
(49, 'Kiran', '9876543246', 47, 'buffalo', 30, '2022-01-25', 98182),
(50, 'Aruna', '9876543247', 48, 'cow', 28, '2022-01-30', 98183),
(51, 'Anjali', '9876543248', 49, 'buffalo', 30, '2022-02-05', 98184),
(52, 'Sujith', '9876543249', 50, 'cow', 28, '2022-02-10', 98185),
(53, 'Srinivas', '9876543250', 51, 'buffalo', 30, '2022-02-15', 98186),
(54, 'Nikhil', '9876543251', 52, 'cow', 28, '2022-02-20', 98187),
(55, 'Manoj', '9876543252', 53, 'buffalo', 30, '2022-02-25', 98188),
(56, 'Rajini', '9876543253', 54, 'cow', 28, '2022-03-01', 98189),
(57, 'Prakash', '9876543254', 55, 'buffalo', 30, '2022-03-05', 98190),
(58, 'Arvind', '9876543255', 56, 'cow', 28, '2022-03-10', 98191),
(59, 'Shankar', '9876543256', 57, 'buffalo', 30, '2022-03-15', 98192),
(60, 'Kishan', '9876543257', 58, 'cow', 28, '2022-03-20', 98193),
(61, 'Alok', '9876543258', 59, 'buffalo', 30, '2022-03-25', 98194),
(62, 'Vivek', '9876543259', 60, 'cow', 28, '2022-03-30', 98195),
(63, 'Hari', '9876543260', 61, 'buffalo', 30, '2022-04-05', 98196),
(64, 'Pradeep', '9876543261', 62, 'cow', 28, '2022-04-10', 98197),
(65, 'Rajendra', '9876543262', 63, 'buffalo', 30, '2022-04-15', 98198),
(66, 'Satish', '9876543263', 64, 'cow', 28, '2022-04-20', 98199),
(67, 'Naveen', '9876543264', 65, 'buffalo', 30, '2022-04-25', 98200),
(68, 'Dinesh', '9876543265', 66, 'cow', 28, '2022-04-30', 98201),
(69, 'Vijay', '9876543266', 67, 'buffalo', 30, '2022-05-05', 98202),
(70, 'Vinay', '9876543267', 68, 'cow', 28, '2022-05-10', 98203),
(71, 'Santosh', '9876543268', 69, 'buffalo', 30, '2022-05-15', 98204),
(72, 'Girish', '9876543269', 70, 'cow', 28, '2022-05-20', 98205),
(73, 'Kiran', '9876543270', 71, 'buffalo', 30, '2022-05-25', 98206),
(74, 'Srinivas', '9876543271', 72, 'cow', 28, '2022-05-30', 98207),
(75, 'Ramesh', '9876543272', 73, 'buffalo', 30, '2022-06-05', 98208),
(76, 'Ravi', '9876543273', 74, 'cow', 28, '2022-06-10', 98209),
(77, 'Rahul', '9876543274', 75, 'buffalo', 30, '2022-06-15', 98210),
(78, 'Suresh', '9876543275', 76, 'cow', 28, '2022-06-20', 98211),
(79, 'Vivek', '9876543276', 77, 'buffalo', 30, '2022-06-25', 98212),
(80, 'Amit', '9876543277', 78, 'cow', 28, '2022-06-30', 98213),
(81, 'Arjun', '9876543278', 79, 'buffalo', 30, '2022-07-05', 98214),
(82, 'Ajay', '9876543279', 80, 'cow', 28, '2022-07-10', 98215),
(83, 'Anil', '9876543280', 81, 'buffalo', 30, '2022-07-15', 98216),
(84, 'Anand', '9876543281', 82, 'cow', 28, '2022-07-20', 98217),
(85, 'Kishore', '9876543282', 83, 'buffalo', 30, '2022-07-25', 98218),
(86, 'Lokesh', '9876543283', 84, 'cow', 28, '2022-07-30', 98219),
(87, 'Rajesh', '9876543284', 85, 'buffalo', 30, '2022-08-05', 98220),
(88, 'Rahul', '9876543285', 86, 'cow', 28, '2022-08-10', 98221),
(89, 'Suresh', '9876543286', 87, 'buffalo', 30, '2022-08-15', 98222),
(90, 'Naveen', '9876543287', 88, 'cow', 28, '2022-08-20', 98223),
(91, 'Manoj', '9876543288', 89, 'buffalo', 30, '2022-08-25', 98224),
(92, 'Praveen', '9876543289', 90, 'cow', 28, '2022-08-30', 98225),
(93, 'Rajiv', '9876543290', 91, 'buffalo', 30, '2022-09-05', 98226),
(94, 'Raj', '9876543291', 92, 'cow', 28, '2022-09-10', 98227),
(95, 'Ravi', '9876543292', 93, 'buffalo', 30, '2022-09-15', 98228),
(96, 'Rahul', '9876543293', 94, 'cow', 28, '2022-09-20', 98229),
(97, 'Suresh', '9876543294', 95, 'buffalo', 30, '2022-09-25', 98230),
(98, 'Vijay', '9876543295', 96, 'cow', 28, '2022-09-30', 98231),
(99, 'Vivek', '9876543296', 97, 'buffalo', 30, '2022-10-05', 98232),
(100, 'Arun', '9876543297', 98, 'cow', 28, '2022-10-10', 98233);
select * from reciept;
-- Table creation for bill
CREATE TABLE IF NOT EXISTS bill (
  bill_id INT NOT NULL AUTO_INCREMENT,
  farmer_id INT DEFAULT NULL,
  dt DATE,
  farmer_name VARCHAR(20) DEFAULT NULL,
  quantity FLOAT DEFAULT NULL,
  amount FLOAT DEFAULT 0.00,
  PRIMARY KEY (bill_id),
  KEY farmer_id (farmer_id),
  CONSTRAINT bill_ibfk_1 FOREIGN KEY (farmer_id) REFERENCES farmer (id) ON DELETE CASCADE
);
-- Data insertion for bill table
INSERT INTO bill (bill_id, farmer_id, dt, farmer_name, quantity, amount) VALUES
(1, 4, '2024-02-17', 'Lokesh',  2, 120),
(2, 1, '2024-02-17', 'Ravi kumar',  26, 1482),
(3, 4, '2024-02-17', 'Lokesh',  2, 120),
(4, 4, '2024-02-17', 'Lokesh',  2, 120),
(5, 4, '2024-02-17', 'Lokesh',  2, 120),
(6, 4, '2024-02-17', 'Lokesh',  2, 120),
(7, 1, '2024-02-17', 'Ravi kumar', 26, 1482),
(8, 1, '2024-02-17', 'Ravi kumar', 26, 1482),
(9, 1, '2024-02-17', 'Ravi kumar',  26, 1482),
(10, 1, '2024-02-17', 'Ravi kumar', 26, 1482),
(11, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(12, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(13, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(14, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(15, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(16, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(17, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(18, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(19, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(20, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(21, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(22, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(23, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(24, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(25, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(26, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(27, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(28, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(29, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(30, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(31, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(32, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(33, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(34, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(35, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(36, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(37, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(38, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(39, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(40, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(41, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(42, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(43, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(44, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(45, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(46, 1, '2024-02-17', 'Ravi kumar', 117, 969),
(47, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(48, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(49, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(50, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(51, 1, '2024-02-17', 'Ravi kumar',  17, 969),
(52, 1, '2024-02-17', 'Ravi kumar', 17, 969),
(53, 1, '2024-02-17', 'Ravi kumar',  17, 969);

-- Table creation for village
CREATE TABLE IF NOT EXISTS village (
  vid INT NOT NULL AUTO_INCREMENT,
  vname VARCHAR(30) NOT NULL,
  PRIMARY KEY (vid)
);

-- Table creation for animal details
CREATE TABLE IF NOT EXISTS an_details (
  animalID INT,
  fid INT,
  animalTYPE VARCHAR(55),
  reg_date DATE,
  PRIMARY KEY (animalID),
  FOREIGN KEY (fid) REFERENCES farmer(id) ON DELETE CASCADE
);

-- Table creation for daily data entry
CREATE TABLE IF NOT EXISTS daily_data_entry (
    fid INT,   
    quantity INT,
    daily_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (fid) REFERENCES farmer(id) ON DELETE CASCADE
);
select * from bill;
-- Data insertion for daily_data_entry table
INSERT INTO daily_data_entry values(

-- Data insertion for animal details
INSERT INTO an_details (animalID, fid, animalTYPE, reg_date) VALUES
(1, 1, 'Cow', '2024-02-01'),
(2, 1, 'Buffalo', '2024-02-02'),
(3, 2, 'Goat', '2024-02-03'),
(4, 2, 'Sheep', '2024-02-04'),
(5, 3, 'Pig', '2024-02-05'),
(6, 3, 'Horse', '2024-02-06'),
(7, 4, 'Duck', '2024-02-07'),
(8, 4, 'Rabbit', '2024-02-08'),
(9, 5, 'Turkey', '2024-02-09'),
(10, 5, 'Dog', '2024-02-10'),
(11, 6, 'Cat', '2024-02-11'),
(12, 6, 'Sheep', '2024-02-12'),
(13, 7, 'Cow', '2024-02-13'),
(14, 7, 'Goat', '2024-02-14'),
(15, 8, 'Chicken', '2024-02-15'),
(16, 8, 'Pig', '2024-02-16'),
(17, 9, 'Horse', '2024-02-17'),
(18, 9, 'Duck', '2024-02-18'),
(19, 1, 'Buffalo', '2024-02-19'),
(20, 1, 'Cow', '2024-02-20');

-- Table creation for complaints
CREATE TABLE IF NOT EXISTS complaint (
    complaint_id INT AUTO_INCREMENT PRIMARY KEY,
    id INT,
    complaint_text TEXT,
    status VARCHAR(50),
    employee_id INT,
    FOREIGN KEY (id) REFERENCES farmer(id) ON DELETE CASCADE
);

-- Description of the complaint table
DESC complaint;





-- Table creation for messages
CREATE TABLE IF NOT EXISTS messages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    farmer_id INT,
    farmer_name VARCHAR(20),
    ph VARCHAR(10),
    complaint_text TEXT,
    status VARCHAR(50),
    employee_id INT,
    FOREIGN KEY (farmer_id) REFERENCES farmer(id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employee(eid) ON DELETE CASCADE
);

-- Inserting data into the messages table from the complaint table joined with the farmer table
INSERT INTO messages (farmer_id, farmer_name, ph, complaint_text, status, employee_id)
SELECT c.id, f.fname, f.ph, c.complaint_text, c.status, c.employee_id
FROM complaint c
JOIN farmer f ON c.id = f.id;


-- Table creation for users
-- Edit the code to create the users table if it doesn't exist
CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL,
  password VARCHAR(30) NOT NULL,
  role VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

-- Data insertion for users table (assuming each employee is a user)
-- Edit the insertion statement to insert data only if it doesn't already exist
INSERT INTO users (id, username, password, role)
SELECT eid, ename, phno, designation
FROM employee
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE users.id = employee.eid
)
UNION 
SELECT id, Fname, ph, MILK_TYPE
FROM FARMER
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE users.id = FARMER.id
);

select * from users;



-- Table creation for chat_history
CREATE TABLE IF NOT EXISTS chat_history (
    chat_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT,
    receiver_id INT,
    message TEXT,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Inserting data into the chat_history table from the messages table
INSERT INTO chat_history (sender_id, receiver_id, message, sent_at)
SELECT 
    CASE WHEN m.employee_id IS NOT NULL THEN m.employee_id ELSE m.farmer_id END AS sender_id,
    CASE WHEN m.employee_id IS NOT NULL THEN m.farmer_id ELSE m.employee_id END AS receiver_id,
    m.complaint_text AS message,
    CURRENT_TIMESTAMP AS sent_at
FROM messages m;
SELECT * FROM CHAT_HISTORY;






-- Constraint to ensure the salary is positive in the employee table
ALTER TABLE employee
ADD CONSTRAINT positive_salary CHECK (salary > 0);

-- Constraint to ensure the phone number format is valid in the employee table
ALTER TABLE employee
ADD CONSTRAINT valid_phno CHECK (LENGTH(phno) = 10 AND phno REGEXP '^[0-9]+$');

-- Constraint to ensure the phone number format is valid in the farmer table
ALTER TABLE farmer
ADD CONSTRAINT valid_ph CHECK (LENGTH(ph) = 10 AND ph REGEXP '^[0-9]+$');

-- Constraint to ensure the quantity is positive in the bill table
ALTER TABLE bill
ADD CONSTRAINT positive_quantity CHECK (quantity > 0);

-- Constraint to ensure the amount is positive in the bill table
ALTER TABLE bill
ADD CONSTRAINT positive_amount CHECK (amount > 0);


SELECT farmer_name, bill_id, dt, amount FROM bill WHERE farmer_id = 1 AND dt BETWEEN 
'2020-02-02' AND '2025-02-02';



-- Create trigger to insert into bill table when daily_data_entry is updated
-- Modify the trigger to calculate the amount based on quantity and milk price
DELIMITER $$
CREATE TRIGGER insert_bill_after_data_entry
AFTER INSERT ON daily_data_entry
FOR EACH ROW
BEGIN
    DECLARE price DECIMAL(10,2);
    
    -- Fetch the milk price for the corresponding farmer
    SELECT milk_price INTO price FROM farmer WHERE id = NEW.fid;
    
    -- Calculate the amount based on quantity and milk price
    SET @amount = NEW.quantity * (price);
    
    -- Insert into the bill table with calculated amount
    INSERT INTO bill (farmer_id, dt, farmer_name, quantity, amount)
     VALUES (NEW.fid, NEW.daily_date, (SELECT fname FROM farmer WHERE id = NEW.fid), NEW.quantity, @amount);
END$$
DELIMITER ;
select * from bill;

select * from farmer;



-- Trigger to insert new employee data into users table
DELIMITER //
CREATE TRIGGER after_employee_insert
AFTER INSERT ON employee
FOR EACH ROW
BEGIN
    INSERT INTO users (id, username, password, role)
    VALUES (NEW.eid, NEW.ename, NEW.phno, NEW.designation)
    ON DUPLICATE KEY UPDATE username = NEW.ename, password = NEW.phno, role = NEW.designation;
END;
//

-- Trigger to insert new farmer data into users table
DELIMITER //
CREATE TRIGGER after_farmer_insert
AFTER INSERT ON farmer
FOR EACH ROW
BEGIN
    INSERT INTO users (id, username, password, role)
    VALUES (NEW.id, NEW.fname, NEW.ph, NEW.milk_type)
    ON DUPLICATE KEY UPDATE username = NEW.fname, password = NEW.ph, role = NEW.milk_type;
END;
//
DELIMITER //
CREATE TRIGGER after_farmer_insertt
AFTER INSERT ON farmer
FOR EACH ROW
BEGIN
    INSERT INTO an_details (animalID,fid,animalTYPE, reg_date)
    VALUES (NEW.animalID,NEW.id,new.milk_type, NEW.reg_date);
END;
//
DELIMITER ;


select * from an_details;
INSERT INTO farmer (id, fname, ph, f_vid, milk_type, milk_price, reg_date, animalID) VALUES
(1981181, 'Rana ', '9876543210', 1, 'Cow', 31, '2023-01-01', 119218);

INSERT INTO farmer (id, fname, ph, f_vid, milk_type, milk_price, reg_date, animalID) VALUES
(101, 'Ramesh Kumar', '9876543210', 1, 'Buffalo', 35, '2023-01-01', 1001),
(102, 'Sita Devi', '9876543211', 2, 'Cow', 32, '2023-01-02', 1002),
(103, 'Amit Singh', '9876543212', 3, 'Buffalo', 38, '2023-01-03', 1003),
(104, 'Priya Sharma', '9876543213', 4, 'Cow', 29, '2023-01-04', 1004),
(105, 'Vikram Patel', '9876543214', 5, 'Buffalo', 33, '2023-01-05', 1005),
(106, 'Anita Yadav', '9876543215', 6, 'Cow', 37, '2023-01-06', 1006),
(107, 'Rajesh Gupta', '9876543216', 7, 'Buffalo', 30, '2023-01-07', 1007),
(108, 'Pooja Singh', '9876543217', 8, 'Cow', 39, '2023-01-08', 1008),
(109, 'Manoj Kumar', '9876543218', 9, 'Buffalo', 28, '2023-01-09', 1009),
(110, 'Kavita Sharma', '9876543219', 10, 'Cow', 34, '2023-01-10', 1010),
(111, 'Deepak Verma', '9876543220', 1, 'Buffalo', 36, '2023-01-11', 1011),
(112, 'Neha Singh', '9876543221', 2, 'Cow', 31, '2023-01-12', 1012),
(113, 'Sanjay Patel', '9876543222', 3, 'Buffalo', 40, '2023-01-13', 1013),
(114, 'Ritu Yadav', '9876543223', 4, 'Cow', 27, '2023-01-14', 1014),
(115, 'Suresh Gupta', '9876543224', 5, 'Buffalo', 35, '2023-01-15', 1015),
(116, 'Aarti Sharma', '9876543225', 6, 'Cow', 32, '2023-01-16', 1016),
(117, 'Rajni Devi', '9876543226', 7, 'Buffalo', 38, '2023-01-17', 1017),
(118, 'Kunal Singh', '9876543227', 8, 'Cow', 29, '2023-01-18', 1018),
(119, 'Meena Patel', '9876543228', 9, 'Buffalo', 33, '2023-01-19', 1019),
(120, 'Vishal Verma', '9876543229', 10, 'Cow', 37, '2023-01-20', 1020),
(121, 'Neelam Kumari', '9876543230', 1, 'Buffalo', 30, '2023-01-21', 1021),
(122, 'Aman Singh', '9876543231', 2, 'Cow', 39, '2023-01-22', 1022),
(123, 'Renu Gupta', '9876543232', 3, 'Buffalo', 28, '2023-01-23', 1023),
(124, 'Anil Kumar', '9876543233', 4, 'Cow', 34, '2023-01-24', 1024),
(125, 'Priyanka Sharma', '9876543234', 5, 'Buffalo', 36, '2023-01-25', 1025),
(126, 'Vijay Patel', '9876543235', 6, 'Cow', 31, '2023-01-26', 1026),
(127, 'Kiran Yadav', '9876543236', 7, 'Buffalo', 40, '2023-01-27', 1027),
(128, 'Gaurav Gupta', '9876543237', 8, 'Cow', 27, '2023-01-28', 1028),
(129, 'Divya Singh', '9876543238', 9, 'Buffalo', 35, '2023-01-29', 1029),
(130, 'Rohit Patel', '9876543239', 10, 'Cow', 32, '2023-01-30', 1030),
(131, 'Pallavi Verma', '9876543240', 1, 'Buffalo', 38, '2023-01-31', 1031),
(132, 'Satish Kumar', '9876543241', 2, 'Cow', 29, '2023-02-01', 1032),
(133, 'Reena Singh', '9876543242', 3, 'Buffalo', 33, '2023-02-02', 1033),
(134, 'Harish Gupta', '9876543243', 4, 'Cow', 37, '2023-02-03', 1034),
(135, 'Geeta Patel', '9876543244', 5, 'Buffalo', 30, '2023-02-04', 1035),
(136, 'Ajay Sharma', '9876543245', 6, 'Cow', 39, '2023-02-05', 1036),
(137, 'Sangeeta Yadav', '9876543246', 7, 'Buffalo', 28, '2023-02-06', 1037),
(138, 'Ankit Gupta', '9876543247', 8, 'Cow', 34, '2023-02-07', 1038),
(139, 'Nisha Singh', '9876543248', 9, 'Buffalo', 36, '2023-02-08', 1039),
(140, 'Alok Patel', '9876543249', 10, 'Cow', 31, '2023-02-09', 1040),
(141, 'Priyanka Kumari', '9876543250', 1, 'Buffalo', 40, '2023-02-10', 1041),
(142, 'Vinod Singh', '9876543251', 2, 'Cow', 27, '2023-02-11', 1042),
(143, 'Madhu Gupta', '9876543252', 3, 'Buffalo', 35, '2023-02-12', 1043),
(144, 'Rahul Patel', '9876543253', 4, 'Cow', 32, '2023-02-13', 1044),
(145, 'Shalini Yadav', '9876543254', 5, 'Buffalo', 38, '2023-02-14', 1045),
(146, 'Deepak Kumar', '9876543255', 6, 'Cow', 29, '2023-02-15', 1046),
(147, 'Anjali Sharma', '9876543256', 7, 'Buffalo', 33, '2023-02-16', 1047),
(148, 'Santosh Gupta', '9876543257', 8, 'Cow', 37, '2023-02-17', 1048),
(149, 'Radha Patel', '9876543258', 9, 'Buffalo', 30, '2023-02-18', 1049),
(150, 'Vivek Singh', '9876543259', 10, 'Cow', 39, '2023-02-19', 1050),
(151, 'Sneha Kumari', '9876543260', 1, 'Buffalo', 28, '2023-02-20', 1051),
(152, 'Rajesh Yadav', '9876543261', 2, 'Cow', 34, '2023-02-21', 1052),
(153, 'Asha Gupta', '9876543262', 3, 'Buffalo', 36, '2023-02-22', 1053),
(154, 'Amita Patel', '9876543263', 4, 'Cow', 31, '2023-02-23', 1054),
(155, 'Shivam Singh', '9876543264', 5, 'Buffalo', 40, '2023-02-24', 1055),
(156, 'Mamta Yadav', '9876543265', 6, 'Cow', 27, '2023-02-25', 1056),
(157, 'Rajiv Kumar', '9876543266', 7, 'Buffalo', 35, '2023-02-26', 1057),
(158, 'Anita Gupta', '9876543267', 8, 'Cow', 32, '2023-02-27', 1058),
(159, 'Alok Singh', '9876543268', 9, 'Buffalo', 38, '2023-02-28', 1059),
(160, 'Komal Patel', '9876543269', 10, 'Cow', 29, '2023-03-01', 1060),
(161, 'Suman Kumari', '9876543270', 1, 'Buffalo', 33, '2023-03-02', 1061),
(162, 'Amit Kumar', '9876543271', 2, 'Cow', 37, '2023-03-03', 1062),
(163, 'Ritu Gupta', '9876543272', 3, 'Buffalo', 30, '2023-03-04', 1063),
(164, 'Vikas Patel', '9876543273', 4, 'Cow', 39, '2023-03-05', 1064),
(165, 'Neha Singh', '9876543274', 5, 'Buffalo', 28, '2023-03-06', 1065),
(166, 'Rajeev Yadav', '9876543275', 6, 'Cow', 34, '2023-03-07', 1066),
(167, 'Sapna Gupta', '9876543276', 7, 'Buffalo', 36, '2023-03-08', 1067),
(168, 'Arvind Patel', '9876543277', 8, 'Cow', 31, '2023-03-09', 1068),
(169, 'Pooja Singh', '9876543278', 9, 'Buffalo', 40, '2023-03-10', 1069),
(170, 'Arun Kumar', '9876543279', 10, 'Cow', 27, '2023-03-11', 1070),
(171, 'Anjali Gupta', '9876543280', 1, 'Buffalo', 35, '2023-03-12', 1071),
(172, 'Rahul Singh', '9876543281', 2, 'Cow', 32, '2023-03-13', 1072),
(173, 'Swati Patel', '9876543282', 3, 'Buffalo', 38, '2023-03-14', 1073),
(174, 'Vishal Yadav', '9876543283', 4, 'Cow', 29, '2023-03-15', 1074),
(175, 'Kavita Gupta', '9876543284', 5, 'Buffalo', 33, '2023-03-16', 1075),
(176, 'Suresh Patel', '9876543285', 6, 'Cow', 37, '2023-03-17', 1076),
(177, 'Priya Singh', '9876543286', 7, 'Buffalo', 30, '2023-03-18', 1077),
(178, 'Arjun Kumar', '9876543287', 8, 'Cow', 39, '2023-03-19', 1078),
(179, 'Sonia Patel', '9876543288', 9, 'Buffalo', 28, '2023-03-20', 1079),
(180, 'Aarti Yadav', '9876543289', 10, 'Cow', 34, '2023-03-21', 1080),
(181, 'Vikram Singh', '9876543290', 1, 'Buffalo', 36, '2023-03-22', 1081),
(182, 'Jyoti Gupta', '9876543291', 2, 'Cow', 31, '2023-03-23', 1082),
(183, 'Alok Yadav', '9876543292', 3, 'Buffalo', 40, '2023-03-24', 1083),
(184, 'Anita Patel', '9876543293', 4, 'Cow', 27, '2023-03-25', 1084),
(185, 'Aman Singh', '9876543294', 5, 'Buffalo', 35, '2023-03-26', 1085),
(186, 'Kavita Sharma', '9876543295', 6, 'Cow', 32, '2023-03-27', 1086),
(187, 'Suresh Gupta', '9876543296', 7, 'Buffalo', 38, '2023-03-28', 1087),
(188, 'Renu Patel', '9876543297', 8, 'Cow', 29, '2023-03-29', 1088),
(189, 'Rajni Yadav', '9876543298', 9, 'Buffalo', 33, '2023-03-30', 1089),
(190, 'Ajay Kumar', '9876543299', 10, 'Cow', 37, '2023-03-31', 1090),
(191, 'Pooja Singh', '9876543300', 1, 'Buffalo', 30, '2023-04-01', 1091),
(192, 'Vikas Gupta', '9876543301', 2, 'Cow', 39, '2023-04-02', 1092),
(193, 'Anita Kumari', '9876543302', 3, 'Buffalo', 28, '2023-04-03', 1093),
(194, 'Ravi Patel', '9876543303', 4, 'Cow', 34, '2023-04-04', 1094),
(195, 'Neetu Singh', '9876543304', 5, 'Buffalo', 36, '2023-04-05', 1095),
(196, 'Ajit Yadav', '9876543305', 6, 'Cow', 31, '2023-04-06', 1096),
(197, 'Rajesh Sharma', '9876543306', 7, 'Buffalo', 40, '2023-04-07', 1097),
(198, 'Geeta Patel', '9876543307', 8, 'Cow', 27, '2023-04-08', 1098),
(199, 'Sanjay Singh', '9876543308', 9, 'Buffalo', 35, '2023-04-09', 1099),
(200, 'Shilpa Gupta', '9876543309', 10, 'Cow', 32, '2023-04-10', 1100);



INSERT INTO employee (eid, ename, phno, salary, designation, address, e_vid) VALUES
(12421, 'Rahul', '9876543282', 5000, 'Staff', 'Mumbai', 2),
(12422, 'Neha', '9876543283', 5000, 'Staff', 'Delhi', 3),
(12423, 'Amitabh', '9876543284', 5000, 'Staff', 'Bangalore', 4),
(12424, 'Priyanka', '9876543285', 5000, 'Staff', 'Chennai', 5),
(12425, 'Vivek', '9876543286', 5000, 'Staff', 'Kolkata', 6),
(12426, 'Deepak', '9876543287', 5000, 'Staff', 'Pune', 7),
(12427, 'Ananya', '9876543288', 5000, 'Staff', 'Hyderabad', 8),
(12428, 'Arun', '9876543289', 5000, 'Staff', 'Mumbai', 9),
(12429, 'Divya', '9876543290', 5000, 'Staff', 'Delhi', 10),
(12430, 'Rajesh', '9876543291', 5000, 'Staff', 'Bangalore', 1),
(12431, 'Sneha', '9876543292', 5000, 'Staff', 'Chennai', 2),
(12432, 'Rohan', '9876543293', 5000, 'Staff', 'Kolkata', 3),
(12433, 'Pooja', '9876543294', 5000, 'Staff', 'Pune', 4),
(12434, 'Manish', '9876543295', 5000, 'Staff', 'Hyderabad', 5),
(12435, 'Ritu', '9876543296', 5000, 'Staff', 'Mumbai', 6),
(12436, 'Vikram', '9876543297', 5000, 'Staff', 'Delhi', 7),
(12437, 'Anjali', '9876543298', 5000, 'Staff', 'Bangalore', 8),
(12438, 'Surya', '9876543299', 5000, 'Staff', 'Chennai', 9),
(12439, 'Nisha', '9876543300', 5000, 'Staff', 'Kolkata', 10),
(12440, 'Alok', '9876543301', 5000, 'Staff', 'Pune', 1),
(12441, 'Sarika', '9876543302', 5000, 'Staff', 'Hyderabad', 2),
(12442, 'Akash', '9876543303', 5000, 'Staff', 'Mumbai', 3),
(12443, 'Preeti', '9876543304', 5000, 'Staff', 'Delhi', 4),
(12444, 'Rajat', '9876543305', 5000, 'Staff', 'Bangalore', 5),
(12445, 'Anita', '9876543306', 5000, 'Staff', 'Chennai', 6),
(12446, 'Vikas', '9876543307', 5000, 'Staff', 'Kolkata', 7),
(12447, 'Rakhi', '9876543308', 5000, 'Staff', 'Pune', 8),
(12448, 'Amit', '9876543309', 5000, 'Staff', 'Hyderabad', 9),
(12449, 'Nitin', '9876543310', 5000, 'Staff', 'Mumbai', 10),
(12450, 'Ananya', '9876543311', 5000, 'Staff', 'Delhi', 1),
(12451, 'Aryan', '9876543312', 5000, 'Staff', 'Bangalore', 2),
(12452, 'Ruchi', '9876543313', 5000, 'Staff', 'Chennai', 3),
(12453, 'Kapil', '9876543314', 5000, 'Staff', 'Kolkata', 4),
(12454, 'Tina', '9876543315', 5000, 'Staff', 'Pune', 5),
(12455, 'Sunny', '9876543316', 5000, 'Staff', 'Hyderabad', 6),
(12456, 'Aradhya', '9876543317', 5000, 'Staff', 'Mumbai', 7),
(12457, 'Rahul', '9876543318', 5000, 'Staff', 'Delhi', 8),
(12458, 'Sarika', '9876543319', 5000, 'Staff', 'Bangalore', 9),
(12459, 'Rahul', '9876543320', 5000, 'Staff', 'Chennai', 10),
(12460, 'Roshan', '9876543321', 5000, 'Staff', 'Kolkata', 1),
(12461, 'Shreya', '9876543322', 5000, 'Staff', 'Pune', 2),
(12462, 'Sameer', '9876543323', 5000, 'Staff', 'Hyderabad', 3),
(12463, 'Sahana', '9876543324', 5000, 'Staff', 'Mumbai', 4),
(12464, 'Anjali', '9876543325', 5000, 'Staff', 'Delhi', 5),
(12465, 'Vivek', '9876543326', 5000, 'Staff', 'Bangalore', 6),
(12466, 'Riya', '9876543327', 5000, 'Staff', 'Chennai', 7),
(12467, 'Nandini', '9876543328', 5000, 'Staff', 'Kolkata', 8),
(12468, 'Ravi', '9876543329', 5000, 'Staff', 'Pune', 9),
(12469, 'Aruna', '9876543330', 5000, 'Staff', 'Hyderabad', 10),
(12470, 'Varun', '9876543331', 5000, 'Staff', 'Mumbai', 1),
(12471, 'Rahul', '9876543332', 5000, 'Staff', 'Delhi', 2),
(12472, 'Shivani', '9876543333', 5000, 'Staff', 'Bangalore', 3),
(12473, 'Santosh', '9876543334', 5000, 'Staff', 'Chennai', 4),
(12474, 'Pranav', '9876543335', 5000, 'Staff', 'Kolkata', 5),
(12475, 'Simran', '9876543336', 5000, 'Staff', 'Pune', 6),
(12476, 'Rohit', '9876543337', 5000, 'Staff', 'Hyderabad', 7),
(12477, 'Sarita', '9876543338', 5000, 'Staff', 'Mumbai', 8),
(12478, 'Sanjay', '9876543339', 5000, 'Staff', 'Delhi', 9),
(12479, 'Priya', '9876543340', 5000, 'Staff', 'Bangalore', 10),
(12480, 'Sushant', '9876543341', 5000, 'Staff', 'Chennai', 1),
(12481, 'Shreya', '9876543342', 5000, 'Staff', 'Kolkata', 2),
(12482, 'Sumanth', '9876543343', 5000, 'Staff', 'Pune', 3),
(12483, 'Pooja', '9876543344', 5000, 'Staff', 'Hyderabad', 4),
(12484, 'Sachin', '9876543345', 5000, 'Staff', 'Mumbai', 5),
(12485, 'Rahul', '9876543346', 5000, 'Staff', 'Delhi', 6),
(12486, 'Shalini', '9876543347', 5000, 'Staff', 'Bangalore', 7),
(12487, 'Nikhil', '9876543348', 5000, 'Staff', 'Chennai', 8),
(12488, 'Aarti', '9876543349', 5000, 'Staff', 'Kolkata', 9),
(12489, 'Sahil', '9876543350', 5000, 'Staff', 'Pune', 10),
(12490, 'Rani', '9876543351', 5000, 'Staff', 'Hyderabad', 1),
(12491, 'Raghav', '9876543352', 5000, 'Staff', 'Mumbai', 2),
(12492, 'Shruti', '9876543353', 5000, 'Staff', 'Delhi', 3),
(12493, 'Rohan', '9876543354', 5000, 'Staff', 'Bangalore', 4),
(12494, 'Rajesh', '9876543355', 5000, 'Staff', 'Chennai', 5),
(12495, 'Tanvi', '9876543356', 5000, 'Staff', 'Kolkata', 6),
(12496, 'Shiva', '9876543357', 5000, 'Staff', 'Pune', 7),
(12497, 'Shreya', '9876543358', 5000, 'Staff', 'Hyderabad', 8),
(12498, 'Aakash', '9876543359', 5000, 'Staff', 'Mumbai', 9),
(12499, 'Ananya', '9876543360', 5000, 'Staff', 'Delhi', 10),
(12500, 'Rahul', '9876543361', 5000, 'Staff', 'Bangalore', 1);

select * from an_details;
select * from farmer;

CREATE TABLE IF NOT EXISTS reciept (
  reciept_id INT NOT NULL AUTO_INCREMENT,
  farmer_id INT DEFAULT NULL,
  dt DATE,
  farmer_name VARCHAR(20) DEFAULT NULL,
  quantity FLOAT DEFAULT NULL,
  amount FLOAT DEFAULT 0.00,
  PRIMARY KEY (reciept_id),
  KEY farmer_id (farmer_id),
  CONSTRAINT bill_ibfk_2 FOREIGN KEY (farmer_id) REFERENCES farmer (id) ON DELETE CASCADE
);
drop trigger after_delete_bill;
DELIMITER //
CREATE TRIGGER after_delete_bill
AFTER DELETE ON bill
FOR EACH ROW
BEGIN
    INSERT INTO reciept (reciept_id,farmer_id, dt, farmer_name, quantity, amount)
    VALUES (OLD.bill_id,OLD.farmer_id, OLD.dt, OLD.farmer_name, OLD.quantity, OLD.amount);
END;
//
DELIMITER ;

drop TRIGGER before_insert_bill;
DELIMITER //
CREATE TRIGGER before_insert_bill
BEFORE INSERT ON bill
FOR EACH ROW
BEGIN
    DECLARE reciept_exists INT;
    SET reciept_exists = (SELECT COUNT(*) FROM reciept WHERE reciept_id = NEW.bill_id);
    IF reciept_exists > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot insert bill with a receipt ID that already exists';
    END IF;
END//

DELIMITER ;


delete from bill where bill_id=99;
update farmer set balance=690 where id=69;
select * from farmer;
select * from reciept;
INSERT INTO bill (bill_id, farmer_id, dt, farmer_name, quantity, amount) VALUES
(31, 31, '2024-02-17', 'Amit',  2, 120);
select * from bill;

SELECT * FROM farmer WHERE id = (SELECT fid FROM daily_data_entry GROUP BY fid ORDER BY SUM(quantity) DESC LIMIT 1);


SELECT f_vid, SUM(quantity) AS total_milk
FROM daily_data_entry
JOIN farmer ON daily_data_entry.fid = farmer.id
GROUP BY f_vid
ORDER BY total_milk DESC
LIMIT 1;


INSERT INTO daily_data_entry (fid, quantity, daily_date)
SELECT 
    id,
    FLOOR(RAND() * 10) + 1 AS quantity,  -- Random quantity between 1 and 10
    DATE_ADD(CURDATE(), INTERVAL -FLOOR(RAND() * 365) DAY) AS daily_date -- Random date from last year to now
FROM
    farmer
WHERE
    id BETWEEN 101 AND 200;
select * from bill;