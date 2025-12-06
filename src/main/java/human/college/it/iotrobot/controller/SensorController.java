package human.college.it.iotrobot.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import human.college.it.iotrobot.entity.SensorEntity;
import human.college.it.iotrobot.repository.SensorRepository;


@Controller
@RequestMapping("/sensor")
public class SensorController {

    private final SensorRepository sensorRepository;

    /**
     * コンストラクタ
     * <p>
     * このコンストラクタは Spring によって呼び出され、SensorRepository のインスタンスが注入される
     * </p>
     *
     * @param sensorRepository
     */
    public SensorController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @GetMapping
    public String index(@RequestParam(value = "date", required = false) LocalDate date,
            Model model) {
        if (date != null) {
            LocalDate startOfDay = date.atStartOfDay().toLocalDate();
            LocalDate endOfDay = date.plusDays(1).atStartOfDay().toLocalDate();
            List<SensorEntity> sensorData = sensorRepository.findByTimestampBetween(
                    startOfDay.atStartOfDay(), endOfDay.atStartOfDay().minusSeconds(1));
            model.addAttribute("sensorData", sensorData);
            model.addAttribute("selectedDate", date);
        }

        List<Date> distinctDates = sensorRepository.findAllDistinctByTimestampAsDate();
        model.addAttribute("distinctDates", distinctDates);
        // distinctDates.forEach(System.out::println);
        return "sensor";
    }

    @GetMapping("temperature")
    public String showTemperatureGraph(
            @RequestParam(value = "targetDate", required = false) LocalDate targetDate,
            Model model) {
        model.addAttribute("graphTypeName", "温度グラフ");
        model.addAttribute("graphType", "temperature");
        model.addAttribute("targetDate", targetDate);

        List<Date> distinctDates = sensorRepository.findAllDistinctByTimestampAsDate();
        model.addAttribute("distinctDates", distinctDates);
        return "sensorGraph";
    }

    @GetMapping("humidity")
    public String showHumidityGraph(
            @RequestParam(value = "targetDate", required = false) LocalDate targetDate,
            Model model) {
        model.addAttribute("graphTypeName", "湿度グラフ");
        model.addAttribute("graphType", "humidity");
        model.addAttribute("targetDate", targetDate);

        List<Date> distinctDates = sensorRepository.findAllDistinctByTimestampAsDate();
        model.addAttribute("distinctDates", distinctDates);
        return "sensorGraph";
    }

    @GetMapping("pressure")
    public String showPressureGraph(
            @RequestParam(value = "targetDate", required = false) LocalDate targetDate,
            Model model) {
        model.addAttribute("graphTypeName", "気圧グラフ");
        model.addAttribute("graphType", "pressure");
        model.addAttribute("targetDate", targetDate);

        List<Date> distinctDates = sensorRepository.findAllDistinctByTimestampAsDate();
        model.addAttribute("distinctDates", distinctDates);
        return "sensorGraph";
    }

}
