INSERT INTO product (grammage, order_amount,producer, description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type)
VALUES (5, 0, 'Cats best', 'Original',null, null, 0, 'drewniany', 'L','kittyLitter');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type)
VALUES (20, 0, 'Benek CornCat', 'Smart Pellets', null, null, 0, 'drewniany', 'L','kittyLitter');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type)
VALUES (20, 0, 'Benek CornCat','Lawenda', null, null, 0, 'silikonowy', 'L','kittyLitter');

INSERT INTO product (grammage, order_amount,producer, description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type,age)
VALUES (200, 0, 'Animonda Carny', 'Fleischkoktail',null, null, 0, 'mokra', 'g','feed', 'kitten');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type,age)
VALUES (400, 0, 'Macs', 'Rind', null, null, 0, 'mokra', 'g','feed','adult');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage,type)
VALUES (1, 0, 'Sanabelle','No grain - poultry', null, null, 0, 'sucha', 'kg','feed');

INSERT INTO barcode (code, product_id) VALUES ('11',1);
INSERT INTO barcode (code,product_id) VALUES ('12',2);
INSERT INTO barcode (code,product_id) VALUES ('13',3);
INSERT INTO barcode (code,product_id) VALUES ('14',1);

INSERT INTO parcel (name) VALUES ('zam1');
INSERT INTO parcel (name) VALUES ('zam2');
INSERT INTO parcel (name) VALUES ('zam3');

INSERT INTO role(name) VALUES('ROLE_REGISTERED');
INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

INSERT INTO volunteer (name,password,enabled) VALUES ('Registered','$2a$10$SSr.8lE4jm7BeniEsuJ3OOt9EMF2ZKfWFIVTVEhU3kXCUwTLqC9EK',1);
INSERT INTO volunteer (name,password,enabled) VALUES ('User','$2a$10$SSr.8lE4jm7BeniEsuJ3OOt9EMF2ZKfWFIVTVEhU3kXCUwTLqC9EK',1);
INSERT INTO volunteer (name,password,enabled) VALUES ('Admin','$2a$10$SSr.8lE4jm7BeniEsuJ3OOt9EMF2ZKfWFIVTVEhU3kXCUwTLqC9EK',1);

INSERT INTO volunteer_role (volunteer_id,role_id) VALUES (1,1);
INSERT INTO volunteer_role (volunteer_id,role_id) VALUES (2,2);
INSERT INTO volunteer_role (volunteer_id,role_id) VALUES (3,3);