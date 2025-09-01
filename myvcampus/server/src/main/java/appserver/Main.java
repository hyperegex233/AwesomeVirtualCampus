package appserver;

import appserver.net.Server;

public class Main {
    public static void main(String[] args) {
        int port = 8000; // 默认端口
        try {
            Server server = new Server(port);
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("服务器启动失败！");
        }
    }
}
