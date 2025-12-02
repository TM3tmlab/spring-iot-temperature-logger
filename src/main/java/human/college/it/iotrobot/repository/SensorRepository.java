package human.college.it.iotrobot.repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import human.college.it.iotrobot.entity.SensorEntity;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Integer> {

    /**
     * 何も記述していなくても JpaRepository を継承するだけで基本的なCRUD操作が利用可能 findAll(), findById(), save(), delete() など
     * さらに複雑なクエリを定義したい場合は、メソッド名に基づいて自動的にクエリを生成する機能も利用可能
     */

    @Query("SELECT DISTINCT DATE(t0.timestamp) FROM bme280_log t0 ")
    public List<Date> findAllDistinctByTimestampAsDate();

    /**
     * 指定された期間内のセンサーデータを取得するメソッド
     *
     * @param start 期間の開始日時
     * @param end 期間の終了日時
     * @return 指定期間内のセンサーデータのリスト
     */
    public List<SensorEntity> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
