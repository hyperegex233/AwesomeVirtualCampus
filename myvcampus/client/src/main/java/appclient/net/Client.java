package appclient.net;

import appclient.model.UserModel;
import appclient.model.Response;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class Client {
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean login(UserModel user) {
        Gson gson = new Gson();
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            // 发送用户信息
            out.write(gson.toJson(user));
            out.newLine();
            out.flush();

            // 读取响应
            String response = in.readLine();
            Response resp = gson.fromJson(response, Response.class);
            return "ok".equalsIgnoreCase(resp.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
