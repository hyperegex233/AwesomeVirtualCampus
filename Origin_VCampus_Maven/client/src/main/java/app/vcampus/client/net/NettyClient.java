package app.vcampus.client.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

public class NettyClient implements Callable<NettyHandler> {
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private final String host;
    private final int port;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public NettyHandler call() {
        try {
            NettyHandler handler = new NettyHandler();

            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(@NotNull SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new JsonObjectDecoder()).addLast(new StringEncoder()).addLast(new StringDecoder()).addLast(handler);
                }
            });

            ChannelFuture f = b.connect(host, port).syncUninterruptibly();

            return handler;
        } finally {

        }
    }
}