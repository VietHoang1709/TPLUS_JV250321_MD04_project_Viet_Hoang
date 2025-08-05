use MD05Session04;
drop table category;
create table category(
    category_id int primary key auto_increment,
    category_name varchar(100) not null,
    category_description varchar(255) not null,
    status enum('true','false') default 'true'
);
drop table product;
create table product(
    product_id int primary key auto_increment,
    product_name varchar(100) not null,
    price double not null,
    category_id int,
    status enum('active','inactive') default 'active',
    foreign key (category_id) references category(category_id)
);
# hien thi
drop procedure display_all_category;
DELIMITER $$
create procedure display_all_category(
)
begin
    select * from category where status = true;
end $$
DELIMITER $$
# them danh muc
DELIMITER $$
create procedure create_category(
    in_category_name varchar(100),
    in_description varchar(255)
)
begin
    insert into category(category_name, category_description)
        values (in_category_name,in_description);
end $$
DELIMITER $$
# tim id
DELIMITER $$
create procedure find_category_by_id(
    in_category_id int
)
begin
    select * from category
        where category_id = in_category_id;
end $$
DELIMITER $$
# cap nhap
drop procedure update_category;
DELIMITER $$
create procedure update_category(
    in_category_id int,
    in_category_name varchar(100),
    in_category_description varchar(255),
    in_status enum('true','false')
)
begin
    update category
        set category_name = in_category_name,
            category_description = in_category_description,
            status = in_status
    where category_id = in_category_id;
end $$
DELIMITER $$
# Xoa danh muc
drop procedure delete_category;
DELIMITER $$
CREATE PROCEDURE delete_category(
    IN in_category_id INT
)
BEGIN
    DECLARE product_count INT;
    SELECT COUNT(*) INTO product_count
    FROM product
    WHERE category_id = in_category_id;
    IF product_count = 0 THEN
        DELETE FROM category WHERE category_id = in_category_id;
    ELSE
        UPDATE category SET status = 0 WHERE category_id = in_category_id;
    END IF;
END $$
DELIMITER ;

INSERT INTO category (category_name, category_description)
VALUES
    ('Cà phê', 'Các loại cà phê đặc sản và truyền thống'),
    ('Trà', 'Các loại trà xanh, trà đen và trà sữa'),
    ('Nước ép', 'Nước ép trái cây tươi nguyên chất'),
    ('Bánh ngọt', 'Bánh ngọt dùng kèm đồ uống'),
    ('Đồ ăn nhẹ', 'Snack, bánh quy, và đồ ăn nhẹ khác');
INSERT INTO product (product_name, price, category_id, status)
VALUES
    ('Cà phê sữa đá', 25000, 1, 'active'),
    ('Cà phê đen nóng', 20000, 1, 'active'),
    ('Trà sữa trân châu', 35000, 2, 'active'),
    ('Trà đào cam sả', 32000, 2, 'active'),
    ('Nước ép cam', 30000, 3, 'active'),
    ('Nước ép dứa', 28000, 3, 'inactive'),
    ('Bánh tiramisu', 45000, 4, 'active'),
    ('Bánh flan', 20000, 4, 'active'),
    ('Khoai tây chiên', 30000, 5, 'active'),
    ('Snack rong biển', 15000, 5, 'inactive');