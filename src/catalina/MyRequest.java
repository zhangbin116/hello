package catalina;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

public class MyRequest {

    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public MyRequest(ChannelHandlerContext ctx, HttpRequest r) {
        this.ctx = ctx;
        this.request = r;
    }

    public String getUri() {
        return request.getUri();
    }

    public String getMethod() {
        return request.getMethod().name();
    }

    public Map<String, List<String>> getParamters() {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
        return queryStringDecoder.parameters();
    }

    public String getParamter(String paraName) {
        Map<String, List<String>> paramters = getParamters();
        List<String> param = paramters.get(paraName);
        if (null != param) {
            return param.get(0);
        }
        return null;
    }

}
