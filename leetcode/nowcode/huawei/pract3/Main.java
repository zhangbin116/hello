package nowcode.huawei.pract3;

import java.util.Scanner;

/**
 * 判断两个IP是否属于同一子网
 *
 * @author Cshuang
 *
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String[] mask = sc.next().split("\\.");
            String[] ip1 = sc.next().split("\\.");
            String[] ip2 = sc.next().split("\\.");

            for (int i = 0; i < mask.length; i++) {
                if (Integer.valueOf(mask[i]) < 0 || Integer.valueOf(mask[i]) > 255 || Integer.valueOf(ip1[i]) < 0
                        || Integer.valueOf(ip1[i]) > 255 || Integer.valueOf(ip2[i]) < 0
                        || Integer.valueOf(ip2[i]) > 255) {
                    System.out.println(1);// IP地址或子网掩码格式非法
                    break;
                }
                //可以直接对整数进行相与
                else if ((Integer.valueOf(mask[i]) & Integer.valueOf(ip1[i])) == (Integer.valueOf(mask[i])
                        & Integer.valueOf(ip2[i]))) {
                    System.out.println(0);// IP1与IP2属于同一子网
                }
                else {
                    System.out.println(2);// IP1与IP2不属于同一子网络
                    break;
                }
            }

        }
    }

}

