CREATE TABLE category(

		id IDENTITY,
		name VARCHAR(50),
		description VARCHAR(255),
		image_url VARCHAR(50),
		is_active BOOLEAN,
		
		CONSTRAINT pk_category_id PRIMARY KEY (id)
);


insert into category(name,description,image_url,is_active) values('television','This is some description for television!','CAT_1.png',true)
	insert into category(name,description,image_url,is_active) values('Mobile','This is some description for Moblie!','CAT_2.png',true)
	insert into category(name,description,image_url,is_active) values('Laptop','This is some description for Laptop!','CAT_3.png',true)


CREATE TABLE user_detail(
	
	id IDENTITY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enable boolean,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number varchar(15),
	
	CONSTRAINT pk_user_id PRIMARY KEY(id),
);

INSERT INTO user_detail (first_name,last_name,role,enable,password,email,contact_number) 
values('Virat','Kohli','ADMIN',true,'$2b$10$4kP.b6vsq8Q7EAVYKLvq8uuoP1xYd28U/ZpOZlf4MvcnrtW28sCIm','vk@gmail.com','8888888888');
//admin


INSERT INTO user_detail (first_name,last_name,role,enable,password,email,contact_number) 
values('Ravindra','Jadeja','SUPPLIER',true,'$2b$10$U2V.cRAHjUUC5eG5pW2ciO.0d4rT3GBhLdyH.l284nUisGmYisWVa','rj@gmail.com','999999999');
//12345


INSERT INTO user_detail (first_name,last_name,role,enable,password,email,contact_number) 
values('Ravichandra','Ashwin','USER',true,'$2b$10$mG0EGYltSPwn8xqHT1gWnuNEjC33fG06nwyc98blm0LrckukzXGkO','ra@gmail.com','7777777777');
//12345


CREATE TABLE product(
	
	id IDENTITY,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active boolean,
	category_id int,
	supplier_id int,
	purchases int default 0,
	views int default 0,
	
	CONSTRAINT pk_product_id PRIMARY KEY(id),
	CONSTRAINT fk_product_category_id foreign key (category_id) references category(id),
	CONSTRAINT fk_supplier_category_id foreign key (supplier_id) references user_detail(id),
	
);


CREATE TABLE address (
	id IDENTITY,
	user_id int,
	address_line_one VARCHAR(100),
	address_line_two VARCHAR(100),
	city VARCHAR(20),
	state VARCHAR(20),
	country VARCHAR(20),
	postal_code VARCHAR(10),
	is_billing BOOLEAN,
	is_shipping BOOLEAN,
	CONSTRAINT fk_address_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_address_id PRIMARY KEY (id)
);


CREATE TABLE cart (
	id IDENTITY,
	user_id int,
	grand_total DECIMAL(10,2),
	cart_lines int,
	CONSTRAINT fk_cart_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_cart_id PRIMARY KEY (id)
);






insert into product(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id,purchases,views)
values('PRDABC123DEFX','iPhone 5s','apple','This is the one of the best phone available in the market',72000.00,12,'true',1,1,4,2);


insert into product(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id,purchases,views)
values('FEFABC123DEFX','Samsung 5s','Samsung','This is the one of the best phone available in the market',32000.00,14,true,2,2,7,22);

insert into product(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id,purchases,views)
values('34343C123DEFX','ONE PLUS','ONE PLUS','This is the one of the best phone available in the market',7000.00,62,true,3,3,44,2);





CREATE TABLE cart_line(

	id IDENTITY,
	cart_id int,
	total DECIMAL(10,2),
	product_id int,
	product_count int,
	buying_price DECIMAL(10,2),
	is_available boolean,
	
	CONSTRAINT fk_cartline_cart_id FOREIGN KEY (cart_id) REFERENCES cart(id),
	CONSTRAINT fk_cartline_product_id FOREIGN KEY (product_id) REFERENCES product(id),
	CONSTRAINT pk_cartline_id PRIMARY KEY(id)
);
















