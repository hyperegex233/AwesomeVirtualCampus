package appserver;

import appserver.net.Server;

public class Main {
    public static void main(String[] args) throws Exception {
        new Server(8000).run();
    }
}
