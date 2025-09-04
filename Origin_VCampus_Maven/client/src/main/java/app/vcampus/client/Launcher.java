package app.vcampus.client;

/**
 * Launcher class to start the JavaFX application.
 * This is a workaround for JavaFX runtime components not being present on the module path in a fat JAR.
 */
public class Launcher {
    public static void main(String[] args) {
        // 调用您真正的 JavaFX Application 主类的 main 方法
        Main.main(args);
    }
}