package human.college.it.iotrobot.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.stereotype.Service;
import human.college.it.iotrobot.entity.SensorEntity;
import human.college.it.iotrobot.repository.SensorRepository;

@Service
public class SensorGraphService {

    // private static final Logger log = LoggerFactory.getLogger(SensorGraphService.class);
    private final SensorRepository sensorRepository;

    public SensorGraphService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    /**
     * ダミー画像を生成して返す
     *
     * @return PNG形式のダミー画像のバイト配列
     */
    public byte[] createDummyImage() {
        // 空画像を返す例
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final int width = 800;
        final int height = 600;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        try {
            // 緑で塗りつぶし背景色を描画
            g2d.setColor(Color.GREEN);
            g2d.fillRect(0, 0, width, height);

            ImageIO.write(image, "png", outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("ダミー画像の生成に失敗しました", e);
        } finally {
            g2d.dispose();
        }
    }

    /**
     * 対象日のセンサーデータを取得する
     *
     * @return 対象日のセンサーデータ
     */
    public List<SensorEntity> getTargetDateSensors(LocalDate targetDate) {
        LocalDateTime targetDateStart = targetDate.atStartOfDay();
        LocalDateTime targetDateEnd = targetDate.plusDays(1).atStartOfDay().minusSeconds(1);
        return sensorRepository.findByTimestampBetween(targetDateStart, targetDateEnd);
    }

    /**
     * 指定された日付のセンサーデータからグラフ画像を生成する
     *
     * @param date 対象日付
     * @return PNG形式のグラフ画像のバイト配列
     */
    public byte[] generateGraphImage(LocalDate date) {
        try {
            // 対象日のセンサーデータを取得
            List<SensorEntity> sensors = getTargetDateSensors(date);

            if (sensors.isEmpty()) {
                // log.warn("No sensor data found for date: {}", date);
                return createNoDataChart(date);
            }

            // JFreeChartを作成
            JFreeChart chart = createTemperatureChart(sensors, date);

            // チャートを画像に変換
            return chartToByteArray(chart, 800, 600);

        } catch (Exception e) {
            throw new RuntimeException("グラフ画像の生成に失敗しました", e);
        }
    }

    /**
     * 温度データから折れ線グラフを作成する
     */
    private JFreeChart createTemperatureChart(List<SensorEntity> sensors, LocalDate date) {
        // TimeSeries作成
        TimeSeries series = new TimeSeries("Temperature");

        for (SensorEntity sensor : sensors) {
            // LocalDateTimeをMinuteに変換
            Minute minute = new Minute(
                    Date.from(sensor.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()));
            series.add(minute, sensor.getTemperature());
        }

        // TimeSeriesCollectionを作成
        // 時系列データのコレクションを取り扱う
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        // チャートを作成
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Temperature Log - " + date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")), // タイトル
                "Time", // X軸ラベル
                "Temperature (°C)", // Y軸ラベル
                dataset, // データセット
                true, // 凡例表示
                true, // ツールチップ表示
                false // URL表示
        );

        // チャートの外観をカスタマイズ
        // customizeChart(chart);

        return chart;
    }

    /**
     * チャートの外観をカスタマイズする
     */
    private void customizeChart(JFreeChart chart) {
        // 背景色を白に設定
        chart.setBackgroundPaint(Color.WHITE);

        // タイトルフォントを設定
        chart.getTitle().setFont(new Font("NotoSansCJK", Font.BOLD, 16));

        // プロットを取得
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        // レンダラーを設定
        // X軸Y軸の線と形状のレンダラーを作成
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new java.awt.BasicStroke(2.0f));
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);

        // X軸（時間軸）の設定
        DateAxis dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.setDateFormatOverride(new java.text.SimpleDateFormat("HH:mm"));
        dateAxis.setLabelFont(new Font("NotoSansCJK", Font.PLAIN, 12));

        // Y軸（データ軸）の設定
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("NotoSansCJK", Font.PLAIN, 12));
    }

    /**
     * データがない場合のチャートを作成する
     */
    public byte[] createNoDataChart(LocalDate date) {
        // X軸となる TimeSeriesを作成
        TimeSeries series = new TimeSeries("Temperature");
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart("Temperature Log - "
                + date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " (No Data)", "Time",
                "Temperature (°C)", dataset, true, true, false);

        customizeChart(chart);
        return chartToByteArray(chart, 800, 600);
    }

    /**
     * JFreeChartをバイト配列に変換する
     */
    private byte[] chartToByteArray(JFreeChart chart, int width, int height) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ChartUtils.writeChartAsPNG(outputStream, chart, width, height);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("グラフ画像のバイト列変換に失敗しました", e);
        }
    }
}
