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
     * 第一次，抄 leetcode 答案
     */
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val A: IntArray
        val B: IntArray
        var m = 0
        var n = 0
        if (nums1.size < nums2.size) {
            A = nums1
            B = nums2
            m = nums1.size
            n = nums2.size
        } else {
            A = nums2
            B = nums1
            m = nums2.size
            n = nums1.size
        }

        val halfLen = (m + n + 1) / 2   // 为什么是 m+n+1？ 确保划分中位数之后左边比右边多
        var iMin = 0
        var iMax = m
        while (iMin <= iMax) {
            val i = (iMin + iMax) / 2
            val j = halfLen - i

            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1 // i 太小了
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1 // i 太大了
            } else {// i 正好

                val maxLeft = when {
                    i == 0 -> B[j - 1]
                    j == 0 -> A[i - 1]
                    else -> Math.max(A[i - 1], B[j - 1])
                }

                if ((m + n) % 2 == 1) return maxLeft.toDouble()

                val minRight = when {
                    i == m -> B[j]
                    j == n -> A[i]
                    else -> Math.min(B[j], A[i])
                }

                return (maxLeft + minRight) / 2.0
            }
        }

        return 0.0
    }
}

fun main() {
    val solution = Solution()
    println(solution.findMedianSortedArrays(intArrayOf(1, 12, 36), intArrayOf(3, 7, 8, 40)))
}