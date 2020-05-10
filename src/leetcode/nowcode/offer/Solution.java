package leetcode.nowcode.offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {


    public static void main(String[] args) {
//        System.out.println(printListFromTailToHead(new ListNode(1)));
        int[] array = new int[]{3, 4, 5, 1, 2};
        System.out.println(new Solution().fibonacci(6));
    }


    /**
     * 斐波那契数列
     *
     * @param n
     * @return
     */
    public int fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        int[] db = new int[n + 1];
        db[0] = 0;
        db[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            db[i] = db[i - 1] + db[i - 2];
        }
        return db[n];
    }

    /**
     * 旋转数组的最小数
     * {3, 4, 5, 1, 2}
     *
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int low = 0;
        int high = array.length - 1;
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (array[middle] <= array[high]) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return array[low];
    }


    // stack1 入栈
    Stack<Integer> stack1 = new Stack<Integer>();
    // stack2 出栈
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() throws Exception {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            throw new Exception("Queue is empty.");
        }
        return stack2.pop();
    }

    // 缓存中序遍历每个节点对应的下标
    private static Map<Integer, Integer> indexForInOrders = new HashMap<>();

    /**
     * 重建二叉树
     *
     * @param pre
     * @param in
     * @return
     */
    private static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i], i);
        }
        return solve(pre, 0, pre.length - 1, 0);
    }


    // pre={1,2,4,7,3,5,6,8}
    // middle={4,7,2,1,5,3,8,6}
    private static TreeNode solve(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = indexForInOrders.get(root.val);
        int leftTreeSize = inIndex - inL;
        root.left = solve(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = solve(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }


    /* Definition for
     binary tree*/
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 从尾到头打印链表
     * 方法二  使用递归思想
     *
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> lst = new ArrayList<>();
        if (listNode != null) {
            lst.addAll(printListFromTailToHead2(listNode.next));
            lst.add(listNode.val);
        }
        return lst;
    }

    /**
     * 从尾到头打印链表
     * 方法一  头插法
     *
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode head = new ListNode(-1);
        while (listNode != null) {
            ListNode current = listNode.next;
            listNode.next = head.next;
            head.next = listNode;
            listNode = current;
        }
        head = head.next;
        ArrayList<Integer> lstNode = new ArrayList<>();
        while (head != null) {
            lstNode.add(head.val);
            head = head.next;
        }
        return lstNode;
    }


    static class ListNode {
        ListNode next = null;
        int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 替换空格  方法二
     *
     * @param str
     * @return
     */
    public static String replaceSpace2(StringBuffer str) {
        char[] chars = str.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                str.append("&&");
            }
        }
        char[] chars1 = str.toString().toCharArray();
        // 表示chars1数组的下标  从后向前遍历
        int index = chars1.length - 1;
        for (int i = chars.length - 1; i > -1; i--) {
            char temp = chars[i];
            if (temp != ' ') {
                chars1[index--] = temp;
                continue;
            }
            chars1[index--] = '0';
            chars1[index--] = '2';
            chars1[index--] = '%';
        }
        return String.valueOf(chars1);
    }

    /**
     * 替换空格  方法一
     *
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll("\\s+", "%20");
    }

    /**
     * 二维数组中的查找
     *
     * @param target
     * @param array
     * @return
     */
    public boolean find(int target, int[][] array) {
        int index = 0;
        int rows = array.length;
        int cols = array[0].length - 1;
        while (cols > -1 && index < rows) {
            int temp = array[index][cols];
            if (target == temp) {
                return true;
            }
            if (target < temp) {
                cols--;
                continue;
            }
            if (target > temp) {
                index++;
            }
        }
        return false;
    }


    /**
     * 剪绳子问题  动态规划问题
     *
     * @return
     */
    public static int cutRope(int target) {
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int[] rope = new int[target + 1];
        // rope数组的小标为绳子的长度，当长度小于4时  不需要拆分，拆分反而更小了
        rope[1] = 1;
        rope[2] = 2;
        rope[3] = 3;
        int max = -1;
        for (int i = 4; i < target + 1; i++) {
            for (int j = 1; j < i / 2 + 1; j++) {
                int temp = rope[j] * rope[i - j];
                max = temp > max ? temp : max;
            }
            rope[i] = max;
        }
        return rope[target];
    }

    /**
     * 1的位数
     *
     * @param n
     * @return
     */
    public static int numberOf1(int n) {
        return Integer.bitCount(n);
    }


    /**
     * 数值的整数次方
     *
     * @param base
     * @param exponent
     * @return
     */
    public static double power(double base, int exponent) {
        if (base == 0 && exponent != 0) {
            return 0;
        }
        if (base != 0 && exponent == 0) {
            return 1;
        }
        int count = 1;
        double power = 1.0;
        if (exponent > 0) {
            do {
                power = power * base;
                count++;
            } while (count < exponent + 1);
        } else {
            do {
                power = power * base;
                count++;
            } while (count < -exponent + 1);
            power = 1 / power;
        }
        return power;
    }


    /**
     * 数值的整数次方  方法二
     *
     * @param base
     * @param exponent
     * @return
     */
    public static double power2(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        boolean isNegative = false;
        if (exponent < 0) {
            exponent = -exponent;
            isNegative = true;
        }
        double pow = power2(base * base, exponent / 2);
        if (exponent % 2 != 0)
            pow = pow * base;
        return isNegative ? 1 / pow : pow;
    }

}
