package leetcode.nowcode.huawei.pract7;

import java.util.Scanner;

/**
 * 汽水瓶问题
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            if(a == 0 ||a == 1) return;
            int b = 0;
            int c = 0;
            int sum = 0;
            do {
                b = a / 3;
                c = a % 3;
                a = b + c;
                sum += b;
            }while(b != 0 );
            if(a == 2){
                sum+=1;
            }
            System.out.println(sum);
        }
    }
}