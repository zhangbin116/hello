package interview;

public class Main {

    /**
     * 输入： 1 8 3 6 5 4 7 2 9
     * 输出   1 2 3 4 5 6 7 8 9
     *
     * @param args
     */
    public static void main(String[] args) {

        // 将链表按奇偶数分成两个链表
        // 对偶数链表进行反转
        // 对两个链表进行合并
        ListNode head = init();
        ListNode[] listNodes = diviseListNode(head);
        ListNode head2 = reverseListNode(listNodes[1]);
        head = mergeTwoListNodes(listNodes[0], head2);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    private static ListNode init() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(8);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(2);
        ListNode node9 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = null;
        return node1;
    }

    // 合并链表
    private static ListNode mergeTwoListNodes(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) return null;
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode head = null;
        if (head1.value > head2.value) {
            head = head2;
            head.next = mergeTwoListNodes(head1, head2.next);
        } else {
            head = head1;
            head.next = mergeTwoListNodes(head1.next, head2);
        }
        return head;
    }

    // 采用头插法  反转数组
    private static ListNode reverseListNode(ListNode head) {
        ListNode tempNode = new ListNode(-1);
        while (head != null) {
            ListNode memo = head.next;
            head.next = tempNode.next;
            tempNode.next = head;
            head = memo;
        }
        return tempNode.next;
    }

    // 将链表  按奇数和偶数下标分开
    private static ListNode[] diviseListNode(ListNode head) {
        int index = 1;
        ListNode head1 = null;
        ListNode head2 = null;

        ListNode cursor1 = null;
        ListNode cursor2 = null;
        while (head != null) {
            if (index % 2 == 1) {
                if (cursor1 != null) {
                    cursor1.next = head;
                    cursor1 = cursor1.next;
                } else {
                    cursor1 = head;
                    head1 = cursor1;
                }
            } else {
                if (cursor2 != null) {
                    cursor2.next = head;
                    cursor2 = cursor2.next;
                } else {
                    cursor2 = head;
                    head2 = cursor2;
                }
            }
            head = head.next;
            index++;
        }
        cursor1.next = null;
        cursor2.next = null;
        return new ListNode[]{head1, head2};
    }


    static class ListNode {
        ListNode next;
        int value;

        public ListNode(int value) {
            this.value = value;
        }
    }

}
