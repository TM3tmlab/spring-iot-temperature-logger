package human.college.it.iotrobot.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import human.college.it.iotrobot.service.SensorGraphService;

@Controller
@RequestMapping(value = "/sensor-graph", produces = MediaType.IMAGE_PNG_VALUE)
public class SensorGraphController {

    private final SensorGraphService sensorGraphService;

    public SensorGraphController(SensorGraphService sensorGraphService) {
        this.sensorGraphService = sensorGraphService;
    }

    // ダミーの画像を返すエンドポイント
    @GetMapping("/dummy")
    public ResponseEntity<byte[]> getDummyImage() {

        // 空画像を返す例
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final int width = 256;
        final int height = 256;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        try {
            // 緑で塗りつぶし背景色を描画
            g2d.setColor(Color.GREEN);
            g2d.fillRect(0, 0, width, height);

            // 青い四角形を描画
            g2d.setColor(Color.BLUE);
            g2d.fillRect(50, 50, 200, 100);

            ImageIO.write(image, "png", outputStream);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
                    .body(outputStream.toByteArray());
        } catch (Exception e) {
            // 何かしらのエラーが発生した場合は500エラーを返す
            return ResponseEntity.status(500).build();
        } finally {
            g2d.dispose();
        }
    }

    @GetMapping("/temperature")
    public ResponseEntity<byte[]> getTemperatureGraph(
            @RequestParam("targetDate") LocalDate targetDate) {
        // ここでSensorGraphServiceを使ってグラフ画像を生成し、バイト配列を取得する
        byte[] graphImageBytes = sensorGraphService.generateGraphImage(targetDate);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(graphImageBytes);
    }
}
