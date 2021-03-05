create table ROLES
(
    ID   BIGINT auto_increment
        primary key,
    NAME VARCHAR(255) not null
);

create table USER
(
    ID            BIGINT auto_increment
        primary key,
    E_MAIL        VARCHAR(255) not null,
    USER_PASSWORD VARCHAR(255) not null,
    PHONE_NUMBER  VARCHAR(255),
    USER_NAME     VARCHAR(255) not null
);

create table USER_ROLES
(
    USER_ID  BIGINT not null,
    ROLES_ID BIGINT not null,
    primary key (USER_ID, ROLES_ID),
        foreign key (ROLES_ID) references ROLES (ID),
        foreign key (USER_ID) references USER (ID)
);

create table BASE
(
    ID               SMALLINT auto_increment
        primary key,
    NAME             VARCHAR(255) not null,
    PRICE_MULTIPLIER DOUBLE       not null
);

create table BASKET
(
    ID        BIGINT auto_increment
        primary key,
    IS_ACTIVE BOOLEAN   not null,
    TIME      TIMESTAMP not null,
    USER_ID   BIGINT    not null,
        foreign key (USER_ID) references USER (ID)
);

create table CATEGORY
(
    ID             SMALLINT auto_increment
        primary key,
    NAME           VARCHAR(255) not null,
    CATEGORY_PRICE DOUBLE       not null
);

create table INGREDIENT
(
    ID              SMALLINT auto_increment
        primary key,
    INGREDIENT_TYPE VARCHAR(255) not null,
    NAME            VARCHAR(255) not null,
    PRICE           DOUBLE       not null
);

create table PRODUCT
(
    ID          BIGINT auto_increment
        primary key,
    DESCRIPTION VARCHAR(255),
    NAME        VARCHAR(255) not null,
    PRICE       DOUBLE,
    BASE_ID     SMALLINT     not null,
    CATEGORY_ID SMALLINT,
    foreign key (CATEGORY_ID) references CATEGORY (ID),
    foreign key (BASE_ID) references BASE (ID)
);

create table PRODUCT_INGREDIENT
(
    PRODUCT_ID    BIGINT   not null,
    INGREDIENT_ID SMALLINT not null,
    foreign key (PRODUCT_ID) references PRODUCT (ID),
    foreign key (INGREDIENT_ID) references INGREDIENT (ID)
);

create table BASKET_ITEM
(
    ID          BIGINT auto_increment
        primary key,
    DESCRIPTION VARCHAR(255),
    PRICE       DOUBLE not null,
    BASKET_ID   BIGINT not null,
    PRODUCT_ID  BIGINT not null,
        foreign key (BASKET_ID) references BASKET (ID),
        foreign key (PRODUCT_ID) references PRODUCT (ID)
);

create table ORDERS
(
    ID              BIGINT auto_increment
        primary key,
    COMMENT         VARCHAR(255),
    PHONE_NUMBER    VARCHAR(255) not null,
    PRICE           DOUBLE,
    STATUS          VARCHAR(255) not null,
    TIME            TIMESTAMP    not null,
    TYPE_OF_PAYMENT VARCHAR(255),
    USER_ID         BIGINT       not null,
        foreign key (USER_ID) references USER (ID)
);

create table ORDER_ITEM
(
    ID          BIGINT auto_increment
        primary key,
    DESCRIPTION VARCHAR(255),
    PRICE       DOUBLE,
    ORDER_ID    BIGINT not null,
    PRODUCT_ID  BIGINT not null,
        foreign key (PRODUCT_ID) references PRODUCT (ID),
        foreign key (ORDER_ID) references ORDERS (ID)
);
//#########################################################################################
//                                ROLES
//#########################################################################################

INSERT INTO PUBLIC.ROLES (NAME) VALUES ('ROLE_USER');
INSERT INTO PUBLIC.ROLES (NAME) VALUES ('ROLE_ADMIN');

//#########################################################################################
//                       USER FOR ORDERS WITH DELETED USER
//#########################################################################################

INSERT INTO USER (USER_NAME, E_MAIL, USER_PASSWORD) VALUES ('Удаленный', '-', '-');

