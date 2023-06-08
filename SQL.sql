CREATE DATABASE car_rental;
GO
CREATE TABLE car_rental.dbo.borrowed_date (
  borrowed_date_id BIGINT IDENTITY
 ,end_date DATETIME2 NULL
 ,start_date DATETIME2 NULL
 ,car_id BIGINT NULL
 ,customer_id BIGINT NULL
 ,customer_name VARCHAR(255) NULL
 ,PRIMARY KEY CLUSTERED (borrowed_date_id)
) ON [PRIMARY]
GO
CREATE TABLE car_rental.dbo.car (
  id BIGINT IDENTITY
 ,description NVARCHAR(300) NULL
 ,name NVARCHAR(100) NOT NULL
 ,price NUMERIC(12) NULL
 ,img_name NVARCHAR(30) NULL
 ,PRIMARY KEY CLUSTERED (id)
) ON [PRIMARY]
GO
CREATE TABLE car_rental.dbo.customer (
  customer_id BIGINT IDENTITY
 ,borrowed_cars INT NULL
 ,full_name VARCHAR(50) NULL
 ,is_paid BIT NULL
 ,email VARCHAR(50) NULL
 ,phone VARCHAR(60) NULL
 ,role VARCHAR(50) NULL
 ,total_price NUMERIC(12, 2) NULL
 ,PRIMARY KEY CLUSTERED (customer_id)
) ON [PRIMARY]
GO
CREATE TABLE car_rental.dbo.customer_cars (
  customers_customer_id BIGINT NOT NULL
 ,cars_id BIGINT NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE car_rental.dbo.customer_cars
ADD CONSTRAINT FKi3keyvo599ujjbg89b5lkoxre FOREIGN KEY (customers_customer_id) REFERENCES dbo.customer (customer_id)
GO

ALTER TABLE car_rental.dbo.customer_cars
ADD CONSTRAINT FKkcl6ik1le1c9b1labgve117j2 FOREIGN KEY (cars_id) REFERENCES dbo.car (id)
GO
CREATE TABLE car_rental.dbo.persistent_logins (
  username VARCHAR(64) NOT NULL
 ,series VARCHAR(64) NOT NULL
 ,token VARCHAR(64) NOT NULL
 ,last_used TIMESTAMP
 ,PRIMARY KEY CLUSTERED (series)
) ON [PRIMARY]
GO
CREATE TABLE car_rental.dbo.users (
  id BIGINT IDENTITY
 ,email VARCHAR(45) NOT NULL
 ,first_name VARCHAR(20) NOT NULL
 ,last_name VARCHAR(20) NOT NULL
 ,password VARCHAR(64) NOT NULL
 ,PRIMARY KEY CLUSTERED (id)
 ,CONSTRAINT UK_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email)
) ON [PRIMARY]
GO

SET DATEFORMAT ymd
SET ARITHABORT, ANSI_PADDING, ANSI_WARNINGS, CONCAT_NULL_YIELDS_NULL, QUOTED_IDENTIFIER, ANSI_NULLS, NOCOUNT ON
SET NUMERIC_ROUNDABORT, IMPLICIT_TRANSACTIONS, XACT_ABORT OFF
GO

SET IDENTITY_INSERT car_rental.dbo.borrowed_date OFF
GO
SET IDENTITY_INSERT car_rental.dbo.borrowed_date OFF
GO

SET IDENTITY_INSERT car_rental.dbo.car ON
GO
INSERT car_rental.dbo.car(id, description, name, price, img_name) VALUES (1, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero new', N'Audi Q7', 1499000, N'Audi Q7.png')
INSERT car_rental.dbo.car(id, description, name, price, img_name) VALUES (2, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero ex.', N'Audi A4', 500000, N'Audi A4.png')
INSERT car_rental.dbo.car(id, description, name, price, img_name) VALUES (3, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero ex.', N'Ford Mustang', 2000000, N'Ford Mustang.png')
INSERT car_rental.dbo.car(id, description, name, price, img_name) VALUES (4, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero ex.', N'Chevrolet Camaro', 700000, N'Chevrolet Camaro.png')
INSERT car_rental.dbo.car(id, description, name, price, img_name) VALUES (5, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero ex.', N'Porsche Boxster', 2000000, N'Porsche Boxster.png')
INSERT car_rental.dbo.car(id, description, name, price, img_name) VALUES (6, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero ex.', N'BMW M3', 1000000, N'BMW M3.png')
INSERT car_rental.dbo.car(id, description, name, price, img_name) VALUES (7, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero ex.', N'Lamborghini Huracan', 5000000, N'Lamborghini Huracan.png')
INSERT car_rental.dbo.car(id, description, name, price, img_name) VALUES (8, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse libero ex.', N'Ferrari California', 2500000, N'Ferrari California.png')
GO
SET IDENTITY_INSERT car_rental.dbo.car OFF
GO

SET IDENTITY_INSERT car_rental.dbo.users ON
GO
INSERT car_rental.dbo.users(id, email, first_name, last_name, password) VALUES (1, 'admin@gmail.com', 'admin', 'admin', '$2a$10$ZzQ4VJELXlBsi7Um4GCcquOyb1MocDct.KkiqDHfu3XqiGLjZwFY.')
GO
SET IDENTITY_INSERT car_rental.dbo.users OFF
GO