<p align="center">
  <img src="https://github.com/user-attachments/assets/7831f049-1834-42c9-a292-d59b49237140" alt="Milk Cooperative Logo" width="150px">
</p>

<h1 align="center" style="font-size: 2.5em;">🐄 Milk Cooperative Management System</h1>
<p align="center" style="font-size: 1.2em;">
  Digitizing rural dairy cooperatives for efficiency and transparency
</p>

---

## 🚀 **Key Features**
- **Modern UI** with intuitive dashboards
- **Automated Billing & Payments** for real-time financial tracking
- **Quality Control** for milk standards assurance
- **Data Analytics** to boost productivity
- **Secure & Scalable** infrastructure

---

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

## 📖 **Table of Contents**
1. [Introduction](#introduction)
2. [Problem Definition](#problem-definition)
3. [Objectives](#objectives)
4. [Technologies Used](#technologies-used)
5. [System Architecture](#system-architecture)
6. [Modules](#modules)
7. [Database Design](#database-design)
8. [User Interface Design](#user-interface-design)
9. [Testing](#testing)
10. [Installation and Setup](#installation-and-setup)
11. [Required JAR Files](#required-jar-files)
12. [Future Scope](#future-scope)
13. [Conclusion](#conclusion)

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

## 🌱 **Introduction** <a name="introduction"></a>
The **Milk Cooperative Management System** is designed to optimize the operations of rural dairy cooperatives by digitizing milk collection, billing, quality control, and communication between farmers and managers. This system fosters transparency and increases efficiency by automating many manual tasks.

---
## 📸 **Snapshots of the Application** <a name="snapshots"></a>

### 🏠 **Homepage**
<p align="center">
  <img src="https://github.com/user-attachments/assets/e66254e1-c1b7-45eb-bdb5-af0af327f518" alt="Homepage" width="600px">
</p>
*The homepage serves as the central hub for users, displaying an overview of the Milk Cooperative Management System. It provides quick access to various functionalities, including the latest updates and announcements relevant to farmers and employees.*

---

### 🔑 **Login Page**
<p align="center">
  <img src="https://github.com/user-attachments/assets/8b25f70f-443e-443d-8484-ae4551a6dce8" alt="Login Page" width="600px">
</p>
*The login page provides secure access to the application, requiring users to enter their credentials. It ensures that only authorized personnel can access sensitive data, maintaining the integrity of the system.*

---

### 🐄 **Farmer Dashboard**
<p align="center">
  <img src="https://github.com/user-attachments/assets/2dc8c2e4-77fd-48f5-b8d1-4782655d3747" alt="Payment Receiver in Farmer Dashboard" width="600px">
</p>
*The farmer dashboard presents a user-friendly interface for farmers to manage their activities. This page includes a payment receiver feature that allows farmers to view their payment details and track financial transactions easily.*

---

### 👨‍🌾 **Employee Dashboard**
<p align="center">
  <img src="https://github.com/user-attachments/assets/60e1f31b-1772-4871-a13a-9fad176b5b3a" alt="Employee Dashboard" width="600px">
</p>
*The employee dashboard allows staff to manage farmer records, log daily milk collections, and track billing history. This page facilitates efficient operations within the cooperative by providing essential data management tools.*

---

### 📊 **Employee Visualization**
<p align="center">
  <img src="https://github.com/user-attachments/assets/f8d0085d-60f9-4e78-964a-6d5712691fe3" alt="Visualization of Employees Working in the Cooperative" width="600px">
</p>
*This page provides a visualization of the employees working in the cooperative, allowing management to track workforce productivity and ensure that resources are allocated efficiently.*

---

### 💬 **Messaging Application in Employee Dashboard**
<p align="center">
  <img src="https://github.com/user-attachments/assets/52c24af7-8221-4103-9854-8845569c76d9" alt="Messaging Application in Employee Dashboard" width="600px">
</p>
*The messaging application within the employee dashboard enables communication between staff members, facilitating collaboration and ensuring that important updates are shared promptly.*

---

### 📋 **Complaints Page in Farmer Dashboard**
<p align="center">
  <img src="https://github.com/user-attachments/assets/b9c3882a-a097-4cfd-8d29-5c185c049ab4" alt="Complaints Page in Farmer Dashboard" width="600px">
</p>
*The complaints page allows farmers to report any issues or concerns regarding the services provided. This feature ensures that the cooperative can address problems promptly and maintain a high level of service.*

---

### 📄 **About Page**
<p align="center">
  <img src="https://github.com/user-attachments/assets/72c61bec-2da0-41fe-b3b3-0dacce946952" alt="About Page" width="600px">
</p>
*The about page outlines the objectives and benefits of the Milk Cooperative Management System. It gives users insight into the purpose of the application and how it enhances dairy cooperative operations.*

---

## 🔍 **Problem Definition** <a name="problem-definition"></a>
Traditional cooperatives encounter challenges such as manual record-keeping, lack of transparency, and inefficient communication. The **Milk Cooperative Management System** solves these issues by providing an automated digital solution.

---

## 🎯 **Objectives** <a name="objectives"></a>
This system aims to:
- **Digitize record-keeping** to avoid manual errors
- Ensure **transparent payments**
- Implement **automated quality checks**
- Provide seamless **farmer-manager communication**
- **Analyze market trends** for better production alignment

---

## 🛠️ **Technologies Used** <a name="technologies-used"></a>
| **Technology**            | **Usage**               |
|---------------------------|-------------------------|
| Java                       | Core Programming        |
| MySQL                      | Database Management     |
| Java Swing & AWT           | UI Design               |
| Eclipse IDE, MySQL Server  | Development Environment |
| Windows XP or higher       | Operating System        |

---

## 🏗️ **System Architecture** <a name="system-architecture"></a>
The system follows a **modular architecture** integrating farmer dashboards, employee dashboards, and billing modules through a centralized MySQL database.

<p align="center">
  <img src="images/system_architecture.png" alt="System Architecture" width="400px">
</p>

---

## 📋 **Modules** <a name="modules"></a>

### 🌾 6.1 Farmer Dashboard
- **Animal Management:** Track health, breed, and milk production.
- **Milk Contribution:** Record daily contributions.
- **Billing:** View and download invoices.

### 📊 6.2 Employee Dashboard
- **Farmer Management:** Add and edit farmer records.
- **Daily Records:** Log daily milk collection and quality checks.

### 🔐 6.3 Login System
- **Secure Authentication** with role-based access.

---

## 🗃️ **Database Design** <a name="database-design"></a>
The MySQL database securely stores all cooperative data:
- **Farmer details**
- **Animal production records**
- **Billing history**

<p align="center">
  <img src="images/database_schema.png" alt="Database Schema" width="450px">
</p>

---

## 🖥️ **User Interface Design** <a name="user-interface-design"></a>
The interface is built with user-friendliness in mind:
- **Farmer-friendly dashboards** for easy data entry.
- **Responsive layout** that works on desktop and mobile devices.
- **Data visualization** for insights into milk production and billing.

---

## 🧪 **Testing** <a name="testing"></a>

### ✅ 9.1 Test Cases
- **Login Module:** Tested for secure access and session management.
- **Data Entry:** Ensured correct milk and billing data entries.
- **Error Handling:** Handled invalid inputs and duplicates.

---

## ⚙️ **Installation and Setup** <a name="installation-and-setup"></a>

### Prerequisites
- **Java Development Kit (JDK)**
- **MySQL Server**

### Setup Steps:
1. Install MySQL and run the provided SQL script:
    ```sql
    CREATE DATABASE milk_cooperative;
    ```

2. Update `app.properties` with MySQL credentials:
    ```properties
    db.url=jdbc:mysql://localhost:3306/milk_cooperative
    db.user=root
    db.password=yourpassword
    ```

3. Compile and run the Java application:
    ```bash
    javac Main.java
    java Main
    ```

---

## 📦 **Required JAR Files** <a name="required-jar-files"></a>

To ensure the application runs smoothly, you need to add the following JAR files to your classpath. These are essential for the application to work as intended:

1. **Hibernate Core**: `hibernate-core-5.3.1.Final.jar`
2. **MySQL Connector**: `mysql-connector-j-8.2.0.jar`
3. **JAXB API**: `jaxb-api-2.3.1.jar`
4. **FontAwesomeFX**: `fontawesomefx-8.9.jar`
5. **Javassist**: `javassist-3.18.1-GA.jar`
6. **JFreeChart**: `jfreechart-1.5.3.jar`
7. **Hibernate Validator**: `hibernate-validator.jar`
8. **JBoss Logging**: `jboss-logging-3.3.0.Final.jar`
9. **JavaFX**: `javafx-graphics-22-ea+28-linux.jar`, `javafx-base-22-ea+28.jar`
10. **DOM4J**: `dom4j-1.6.1.jar`
11. **ANTLR**: `antlr-2.7.7.jar`
12. **Geronimo JTA**: `geronimo-jta-1.1_spec-1.1.1.jar`

You can download the required libraries from their official repositories or use a dependency manager like Maven or Gradle to manage them efficiently.

---
## 🚀 **Future Scope** <a name="future-scope"></a>

- **IoT Integration** for real-time milk production monitoring.
- **Machine Learning** for milk production trend predictions.
- **Blockchain** for secure financial records.
- **Mobile App** development for easy access.

---

## 🌟 **Conclusion** <a name="conclusion"></a>
The **Milk Cooperative Management System** digitizes dairy operations, ensuring transparency, efficiency, and growth. Future integration with IoT, blockchain, and machine learning will further enhance cooperative management.

<p align="center">
  <img src="images/conclusion.png" alt="Conclusion" width="400px">
</p>
