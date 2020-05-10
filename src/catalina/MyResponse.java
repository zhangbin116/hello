package catalina;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.util.HashMap;
import java.util.Map;

public class MyResponse {
    private ChannelHandlerContext ctx;
    private HttpRequest request;

    private static Map<Integer, HttpResponseStatus> statusMapping = new HashMap<>();

    static {
        statusMapping.put(200, HttpResponseStatus.OK);
        statusMapping.put(404, HttpResponseStatus.NOT_FOUND);
    }


    public MyResponse(ChannelHandlerContext ctx, HttpRequest r) {
        this.ctx = ctx;
        this.request = r;
    }

    public void write(String out, Integer status) {
        try {
            FullHttpResponse response
                    = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1
                    , statusMapping.get(status), Unpooled.wrappedBuffer(out.getBytes("UTF-8")));
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/json");
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaders.Names.EXPIRES, 0);
            if (HttpHeaders.isKeepAlive(request)) {
                response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            }
            ctx.write(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ctx.flush();
        }


    }
}
