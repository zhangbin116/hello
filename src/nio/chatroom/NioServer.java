package nio.chatroom;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 实现聊天室功能
 */
public class NioServer {


    private int port = 8080;

    private Charset charset = Charset.forName("UTF-8");
    private static HashSet<String> users = new HashSet<>();
    private static String USER_EXIST = "系统提示：该昵称已经存在，请换一个昵称";
    //相当于自定义协议格式，与客户端协商好
    private static String USER_CONTENT_SPILIT = "#@#";
    private Selector selector = null;

    public NioServer(int port) {
        this.port = port;
        try {
            ServerSocketChannel serverChanel = ServerSocketChannel.open();
            serverChanel.bind(new InetSocketAddress(port));
            serverChanel.configureBlocking(false);
            selector = Selector.open();
            serverChanel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务已经启动，监听端口为：" + this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        new NioServer(5566).listen();
    }

    /**
     * 监听端口
     *
     * @throws IOException
     */
    private void listen() throws IOException {
        while (true) {
            int select = selector.select();
            if (select == 0) continue;
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                process(key);
            }
        }
    }

    private void process(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            key.interestOps(SelectionKey.OP_ACCEPT);
            client.write(charset.encode("请输入你的昵称"));
        }
        if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buff = ByteBuffer.allocate(1024);
            StringBuilder content = new StringBuilder();
            try {
                while (client.read(buff) > 0) {
                    buff.flip();
                    content.append(charset.decode(buff));
                }
                key.interestOps(SelectionKey.OP_READ);
            } catch (Exception e) {
                key.cancel();
                if (key.channel() != null) {
                    key.channel().close();
                }
            }
            if (content.length() > 0) {
                String[] arrayContent = content.toString().split(USER_CONTENT_SPILIT);
                if (arrayContent != null && arrayContent.length == 1) {
                    String nickName = arrayContent[0];
                    if (users.contains(nickName)) {
                        client.write(charset.encode(USER_EXIST));
                    } else {
                        users.add(nickName);
                        int onlineCount = onlineCount();
                        String message = "欢迎 " + nickName + "进入聊天室！当前在线人数：" + onlineCount;
                        broadCast(null, message);
                    }
                } else if (arrayContent != null && arrayContent.length > 1) {
                    String nickName = arrayContent[0];
                    String message = content.substring(nickName.length() + USER_CONTENT_SPILIT.length());
                    message = nickName + " 说 " + message;
                    if (users.contains(nickName)) {
                        broadCast(client, message);
                    }
                }
            }
        }
    }

    private void broadCast(SocketChannel client, String content) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != client) {
                SocketChannel target = (SocketChannel) targetChannel;
                target.write(charset.encode(content));
            }
        }
    }

    private int onlineCount() {
        int res = 0;
        for (SelectionKey key : selector.keys()) {
            Channel target = key.channel();
            if (target instanceof SocketChannel) {
                res++;
            }
        }
        return res;
    }


}
