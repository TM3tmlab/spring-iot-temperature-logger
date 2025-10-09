package human.college.it.iotrobot.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * センサーデータエンティティ
 *
 * bme280_log テーブルに対応するエンティティクラスで、センサーデータの各フィールドを表します
 */
@Entity
@Table(name = "bme280_log")
@Data
public class Sensor {
    /**
     * ID
     */
    @Id
    private long id;

    /**
     * 記録タイムスタンプ
     */
    private LocalDateTime timestamp;

    /**
     * 温度
     */
    private double temperature;

    /**
     * 湿度
     */
    private double humidity;

    /**
     * 気圧(hPa)
     */
    private double pressure;
}
