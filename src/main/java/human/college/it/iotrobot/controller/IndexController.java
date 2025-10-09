package human.college.it.iotrobot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import human.college.it.iotrobot.repository.SensorRepository;
import lombok.RequiredArgsConstructor;


/**
 * ルート(/)要素のコントローラ
 */
// Controller アノテーションを付けると、SpringBoot がこのクラスを コントローラとして自動登録します
// RequestMapping と Get/PostMapping を組み合わせて反応するURLを生成します
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public final class IndexController {

    private final SensorRepository sensorRepository;

    // /**
    // * ルートページを表示します
    // *
    // * @return html Controller の中で String文字列 を返却すると src/main/resources/templates/ 以下の
    // * テンプレートファイルを探して表示します ここでは index を指定したので src/main/resources/templates/"index".html
    // * を表示します
    // */
    // // GetMapping は HTTP の GET メソッドでアクセスされた場合に反応します
    // @GetMapping
    // public String index() {
    // return "index.html";
    // }

    @GetMapping("data")
    public ModelAndView rawData() {
        ModelAndView mav = new ModelAndView("data");
        mav.addObject("sensors", sensorRepository.findAll());
        return mav;
    }

}
