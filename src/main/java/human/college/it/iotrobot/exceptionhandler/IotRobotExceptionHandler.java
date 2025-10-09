package human.college.it.iotrobot.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import human.college.it.iotrobot.exception.IotRobotException;
import lombok.extern.slf4j.Slf4j;

/**
 * IoTロボット関連の例外を処理するためのグローバル例外ハンドラ
 *
 * このクラスは ControllerAdvice アノテーションを使用して、アプリケーション全体で発生する IotRobotException をキャッチし、 適切なエラーページを表示します
 */
@ControllerAdvice
@Slf4j
public class IotRobotExceptionHandler extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(IotRobotException.class)
    public ModelAndView handleIotRobotException(IotRobotException ex) {
        log.error("IotRobotExceptionが発生しました", ex);
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("message", ex.getMessage());
        return mav;
    }

}
