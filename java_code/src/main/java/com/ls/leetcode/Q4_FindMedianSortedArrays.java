package com.ls.leetcode;

/**
 * 4
 *
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 */
public class Q4_FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0;
    }

}














/*
 *
 * 假设 m+n = L，若 L 为奇数，即两个数组的元素总个数为奇数，那么它们的中位数就是第 int(L / 2) + 1 小的数。例如，数组 { 1, 2, 3 } 的中位数是 2，2 就是第二小的数 2 = int(3/2) + 1。
 *
 *
 *
 * 如果 L 是偶数，那么中位数就是第 int(L/2) 小与第 int(L/2)+1 小的数的和的平均值。例如，数组 {1, 2, 3, 4} 的中位数是 (2 + 3) / 2 = 2.5，其中，2 = int(4/2)，3 = int(4/2) + 1。
 *
 *
 *
 * 因此这个问题就转变为在两个有序数组中寻找第 k 小的数 f(k)，当 L 是奇数的时候，另 k = L/2，结果为 f(k + 1)；而当 L 是偶数的时候，结果为 f(k) + f(k + 1) / 2。
 *
 *
 *
 * 如何从两个排好序的数组里找出第 k 小的数？
 *
 *
 *
 * 假设我们从第一个数组里前面 k1 个数，从第二个数组里取出前面 k2 个数，如下图。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/30/CgoB5l2InpaAZ6lZAAC7LqjZWnU769.png)
 *
 *
 *
 * 假设 k = 5，k1 = 3，k2 = 2，有下面几种情况。
 *
 *
 *
 * \1. 当 a2 = b1 时，可以肯定 a2 和 b1 就是第 5 小的数。
 *
 * 因为当把 a0、a1、a2 以及 b0、b1 按照大小顺序合并在一起的时候， a2 和 b1 一定排在最后面，完全不需要考虑 a0、a1 和 b0 的大小关系。其中一种可能的排列如下。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/50/CgotOV2InpaAAPVqAADiT5r6EoE952.png)
 *
 *
 *
 * \2. 当 a2 < b1 的时候，无法肯定 a2 和 b1 是不是第 5 小的数。举例如下。    ![img](http://www.lgstatic.com/i/image2/M01/91/30/CgoB5l2InpaAdWQTAACcO4jiikI797.png)
 *
 * 而最终第 5 小的数是 a3 5 这个数。因此，在这种情况下，我们不能得出第 5 小的数是哪个。
 *
 *
 *
 * 但是，在这种情况下，至少我们可以肯定的是，我们要找的结果肯定不会在 a0，a1，a2 之间，即不会出现在 nums1 数组的前半段里。为什么呢？很简单，因为如果第 5 小的数是 a0，a1，a2 其中一个的话，意味着 k1+k2 必然大于 5，这就跟我们的假设不符了。
 *
 *
 *
 * 那么结果会不会在 nums2 的后半段呢？不可能，加入第 5 小的数在 nums2 的后半段，那么意味着，这个数要大于 b1（即  7），也会大于 a2（即 3），但是 k1 + k2 已经等于 5了，所以就和假设冲突了。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/50/CgotOV2InpiANLV4AGB7zVnr6UQ076.gif)
 *
 *  在这样的情况下，我们可以把搜索的范围缩小，从 nums1 的后半段以及 nums2 的前半段中继续寻找。
 *
 *
 *
 * 1. 当 a2 > b1 的时候，无法肯定 a2 和 b1 是不是第 5 小的数。举例如下。
 *
 * nums1[] = {5, 6, **7**, 8, 9}
 *
 * nums2[] = {1, **2**, 3, 4}
 *
 *
 *
 * a2 = 7，b1 = 2
 *
 *
 *
 * 而最终第 5 小的数是 a0 5 这个数。因此，在这种情况下，我们也不能得出第 5 小的数是哪个。
 *
 *
 *
 * 但是，在这种情况下，至少我们可以肯定的是，我们要找的结果肯定不会是 b0，或者
 *
 * nums2 数组的前半段里。为什么呢？因为如果第 5 小的数是 b0 的话，意味着 k1+k2 必然大
 *
 * 于 5，这也跟我们的假设不符了。同样的，结果也不可能在 nums1 的后半段里。
 *
 *
 *
 * 在这样的情况下，我们可以把搜索的范围缩小，从 nums2 的后半段以及 nums1 中继续寻找。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/30/CgoB5l2InpmAHlttACt9vWodiQU791.gif)
 *
 *
 *
 * **代码实现
 * **
 *
 * ```
 * double findMedianSortedArrays(int nums1[], int nums2[]) {
 *     int m = nums1.length;
 *     int n = nums2.length;
 *
 *     int k = (m + n) / 2;
 *
 *     if ((m + n) % 2 == 1) {
 *         return findKth(nums1, 0, m - 1, nums2, 0, n - 1, k + 1);
 *       } else {
 *         return (
 *             findKth(nums1, 0, m - 1, nums2, 0, n - 1, k) +
 *             findKth(nums1, 0, m - 1, nums2, 0, n - 1, k + 1)
 *         ) / 2.0;
 *     }
 * }
 *
 * double findKth(int[] nums1, int l1, int h1, int[] nums2, int l2, int h2, int k) {
 *     int m = h1 - l1 + 1;
 *     int n = h2 - l2 + 1;
 *
 *     if (m > n) {
 *         return findKth(nums2, l2, h2, nums1, l1, h1, k);
 *     }
 *
 *     if (m == 0) {
 *         return nums2[l2 + k - 1];
 *     }
 *
 *     if (k == 1) {
 *         return Math.min(nums1[l1], nums2[l2]);
 *     }
 *
 *     int na = Math.min(k/2, m);
 *     int nb = k - na;
 *     int va = nums1[l1 + na - 1];
 *     int vb = nums2[l2 + nb - 1];
 *
 *     if (va == vb) {
 *         return va;
 *     } else if (va < vb) {
 *         return findKth(nums1, l1 + na, h1, nums2, l2, l2 + nb - 1, k - na);
 *     } else {
 *        return findKth(nums1, l1, l1 + na - 1, nums2, l2 + nb, h2, k - nb);
 *     }
 *
 * }
 * ```
 *
 * 1. 主体函数其实就是根据两个字符串长度的总和进行判断，看看如何调用递归函数以及返回结果。当总长度是奇数的时候，返回正中间的那个数；当总长度是偶数的时候，返回中间两个数的平均值。
 * 2. 进入 findkth 函数，这个函数的目的是寻找第 k 小的元素。
 * 3. 如果 nums1 数组的长度大于 nums2 数组的长度，我们将它们互换一下，这样可以让程序结束得快一些。
 * 4. 当 nums1 的长度为 0 时，直接返回 nums2 数组里第 k 小的数。当 k 等于 1 的时候，返回两个数组中的最小值。
 * 5. 接下来，分别选两个数组的中间数。
 * 6. 比较一下两者的大小，如果相等，表明我们找到了中位数，返回它；如果不等的话，我们进行剪枝处理。
 *
 * ### **算法分析**
 *
 * 由于要求中位数，即 k = (m+n) / 2，k1 = k / 2，k2 = k / 2，每次都能将一半的数排除，即问题的规模减小一半，因此，算法复杂度就类似二分搜索，复杂度就是 log(k)，即 O(log((m+n) / 2))。
 *
 *
 *
 * ##
 *
 * ###### 扩展一
 *
 * 例题：如果给定的两个数组是没有经过排序处理的，应该怎么找出中位数呢？
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/50/CgotOV2InpmAMCH-AACt9UBVjdM117.png)
 *
 *
 *
 * ###
 *
 * ###### 解法 1：直观方法
 *
 * 先将两个数组合并在一起，然后排序，再选出中位数。时间复杂度是：O((m+n)× log(m+n))。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/30/CgoB5l2InpqADa6YACUXrW-nt7c325.gif)
 *
 *
 *
 * ###
 *
 * ###### 解法 2：快速选择算法
 *
 * 快速选择算法，可以在 O(n) 的时间内从长度为 n 的没有排序的数组中取出第 k 小的数，运用了快速排序的思想。
 *
 *
 *
 * 假如将 nums1[] 与 nums2[] 数组组合成一个数组变成 nums[]：{2, 5, 3, 1, 6, 8, 9, 7, 4}，那么如何在这个没有排好序的数组中找到第 k 小的数呢？
 *
 *
 *
 * \1. 随机地从数组中选择一个数作为基准值，比如 7。一般而言，随机地选择基准值可以避免最坏的情况出现。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/50/CgotOV2InpuAQV-WAB7LzfFC6cc207.gif)
 *
 * \2. 将数组排列成两个部分，以基准值作为分界点，左边的数都小于基准值，右边的都大于基准值。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/50/CgotOV2InpyAWKEsACyLx0hVqIg915.gif)
 *
 * \3. 判断一下基准值所在位置 p：
 *
 * 1. 1. 如果 p 刚好等于 k，那么基准值就是所求数，直接返回。
 *    2. 如果 k < p，即基准值太大，搜索的范围应该缩小到基准值的左边。
 *    3. 如果 k > p，即基准值太小，搜索的范围应该缩小到基准值的右边。此时需要找的应该是第 k - p 小的数，因为前 p 个数被淘汰。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/30/CgoB5l2Inp-ANdNWAFfawadYn4g058.gif)
 *
 *
 *
 * \4. 重复第一步，直到基准值的位置 p 刚好就是要找的 k。
 *
 *
 *
 *
 *
 * **代码实现**
 *
 * ```
 * public int findKthLargest(int[] nums, int k) {
 *     return quickSelect(nums, 0, nums.length - 1, k);
 * }
 *
 * // 随机取一个基准值，这里取最后一个数作为基准值
 * int quickSelect(int[] nums, int low, int high, int k) {
 *     int pivot = low;
 *
 *     // 比基准值小的数放左边，把比基准值大的数放右边
 *     for (int j = low; j < high; j++) {
 *       if (nums[j] <= nums[high]) {
 *           swap(nums, pivot++, j);
 *       }
 *     }
 *     swap(nums, pivot, high);
 *
 *     // 判断基准值的位置是不是第 k 大的元素
 *     int count = high - pivot + 1;
 *     // 如果是，就返回结果。
 *     if (count == k) return nums[pivot];
 *     // 如果发现基准值小了，继续往右边搜索
 *     if (count > k) return quickSelect(nums, pivot + 1, high, k);
 *     // 如果发现基准值大了，就往左边搜索
 *     return quickSelect(nums, low, pivot - 1, k - count);
 *
 * }
 * ```
 *
 *
 *
 * **时间复杂度**
 *
 * **
 * **
 *
 * 时间复杂度为什么是 O(n)。分析如下。
 *
 *
 *
 * 为了方便推算，假设每次都选择中间的那个数作为基准值。
 *
 *
 *
 * 1. 设函数的时间执行函数为 T(n)，第一次运行的时候，把基准值和所有的 n 个元素进行比较，然后将输入规模减半并递归，所以 T(n) = T(n/2) + n。
 * 2. 当规模减半后，新的基准值只和 n/2 个元素进行比较，因此 T(n/2) = T(n/4) + n/2。
 * 3. 以此类推：
 *
 * T(n/4) = T(n/8) + n/4
 *
 * …
 *
 * T(2) = T(1) + 2
 *
 * T(1) = 1
 *
 *
 *
 * 将上面的公式逐个代入后得到 T(n) = 1 + 2 + … + n/8 + n/4 + n/2 + n = 2×n，所以  O(T(n)) = O(n)。
 *
 *
 *
 * **空间复杂度**
 *
 * **
 * **
 *
 * 如果不考虑递归对栈的开销，那么算法并没有使用额外的空间，swap 操作都是直接在数组里完成，因此空间复杂度为 O(1)。
 *
 * ###
 *
 * ###### 解法 3：数组“组合”
 *
 * 把这两个数组“虚拟”地组合在一起，即它们是分开的，但是在访问它们的元素时，把它们看成是一个数组。那么就能运用快速选择的算法。
 *
 *
 *
 * **代码实现**
 *
 * ```
 * double findMedianArrays(int[] nums1, int[] nums2) {
 *     int m = nums1.length;
 *     int n = nums2.length;
 *
 *     int k = (m + n) / 2;
 *
 *     return (m + n) % 2 == 1 ?
 *         findKthLargest(nums1, nums2, k + 1) :
 *         (findKthLargest(nums1, nums2, k) + findKthLargest(nums1, nums2, k + 1)) / 2.0;
 * }
 *
 * double findKthLargest(int[] nums1, int[] nums2, int k) {
 *     return quickSelect(nums1, nums2, 0, nums1.length + nums2.length - 1, k);
 * }
 *
 * double quickSelect(int[] nums1, int[] nums2, int low, int high, int k) {
 *     int pivot = low;
 *
 *     // use quick sort's idea
 *     // put nums that are <= pivot to the left
 *     // put nums that are  > pivot to the right
 *     for (int j = low; j < high; j++) {
 *         if (getNum(nums1, nums2, j) <= getNum(nums1, nums2, high)) {
 *             swap(nums1, nums2, pivot++, j);
 *         }
 *     }
 *     swap(nums1, nums2, pivot, high);
 *
 *     // count the nums that are > pivot from high
 *     int count = high - pivot + 1;
 *     // pivot is the one!
 *     if (count == k) return getNum(nums1, nums2, pivot);
 *     // pivot is too small, so it must be on the right
 *     if (count > k) return quickSelect(nums1, nums2, pivot + 1, high, k);
 *     // pivot is too big, so it must be on the left
 *     return quickSelect(nums1, nums2, low, pivot - 1, k - count);
 * }
 *
 * int getNum(int[] nums1, int[] nums2, int index) {
 *     return (index < nums1.length) ? nums1[index] : nums2[index - nums1.length];
 * }
 *
 * void swap(int[] nums1, int[] nums2, int i, int j) {
 *     int m = nums1.length;
 *
 *     if (i < m && j < m) {
 *         swap(nums1, i, j);
 *     } else if (i >= m && j >= m) {
 *         swap(nums2, i - m, j - m);
 *     } else if (i < m && j >= m) {
 *         int temp = nums1[i];
 *         nums1[i] = nums2[j - m];
 *         nums2[j - m] = temp;
 *     }
 * }
 *
 * void swap(int[] nums, int i, int j) {
 *     int temp = nums[i];
 *     nums[i] = nums[j];
 *     nums[j] = temp;
 * }
 * ```
 *
 *
 *
 * 因为这道题的解法与之前讲的快速选择算法非常类似，差别在于将两个数组合在一起考虑。因此大家可以自己分析一下代码。
 *
 *
 *
 * 时间复杂度是 O(m+n)，空间复杂度 O(1)。
 *
 * ##
 *
 * ###### 扩展二
 *
 * 例题：有一万个服务器，每个服务器上存储了十亿个没有排好序的数，现在要找所有数当中的中位数，怎么找？
 *
 *
 *
 * 对于分布式地大数据处理，应当考虑两个方面的限制：
 *
 * 1. 每台服务器进行算法计算的复杂度限制，包括时间和空间复杂度
 * 2. 服务器与服务器之间进行通信时的网络带宽限制
 *
 * ###
 *
 * ##### 限制 1：空间复杂度
 *
 * 假设存储的数都是 32 位整型，即 4 个字节，那么 10 亿个数需占用 40 亿字节，大约 4GB
 *
 * - 归并排序至少得需要 4GB 的内存
 * - 快速排序的空间复杂度为 log(n)，即大约 30 次堆栈压入
 *
 *
 *
 * 用非递归的方法去实现快速排序，代码如下。
 *
 * ```
 * // 每次只需将数组中的某个起始点和终点，即一个范围，压入堆栈中，压入 30 个范围的大小约为 30×2×4=240 字节
 * class Range {
 *     public int low;
 *     public int high;
 *
 *     public Range(int low, int high) {
 *         this.low = low;
 *         this.high = high;
 *     }
 * }
 *
 * // 不使用递归写法，压入堆栈的还包括程序中的其他变量等，假设需要 100 字节，总共需要 30×100=3K 字节
 * void quickSort(int[] nums) {
 *     Stack<Range> stack = new Stack<>();
 *
 *     Range range = new Range(0, nums.length - 1);
 *     stack.push(range);
 *
 *     while (!stack.isEmpty()) {
 *         range = stack.pop();
 *
 *         int pivot = partition(nums, range.low, range.high);
 *
 *         if (pivot - 1 > range.low) {
 *             stack.push(new Range(range.low, pivot - 1));
 *         }
 *
 *         if (pivot + 1 < range.high) {
 *             stack.push(new Range(pivot + 1, range.high));
 *         }
 *     }
 * }
 *
 * // 快速排序对内存的开销非常小
 * int partition(int[] nums, int low, int high) {
 *     int pivot = randRange(low, high), i = low;
 *     swap(nums, pivot, high);
 *
 *     for (int j = low; j < high; j++) {
 *         if (nums[j] <= nums[high]) {
 *             swap(nums, i++, j);
 *         }
 *     }
 *
 *     swap(nums, i, high);
 *
 *     return i;
 * }
 * ```
 *
 * 如上，利用一个栈 stack 来记录每次进行快速排序时的范围。一旦发现基准值左边还有未处理完的数，就将左边的范围区间压入到栈里；如果发现基准值右边还有未处理完的数，就将右边的范围区间压入到栈里。其中，处理基准值的 partition 函数非常重要，之前已经介绍过。
 *
 * ###
 *
 * ###### 限制 2：网络带宽
 *
 * 在实际应用中，这是最重要的考量因素，很多大型的云服务器都是按照流量来进行收费，如何有效地限制流量，避免过多的服务器之间的通信，就是要考量的重点，并且，实际上它与算法的时间复杂度有很大的关系。
 *
 *
 *
 * **解决方案**
 *
 * **
 * **
 *
 * 借助扩展一的思路。
 *
 *
 *
 * \1. 从 1万 个服务器中选择一个作为主机（master server）。这台主机将扮演主导快速选择算法的角色。![img](http://www.lgstatic.com/i/image2/M01/91/30/CgoB5l2Inp-ADWduAACy1HlNYdU966.png)
 *
 * \2. 在主机上随机选择一个基准值，然后广播到其他各个服务器上。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/50/CgotOV2InqCADiJBADbDhpQ_g34352.gif)
 *
 * \3. 每台服务器都必须记录下最后小于、等于或大于基准值数字的数量：less count，equal count，greater count。
 *
 * ​    ![img](http://www.lgstatic.com/i/image2/M01/91/50/CgotOV2InqOAW68SAEJNyDb5D5o855.gif)
 *
 *
 *
 * \4. 每台服务器将 less count，equal count 以及 greater count 发送回主机。
 *
 *
 *
 * \5. 主机统计所有的 less count，equal count 以及 greater count，得出所有比基准值小的数的总和 total less count，等于基准值的总和 total equal count，以及大于基准值的总和 total greater count。进行如下判断。
 *
 * 1. 1. 如果 total less count >= total count / 2，表明基准值太大。
 *    2. 如果total less count + total equal count >= total count / 2，表明基准值即为所求结果。
 *    3. 否则，total less count + total equal count < total count / 2 表明基准值太小。
 *
 *
 *
 * \6. 后面两种情况，主机会把新的基准值广播给各个服务器，服务器根据新的基准值的大小判断往左半边或者右半边继续进行快速选择。直到最后找到中位数。
 *
 *
 *
 * **时间复杂度**
 *
 * **
 * **
 *
 * 整体的时间复杂度是 O(nlog(n))，主机和各个其他服务器之间的通信总共也需要 nlog(n)次，每次通信需要传递一个基准值以及三个计数值。
 *
 *
 *
 * 如果用一些组播网络（Multicast Network)，可以有效地节省更多的带宽。
 */