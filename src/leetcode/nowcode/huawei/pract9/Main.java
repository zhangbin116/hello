package leetcode.nowcode.huawei.pract9;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        while(in.hasNextInt()){
            int cnt=in.nextInt();
            int[]arrHeight=new int[cnt];
            for (int i = 0; i < cnt; i++) {
                arrHeight[i]=in.nextInt();
            }

            int[] left=new int[cnt];//存放从左往右当前遍历位置最长序列长度（只是长度并非元素）
            for (int i = 0; i < cnt; i++) {//必须从0开始，而不能从开始，否则没法对left[0]初始化
                left[i]=1;    //初始化 默认长度
                for (int j = 0; j < i; j++) {
                    if(arrHeight[i]>arrHeight[j]){
                        left[i]=Math.max(left[i], left[j]+1);
                    }
                }
            }


            int[] right=new int[cnt];
            for (int i = cnt-1; i > 0; i--) {//必须从cnt-1开始，而不能从cnt-2开始，否则没法初始化
                right[i]=1;
                for (int j = cnt-1; j > i; j--) {
                    if(arrHeight[i]>arrHeight[j]){
                        right[i]=Math.max(right[i], right[j]+1);
                    }
                }
            }

            int res=0;//合唱人数，其实左右人数不一定要对称
            for(int i=0;i<cnt;i++){
                if(left[i]+right[i]-1>res)//挑选出最长队列，减1是因为左右，每一点重复了一次
                    res=left[i]+right[i]-1;
            }
            System.out.println(cnt-res);
        }
        in.close();
    }
}

