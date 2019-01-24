INSERT INTO barcode (code) VALUE ('1231231');
INSERT INTO barcode (code) VALUE ('9231178');
INSERT INTO barcode (code) VALUE ('8686666');
INSERT INTO barcode (code) VALUE ('1208357');


INSERT INTO product (grammage, order_amount,producer, description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage)
VALUE (5, 0, 'Cats best', 'Original',null, null, 0, 'drewniany', 'L');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage)
VALUE (20, 0, 'Benek CornCat', 'Smart Pellets', null, null, 0, 'drewniany', 'L');
INSERT INTO product (grammage,order_amount,producer,description, remarks, reservation_by_volunteer, reserve_amount, sort,unit_of_grammage)
VALUE (20, 0, 'Benek CornCat','Lawenda', null, null, 0, 'silikonowy', 'L');


INSERT INTO volunteer (name) VALUE ('wolo1');
INSERT INTO volunteer (name) VALUE ('wolo2');
INSERT INTO volunteer (name) VALUE ('wolo3');

INSERT INTO parcel (name) VALUE ('zam1');
INSERT INTO parcel (name) VALUE ('zam2');
INSERT INTO parcel (name) VALUE ('zam3');