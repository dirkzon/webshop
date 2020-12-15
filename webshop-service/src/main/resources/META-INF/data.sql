insert into accounts(id, joined, email, password, role, user_name) values('1', '2018-10-02','john@outlook.com' ,'abcd', 'Retailer', 'john')
insert into accounts(id, joined, email,  password, role, user_name) values('2', '2020-03-18','henk@gmail.com', '1234', 'Customer', 'henk')
insert into accounts(id, joined, email,  password, role, user_name) values('3', '2020-03-18','peter@hotmail.com', 'pass', 'Customer', 'peter')

insert into addresses(id, country, house_number, street_name) values('1', 'netherlands', '12', 'street')
insert into addresses(id, country, house_number, street_name) values('2', 'germany', '72', 'bakkerstraat')

insert into images(id, url) values('1', 'https://cdn.jpegmini.com/user/images/slider_puffin_jpegmini_mobile.jpg')
insert into images(id, url) values('2', 'https://3.bp.blogspot.com/-xne-2R0z87A/UVKbpL9j_UI/AAAAAAAAIFo/JaLg41ePh-8/s320/red_panda-2013-02.jpg')
insert into images(id, url) values('3', 'https://pbs.twimg.com/profile_images/3528062742/367430a3bbafcccd59c595bf3349590e.jpeg')
insert into images(id, url) values('4', 'https://ekameco.com/wp-content/uploads/2019/03/product-placeholder.jpg')

insert into retailers(id, account_id, avatar_id) values('1','1','1')

insert into customers(id, account_id, address_id, avatar_id) values('1', '2', '1', '2')
insert into customers(id, account_id, address_id, avatar_id) values('2', '3', '2', '2')

insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('1', '2020-11-30', 'computer mouse', 'mouse', '12.50', '4.5', '4', '1')
insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('2', '2019-02-15', 'keyboard description', 'keyboard', '200.0', '3.5', '4', '1')
insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('3', '2018-06-12', '300 watt speakers', 'speakers', '150.50', '4.5', '4', '1')
insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('4', '2015-08-10', '500 megapixel camera ', 'camera', '240.95', '2.0', '4', '1')
insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('5', '2013-11-11', 'phone ', 'motorola power', '170.89', '5.0', '4', '1')
insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('6', '2019-05-21', 'the newest playstation', 'playstation 6', '623.60', '3.5', '4', '1')
insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('7', '2020-09-23', '15 inch laptop with intell i10', 'laptop', '1020.70', '4.5', '4', '1')
insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('8', '2018-06-09', 'headphones for on your head ', 'jbl headphones', '200.0', '2.5', '4', '1')
insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('9', '2017-01-15', '6g router ', 'router', '200.0', '1.0', '4', '1')
insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('10', '2017-12-25', 'gtx 4090 videocard with 200gb of vram', 'gtx 4090 videocard', '860.80', '4.5', '4', '1')


insert into reviews(id, body, created, rating, customer_id, prod_id) values('1', 'body', '2020-06-14', '4.0', '1', '1')


