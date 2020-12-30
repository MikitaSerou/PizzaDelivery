CREATE TABLE User
(
    id BIGINT NOT NULL PRIMARY KEY,
--     session_id  VARCHAR(128),
    user_name   VARCHAR(20) NOT NULL,
    user_password VARCHAR(20) NOT NULL,
    is_admin    BOOLEAN NOT NULL
);

CREATE TABLE Category
(
    id SMALLINT NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE Dough
(
    id SMALLINT NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    price NUMERIC(3,2) NOT NULL,
    size  VARCHAR(20) NOT NULL
);

-- CREATE TABLE Type
-- (
--     id SMALLINT NOT NULL PRIMARY KEY,
--     name VARCHAR(20) NOT NULL
-- );

CREATE TABLE Ingredient
(
    id SMALLINT NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    price NUMERIC(3,2) NOT NULL,
    type    VARCHAR (20) NOT NULL,
    is_vegan    BOOLEAN NOT NULL
);

CREATE TABLE Pizza
(
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    category_id SMALLINT NOT NULL REFERENCES Category (id),
    dough_id SMALLINT REFERENCES Dough (id),
    price NUMERIC(5,2),
    describe VARCHAR(128),
    is_vegan BOOLEAN
);

CREATE TABLE Pizza_ingredient
(
    pizza_id    BIGINT NOT NULL REFERENCES Pizza (id),
    ingredient_id   SMALLINT NOT NULL REFERENCES Ingredient (id)
);

CREATE TABLE User_pizza
(
    user_id BIGINT NOT NULL REFERENCES User (id),
    pizza_id BIGINT NOT NULL REFERENCES Pizza (id)
);

CREATE TABLE Orders
(
    id BIGINT NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES User (id),
    phone_number VARCHAR(18) NOT NULL,
    price NUMERIC(7,2),
type_of_payment VARCHAR(15) NOT NULL,
    time_of_order TIMESTAMP NOT NULL,
    comment VARCHAR(128),
    status VARCHAR(10) NOT NULL
);

CREATE TABLE Order_item
(
    order_id    BIGINT NOT NULL REFERENCES Orders (id),
    pizza_id    BIGINT NOT NULL REFERENCES Pizza (id),
    price BIGINT NOT NULL
);


