package catalina;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

public class MyTomcatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest r = (HttpRequest) msg;

            MyRequest request = new MyRequest(ctx, r);
            MyResponse response = new MyResponse(ctx, r);

            String uri = request.getUri();
            String method = request.getMethod();

            MyServlet servlet = new FirstServlet();
            servlet.doGet(request, response);

        }


    }
}
