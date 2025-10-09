package human.college.it.iotrobot.exception;

/**
 * IoTロボット関連の例外を表すカスタム例外クラス
 *
 * このクラスは RuntimeException を継承しており、IoTロボットアプリケーション内で発生する特定のエラー状況を表現するために使用されます
 */
public final class IotRobotException extends RuntimeException {

    public IotRobotException(String message) {
        super(message);
    }

    public IotRobotException(String message, Throwable cause) {
        super(message, cause);
    }
}
