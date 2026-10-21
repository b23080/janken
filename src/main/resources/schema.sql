/* userやgroupといった名前はSQLでは予約語で使えないため，userNameとしていることに注意 */
CREATE TABLE users (
    id IDENTITY NOT NULL PRIMARY KEY,
    userName VARCHAR NOT NULL
);
CREATE TABLE matches (
    id IDENTITY NOT NULL PRIMARY KEY,
    user1 INT NOT NULL,
    user2 INT NOT NULL,
    user1Hand VARCHAR,
    user2Hand VARCHAR
);
