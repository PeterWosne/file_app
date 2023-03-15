CREATE TABLE products (
     id                  bigserial primary key,
     title               varchar(255),
     price               numeric(8,2),
     production_year     int,
     cc                  int,
     mileage             int,
     created_at          timestamp default current_timestamp
);

INSERT INTO products(title, price,production_year, cc, mileage) VALUES
('Yamaha XJR1300', 540000.00, 2011, 1298, 24300),
('Honda STX1300', 630000.00, 2010, 1299, 53672);


CREATE TABLE fileinfos (
    id                  bigserial primary key,
    product_id          bigint references products(id),
    file_name           varchar(255),
    file_size           bigint,
    file_key            varchar(255),
    upload_date         timestamp default current_timestamp
);

INSERT INTO fileinfos(product_id, file_name, file_size, file_key) VALUES
(1, 'xjr1300.jpg', 94411, 'f518254e-00cb-420c-b15d-af2d73e71543'),
(2, 'bb464a46c4ff3f5e32ad20b24d75.jpg', 38247, 'faf3171f-a877-439e-bf43-3ff0bf7ad81b'),
(1, 'xjr1300-2.jpg', 96213, 'c1c91c4e-3862-4b20-88e9-ae738d5e2f6b');

