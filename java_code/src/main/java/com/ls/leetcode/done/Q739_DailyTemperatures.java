package com.ls.leetcode.done;

import com.ls.leetcode.general.GeneralUtil;

import java.util.Stack;

/**
 * 739
 *
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 */
public class Q739_DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            if(stack.size() <= 0){
                stack.push(i);
            } else {
                while(stack.size() > 0) {
                    int p = stack.peek();
                    if (T[i] > T[p]) {
                        result[p] = i - p;
                        stack.pop();
                    } else {
                        break;
                    }
                }
                stack.push(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GeneralUtil.printIntArray(new Q739_DailyTemperatures().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
        GeneralUtil.printIntArray(new Q739_DailyTemperatures().dailyTemperatures(new int[]{7, 7, 7, 7, 69, 7, 7, 7}));
    }

}
