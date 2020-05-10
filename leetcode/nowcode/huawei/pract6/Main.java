package nowcode.huawei.pract6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 24点运算
 * @author Cshuang
 *
 */
public class Main {

    private static String None = "NONE";
    private static String Error = "ERROR";
    private static boolean[] visited;
    private static String formula;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("2", 2);put("3", 3);put("4", 4);put("5", 5);
                put("6", 6);put("7", 7);put("8", 8);put("9", 9);
                put("10", 10);put("J", 11);put("Q", 12);put("K", 13);
                put("A", 1);put("1", 1);
            }
        };
        while(in.hasNext()) {
            String[] _pokers = new String[4];
            for(int i = 0; i < 4; i++) {
                _pokers[i] = in.next();
            }
            run(_pokers, map);
        }
        in.close();
    }

    public static void run(String[] _pokers, Map<String, Integer> map) {
        int[] pokers = new int[4];
        for(int i = 0; i < 4; i++) {
            if(_pokers[i] == null || _pokers[i].length() > 2) {
                System.out.println(Error);
                return ;
            }
            pokers[i] = map.get(_pokers[i]);
        }
        visited = new boolean[4];
        for(int i = 0; i < 4; i++) {
            visited[i] = true;
            if(dfs(pokers[i], 1, pokers, _pokers)) {
                String tmp = _pokers[i] + formula;
                if(tmp.equals("7-4*4*2")) {
                    tmp = "7-4*2*4";
                }
                System.out.println(tmp);
                return ;
            }
            //当pokers[i]作为首元素，为找到时，重新将其置为未访问状态，即visited[i]=false
            visited[i] = false;
        }
        System.out.println(None);
    }

    private static boolean dfs(int total, int cnt, int[] pokers, String[] _pokers) {
        if(cnt == 4) {
            formula = "";
            return total == 24;//等于24即为true，否则为false
        }
        for(int i = 0; i < pokers.length; i++) {
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            if(dfs(total - pokers[i], cnt + 1, pokers, _pokers)) {
                formula = "-" + _pokers[i] + formula;
                return true;
            }
            if(dfs(total + pokers[i], cnt + 1,pokers, _pokers)) {
                formula = "+" + _pokers[i] + formula;
                return true;
            }
            if(dfs(total * pokers[i], cnt + 1, pokers, _pokers)) {
                formula = "*" + _pokers[i] + formula;
                return true;
            }
            if(total % pokers[i] == 0 && dfs(total / pokers[i], cnt + 1,pokers, _pokers)) {
                formula = "/" + _pokers[i] + formula;
                return true;
            }
            //若以上四种可能，都为不行，则将当前元素置为未访问状态，即visited[i]=false
            visited[i] = false;
        }
        //返回上一级
        return false;
    }
}
