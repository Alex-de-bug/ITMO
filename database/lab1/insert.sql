iNSERT INTO Coloring (color) VALUES
('красный'),
('синий'),
('желтый'),
('зеленый'),
('черный'),
('белый'),
('серый'),
('оранжевый'),
('розовый'),
('фиолетовый'),
('коричневый'),
('бежевый'),
('голубой'),
('малиновый'),
('бирюзовый');

INSERT INTO Car (max_speed, color) VALUES
                                       (220, 1),
                                       (180, 2),
                                       (200, 3),
                                       (190, 4),
                                       (240, 5),
                                       (210, 6),
                                       (170, 7),
                                       (195, 8),
                                       (185, 9),
                                       (175, 10),
                                       (200, 1),
                                       (215, 2),
                                       (230, 3),
                                       (190, 4),
                                       (210, 5);

INSERT INTO Country (name_country) VALUES
                                       ('Россия'),
                                       ('Германия'),
                                       ('Франция'),
                                       ('Испания'),
                                       ('Италия'),
                                       ('Китай'),
                                       ('Япония'),
                                       ('США'),
                                       ('Канада'),
                                       ('Мексика'),
                                       ('Аргентина'),
                                       ('Бразилия'),
                                       ('Южная Африка'),
                                       ('Египет'),
                                       ('Австралия');
INSERT INTO City (name_city, capital, country_id) VALUES
                                                      ('Москва', true, 1),
                                                      ('Санкт-Петербург', false, 1),
                                                      ('Берлин', true, 2),
                                                      ('Мюнхен', false, 2),
                                                      ('Париж', true, 3),
                                                      ('Марсель', false, 3),
                                                      ('Мадрид', true, 4),
                                                      ('Барселона', false, 4),
                                                      ('Рим', true, 5),
                                                      ('Милан', false, 5),
                                                      ('Пекин', true, 6),
                                                      ('Шанхай', false, 6),
                                                      ('Токио', true, 7),
                                                      ('Киото', false, 7),
                                                      ('Нью-Йорк', true, 8),
                                                      ('Лос-Анджелес', false, 8),
                                                      ('Оттава', true, 9),
                                                      ('Торонто', false, 9),
                                                      ('Мехико', true, 10),
                                                      ('Канкун', false, 10),
                                                      ('Буэнос-Айрес', true, 11),
                                                      ('Кордова', false, 11),
                                                      ('Сан-Паулу', true, 12),
                                                      ('Рио-де-Жанейро', false, 12),
                                                      ('Кейптаун', true, 13),
                                                      ('Йоханнесбург', false, 13),
                                                      ('Каир', true, 14),
                                                      ('Гиза', false, 14),
                                                      ('Сидней', true, 15),
                                                      ('Мельбурн', false, 15);
INSERT INTO Gender (gender) VALUES
                                ('Мужской'),
                                ('Женский');
INSERT INTO Dinosaur (type_dino, age, sex, height) VALUES
                ('Тираннозавр', 25, 1, 7),
                ('Стегозавр', 30, 2, 6),
                ('Велоцираптор', 10, 1, 2),
                ('Трицератопс', 15, 2, 5),
                ('Анкилозавр', 20, 1, 4),
                ('Диплодок', 35, 1, 9),
                ('Птеродактиль', 5, 2, 1),
                ('Терродактиль', 7, 2, 2);
INSERT INTO Human (first_name, age, sex) VALUES
                                             ('Александр', 35, 1),
                                             ('Екатерина', 25, 2),
                                             ('Джон', 40, 1),
                                             ('Анна', 30, 2),
                                             ('Дмитрий', 28, 1),
                                             ('София', 18, 2),
                                             ('Дэвид', 55, 1),
                                             ('Элла', 42, 2);

INSERT INTO Acquaintance (id_human, id_dinosaur) VALUES
                                                     (1, 1),
                                                     (2, 2),
                                                     (3, 3),
                                                     (4, 4);
INSERT INTO Location_human (id_human, id_city, arrival_time) VALUES
                                                   (1, 1, '30.03.2022'),
                                                   (2, 2, '15.03.2022'),
                                                   (3, 3, '09.03.2022'),
                                                   (4, 4, '21.03.2022');
INSERT INTO Location_dinosaur (id_dinosaur, id_city, arrival_time) VALUES
                                                     (1, 1, '05.03.2022'),
                                                     (2, 2, '03.03.2022'),
                                                     (3, 3, '08.03.2022'),
                                                     (4, 4, '24.03.2022');
INSERT INTO Coloring_dinosaur (id_dinosaur, id_color) VALUES
                                                     (3, 1),
                                                     (2, 4),
                                                     (2, 8),
                                                     (3, 6);

INSERT INTO Car (max_speed, color) VALUES
                                       (220, 1),
                                       (180, 2),
                                       (200, 3),
                                       (190, 4),
                                       (240, 5),
                                       (210, 6),
                                       (170, 7),
                                       (195, 8),
                                       (185, 9),
                                       (175, 10),
                                       (200, 1),
                                       (215, 2),
                                       (230, 3),
                                       (190, 4),
                                       (210, 5);                                                     

INSERT INTO Car_human (id_human, id_car) VALUES
                                                   (3, 7),
                                                   (2, 4),
                                                   (3, 1),
                                                   (5, 4);

INSERT INTO Car_human (id_human, id_car) VALUES (5, 1);