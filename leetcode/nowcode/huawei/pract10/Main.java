package nowcode.huawei.pract10;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 字符串排序问题
 * @author hadoop
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in=new Scanner(System.in);
        while(in.hasNext()){
            String string=in.nextLine();
            LinkedList<String> list=new LinkedList<>();
            char[] cs=string.toCharArray();
            for(int i=0;i<cs.length;i++){
                if((cs[i]>='a'&&cs[i]<='z')||(cs[i]>='A'&&cs[i]<='Z')){
                    list.add(String.valueOf(cs[i]));
                }

            }
            //字符串忽略大小写排序
            Collections.sort(list, String.CASE_INSENSITIVE_ORDER);

            for(int i=0,j=0;i<cs.length;i++){
                if((cs[i]>='a'&&cs[i]<='z')||(cs[i]>='A'&&cs[i]<='Z')){
                    System.out.print(list.get(j));
                    j++;
                }else{
                    System.out.print(cs[i]);
                }
            }
            System.out.println();
        }

    }
}