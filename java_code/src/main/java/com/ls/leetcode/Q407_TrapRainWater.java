package com.ls.leetcode;

/**
 * 407
 *
 * 给你一个 m x n 的矩阵，其中的值均为正整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 *
 *
 *
 * 示例：
 *
 * 给出如下 3x6 的高度图:
 * [
 *   [1,4,3,1,3,2],
 *   [3,2,1,3,2,4],
 *   [2,3,3,2,3,1]
 * ]
 *
 * 返回 4 。
 *
 * 这是下雨前的高度图[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] 的状态。
 *
 * 下雨后，雨水将会被存储在这些方块中。总的接雨水量是4。
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 110
 * 0 <= heightMap[i][j] <= 20000
 *
 *
 */
public class Q407_TrapRainWater {

    public int trapRainWater(int[][] heightMap) {
        return 0;
    }

}

















/**
 LeetCode 第 407 题：给定一个 m x n 的矩阵，其中的值均为正整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。



 说明：m 和 n 都是小于 110 的整数。每一个单位的高度都大于 0 且小于 20000。



 示例：

 给出如下 3x6 的高度图:

 [

 [1,4,3,1,3,2],

 [3,2,1,3,2,4],

 [2,3,3,2,3,1]

 ]



 返回 4。

 ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4ReAeHXlAIqi3-dedKM816.gif)

 1. 下雨前的高度图 [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]。
 2. 下雨后，雨水将会被存储在这些方块中，总的接雨水量是 4。

 ##

 ###### 解题思路一：从内向外

 ### 基本情况

 举例：假如有一个点高度是 0，而它四周的柱子的高度分别是 1，2，3，4。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4ReAKOakAABLZGpg3no849.png)



 解法：中间的那个位置最多能接高度为 1 的水，因为它的四周最矮的柱子是 1。



 ### 扩展情况

 举例：假设现在 0 的周围是如下情况，那么 0 那个位置能接水的高度还是 1 吗？

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4RiAE6xSAABpFZMq9Kc568.png)

 答案应该是 4。



 总结思路：对于每个点，都要不断地往外去寻找那个高过自己的最矮的柱子。假设在平面上，一共有 n 个点，按照这样的算法去计算所有的点的接水高度，复杂度是 O(n^3)。

 ## 解题思路二：从外向内



 为了提高效率，采用“农村包围城市”的策略，从外面往里面进行计算。



 者是因为，每个点都必须找到最外围的高度，否则无法确定它能接多少雨水。既然如此，为什么不从最外面开始呢？即，每一次我们都从外面最矮的开始，慢慢地往里面计算。



 以上述例子说明。

 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/51/CgoB5l2I4RiAR15vAACREQ7D-94461.png)



 ​    ![img](http://www.lgstatic.com/i/image2/M01/91/71/CgotOV2I4RmAE6UaADwhl85qEAo165.gif)

 1. 最外围开始，而最外围的方块无法承载雨水。
 2. 从最外围的高度中选择最矮的柱子，先对它的邻居进行处理。这是因为决定能够接多少雨水并不是由周围最高的柱子决定，而是由最矮的决定。
 3. 高度 4 是最矮的，于是对其做 BFS，它的邻居是高度为 2 的方块。
 4. 由于 2 小于 4，2 的位置能够接纳高度为 2 的雨水，于是这个位置上的高度就变成了 4。
 5. 还是从最矮的点出发，还是 4，它的邻居是 0，于是 0 所能接的雨水高度就是 4。
 6. 还是 4 是最矮，可以更新它周围的点在接了雨水后的高度。



 那么，如何快速知道接下来哪个高度最矮呢？可以用优先队列来提高速度。

 ##

 ###### 代码实现

 代码实现如下，为了配合优先队列的操作，定义一个 Cell 类，用来保存每个方块的坐标以及接了雨水后的高度。

 ```
 class Cell {
 int row;
 int col;
 int height;

 public Cell(int row, int col, int height) {
 this.row = row;
 this.col = col;
 this.height = height;
 }
 }
 ```



 首先对输入进行一些基本的判断。用变量 m 和 n 分别表示输入矩阵的行数和列数。定义一个优先队列或者最小堆，按照每个方块接雨水后的高度排列。初始化优先队列的时候，把矩形的外围四个边上的方块都加入到优先队列中。

 进入 while 循环，开始进行 BFS。每次，从优先队列中取出高度最矮的方块。从四个方向扩散。该方向上的邻居方块能接多少雨水，取决于它是否低于当前的方块了。同时，将新方块加入到优先队列中。

 最后返回承接雨水的总量。



 ```
 public int trapRainWater(int[][] heights) {
 // Sanity check
 if (heights == null || heights.length == 0 || heights[0].length == 0) {
 return 0;
 }

 int m = heights.length;
 int n = heights[0].length;

 PriorityQueue<Cell> queue = new PriorityQueue(new Comparator<Cell>() {
 public int compare(Cell a, Cell b) { return a.height - b.height; }
 });

 boolean[][] visited = new boolean[m][n];

 // Initially, add all the Cells which are on borders to the queue.
 for (int i = 0; i < m; i++) {
 visited[i][0] = true;
 visited[i][n - 1] = true;
 queue.offer(new Cell(i, 0, heights[i][0]));
 queue.offer(new Cell(i, n - 1, heights[i][n - 1]));
 }

 for (int j = 0; j < n; j++) {
 visited[0][j] = true;
 visited[m - 1][j] = true;
 queue.offer(new Cell(0, j, heights[0][j]));
 queue.offer(new Cell(m - 1, j, heights[m - 1][j]));
 }

 // From the borders, pick the shortest cell visited and check its
 // neighbors:
 // If the neighbor is shorter, collect the water it can trap and update
 // its height as its height plus the water trapped.
 // Add all its neighbors to the queue.
 int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

 int total = 0;

 while (!queue.isEmpty()) {
 Cell cell = queue.poll();

 for (int[] dir : dirs) {
 int row = cell.row + dir[0];
 int col = cell.col + dir[1];

 if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col])
 {
 visited[row][col] = true;
 total += Math.max(0, cell.height - heights[row][col]);
 queue.offer(
 new Cell(row, col, Math.max(heights[row][col], cell.height))
 );
 }
 }
 }

 return total;

 }
 ```

 ##

 ###### 复杂度分析

 假设一共有 m 行 n 列，那么一共有 m×n 个方块。对于每个方块，都有可能会进行优先队列的操作，而优先队列的大小为 m + n，加上初始化优先队列的操作时间，因此，整体的时间复杂度为 O(m + n) + O(m×n×log(m + n)) = O(m×n×log(m + n))。



 由上可知，将复杂度下降了一个维度。



 建议：对于这种在 BFS 中运用“农村包围城市”的策略，LeetCode 上还有一道题，第 417 题，太平洋大西洋水流问题，建议大家课后去试试。
*/