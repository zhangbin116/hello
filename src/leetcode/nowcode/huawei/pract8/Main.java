package leetcode.nowcode.huawei.pract8;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));

        while(sc.hasNext()){
            String nextLine = sc.nextLine();
            System.out.println(deleteMinChar(nextLine));
        }
    }

    private static String deleteMinChar(String nextLine) {
        //统计
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for (int i = 0; i < nextLine.length(); i++) {
            char c = nextLine.charAt(i);
            if(!map.containsKey(c)){
                map.put(c, 1);
            }else{
                map.put(c, map.get(c)+1);
            }
        }

        int minCount = 21;
        for(Map.Entry<Character, Integer> entry:map.entrySet()){
            if(entry.getValue()<minCount){
                minCount = entry.getValue();
            }
        }

        char[] char_array = new char[nextLine.length()];
        int index_char_array = 0;
        for (int i = 0; i < nextLine.length(); i++) {
            if(map.get(nextLine.charAt(i))>minCount){
                char_array[index_char_array++] = nextLine.charAt(i);
            }
        }

        return new String(char_array).trim();
    }

}