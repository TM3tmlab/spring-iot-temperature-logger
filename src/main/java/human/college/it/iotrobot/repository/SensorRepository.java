package human.college.it.iotrobot.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import human.college.it.iotrobot.entity.Sensor;

/**
 * センサーデータリポジトリ
 *
 * Sensor エンティティのデータ操作を行うリポジトリインターフェース JpaRepository を継承することで、基本的な CRUD 操作が自動的に提供されます
 *
 * Long は Sensor エンティティの主キーの型を示します
 *
 * SQL クエリを直接書かかず、メソッド名に基づいてクエリを自動生成する機能を活用します
 */
@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    /**
     * センサーデータが記録された日付の一覧を取得する
     *
     * @return センサーデータが記録された日付の一覧
     */
    @Query("SELECT DISTINCT DATE(s.timestamp) FROM Sensor s ORDER BY DATE(s.timestamp) DESC")
    public List<LocalDate> findDistinctDateOrderByDateDesc();

    /**
     * 対象日のセンサーデータを取得する
     *
     * @param targetDateStart 対象日の開始日時
     * @param targetDateEnd 対象日の終了日時
     * @return 対象日のセンサーデータ一覧
     */
    public List<Sensor> findByTimestampBetween(LocalDateTime targetDateStart,
            LocalDateTime targetDateEnd);
}
