package com.ls.leetcode;

import java.util.List;

/**
 * 336
 *
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 * 示例 1:
 *
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 *
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 *
 */
public class Q336_PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        return null;
    }

}



























/**
 LeetCode  第 336 题，回文对：给定一组唯一的单词， 找出所有不同的索引对 (i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。



 **示例 1**

 输入: ["abcd","dcba","lls","s","sssll"]

 输出: [[0,1],[1,0],[3,2],[2,4]]



 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]



 **示例 2**

 输入: ["bat","tab","cat"]

 输出: [[0,1],[1,0]]



 解释: 可拼接成的回文串为 ["battab","tabbat"]

 ##

 ###### 解题思路：暴力法

 所谓回文，就是正读和反读都一样的字符串，例如"leetteel"。



 检查一个字符串是否是回文，方法如下。

 1. 将给定的字符串翻转，然后跟原字符串对比，看是否相等。但空间复杂度为 O(n) 。
 2. 定义两个指针 i、j，一个指向字符串的头，一个指向字符串的尾巴，同时从两头进行检查，一旦发现不相等就表明不是回文，一直检查到两个指针相遇为止。

 将上述方法 2 用代码实现如下。

 ```
 boolean isPalindrome(String word, int i, int j) {
 while (i < j) {
 if (word.charAt(i++) != word.charAt(j--)) return false;
 }

 return true;

 }
 ```



 代码非常简单，因此不作过多讲解。



 回到例题本身，用暴力法怎么解。



 实现方法：

 - 先找出所有的两两组合

 - 对每种组合进行排查，看看哪种组合可以构成回文。





 时间复杂度：

 - 假设一共有 n 个单词，每个单词的平均长度为 k，两两组合，有 P(n, 2) = n×(n - 1) 种；
 - 对组合的字符串进行回文检查，需要 2k 的时间复杂度；
 - 最终的时间复杂度是：O(n2×k)。

 ##

 ###### 暴力法优化

 暴力法需要检查哪些情况？

 进行回文检查的时候，根据两个字符串的长度不同的程度，假设组合字符串的长度分别为 k1、k2，那么会出现以下三种情况。

 - k1 = k2

 举例：字符串 s1 = "abcd"，字符串 s2 = "dcba"

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4Q2AHWZiAAA5qCvSGLk571.png)

 实现：同时从两边进行检查，看看它们能否构成回文，构成回文的条件就是两个指针相遇，或者同时扫描完两个字符串。



 - k1 > k2

 举例：s1 = "abcdefe"，s2 = "dcba"

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/51/CgoB5l2I4Q2ATkxxAABJBdsgXhA725.png)

 实现：同时从两头进行检查，由于 s2 的长度短，那么 s2 首先会被遍历完毕，此时 s1 还剩下的部分必须要满足回文。



 - k1 < k2

 举例：s1 = "abcd"，s2 = "efedcba"

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4Q2AEHAWAABJfYeVV4k708.png)

 实现：跟第二种情况类似，同时从两头进行检查，由于 s1 的长度短，s1 首先会被遍历完毕，此时 s2 还剩下的部分必须满足回文。



 暴力法如何优化？

 暴力法之所以那么慢，是因为它要对所有情况进行检查。而对于 s1 = "abcd" ，s1 + s2 的组合构成回文的一个条件就是，s2 的最后一个字符必须是 a，如果 k2>=2，它最后两个字符一定是 ba。不满足条件的字符串，不需要理会。



 那么，如何能快速地知道哪些字符串以 a 结尾，哪些字符串以 ba 结尾呢？

 如果反看 s2 ，这个问题相当于，怎么能快速地找出所有以 a 开头或者以 ab 开头的字符串？第 2 节课里介绍的 Trie，正是快速查找以某个字符串开头的数据结构。



 注意：此处要对每个字符串反着构建 Trie。

 ### **Trie**

 一个 Trie 一般都是由很多个 TrieNode 节点构成的，最普通的 TrieNode 节点一般有以下的结构。

 ```
 class TrieNode {
 boolean isEnd;
 HashMap<Character, TrieNode> children;

 TrieNode() {
 isEnd = false;
 children = new HashMap<>();
 }
 }
 ```



 其中，

 - children：数组或者集合，罗列出每个分支当中包含的所有字符
 - isEnd：布尔值，表示该节点是否为某字符串的结尾

 由上可知，Trie 是一种通过字符链接起来的树状结构，且 Trie 一定有一个根节点 root，它的 children 集合包含了所有字符串的开头那个字符。



 举例：给定一系列字符串："ab”, "abc”, “abde", “bcd"，用 Trie 表示的结构如下。



 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/51/CgoB5l2I4Q2AHbOfAABrqvMxUVU130.png)

 其中，

 - 字符作为链接每个节点的边，这些字符也是哈希表里的 key。
 - 这些 key 对应的 value 是节点，绿色的节点表示节点里的 isEnd 布尔值为 true，也就是这个节点表示了一个字符串的结束。
 - 要利用这个 Trie 来查找所有以 b 字符开头的字符串时，可以避开左边三个以 a 字母开头的字符串。

 ### 构建 Tire

 将给定的字符串变成 "ba”，"cba”，“edba"，“dcb"，它们其实就是之前的字符串的翻转。对它们逆序进行 Trie 的构建，也得出了相同的结构。为了能让给定的字符串能组合成回文，再添加两个字符串：”a“，”abc“，同时，把”dcb” 删除，Trie 变成了下面的结构。



 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4Q2AGNocAABw4HvSobY774.png)





 就之前提到的三种情况来分析如何利用 Trie 判断合并两个字符串能否构成回文。基本上是同时遍历字符串和Trie。



 - k1 = k2，即两个字符串的长度相同并且能够构成回文



 举例：s1 = “abc”，s2 = “cba”，s1+s2 = “abccba”。

 1. 从 s1 的第一个字符 a 开始，Trie 里有记录以 a 结尾的字符串，其他那些不是以 a 结尾的字符串不予考虑。
 2. 第二个字符 b，那么从 a 节点开始，看看有没有以 b 作为键值 key 的节点，有，继续。
 3. 第三个字符 c，在 Trie 里，从 b 指向的节点开始，看看有没有以 c 作为键值的节点，有，继续。那些不是以 c 作为键值的分支可以不必考虑。
 4. 字符串遍历结束，在 Trie 里，当前节点是 c 指向的节点。由于该节点恰好表示字符串“cba”的结束，因此，得出两个字符串合在一起可以构成回文串。



 - k1 > k2

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4Q-AFUARAFdBFfNwj4s416.gif)

 举例：s1 = “abc”，s2 = “ba” ，s1+s2 =“abcba“。

 1. 从 s1 的第一个字符 a 开始，能从 Trie 里找到 a，于是继续。
 2. 字符 b，也能找到，并且 b 指向的节点是一个绿色节点，即从 Trie 里找到了字符”ba“。
 3. 要能使 s1 + s2 构成回文，条件就是 s1 里剩下的部分也是回文，此时 s1 剩下的是字符 c，而字符 c 是回文，因此，”abc” 和 “ba”能构成回文串。



 - k1 < k2

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/51/CgoB5l2I4RGAdPAxAGKOo26J4J8733.gif)

 举例：s1 = “a”，s2 = “ba”，s1+s2 =”aba”。



 当 s1 遍历完毕后，Trie 来到了 b 节点，由于 b 也是回文，因此它们两个也能构成回文串。



 对于情况一、三：

 1. s1 字符串一定会被遍历完毕
 2. 遍历完毕后，在 Trie 里所对应的节点

 - 是 s2 中的最后一个字符；

 - 是 s2 的剩余字符

 - - 只要该剩余字符本身是回文，就可以给这个节点添加一个数组，用来记录从该节点向后所有剩余能构成回文的字符串的下标即可。



 对于情况二：

 1. 在 Trie 里，当遇到某个绿色节点，而它表示了某个字符串的结束，只要 s1 剩下的字符能构成回文即可。

 2. 修改 isEnd，用 index 替代，来得到 Trie 里 s2 的下标。

 3. 1. 当 index 为 -1 时，表示不是字符串的结束位置。
 2. 当是字符串的结束时，用 index 来记录输入字符串的下标即可。

 ##

 ###### 代码实现

 ```
 // 修改 TrieNode 结构，用 index 替换 isEnd
 class TrieNode {
 int index;
 List<Integer> palindromes;
 HashMap<Character, TrieNode> children;

 // 添加一个 palindromes 列表，用来记录从该节点往下的能构成回文的所有输入字符串的下标
 TrieNode() {
 index = -1;
 children = new HashMap<>();
 palindromes = new ArrayList<>();
 }
 }
 ```



 主函数代码如下。

 ```
 List<List<Integer>> palindromePairs(String[] words) {
 List<List<Integer>> res = new ArrayList<>(); // 定义一个空的列表，用来记录找到的配对

 TrieNode root = new TrieNode(); // 定义一个 Trie 的根节点 root

 for (int i = 0; i < words.length; i++) {
 addWord(root, words[i], i);
 } // 创建 Trie

 for (int i = 0; i < words.length; i++) {
 search(words, i, root, res);
 }// 利用 Trie，找出所有的配对

 return res;
 }
 ```



 创建 Tire 如下。

 ```
 // 创建 Trie 的时候，从每个字符串的末尾开始遍历
 void addWord(TrieNode root, String word, int index) {
 for (int i = word.length() - 1; i >= 0; i--) {
 char ch = word.charAt(i);

 // 对于每个当前字符，如果它还没有被添加到 children 哈希表里，就创建一个新的节点
 if (!root.children.containsKey(ch)) {
 root.children.put(ch, new TrieNode());
 }
 // 若该字符串从头开始到当前位置能成为回文的话，把这个字符串的下标添加到这个 Trie 节点的回文列表里
 if (isPalindrome(word, 0, i)) {
 root.palindromes.add(index);
 }

 root = root.children.get(ch);
 }

 // 当对该字符串创建完 Trie 之后，将字符串的下标添加到回文列表里，并且将它赋给 index
 root.palindromes.add(index);
 root.index = index;

 }
 ```



 若该字符串从头开始到当前位置能成为回文的话，把这个字符串的下标添加到这个 Trie 节点的回文列表里。例如，如果字符串是“aaaba”，由于我们从后面往前面遍历，当遍历到字符 b 的时候，发现 aaa 是回文，于是更新 b 所指向的那个节点，说该节点往下有一个字符串能构成回文。



 处理查找如下。

 ```
 // 处理查找，从头遍历每个字符串，然后从 Trie 里寻找匹配的字符串
 void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {

 // k1 > k2，且 s1 剩下的字符能构成回文，就把这对组合添加到结果中
 // k1=k2 或 k1<k2，只需要把回文列表里的字符串都和 s1 组合即可
 for (int j = 0; j < words[i].length(); j++) {
 if (root.index >= 0 && root.index != i &&
 isPalindrome(words[i], j, words[i].length() - 1))
 {
 res.add(Arrays.asList(i, root.index));
 }

 root = root.children.get(words[i].charAt(j));
 if (root == null) return;
 }

 for (int j : root.palindromes) {
 if (i == j) continue;
 res.add(Arrays.asList(i, j));
 }
 }
 ```

 ##

 ###### 复杂度分析

 利用 Trie，在创建和查找的过程中，最多会遇到 n×k 个节点，而且会进行回文检查，所以整体的时间复杂度是：O(n×k×k)。



 如果字符串的字符个数是在一定范围之内的，那么这个问题就可以优化成一个近乎于线性问题了。
*/