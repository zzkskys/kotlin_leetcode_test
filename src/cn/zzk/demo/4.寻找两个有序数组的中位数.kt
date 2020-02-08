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
     * 第一次，抄 leetcode 答案. 看完评论才发现答案的错误之处--虽然运行正确但理解上带来困扰
     *
     *答案错误之处：
     *if (i < iMax && B[j-1] > A[i]){
     *  iMin = i + 1; // i is too small
     *}
     *else if (i > iMin && A[i-1] > B[j]) {
     *  iMax = i - 1; // i is too big
     *}
     * 准确的来讲是:
     * if (i < m && B[j - 1] > A[i]) {
     *   iLeft = i + 1
     * } else if (i > 0 && A[i - 1] > B[j]) {
     *   iRight = i - 1
     *}
     *  i < m 的判断含义 ：数组A 已在左半边，不可调整
     *  i >0 的含义 ： 数组 B 已全部在右半边，不可调整.
     *
     *  思路：
     *  中位数的含义是将集合划分为两个等长的子集，一个集合中的任意元素大于另一集合中的任意元素。
     *  因此可以将 A数组，B 数组划分2个部分
     *      left_part           |    right_part
     *    A[0],A[1],...,A[i-1]  | A[i],A[i+1],...,A[m]
     *    B[0],B[1],...,B[j-1]  | B[j],B[j+1],...,B[n]
     *
     * 满足以下条件：
     *    两个集合长度相等 -> i+j = (m+n)/2
     *    A[i-1] < B[j]
     *    B[j-1] < A[i]
     *
     *
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

        val halfLen = (m + n + 1) / 2   // m+n+1? 若数组是奇数个，确定是左半边多
        var iLeft = 0
        var iRight = m
        while (iLeft <= iRight) {
            val i = (iLeft + iRight) / 2
            val j = halfLen - i

            if (i < m && B[j - 1] > A[i]) { //为什么 i < m ? 因为 i 是从 m/2 累加，若 i == m 则说明 数组A 都是左半边，不能调整
                iLeft = i + 1 // i 太小了
            } else if (i > 0 && A[i - 1] > B[j]) { // i >0 ? 因为 m/2 减少 ,若 i == 0 说明数组B 都在右半边，不能调整
                iRight = i - 1 // i 太大了
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
    println(solution.findMedianSortedArrays(intArrayOf(2), intArrayOf(1, 3)))
}
