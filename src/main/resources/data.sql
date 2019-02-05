INSERT INTO product (grammage, order_amount,producer, description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type)
VALUE (5, 0, 'Cats best', 'Original',null, null, 0, 'drewniany', 'L','kittyLitter');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type)
VALUE (20, 0, 'Benek CornCat', 'Smart Pellets', null, null, 0, 'drewniany', 'L','kittyLitter');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type)
VALUE (20, 0, 'Benek CornCat','Lawenda', null, null, 0, 'silikonowy', 'L','kittyLitter');

INSERT INTO product (grammage, order_amount,producer, description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type,age)
VALUE (200, 0, 'Animonda Carny', 'Fleischkoktail',null, null, 0, 'mokra', 'g','feed', 'kitten');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type,age)
VALUE (400, 0, 'Macs', 'Rind', null, null, 0, 'mokra', 'g','feed','adult');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type)
VALUE (1, 0, 'Sanabelle','No grain - poultry', null, null, 0, 'sucha', 'kg','feed');

INSERT INTO barcode (code, product_id) VALUE ('11',1);
INSERT INTO barcode (code,product_id) VALUE ('12',2);
INSERT INTO barcode (code,product_id) VALUE ('13',3);
INSERT INTO barcode (code,product_id) VALUE ('14',1);


/*INSERT INTO volunteer (name) VALUE ('wolo1');
INSERT INTO volunteer (name) VALUE ('wolo2');
INSERT INTO volunteer (name) VALUE ('wolo3');
INSERT INTO volunteer (name) VALUE ('Adam');
INSERT INTO volunteer (name) VALUE ('Maciej');
INSERT INTO volunteer (name) VALUE ('Alan');*/

INSERT INTO parcel (name) VALUE ('zam1');
INSERT INTO parcel (name) VALUE ('zam2');
INSERT INTO parcel (name) VALUE ('zam3');

INSERT INTO role(name) VALUE('ROLE_REGISTERED');
INSERT INTO role(name) VALUE('ROLE_USER');
INSERT INTO role(name) VALUE('ROLE_ADMIN');

INSERT INTO volunteer (name,password,enabled) VALUE ('Melpomena','$2a$10$SSr.8lE4jm7BeniEsuJ3OOt9EMF2ZKfWFIVTVEhU3kXCUwTLqC9EK',1);
INSERT INTO volunteer (name,password,enabled) VALUE ('Wegorek','$2a$10$SSr.8lE4jm7BeniEsuJ3OOt9EMF2ZKfWFIVTVEhU3kXCUwTLqC9EK',1);
INSERT INTO volunteer (name,password,enabled) VALUE ('Wojtek','$2a$10$SSr.8lE4jm7BeniEsuJ3OOt9EMF2ZKfWFIVTVEhU3kXCUwTLqC9EK',1);

INSERT INTO volunteer_role (volunteer_id,role_id) VALUE (1,1);
INSERT INTO volunteer_role (volunteer_id,role_id) VALUE (2,2);
INSERT INTO volunteer_role (volunteer_id,role_id) VALUE (3,3);