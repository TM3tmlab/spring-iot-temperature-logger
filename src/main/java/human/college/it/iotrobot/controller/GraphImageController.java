package human.college.it.iotrobot.controller;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import human.college.it.iotrobot.service.SensorService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
@RequestMapping("/img")
public class GraphImageController {

    private final SensorService sensorService;

    /**
     * グラフ画像を生成します
     *
     * @return グラフ画像のPNGデータ
     */
    @ResponseBody
    @GetMapping("graph/{date}.png")
    public ResponseEntity<byte[]> getGraphImage(
            @PathParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        date = LocalDate.of(2025, 9, 1); // TODO: 仮で今日の日付をセット

        // グラフ画像を生成するロジックを実装
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
                .body(sensorService.generateGraphImage(date));
    }

}
