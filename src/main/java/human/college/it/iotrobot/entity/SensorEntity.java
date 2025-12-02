package human.college.it.iotrobot.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * センサー情報エンティティ
 */
@Entity(name = "bme280_log") /* 引数は テーブル名を指定 */
@Data /* Data アノテーション (Lombok) 自動的にメンバー変数に応じたゲッター、セッター、toStringなどを生成 */
public class SensorEntity {
    /**
     * ID
     */
    @Id
    private int id;

    /**
     * 記録年月日
     */
    private LocalDateTime timestamp;

    /**
     * 温度(℃)
     */
    private double temperature;

    /**
     * 湿度(%)
     */
    private double humidity;

    /**
     * 気圧(Pa)
     */
    private double pressure;

}
