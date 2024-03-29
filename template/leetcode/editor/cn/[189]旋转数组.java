//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return;
        }
        rotateA(nums, k);
    }

    /**
     * 整体反转一遍，然后k位前后两部分分别反转
     * 时间O(n) 空间O(1)
     * @param nums
     * @param k
     */
    public void rotateA(int[] nums, int k) {
        int lastIdx = nums.length - 1;
        reversal(nums, 0, lastIdx);
        reversal(nums, 0, k - 1);
        reversal(nums, k, lastIdx);
    }

    private void reversal(int[] nums, int l, int r) {
        int tmp;
        while (l < r) {
            tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }

    /**
     * 环形替换
     * 时间O(n) 空间O(1)
     *
     * @param nums
     * @param k
     */
    public void rotateB(int[] nums, int k) {
        int count = 0;
        int current;
        int next;
        int tmp;
        for (int i = 0; count < nums.length; i++) {
            current = i;
            do {
                next = (current + k) % nums.length;
                current = next;
                tmp = nums[i];
                nums[i] = nums[next];
                count++;
            } while (current != i);
        }
    }

    /**
     * 新建监时数组，将原数据写要移位的位置，然后copy新数组
     * 时间O(n) 空间O(n)
     *
     * @param nums
     * @param k
     */
    public void rotateC(int[] nums, int k) {
        k = k % nums.length;
        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = tmp[i];
        }
    }

    /**
     * 爆力移位
     * 时间O(k*n) 空间O(1)
     *
     * @param nums
     * @param k
     */
    public void rotateD(int[] nums, int k) {
        k = k % nums.length;
        int tmp;
        while (k > 0) {
            for (int i = 0; i < nums.length; i++) {
                int lastIdx = nums.length - 1;
                tmp = nums[i];
                nums[i] = nums[lastIdx];
                nums[lastIdx] = tmp;
            }
            k--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
