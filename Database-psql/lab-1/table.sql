
-- scp -P 2222 hello.txt s367193@helios.cs.ifmo.ru:~/
-- ssh -p 2222 s367193@se.ifmo.ru -L 5432:pg:5432
-- psql -h pg -d studs

--Table  with color
CREATE TABLE Coloring
(
    id SERIAL primary key,
    color text
);

--Table  with car
CREATE TABLE Car
(
    car_id SERIAL primary key,
    max_speed integer,
    color int not null,
    FOREIGN KEY (color) REFERENCES Coloring (id)
);

-- Table with countries
CREATE TABLE Country
(
    id   SERIAL primary key,
    name_country text
);

-- Table with Cities
CREATE TABLE City
(
    id   SERIAL primary key,
    name_city text,
    capital boolean not null,
    country_id integer not null,
    FOREIGN KEY (country_id) REFERENCES Country (id)
);

-- Gender table
CREATE TABLE Gender
(
    id   SERIAl primary key,
    gender text NOT NULL
);

--Table with dinosaurs
CREATE TABLE Dinosaur
(
    dinosaur_id SERIAl primary key,
    type_dino text not null,
    age integer,
    sex integer not null,
    height integer,
    FOREIGN KEY (sex) REFERENCES Gender (id)
);

-- Table with humans
CREATE TABLE Human
(
    human_id SERIAL primary key,
    first_name text,
    age integer,
    sex integer not null,
    FOREIGN KEY (sex) REFERENCES Gender (id)
);

-- Table with Acquaintances
CREATE TABLE Acquaintance
(
    id_human INTEGER REFERENCES Human (human_id),
    id_dinosaur INTEGER REFERENCES Dinosaur (dinosaur_id),
    PRIMARY KEY (id_human, id_dinosaur)
);

-- Table with Location human
CREATE TABLE Location_human
(
    id_human INTEGER REFERENCES Human (human_id),
    id_city INTEGER REFERENCES City (id),
    arrival_time text not null,
    PRIMARY KEY (id_human, id_city)
);

-- Table with Car human
CREATE TABLE Car_human
(
    id_human INTEGER REFERENCES Human (human_id),
    id_car INTEGER REFERENCES Car (car_id),
    PRIMARY KEY (id_human, id_car)
);

-- Table with Location human
CREATE TABLE Location_dinosaur
(
    id_dinosaur INTEGER REFERENCES Dinosaur (dinosaur_id),
    id_city INTEGER REFERENCES City (id),
    arrival_time text not null,
    PRIMARY KEY (id_dinosaur, id_city)
);

-- Table with Coloring dinosaur
CREATE TABLE Coloring_dinosaur
(
    id_dinosaur INTEGER REFERENCES Dinosaur (dinosaur_id),
    id_color INTEGER REFERENCES Coloring (id),
    PRIMARY KEY (id_dinosaur, id_color)
);

-- Table with Car human dn
CREATE TABLE Car_human_dn
(
    id_car SERIAL primary key,
    id_human INTEGER REFERENCES Human (human_id),
    max_speed integer,
    color text
);