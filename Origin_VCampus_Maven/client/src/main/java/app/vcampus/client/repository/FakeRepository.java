package app.vcampus.client.repository;

import app.vcampus.client.gateway.AuthClient;
import app.vcampus.client.net.NettyHandler;
import app.vcampus.server.entity.User;

public class FakeRepository {
    public static NettyHandler handler;
    public static boolean isConnected = false;
    public static User user;

    public static boolean login(String username, String password) {
        User result = AuthClient.login(handler, username, password);
        if (result != null) {
            user = result;
            return true;
        }
        return false;
    }

    public static void disconnect() {
        if (handler != null) {
            handler.disconnect();
        }
        isConnected = false;
        handler = null;
        user = null;
    }
}
