package com.ls.leetcode.undone.locked;

/**
 * 340 locked
 */
public class Q340_LongestSubstringWithAtMostKDistinctCharacters {
}






































/**
 *
 LeetCode 第 340 题：给定一个字符串 s ，找出至多包含 k 个不同字符的最长子串 T。



 **示例 1**

 输入: s = "eceba", k = 2

 输出: 3



 解释: 则 T 为 "ece"，所以长度为 3。



 **示例 2**

 输入: s = "aa", k = 1

 输出: 2



 解释: 则 T 为 "aa"，所以长度为 2。

 ##

 ###### 解题思路：暴力法

 思路：找出所有的子串，然后逐一检查是否最多包含 k 个不同的字符。



 实现：用一个哈希表或者哈希集合去统计。



 复杂度：O(n^2)。



 第 8 课讲解了一道 LeetCode 的题目，给定一个字符串，找出无重复字符的最长子串。当时提出了一种比较聪明的办法，能够在 O(n) 的时间里找到答案。上述例题其实是它的另外一种扩展，可以运用相似的策略来进行。



 举例：s = “eceba”，k = 2。



 实现过程如下。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4RGActCuAACWTDVjmS4562.png)

 用两个快慢指针：i 和 j，i 是慢指针，j 是快指针。一开始，两个指针都指向字符串的开头。另外，还需要一个哈希表来统计每个字符出现的个数，同时用来统计不同字符的个数。

 ​     ![img](http://www.lgstatic.com/i/image2/M01/91/51/CgoB5l2I4RGAIz3YAACUEV4BZ10058.png)

 \1. 每次将快指针指向的字符添加到哈希表中，统计它出现的次数。第一个字符是 e，加入到 map 中。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4RKAYjhXAA248QeqTy0883.gif)

 \2. map 的大小为 1，表明到目前为止出现了一个字符。由于 map 的大小还没有超过 k，快指针向前移动一步。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/51/CgoB5l2I4RKAB20KAA0Y_xJpZGc219.gif)

 \3. j 指向的字符是 c，同样统计到 map 中，此时 map 的大小为 2，也没有超过 k，快指针继续移动。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4RKAQZMoAACUJa2fTUI207.png)

 \4. 当前 j 指向的字符是 e，现在 e 出现了 2 次，但是 map 的大小还是 2，表明到目前为止只看到两个不同的字符，即 e 和 c。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/51/CgoB5l2I4ROAXs2mABP3Qvxxwgc796.gif)

 \5. 继续移动 j 指针，出现了新的字符 b，加入到 map 中。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4ROABmg9ACHl6sZ0cUE027.gif)

 \6. 此时 map 的大小为 3，已经超过了 2，于是慢指针开始删除字符，目的是为了控制住 map 的大小不超过 2。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/51/CgoB5l2I4RSAS2V0AAwyhXeaga4800.gif)

 \7. 当把第一个字符删除的时候，在 map 里更新 e 字符的计数，但是整个 map 的大小还是等于 3，继续相同的操作。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4RSAP5kkABVxWpINXZU330.gif)

 \8. c 的个数只有一个，直接把它从 map 里删除掉。现在 map 的大小恢复正常，继续移动快指针。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/51/CgoB5l2I4RWAEvZNAB6BcQzB1BA923.gif)

 \9. 当把 a 添加到 map 里后，map 的大小又超过了 2，于是移动慢指针，把它指向的字符从 map 中删除掉。

 \10. 结束循环。

 ##

 ###### 代码实现

 - 初始化一个哈希表 map，用来统计所出现了的不同字符。
 - 用 max 变量记录最长的子串，其中子串最多包含 k 个不同的字符。
 - 用快慢指针遍历字符串。
 - 将快指针指向的字符加入到 map 中，统计字符出现的次数。
 - 如果发现 map 的大小超过了 k，那么就得开始不断地将慢指针所指向的字符从 map 里清除掉。
 - 首先获取当前慢指针指向的字符。
 - 将它在map中的计数减一。
 - 一旦它的统计次数变成了 0，就可以把它从 map 中删掉了。
 - 接下来，慢指针继续往前走。
 - 当 map 的大小恢复正常了，统计一下当前子串的长度。
 - 最后返回最大的子串长度。

 ```
 int lengthOfLongestSubstringKDistinct(String s, int k) {
 HashMap<Character, Integer> map = new HashMap<>();
 int max = 0;

 for (int i = 0, j = 0; j < s.length(); j++) {
 char cj = s.charAt(j);

 // Step 1. count the character
 map.put(cj, map.getOrDefault(cj, 0) + 1);

 // Step 2. clean up the map if condition doesn't match
 while (map.size() > k) {
 char ci = s.charAt(i);

 map.put(ci, map.get(ci) - 1);

 if (map.get(ci) == 0) {
 map.remove(ci); // that character count has become 0
 }
 i++;
 }

 // Step 3. condition matched, now update the result
 max = Math.max(max, j - i + 1);
 }

 return max;
 }
 ```

 ##

 ###### 复杂度分析

 快慢指针遍历字符串一遍，时间复杂度为 O(n)。

 运用了一个 map来作统计，空间复杂度为 O(n)。

 */