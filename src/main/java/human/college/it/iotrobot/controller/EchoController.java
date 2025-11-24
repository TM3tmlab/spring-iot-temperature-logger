package human.college.it.iotrobot.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * エコーコントローラー
 */
@Controller
@RequestMapping("/echo")
public class EchoController {

    /**
     * トップページを表示する
     *
     * @return html
     */
    @GetMapping
    public String index() {
        return "echo";
    }

    /**
     * リクエストされた文字列を表示する
     *
     * @param echoString リクエストされた文字列
     * @param model モデル
     * @return html
     */
    @PostMapping("echo")
    public String echo(@RequestParam("echo") String echoString, Model model) {
        model.addAttribute("echo", echoString);
        return "echo";
    }

    /**
     * リクエストされた文字列を加工して表示する
     *
     * @param yamabikoString リクエストされた文字列
     * @param model モデル
     * @return html
     */
    @PostMapping("yamabiko")
    public String yamabiko(@RequestParam("yamabiko") String yamabikoString, Model model) {
        // やまびこのようにリクエストした文字列を一文字ずつ減らしながら表示する
        // 減らした文字列は リストに格納し、後の加工はHTMLに任せる
        List<String> yamabikoList = new ArrayList<>();
        for (int i = 0; i < yamabikoString.length(); i++) {
            yamabikoList.add(yamabikoString.substring(i));
        }
        model.addAttribute("yamabiko", yamabikoList);
        return "echo";
    }

}
