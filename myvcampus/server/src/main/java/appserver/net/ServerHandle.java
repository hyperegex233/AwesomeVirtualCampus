package appserver.net;

import appserver.model.UserModel;
import appserver.service.UserService;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandle extends SimpleChannelInboundHandler<String> {

    private final UserService userService = new UserService();
    private final Gson gson = new Gson();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        UserModel user = gson.fromJson(msg, UserModel.class);
        boolean success = userService.login(user);

        String response = gson.toJson(new Response(success ? "ok" : "fail")) + "\n"; // 注意换行
        ctx.writeAndFlush(response);
    }

    public static class Response {
        private final String status;
        public Response(String status) { this.status = status; }
        public String getStatus() { return status; }
    }
}
