INSERT INTO product (grammage, order_amount,producer, description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage)
VALUE (5, 0, 'Cats best', 'Original',null, null, 0, 'drewniany', 'L');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage)
VALUE (20, 0, 'Benek CornCat', 'Smart Pellets', null, null, 0, 'drewniany', 'L');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage)
VALUE (20, 0, 'Benek CornCat','Lawenda', null, null, 0, 'silikonowy', 'L');

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