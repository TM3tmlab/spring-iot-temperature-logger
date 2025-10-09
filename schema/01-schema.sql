CREATE DATABASE IF NOT EXISTS sensor_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_bin
;

CREATE USER IF NOT EXISTS 'logger'@'localhost' IDENTIFIED BY 'secretpass';
GRANT ALL PRIVILEGES ON sensor_db.* TO 'logger'@'localhost';

CREATE USER IF NOT EXISTS 'reader'@'%' IDENTIFIED BY 'secretpass';
GRANT SELECT ON sensor_db.* TO 'reader'@'%';

CREATE TABLE IF NOT EXISTS bme280_log (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'PK',
    timestamp DATETIME NOT NULL COMMENT '記録年月日', -- 2038年問題対策でDATETIMEを使用
    temperature DOUBLE NOT NULL COMMENT '温度(℃)',
    humidity DOUBLE NOT NULL COMMENT '湿度',
    pressure DOUBLE NOT NULL COMMENT '気圧(Pa)',

    INDEX (timestamp)
) ENGINE 'InnoDB' CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin' COMMENT 'BME280データログテーブル';
