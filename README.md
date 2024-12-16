# GadgetHub Web Application

## Overview

The GadgetHub project is a web-based retailer application developed using Java Servlets. The project includes a variety of features and functionalities, incorporating both MySQL and MongoDB databases to create a robust and dynamic online shopping experience.

This document provides instructions on how to set up, compile, and run the Smart Gadgets application, as well as a summary of its features.

---

## Features

### Core Features

#### User Roles:
- **StoreManager:** Add, delete, and update products.
- **Salesman:** Create customer accounts and manage customer orders.
- **Customers:** Create accounts, shop, and manage orders.

#### Product Categories:
- TVs, Sound Systems, Phones, Laptops, Voice Assistants, Wearable Technology (e.g., Fitness Watches, Smart Watches, Headphones, Virtual Reality, Pet Trackers).

#### Shopping Features:
- Customers can shop online, add/remove items to/from their cart.
- Choose between store pickup or home delivery.
- Payment processing via credit card.

#### Order Management:
- Generate confirmation numbers and provide estimated delivery/pickup dates.
- Allow customers to cancel orders (at least 5 business days before delivery).

#### Product Details:
- Display associated accessories below selected products.
- Include warranty purchase options, retailer discounts, and manufacturer rebates.

### Extended Features

#### Database Integration:
- **MySQL:**
  - Store product information, store locations, customer accounts, and order transactions.
- **MongoDB:**
  - Store customer product reviews.

#### Enhanced Features:
- Customers can write and submit product reviews.

#### Trending Data Analytics:
- Top 5 most liked products.
- Top 5 zip codes with the highest product sales.
- Top 5 most sold products regardless of ratings.

#### Form Enhancements:
- Product Review Form includes details like product model, price, store information, manufacturer information, user demographics, and review text.

---

## Setup Instructions

### Prerequisites

- Java Development Kit (JDK)
- Apache Tomcat Server
- MySQL Database Server
- MongoDB Database Server
- Text Editor or Command Line Tools (No IDE is allowed as per requirements)

### Steps to Set Up

#### Download Dependencies:
1. Ensure all required JAR files for MySQL and MongoDB connectivity are available in the project library.

#### Database Setup:
1. Set up MySQL tables for:
   - Stores
   - Products
   - Customers
   - Transactions
2. Populate tables with the required data.
3. Set up MongoDB collections for product reviews and populate with sample data.

#### Configure Database Utilities:
1. Update connection details in `MySQLDataStoreUtilities.java` and `MongoDBDataStoreUtilities.java`.

---

## Compilation and Deployment

### Compilation:
- Use `javac` to compile all `.java` files.
- Example: `javac -d . *.java`

### Deployment:
1. Package compiled files into a WAR file.
2. Deploy the WAR file to the `webapps` directory of the Apache Tomcat server.

### Run Application:
1. Start the Tomcat server.
2. Access the application in a web browser at `http://localhost:8080`.

---

## How to Use

### Customers
1. Create an account.
2. Browse products by categories.
3. Add items to the shopping cart.
4. Place an order and choose delivery or store pickup.
5. Submit product reviews and view trending data analytics.

### StoreManager
1. Log in with StoreManager credentials.
2. Manage product inventory by adding, deleting, or updating products.

### Salesman
1. Log in with Salesman credentials.
2. Manage customer accounts and orders.
