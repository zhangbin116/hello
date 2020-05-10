package nio;

import java.nio.IntBuffer;

/**
 * 测试NIO 特性
 *
 *
 */
public class NioTestDemo {


    public static void main(String[] args) {
        IntBuffer allocate = IntBuffer.allocate(8);
        for (int i = 0; i < allocate.capacity(); i++) {
            int j = 2 * (i + 1);
            allocate.put(j);
        }
        // 重设此缓冲区
        allocate.flip();

        while (allocate.hasRemaining()) {
            int j = allocate.get();
            System.out.print(j + " ");
        }
    }
}
