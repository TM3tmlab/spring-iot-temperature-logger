package human.college.it.iotrobot.controller;

import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * おみくじコントローラー
 */
@Controller
@RequestMapping("/omikuji")
public class OmikujiController {

    /**
     * トップページを表示する
     *
     * @return html
     */
    @GetMapping
    public String index() {
        return "mikuji";
    }

    /**
     * おみくじを引く
     *
     * @param model モデル
     * @return html
     */
    @GetMapping("/mikuji")
    public String pickMikuji(Model model) {

        Random rand = new Random();
        String[] fortunes = {"大吉", "中吉", "小吉", "吉", "末吉", "凶"};
        String fortune = fortunes[rand.nextInt(fortunes.length)];

        model.addAttribute("fortune", fortune);
        return "mikuji";
    }
}
