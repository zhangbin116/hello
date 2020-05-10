package nowcode.huawei.pract2;

import java.util.Scanner;

/**
 * 统计每个月兔子的总数
 * @author Cshuang
 *
 */
public class Main {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        while(in.hasNext()){
            int month=in.nextInt();
            System.out.println(getTotalCount(month));
        }
        in.close();
    }
    public static int getTotalCount(int m){
        int a=1,b=0,c=0;//a:一个月兔子数，b：两个月兔子数，c：三个月兔子个数
        while(--m!=0){//每过一个月兔子数变化
            c+=b;//只有满3个月的兔子会累加
            b=a;//（1个月的兔子变成两个月的）
            a=c;//第三个月的兔子开始生小兔（1个月的）
        }
        return a+b+c;
    }
}
