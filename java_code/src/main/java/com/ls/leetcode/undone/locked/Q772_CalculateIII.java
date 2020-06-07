package com.ls.leetcode.undone.locked;

/**
 * 772 locked
 */
public class Q772_CalculateIII {

    public int calculate(String s) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println();
    }

}






























/**
 * LeetCode 第 772 题，基本计算器：实现一个基本的计算器来计算简单的表达式字符串。
 *
 *
 *
 * 说明：
 *
 * - 表达式字符串可以包含左括号 ( 和右括号 )，加号 + 和减号 -，非负整数和空格。
 * - 表达式字符串只包含非负整数， +  -  *  / 操作符，左括号 ( ，右括号 ) 和空格。整数除法需要向下截断。
 *
 *
 *
 * **示例 1**：
 *
 * "1 + 1" = 2
 *
 * " 6-4 / 2 " = 4
 *
 * "2×(5+5×2)/3+(6/2+8)" = 21
 *
 * "(2+6×3+5- (3×14/7+2)×5)+3" = -12
 *
 * ##
 *
 * ###### 解题思路一：只有加号
 *
 * 例题：若表达式里只有数字和加法符号，没有减法，也没有空格，并且输入的表达式一定合法，那么应该如何处理？例如：1+2+10。
 *
 *
 *
 * 解法：一旦遇到了数字就不断地相加。
 *
 * ### 代码实现
 *
 * ```
 * // 转换，将字符串的字符放入到一个优先队列中
 * int calculate(String s) {
 *     Queue<Character> queue = new LinkedList<>();
 *     for (char c : s.toCharArray()) {
 *         queue.offer(c);
 *     }
 *
 *     // 定义两个变量，num 用来表示当前的数字，sum 用来记录最后的和
 *     int num = 0, sum = 0;
 *
 *     // 遍历优先队列，从队列中一个一个取出字符
 *     while (!queue.isEmpty()) {
 *         char c = queue.poll();
 *
 *         // 如果当前字符是数字，那么就更新 num 变量，如果遇到了加号，就把当前的 num 加入到 sum 里，num 清零
 *         if (Character.isDigit(c)) {
 *             num = 10 * num + c - '0';
 *         } else {
 *             sum += num;
 *             num = 0;
 *         }
 *     }
 *
 *     sum += num; // 最后没有加号，再加一次
 *
 *     return sum;
 *
 * }
 * ```
 *
 *
 *
 * ### 代码扩展一
 *
 * 如上，在返回 sum 之前，我们还进行了一次额外的操作：sum += num，就是为了要处理结束时的特殊情况。若在表达式的最后添加上一个”+”，也能实现同样的效果，代码实现如下。
 *
 * ```
 * int calculate(String s) {
 *     Queue<Character> queue = new LinkedList<>();
 *     for (char c : s.toCharArray()) {
 *         queue.offer(c);
 *     }
 *     queue.add('+'); // 在末尾添加一个加号
 *
 *     while (!queue.isEmpty()) {
 *         ...
 *     }
 *
 *     return sum;
 * }
 * ```
 *
 * 如上，在优先队列的最后添加一个加号。
 *
 * ### 代码扩展二
 *
 * 若输入的时候允许空格，如何处理？代码实现如下。
 *
 * ```
 * int calculate(String s) {
 *     ...
 *     for (char c : s.toCharArray()) {
 *         if (c != ' ') queue.offer(c);
 *     }
 *     ...
 * }
 * ```
 *
 * 如上，在添加到优先队列的时候，过滤到那些空格就好了。
 *
 * ##
 *
 * ###### 解题思路二：引入减号
 *
 * 例题：若表达式支持减法，应该怎么处理？例如：1 + 2 - 10。
 *
 *
 *
 * 解法 1：借助两个 stack，一个 stack 专门用来放数字；一个 stack 专门用来放符号。
 *
 *
 *
 * 解法 2：将表达式看作 1 + 2 + (-10)，把 -10 看成一个整体，同时，利用一个变量 sign 来表示该数字前的符号，这样即可沿用解法。
 *
 *
 *
 * 解法 2 的具体操作如下。    ![img](http://www.lgstatic.com/i/image2/M01/91/1B/CgotOV2IepKAGgQSAIKuyG7w9pk329.gif)
 *
 * ### 代码实现
 *
 * ```
 * int calculate(String s) {
 *     Queue<Character> queue = new LinkedList<>();
 *     for (char c : s.toCharArray()) {
 *         if (c != ' ') queue.offer(c);
 *     }
 *     queue.add('+');
 *
 *     char sign = '+'; // 添加一个符号标志变量
 *
 *     int num = 0, sum = 0;
 *
 *     while (!queue.isEmpty()) {
 *         char c = queue.poll();
 *
 *         if (Character.isDigit(c)) {
 *             num = 10 * num + c - '0';
 *         } else {
 *             // 遇到了符号，表明我们要开始统计一下当前的结果了
 *             if (sign == '+') { //数字的符号是 +
 *                 sum += num;
 *             } else if (sign == '-') { // 数字的符号是 -
 *                 sum -= num;
 *             }
 *
 *             num = 0;
 *             sign = c;
 *         }
 *     }
 *
 *     return sum;
 * }
 * ```
 *
 * ##
 *
 * ###### 解题思路三：引入乘除
 *
 * 例题：若引入乘法和除法，如何处理？举个例子：1 + 2 x 10。
 *
 *
 *
 * 解法：要考虑符号的优先级问题，不能再简单得对 sum 进行单向的操作。当遇到乘号的时候：sum = 1，num = 2，而乘法的优先级比较高，得先处理 2 x 10 才能加 1。对此，就把它们暂时记录下来，具体操作如下。    ![img](http://www.lgstatic.com/i/image2/M01/90/FB/CgoB5l2IepSAeUhVAFLy8rXwn-M290.gif)
 *
 * ### 代码实现
 *
 * ```
 * int calculate(String s) {
 *     Queue<Character> queue = new LinkedList<>();
 *     for (char c : s.toCharArray()) {
 *         if (c != ' ') queue.offer(c);
 *     }
 *     queue.add('+');
 *
 *     char sign = '+';
 *     int num = 0;
 *
 *     // 定义一个新的变量 stack，用来记录要被处理的数
 *     Stack<Integer> stack = new Stack<>();
 *
 *     while (!queue.isEmpty()) {
 *         char c = queue.poll();
 *
 *         if (Character.isDigit(c)) {
 *             num = 10 * num + c - '0';
 *         } else {
 *             if (sign == '+') {
 *                 stack.push(num); // 遇到加号，把当前的数压入到堆栈中
 *             } else if (sign == '-') {
 *                 stack.push(-num); // 减号，把当前数的相反数压入到堆栈中
 *             } else if (sign == '*') {
 *                 stack.push(stack.pop() * num); // 乘号，把前一个数从堆栈中取出，然后和当前的数相乘，再放回堆栈
 *             } else if (sign == '/') {
 *                 stack.push(stack.pop() / num); // 除号，把前一个数从堆栈中取出，然后除以当前的数，再把结果放回堆栈
 *             }
 *
 *             num = 0;
 *             sign = c;
 *         }
 *     }
 *
 *
 *     int sum = 0;
 *
 *     // 堆栈里存储的都是各个需要相加起来的结果，把它们加起来，返回总和即可
 *     while (!stack.isEmpty()) {
 *         sum += stack.pop();
 *     }
 *     return sum;
 *
 * }
 * ```
 *
 *
 *
 * ##
 *
 * ###### 解题思路四：引入小括号
 *
 * 例题：如何支持小括号？
 *
 *
 *
 * 解法：小括号里的表达式优先计算。
 *
 * 1. 先利用上面的方法计算小括号里面的表达式。
 * 2. 当遇到一个左括号的时候，可以递归地处理；当遇到了右括号，表明小括号里面的处理完毕，递归应该返回。
 *
 * ### 代码实现
 *
 * ```
 * // 在主函数里调用一个递归函数
 * int calculate(String s) {
 *     Queue<Character> queue = new LinkedList<>();
 *     for (char c : s.toCharArray()) {
 *         if (c != ' ') queue.offer(c);
 *     }
 *     queue.offer('+');
 *
 *     return calculate(queue);
 * }
 *
 * int calculate(Queue<Character> queue) {
 *     char sign = '+';
 *     int num = 0;
 *
 *     Stack<Integer> stack = new Stack<>();
 *
 *     while (!queue.isEmpty()) {
 *         char c = queue.poll();
 *
 *         if (Character.isDigit(c)) {
 *             num = 10 * num + c - '0';
 *         }
 *         // 遇到一个左括号，开始递归调用，求得括号里的计算结果，将它赋给当前的 num
 *         else if (c == '(') {
 *             num = calculate(queue);
 *         }
 *         else {
 *             if (sign == '+') {
 *                 stack.push(num);
 *             } else if (sign == '-') {
 *                 stack.push(-num);
 *             } else if (sign == '*') {
 *                 stack.push(stack.pop() * num);
 *             } else if (sign == '/') {
 *                 stack.push(stack.pop() / num);
 *             }
 *
 *             num = 0;
 *             sign = c;
 *
 *             // 遇到右括号，就可以结束循环，直接返回当前的总和
 *             if (c == ')') {
 *                 break;
 *             }
 *         }
 *     }
 *
 *     int sum = 0;
 *     while (!stack.isEmpty()) {
 *         sum += stack.pop();
 *     }
 *     return sum;
 *
 * }
 * ```
 */