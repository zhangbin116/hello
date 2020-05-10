package catalina;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class MyTomcat {


    public static void main(String[] args) {
        new MyTomcat().start(8080);
    }

    private void start(int port) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap boot = new ServerBootstrap();
        boot.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new HttpResponseEncoder());
                ch.pipeline().addLast(new HttpRequestDecoder());
                ch.pipeline().addLast(new MyTomcatHandler());
            }
        }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            ChannelFuture futrue = boot.bind(port).sync();
            futrue.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
