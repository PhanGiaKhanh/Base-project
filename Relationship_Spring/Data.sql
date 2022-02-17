CREATE DATABASE IF NOT EXISTS
relationship_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE relationship_db;
INSERT INTO `relationship_db`.`addresses` (`location`) VALUES ('Đà Nẵng');
INSERT INTO `relationship_db`.`addresses` (`location`) VALUES ('Hà Nội');
INSERT INTO `relationship_db`.`addresses` (`location`) VALUES ('HCM');
INSERT INTO `relationship_db`.`addresses` (`location`) VALUES ('Huế');
INSERT INTO `relationship_db`.`addresses` (`location`) VALUES ('Đà Lạt');
INSERT INTO `relationship_db`.`addresses` (`location`) VALUES ('Quảng Bình');

INSERT INTO `relationship_db`.`libraries` (`name`, `address_id`) VALUES ('Hồng Hà', '1');
INSERT INTO `relationship_db`.`libraries` (`name`, `address_id`) VALUES ('Minh Châu', '2');
INSERT INTO `relationship_db`.`libraries` (`name`, `address_id`) VALUES ('Quảng Kim', '3');
INSERT INTO `relationship_db`.`libraries` (`name`, `address_id`) VALUES ('Đà Nẵng', '4');

INSERT INTO `relationship_db`.`books` (`title`, `library_id`) VALUES ('Truyện cười', '1');
INSERT INTO `relationship_db`.`books` (`title`, `library_id`) VALUES ('Táo quân', '1');
INSERT INTO `relationship_db`.`books` (`title`, `library_id`) VALUES ('Truyện cổ tích 1', '2');
INSERT INTO `relationship_db`.`books` (`title`, `library_id`) VALUES ('Truyện cổ tích 2', '2');
INSERT INTO `relationship_db`.`books` (`title`, `library_id`) VALUES ('One piece tập 1', '2');
INSERT INTO `relationship_db`.`books` (`title`, `library_id`) VALUES ('One piece tập 2', '2');
INSERT INTO `relationship_db`.`books` (`title`, `library_id`) VALUES ('Naruto', '3');

