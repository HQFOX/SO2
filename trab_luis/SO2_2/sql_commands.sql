CREATE TABLE facility (field text PRIMARY KEY, price numeric);
CREATE TABLE reserves (code SERIAL, field text, name text, phone integer, cost integer, users integer, checkin TIMESTAMP WITHOUT TIME ZONE, checkout TIMESTAMP WITHOUT TIME ZONE, PRIMARY KEY(code));

INSERT INTO facility VALUES ('tenis', 5);
INSERT INTO facility VALUES ('futsal', 10);
INSERT INTO facility VALUES ('padel', 7);

INSERT INTO reserves (field,name,phone,cost,users,checkin,checkout)VALUES ('tenis', 'luis', 123, 5,2,'2017-04-11 10:00:00','2017-04-11 11:00:00');

INSERT INTO reserves (field,name,phone,cost,users,checkin,checkout)VALUES ('tenis', 'luis', 123, 5,2,'2017-04-11 15:00:00','2017-04-11 16:00:00');
