package com.ls.leetcode.undone.locked;

/**
 * 269 locked
 */
public class Q269_AlienDictionary {
}





































/**
 * LeetCode  第 269 题，火星字典：现有一种使用字母的全新语言，这门语言的字母顺序与英语顺序不同。假设，您并不知道其中字母之间的先后顺序。但是，会收到词典中获得一个不为空的单词列表。因为是从词典中获得的，所以该单词列表内的单词已经按这门新语言的字母顺序进行了排序。您需要根据这个输入的列表，还原出此语言中已知的字母顺序。
 *
 *
 *
 * **示例 1**
 *
 * 输入:
 *
 * [ "wrt", "wrf","er","ett", "rftt"]
 *
 *
 *
 * 输出: "wertf"
 *
 *
 *
 * **示例 2**
 *
 * 输入:
 *
 * [ "z", "x"]
 *
 *
 *
 * 输出: "zx"
 *
 *
 *
 * **示例 3**
 *
 * 输入:
 *
 * [ "z", "x","z"]
 *
 *
 *
 * 输出: ""
 *
 *
 *
 * 解释: 此顺序是非法的，因此返回 ""。
 *
 * ## 解题思路
 *
 *
 *
 * 首先，确定字符串排序方法。
 *
 *
 *
 * 理解题意，关键是搞清楚给定的输入字符串是怎么排序的？
 *
 *
 *
 * 举例：假如我们有这些单词 bar，bat，algorithm，cook，cog，那么按照字符顺序，应该怎么排呢？
 *
 * 正确的排序应该是：algorithm bat bar cog cook。
 *
 *
 *
 * 解法：
 *
 * - 逐位地比较两个相邻的字符串
 * - 第一个字母出现的顺序越早，排位越靠前
 * - 第一个字母相同时，比较第二字母，以此类推
 *
 * 注意：两个字符串某个相同的位置出现了不同，就立即能得出它们的顺序，无需继续比较字符串剩余字母。
 *
 * ### **求解示例 1**
 *
 * 输入是：
 *
 * ```
 * wrt
 * wrf
 * er
 * ett
 * rftt
 * ```
 *
 * **
 * **
 *
 * 解法：
 *
 * \1. 比较以 w 开头的字符串，它们是 wrt 和 wrf，之所以 wrt 会排在 wrf 之前，是因为 t 比 f 在火星字典里出现的顺序要早。此时将这两个字母的关系表达为 t -> f。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/1B/CgotOV2Ieo2AUKzRAB96MtWjhn0834.gif)
 *
 * \2. 比较 wrf 和 er，第一个字母开始不同，因此，得出 w 排在 e 之前，记为 w -> e。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/90/FB/CgoB5l2Ieo2AHp2gABsjRhdqs-o833.gif)
 *
 * \3. 比较 er 和 ett，从第二个字母开始不一样，因此，得出 r 排在 t 之前，记为 r -> t。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/1B/CgotOV2Ieo6AGzbmABkoJlXVd6Q543.gif)
 *
 * \4. 比较 ett 和 rftt，从第一个字母开始不一样，得出 e 排在 r 之前，记为 e -> r。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/90/FB/CgoB5l2Ieo-AQsABACwHvWpfLUM265.gif)
 *
 * 梳理上述关系，得 t -> f，w -> e，r -> t，e -> r
 *
 *
 *
 * 拓扑排序得到正确顺序：将每个字母看成是图里的顶点，它们之间的关系就好比是连接顶点与顶点的变，而且是有向边，所以这个图是一个有向图。最后对这个有向图进行拓扑排序，就可以得出最终的结果。 ![img](http://www.lgstatic.com/i/image2/M01/91/1B/CgotOV2Ieo-AL2rOAAA-SDWivvo697.png)
 *
 *  **代码实现**
 *
 * **
 * **
 *
 * 包括两大步骤，第一步是根据输入构建一个有向图；第二步是对这个有向图进行拓扑排序。
 *
 * ```
 * // 基本情况处理，比如输入为空，或者输入的字符串只有一个
 * String alienOrder(String[] words) {
 *     if (words == null || words.length == 0)
 *         return null;
 *     if (words.length == 1) {
 *         return words[0];
 *     }
 *
 *     // 构建有向图：定义一个邻接链表 adjList，也可以用邻接矩阵
 *     Map<Character, List<Character>> adjList = new HashMap<>();
 *
 *     for (int i = 0; i < words.length - 1; i++) {
 *         String w1 = words[i], w2 = words[i + 1];
 *         int n1 = w1.length(), n2 = w2.length();
 *
 *         boolean found = false;
 *
 *         for (int j = 0; j < Math.max(w1.length(), w2.length()); j++) {
 *             Character c1 = j < n1 ? w1.charAt(j) : null;
 *             Character c2 = j < n2 ? w2.charAt(j) : null;
 *
 *             if (c1 != null && !adjList.containsKey(c1)) {
 *                 adjList.put(c1, new ArrayList<Character>());
 *             }
 *
 *             if (c2 != null && !adjList.containsKey(c2)) {
 *                 adjList.put(c2, new ArrayList<Character>());
 *             }
 *
 *             if (c1 != null && c2 != null && c1 != c2 && !found) {
 *                 adjList.get(c1).add(c2);
 *                 found = true;
 *             }
 *         }
 *     }
 *
 *     Set<Character> visited = new HashSet<>();
 *     Set<Character> loop = new HashSet<>();
 *     Stack<Character> stack = new Stack<Character>();
 *
 *     for (Character key : adjList.keySet()) {
 *         if (!visited.contains(key)) {
 *             if (!topologicalSort(adjList, key, visited, loop, stack)) {
 *                 return "";
 *             }
 *         }
 *     }
 *
 *     StringBuilder sb = new StringBuilder();
 *
 *     while (!stack.isEmpty()) {
 *         sb.append(stack.pop());
 *     }
 *
 *     return sb.toString（）；
 *
 * }
 * // 将当前节点 u 加入到 visited 集合以及 loop 集合中。
 * boolean topologicalSort(Map<Character, List<Character>> adjList, char u,
 *                         Set<Character> visited, Set<Character> loop, Stack<Character> stack) {
 *     visited.add(u);
 *     loop.add(u);
 *
 *     if (adjList.containsKey(u)) {
 *         for (int i = 0; i < adjList.get(u).size(); i++) {
 *             char v = adjList.get(u).get(i);
 *
 *             if (loop.contains(v))
 *                 return false;
 *
 *             if (!visited.contains(v)) {
 *                 if (!topologicalSort(adjList, v, visited, loop, stack)) {
 *                     return false;
 *                 }
 *             }
 *         }
 *     }
 *
 *
 *     loop.remove(u);
 *
 *     stack.push(u);
 *
 *     return true;
 * }
 * ```
 *
 *
 *
 * 用深度优先的方法进行拓扑排序，一定要借用下面三者。
 *
 * 1. visited 集合，用来记录哪些顶点已经被访问过。
 * 2. stack 堆栈，从某个顶点出发，访问完了所有其他顶点，最后才把当前的这个顶点加入到堆栈里。即，若要该点加入到 stack 里，必须先把跟它有联系的顶点都处理完。举例说明，如果我要学习课程 A，得先把课程 B，C 以及 D 都看完。
 * 3. loop 集合，为了有效防止有向图里出现环的情况。举例说明如下。
 *
 * 假如我们有这么一个有向图。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/90/FB/CgoB5l2Ieo-AfXiGAB41ZU6ORu4144.gif)
 *
 *
 *
 * - 从 A 开始对这个图进行深度优先的遍历，那么当访问到顶点 D 的时候，visited 集合以及 loop 集合都是 {A, B, C, D}。
 * - 当从 D 继续遍历到 B 的时候，发现 B 已经在 loop 集合里。
 * - 因此得出结论，在这一轮遍历中，出现了环。
 *
 *
 *
 * 为什么不能单单用 visited 集合来帮助判断呢？例如下面情况。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/1B/CgotOV2IepCADkCaAAA9qnc1K_8002.png)
 *
 *
 *
 *
 *
 * - 从 D 访问 B 的时候，如果判断因为 B 已经被访问过了，于是得出这里就有一个环，显然判断错误。
 * - 当每一轮访问结束后，都必须要把 loop 集合清空，才能把其他顶点也加入到堆栈里。
 * - 否则，当 D 遇到 B 的时候，也会认为这里有环出现，而提前终止程序，无法将它加入到堆栈中。
 */