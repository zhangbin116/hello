package nio.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NioClient {

    private final InetSocketAddress serverAddress = new InetSocketAddress("localhost", 5566);

    private static String USER_EXIST = "系统提示：该昵称已经存在,请换一个昵称";
    private static String USER_CONTENT_SPLIT = "#@#";

    private Selector selector = null;
    private SocketChannel client = null;
    private Charset charset = Charset.forName("UTF-8");
    private String nickName = "";

    public NioClient() throws IOException {
        client = SocketChannel.open(serverAddress);
        client.configureBlocking(false);
        selector = Selector.open();
        client.register(selector, SelectionKey.OP_READ);
    }

    public static void main(String[] args) {
        try {
            new NioClient().session();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void session() {
        new Writer().start();
        new Readder().start();

    }

    class Readder extends Thread {
        @Override
        public void run() {
            try {
                while (true) {

                    int readyChannel = selector.select();
                    if (readyChannel == 0) {
                        Set<SelectionKey> keys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = keys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            iterator.remove();
                            process(key);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    private void process(SelectionKey key) throws IOException {
        if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String content = "";
            while (channel.read(buffer) > 0) {
                buffer.flip();
                content += charset.decode(buffer);
            }
            if (USER_EXIST.equals(content)) {
                nickName = "";
            }
            System.out.println(content);
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    class Writer extends Thread {

        @Override
        public void run() {
            try {
                Scanner scanner = new Scanner(System.in);
                while (scanner.nextLine() != null) {
                    String line = scanner.nextLine();
                    if ("".equals(line)) {
                        continue;
                    }
                    if ("".equals(nickName)) {
                        nickName = line;
                        line = nickName + USER_CONTENT_SPLIT;
                    } else {
                        line = nickName + USER_CONTENT_SPLIT + line;
                    }
                    client.write(charset.encode(line));
                }
                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
