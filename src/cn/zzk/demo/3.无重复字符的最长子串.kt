package cn.zzk.demo

/**
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
private class Solution1 {
    /*
        反思: 这是我第2次写这道题，主要的问题是卡在了 str.substring() 函数的取子串是左闭又开，而不是左闭右闭。
        因此取子串总是失败...... 之后 dubug 才发现了该错误
     */
    fun lengthOfLongestSubstring(s: String): Int {
        var tempSubChars = ""
        var length = 0
        s.forEachIndexed { index, c ->
            val indexOf = tempSubChars.indexOf(c)
            if (indexOf == -1) {
                tempSubChars += c
                length = if (length > tempSubChars.length) length else tempSubChars.length
            } else {
                tempSubChars = if (indexOf == tempSubChars.lastIndex) {
                    c.toString()
                } else{
                    tempSubChars.substring(indexOf + 1, tempSubChars.length)+c
                }
            }
        }
        return length
    }
}

fun main() {
    val solution = Solution1()
    println(solution.lengthOfLongestSubstring("abcabcbb"))
    println(solution.lengthOfLongestSubstring("bbbbb"))
    println(solution.lengthOfLongestSubstring("pwwkew"))
    println(solution.lengthOfLongestSubstring("aab"))
    println(solution.lengthOfLongestSubstring("dvdf"))
}