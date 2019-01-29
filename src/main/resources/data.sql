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

/*INSERT INTO barcode (code) VALUE ('11');
INSERT INTO barcode (code) VALUE ('12');
INSERT INTO barcode (code) VALUE ('13');
INSERT INTO barcode (code) VALUE ('14');*/

INSERT INTO volunteer (name) VALUE ('wolo1');
INSERT INTO volunteer (name) VALUE ('wolo2');
INSERT INTO volunteer (name) VALUE ('wolo3');
INSERT INTO volunteer (name) VALUE ('Adam');
INSERT INTO volunteer (name) VALUE ('Maciej');
INSERT INTO volunteer (name) VALUE ('Alan');

INSERT INTO parcel (name) VALUE ('zam1');
INSERT INTO parcel (name) VALUE ('zam2');
INSERT INTO parcel (name) VALUE ('zam3');