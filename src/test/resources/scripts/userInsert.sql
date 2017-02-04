
INSERT INTO USER_ROLE(user_role_id, description) VALUES('ROLE_ADMIN','Admin');
INSERT INTO USER_ROLE(user_role_id, description) VALUES('ROLE_USR','Registered User');


INSERT INTO USER(username, password, name, user_role_id, active) VALUES('admin','admin','Adminstrator','ROLE_ADMIN','S');
INSERT INTO USER(username, password, name, user_role_id, active) VALUES('eduardo','eduardo','eduardo','ROLE_USR','S');
INSERT INTO USER(username, password, name, user_role_id, active) VALUES('daniel','daniel','daniel','ROLE_USR','S');
INSERT INTO USER(username, password, name, user_role_id, active) VALUES('monica','monica','monica','ROLE_USR','S');


INSERT INTO CATEGORY(category_id,description) VALUES(1,'SHOES');
INSERT INTO CATEGORY(category_id,description) VALUES(2,'SWEATER');



INSERT INTO ITEM(item_id,description,features,unit_price,stock,active) VALUES(1,'NIKE','SIZE 10',17.0,4,'S');
INSERT INTO ITEM(item_id,description,features,unit_price,stock,active) VALUES(2,'ADIDAS','SIZE 8',1.0,4,'S');
INSERT INTO ITEM(item_id,description,features,unit_price,stock,active) VALUES(3,'REBOOK','SIZE 5',18.3,4,'S');
INSERT INTO ITEM(item_id,description,features,unit_price,stock,active) VALUES(4,'BLUE','LARGE',7.0,4,'S');
INSERT INTO ITEM(item_id,description,features,unit_price,stock,active) VALUES(5,'WHITE','SMALL',25.0,4,'S');
INSERT INTO ITEM(item_id,description,features,unit_price,stock,active) VALUES(6,'RED','SMALL',8.3,4,'S');

INSERT INTO CATEGORY_ITEM(category_id,item_id) VALUES(1,1);
INSERT INTO CATEGORY_ITEM(category_id,item_id) VALUES(1,2);
INSERT INTO CATEGORY_ITEM(category_id,item_id) VALUES(1,3);
INSERT INTO CATEGORY_ITEM(category_id,item_id) VALUES(2,4);
INSERT INTO CATEGORY_ITEM(category_id,item_id) VALUES(2,5);
INSERT INTO CATEGORY_ITEM(category_id,item_id) VALUES(2,6);


INSERT INTO CART(cart_id,shipping_amount,cart_amount,usernmae,active,create_date,update_date) VALUES(1,0.0,0.0,'eduardo','S',now(),now());
INSERT INTO CART(cart_id,shipping_amount,cart_amount,usernmae,active,create_date,update_date) VALUES(2,0.0,0.0,'daniel','S',now(),now());
INSERT INTO CART(cart_id,shipping_amount,cart_amount,usernmae,active,create_date,update_date) VALUES(3,0.0,0.0,'monica','S',now(),now());
