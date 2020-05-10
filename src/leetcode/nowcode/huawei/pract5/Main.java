package leetcode.nowcode.huawei.pract5;

import java.util.Scanner;
/**
 * 成绩排序
 * @author Cshuang
 *
 */
public class Main{
    public static void main(String[]args){
        Scanner in=new Scanner(System.in);
        while(in.hasNext()){
            int n=Integer.parseInt(in.nextLine());//这里不能用in.nextInt,容易出现格式错误
            int flag=Integer.parseInt(in.nextLine());
            Student[]st=new Student[n];
            for(int i=0;i<n;i++){
                String[] str=in.nextLine().split(" ");
                st[i]=new Student();
                st[i].name=str[0];
                st[i].grade=Integer.parseInt(str[1]);
            }
            //从大到小
            for(int i=0;i<n;i++){
                for(int j=0;j<n-i-1;j++){
                    if(flag==0){
                        if(st[j].grade<st[j+1].grade){
                            swap(st,j,j+1);
                        }
                    }else{
                        if(st[j].grade>st[j+1].grade){
                            swap(st,j,j+1);
                        }
                    }
                }
            }
            for(int i=0;i<n;i++){
                System.out.println(st[i].name+" "+st[i].grade);
            }
        }
        in.close();
    }

    public static void swap(Student []st,int m,int n){
        Student temp=new Student();
        temp=st[m];
        st[m]=st[n];
        st[n]=temp;
    }
}

class Student{
    String name;
    int grade;
}
