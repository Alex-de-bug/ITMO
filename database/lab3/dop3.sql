CREATE OR REPLACE FUNCTION insert_Car_human_dn()
 RETURNS TRIGGER AS $$
    BEGIN
        IF EXISTS(SELECT 1 FROM Car_human_dn WHERE (Car_human_dn.id_human is null) AND Car_human_dn.id_car = NEW.id_car) THEN
            UPDATE Car_human_dn SET id_human = NEW.id_human
            WHERE (id_human is null) AND id_car = NEW.id_car;
        ELSE
        INSERT INTO Car_human_dn (id_human, max_speed, color)
            SELECT NEW.id_human, Car.max_speed, Coloring.color
            FROM Car_human
            INNER JOIN Car ON Car_human.id_car = Car.car_id
            INNER JOIN Coloring ON Car.color = Coloring.id
            WHERE (Car_human.id_human = NEW.id_human) AND (Car_human.id_car = NEW.id_car);
        END IF;
        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Car_human_insert_trigger AFTER INSERT ON Car_human
FOR EACH ROW EXECUTE FUNCTION insert_Car_human_dn();


CREATE OR REPLACE FUNCTION insert_Car()
 RETURNS TRIGGER AS $$
    BEGIN
        INSERT INTO Car_human_dn (max_speed, color)
            SELECT NEW.max_speed, Coloring.color
            FROM Car
            INNER JOIN Coloring ON Car.color = Coloring.id
            WHERE(NEW.car_id=Car.car_id);
    RETURN NEW;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Car_insert_trigger AFTER INSERT ON Car
FOR EACH ROW EXECUTE FUNCTION insert_Car();

DROP TRIGGER IF EXISTS Car_human_insert_trigger ON Car_human;
DROP FUNCTION IF EXISTS insert_Car_human_dn();
DROP TRIGGER IF EXISTS Car_insert_trigger ON Car;
DROP FUNCTION IF EXISTS insert_Car();