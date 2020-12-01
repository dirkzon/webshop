insert into accounts(id, joined, password, role, user_name) values('1', '2018-10-02', 'abcd', 'Retailer', 'john')
insert into accounts(id, joined, password, role, user_name) values('2', '2020-03-18', '1234', 'Customer', 'henk')

insert into addresses(id, country, house_number, street_name) values('1', 'NL', '12', 'street')

insert into images(id, url) values('1', 'url')
insert into images(id, url) values('2', 'url')
insert into images(id, url) values('3', 'url')
insert into images(id, url) values('4', 'url')

insert into retailers(id, account_id, avatar_id) values('1','1','1')

insert into customers(id, account_id, address_id, avatar_id) values('1', '2', '1', '2')

insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('1', '2020-11-30', 'description', 'name', '12.50', '4.5', '3', '1')
insert into products(product_id, created, description, name, price, rating, image_id, retailer_id) values('2', '2019-02-15', 'flatscreen', 'tv', '200.0', '3.5', '4', '1')

--insert into reviews(id, body, created, rating, customer_id, prod_id) values('1', 'body', '2020-06-14', '4.0', '1', '1')