//#########################################################################################
//                                BASES FOR PRODUCTS
//#########################################################################################

INSERT INTO PUBLIC.BASE (NAME, PRICE_MULTIPLIER)
VALUES ('Большая', 2);
INSERT INTO PUBLIC.BASE (NAME, PRICE_MULTIPLIER)
VALUES ('Средняя', 1.5);
INSERT INTO PUBLIC.BASE (NAME, PRICE_MULTIPLIER)
VALUES ('Маленькая', 1);
INSERT INTO PUBLIC.BASE (NAME, PRICE_MULTIPLIER)
VALUES ('Тонкая средняя', 1.2);
INSERT INTO PUBLIC.BASE (NAME, PRICE_MULTIPLIER)
VALUES ('Тонкая большая', 1.8);
INSERT INTO PUBLIC.BASE (NAME, PRICE_MULTIPLIER)
VALUES ('Кальцоне', 1.5);

//#########################################################################################
//                                PRODUCT CATEGORIES
//#########################################################################################

INSERT INTO PUBLIC.CATEGORY (NAME, CATEGORY_PRICE)
VALUES ('Архив', 0);
INSERT INTO PUBLIC.CATEGORY (NAME, CATEGORY_PRICE)
VALUES ('Своя', 14);
INSERT INTO PUBLIC.CATEGORY (NAME, CATEGORY_PRICE)
VALUES ('Классика', 15);
INSERT INTO PUBLIC.CATEGORY (NAME, CATEGORY_PRICE)
VALUES ('Премиум', 19);
INSERT INTO PUBLIC.CATEGORY (NAME, CATEGORY_PRICE)
VALUES ('Любимые', 17);

//#########################################################################################
//                                INGREDIENTS
//#########################################################################################

INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SAUCE', 'Томатный соус', 2);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SAUCE', 'BBQ соус', 2.1);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SAUCE', 'Карри соус', 2.2);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SAUCE', 'Чесночный соус', 2.1);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('CHEESE', 'Моцарелла', 1);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('CHEESE', 'Чеддер', 1.5);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('CHEESE', 'Пармезан', 2);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('CHEESE', 'Дорблю', 2.2);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('CHEESE', 'Фета', 1.8);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('MEAT', 'Бекон', 3.5);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('MEAT', 'Телятина', 4);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('MEAT', 'Ветчина', 2.8);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('MEAT', 'Колбаски', 3);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('MEAT', 'Пепперони', 2.8);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('MEAT', 'Курица', 2.5);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('VEGETABLE', 'Томат', 1.5);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('VEGETABLE', 'Лук', 1);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('VEGETABLE', 'Оливки', 2);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('VEGETABLE', 'Шампиньоны', 1.8);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('VEGETABLE', 'Соленые огурцы', 1.6);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('VEGETABLE', 'Ананас', 2);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('VEGETABLE', 'Сладкий перец', 1.7);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('VEGETABLE', 'Халапеньо', 2);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SEAFOOD', 'Креветки', 2.1);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SEAFOOD', 'Кальмар', 2);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SEAFOOD', 'Мидии', 2);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SEAFOOD', 'Тунец', 2.5);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SEAFOOD', 'Анчоусы', 1.8);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SAUCE', 'Бургер соус', 2.4);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('CHEESE', 'Тофу', 2.5);
INSERT INTO PUBLIC.INGREDIENT (INGREDIENT_TYPE, NAME, PRICE) VALUES ('SAUCE', 'Сливочный соус', 2);

//#########################################################################################
//                                PRODUCTS
//#########################################################################################
INSERT INTO PUBLIC.PRODUCT (NAME, BASE_ID, DESCRIPTION, CATEGORY_ID) VALUES
('База', 1, 'Ваша неповторимая пицца', 2),
('База', 2,'Ваша неповторимая пицца', 2),
('База', 3,'Ваша неповторимая пицца', 2),
('База', 4,'Ваша неповторимая пицца', 2),
('База', 5,'Ваша неповторимая пицца', 2),
('База', 6,'Ваша неповторимая пицца', 2);
