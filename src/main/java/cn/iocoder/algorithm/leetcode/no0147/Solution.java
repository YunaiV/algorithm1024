package cn.iocoder.algorithm.leetcode.no0147;

/**
 * https://leetcode-cn.com/problems/insertion-sort-list/
 *
 * 插入排序
 */
public class Solution {

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 虚拟头节点，方便编程
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        // 从头节点开始，插入排序
        ListNode prev = head;
        while (prev.next != null) {
            // 如果当前节点（prev）比下一个节点大，直接跳到下个节点。
            if (prev.val <= prev.next.val) {
                prev = prev.next;
                continue;
            }

            // 删除 prev.next 节点
            ListNode tmp = prev.next;
            prev.next = prev.next.next;

            // 寻找最后一个比 prev.next 小的节点
            ListNode query = dummy;
            while (tmp.val >= query.next.val) { // 注意，这里的 query.next
                query = query.next;
            }

            // 将 tmp 插入到 query 后面
            tmp.next = query.next;
            query.next = tmp;
        }

        return dummy.next;
    }

// 数组的快速排序的实现
//    public void insertionSort(int[] array) {
//        for (int i = 1; i < array.length; i++) {
//            int key = array[i];
//            int j = i - 1;
//            while (j >= 0 && array[j] > key) {
//                array[j + 1] = array[j];
//                j--;
//            }
//            array[j + 1] = key;
//        }
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        if (true) {
            ListNode head = new ListNode(4);
            head.next = new ListNode(2);
            head.next.next = new ListNode(1);
            head.next.next.next = new ListNode(3);
            System.out.println(solution.insertionSortList(head));
        }
        if (false) {
            ListNode head = new ListNode(-1);
            head.next = new ListNode(5);
            head.next.next = new ListNode(3);
            head.next.next.next = new ListNode(4);
            head.next.next.next.next = new ListNode(0);
            System.out.println(solution.insertionSortList(head));
        }
        if (false) {
            ListNode head = new ListNode(1);
            head.next = new ListNode(1);
            System.out.println(solution.insertionSortList(head));
        }
        if (false) {
            ListNode head = new ListNode(3);
            head.next = new ListNode(2);
            head.next.next = new ListNode(4);
            System.out.println(solution.insertionSortList(head));
        }
    }

}
