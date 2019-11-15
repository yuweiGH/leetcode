//将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return mergeTwoListsB(l1, l2);
    }

    /**
     * 前提链表有序，遍历将最小放入新链表内
     * 收尾工作需要将未比较完的写入next节点
     * 理解清楚引用用法，解法就很清楚
     * 时间O(n+m) 空间O(n+m)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsA(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(0);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

    /**
     * 递归选出最小一个，最后将每层最小一个串起来。
     * 时间O(n+m) 空间O(1)
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsB(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoListsB(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsB(l1, l2.next);
            return l2;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
