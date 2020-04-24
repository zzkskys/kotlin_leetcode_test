package cn.zzk.demo

/**
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 */
private class Solution {
    /**
     * 设 : 数组 nums1 长度为 m ，数组 nums2 长度为 n
     * 查找中位数则可以理解为在 nums1 和 nums2 中分别找出第 (m+n+1) /2 大的元素 ,第 (m+n+2)/2 大的元素并求平均值
     *
     * 那么这个方法则简化为如何在 nums1 有序数组和 nums2 有序数组查找第 k 大的元素
     */
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val m = nums1.size
        val n = nums2.size
        val left = (m + n + 1) / 2
        val right = (m + n + 2) / 2

        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0
    }

    /**
     * 如何在 nums1 ,nums2 中查找第 k 大的元素？
     * 先从 nums1,nums2 中找各自的第 k/2 个元素，若 num1 中的第 k/2 个元素比 nums2 大，说明 nums2 中前 k/2 个元素是最小的。
     * 在 nums1,nums2 中查找第 k 大的元素 变成了在索引为 [0,m) 的 nums1 中 和 (k/2,n)的 num2 中找 第 k-k/2 个大的元素。
     * 递归该方法，直至 nums1或 nums2 中任意数组的元素全部被抛弃。
     *
     * 一般的思路 ： 
     * i 表示丢弃  数组num1 [0,i-1] 个数已经丢弃,k 从 第 i 个开始数
     * j 表示丢弃 第 j 个前面的数
     */
    fun findKth(nums1: IntArray, i: Int, nums2: IntArray, j: Int, k: Int): Int {
        if (k == 1) {
            return Math.min(nums1[i], nums2[j])
        }
        if (i >= nums1.size) return nums2[j + k - 1]
        if (j >= nums2.size) return nums1[i + k - 1]
        val nums1_half_k = i + k / 2 - 1
        val nums2_half_k = j + k / 2 - 1

        val nums1_half_k_value = if (nums1_half_k < nums1.size) nums1[nums1_half_k] else Int.MAX_VALUE
        val nums2_half_k_value = if (nums2_half_k < nums2.size) nums2[nums2_half_k] else Int.MAX_VALUE

        return if (nums1_half_k_value < nums2_half_k_value) {
            findKth(nums1, i + k / 2, nums2, j, k - k / 2)
        } else {
            findKth(nums1, i, nums2, j + k / 2, k - k / 2)
        }
    }

}

/**
 * 参考了他人的答案而写的方法，还好思路理解的比较清晰。
 * 核心的思路是二分法和递归。
 * 重点复习了递归的重要条件:
 *  1. 重复执行某一步骤
 *  2. 有边界判断，若处于边界状态则停止重复执行
 */
fun main() {
    val solution = Solution()
    println(solution.findMedianSortedArrays(intArrayOf(1, 3, 5), intArrayOf(2, 4, 6, 8, 10)))
}
