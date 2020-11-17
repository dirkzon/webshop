--addresses:
INSERT INTO addresses(address_id, country, postal_code, street, house_number, house_number_addition) VALUES('address:1', 'NL', '4321AB','Oosterweg', '23', 'A')

--images:
INSERT INTO images(image_id, url, height, width) VALUES('image:1', 'https://cdn.jpegmini.com/user/images/slider_puffin_jpegmini_mobile.jpg', 800, 800)
INSERT INTO images(image_id, url, height, width) VALUES('image:2', 'https://cdn.jpegmini.com/user/images/slider_puffin_jpegmini_mobile.jpg', 800, 800)

--customers:
INSERT INTO customers(customer_id, address_id, name, password, image_id, email, joined) VALUES('customer:1', 'address:1', 'peter', 'password', 'image:2', 'peter@outlook.com', '2019-7-05')
INSERT INTO customers(customer_id, address_id, name, password, image_id, email, joined) VALUES('customer:2', 'address:1', 'henk', '1234', 'image:2', 'henk@hotmail.com', '2020-4-9')

--retailers:
INSERT INTO retailers(retailer_id, rating, url, name, password, image_id, email, joined) VALUES('retailer:1', 4.5, 'testurl', 'john', 'abcd', 'image:2', 'john@gmail.com', '2020-3-11')

--catagories:
INSERT INTO categories(category_id, name, image_id, url) VALUES('category:1', 'tech', 'image:1', 'testcategoryurl')
INSERT INTO categories(category_id, name, image_id, url) VALUES('category:2', 'house', 'image:1', 'testcategoryurl')

--products:
INSERT INTO products(product_id, name, price, category_id, description, created, retailer_id, rating, url) VALUES('product:1', 'usb', 10.50, 'category:1', '8GB 2.0 usb stick', '2020-4-15', 'retailer:1', 4.0, 'testurlproduct')
INSERT INTO products(product_id, name, price, category_id, description, created, retailer_id, rating, url) VALUES('product:2', 'keyboard', 65.95, 'category:1', 'keyboard for typing', '2020-6-22', 'retailer:1', 2.5, 'testurlproduct')
INSERT INTO products(product_id, name, price, category_id, description, created, retailer_id, rating, url) VALUES('product:3', 'tv', 300.00, 'category:2', '72 inch oled lg flatscreen', '2019-11-08', 'retailer:1', 4.5, 'testurlproduct')

--product-images:
INSERT INTO product_images(product_id, image_id, url, height, width) VALUES('product:1', 'product_image:1', 'https://www.topsjop.nl/99-large_default/4gb-usb-stick-met-voice-recorder.jpg', 800, 800)
INSERT INTO product_images(product_id, image_id, url, height, width) VALUES('product:2', 'product_image:2', 'https://i.redd.it/53j4qmv9jtn31.jpg', 3115, 2082)
INSERT INTO product_images(product_id, image_id, url, height, width) VALUES('product:3', 'product_image:5', 'https://media.s-bol.com/lZnyXPOOXq5/550x495.jpg', 550, 495)
INSERT INTO product_images(product_id, image_id, url, height, width) VALUES('product:3', 'product_image:4', 'https://media.s-bol.com/RoJN2jRKoQ5w/550x358.jpg', 550, 358)

--reviews:
INSERT INTO reviews(review_id, rating, body, created, customer_id, product_id) VALUES('review:1', 4.5, 'Great product!', '2020-4-18', 'customer:1', 'product:1')
INSERT INTO reviews(review_id, rating, body, created, customer_id, product_id) VALUES('review:2', 2.0, 'would not recommend', '2020-5-10', 'customer:2', 'product:1')
INSERT INTO reviews(review_id, rating, body, created, customer_id, product_id) VALUES('review:3', 3.5, 'not bad', '2019-7-12', 'customer:2', 'product:2')
INSERT INTO reviews(review_id, rating, body, created, customer_id, product_id) VALUES('review:4', 5.0, 'awesome', '2020-1-30', 'customer:1', 'product:3')