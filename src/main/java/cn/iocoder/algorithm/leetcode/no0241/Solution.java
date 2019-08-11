package cn.iocoder.algorithm.leetcode.no0241;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/different-ways-to-add-parentheses/
 */
public class Solution {

    public List<Integer> diffWaysToCompute(String input) {
        if (input.equals("")) {
            return Collections.emptyList();
        }
        // 解析字符串
        List<Character> ops = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        int index = 0;
        while (index < input.length()) {
            char ch = input.charAt(index);
            index++;
            // 运算符
            if (!this.isNumber(ch)) {
                ops.add(ch);
                continue;
            }
            int value = ch - '0';
            while (index < input.length()
                && isNumber(input.charAt(index))) {
                value = value * 10 + input.charAt(index) - '0';
                index++;
            }
            numbers.add(value);
        }

        // 计算情况
        List<Integer> results = new ArrayList<>();
        this.calc(results, ops, numbers);
        return results;
    }

    private void calc(List<Integer> results, List<Character> ops, List<Integer> numbers) {
        if (numbers.size() == 1) {
            results.add(numbers.get(0));
            return;
        }

        // 顺序选择一个位置操作
        for (int i = 0; i < ops.size(); i++) {
            // 计算
            char ch = ops.get(i);
            int sum = this.sum(ch, numbers, i);
            // 继续计算
            this.calc(results,
                    this.removeOps(ops, i),
                    this.removeNumbers(numbers, i, sum));
        }
    }

    private boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private int sum(char ch, List<Integer> numbers, int index) {
        if (ch == '+') {
            return numbers.get(index) + numbers.get(index + 1);
        }
        if (ch == '-') {
            return numbers.get(index) - numbers.get(index + 1);
        }
        if (ch == '*') {
            return numbers.get(index) * numbers.get(index + 1);
        }
        throw new IllegalStateException("未知字符串" + ch);
    }

    private List<Character> removeOps(List<Character> ops, int index) {
        List<Character> newOps = new ArrayList<>();
        for (int i = 0; i < ops.size(); i++) {
            if (i == index) {
                continue;
            }
            newOps.add(ops.get(i));
        }
        return newOps;
    }

    private List<Integer> removeNumbers(List<Integer> numbers, int index, int result) {
        List<Integer> newNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (i == index) {
                newNumbers.add(result);
            } else if (i == index + 1) {
            } else {
                newNumbers.add(numbers.get(i));
            }
        }
        return newNumbers;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.diffWaysToCompute("2-1-1"));
        System.out.println(solution.diffWaysToCompute("2*3-4*5"));
    }

}
