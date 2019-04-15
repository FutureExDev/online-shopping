CREATE TABLE category(

		id IDENTITY,
		name VARCHAR(50),
		description VARCHAR(255),
		image_url VARCHAR(50),
		is_active BOOLEAN,
		
		CONSTRAINT pk_category_id PRIMARY KEY (id)
);


insert into category(name,description,image_url,is_active) values('television','This is some description for television!','CAT_1.png','true')
insert into category(name,description,image_url,is_active) values('Mobile','This is some description for Moblie!','CAT_2.png','true')
insert into category(name,description,image_url,is_active) values('Laptop','This is some description for Laptop!','CAT_3.png','true')


CREATE TABLE user_detail(
	
	id IDENTITY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enable boolean,
	password VARCHAR(50),
	email VARCHAR(100),
	contact_number varchar(15),
	
	CONSTRAINT pk_user_id PRIMARY KEY(id),
);

INSERT INTO user_detail (first_name,last_name,role,enable,password,email,contact_number) 
values('Virat','Kohli','ADMIN',true,'admin','vk@gmail.com','8888888888');

INSERT INTO user_detail (first_name,last_name,role,enable,password,email,contact_number) 
values('Ravindra','Jadeja','SUPPLIER',true,'12345','rj@gmail.com','999999999');


INSERT INTO user_detail (first_name,last_name,role,enable,password,email,contact_number) 
values('Ravichandra','Ashwin','SUPPLIER',true,'12345','ra@gmail.com','7777777777');



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

insert into product(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id,purchases,views)
values('PRDABC123DEFX','iPhone 5s','apple','This is the one of the best phone available in the market',72000.00,12,'true',1,1,4,2);


insert into product(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id,purchases,views)
values('FEFABC123DEFX','Samsung 5s','Samsung','This is the one of the best phone available in the market',32000.00,14,true,2,2,7,22);

insert into product(code,name,brand,description,unit_price,quantity,is_active,category_id,supplier_id,purchases,views)
values('34343C123DEFX','ONE PLUS','ONE PLUS','This is the one of the best phone available in the market',7000.00,62,true,3,3,44,2);
















