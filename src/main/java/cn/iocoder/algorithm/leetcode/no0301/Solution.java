package cn.iocoder.algorithm.leetcode.no0301;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/remove-invalid-parentheses/
 *
 * BFS
 */
public class Solution {

    public class Node {

        private String text;
        private int lastIndex;

        public Node(String text, int lastIndex) {
            this.text = text;
            this.lastIndex = lastIndex;
        }
    }

    private List<String> result;
    private Queue<Node> queue;
    private String s;

    public List<String> removeInvalidParentheses(String s) {
        this.result = new ArrayList<>();
        this.queue = new LinkedList<>();
        this.s = s;
        // 初始队列
        queue.add(new Node("", 0));

        // bfs 开始
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                this.processNode(node);
            }
            // 如果已经出现结果，结束 bfs
            if (!result.isEmpty()) {
                break;
            }
        }
//        if (result.isEmpty()) {
//            result.add(this.removeLeftAndRight());
//        }
        return result;
    }

    private void processNode(Node node) {
        // 寻找 ) 出现更多的首个位置
        int leftCount = 0;
        int rightCount = 0;
        int rightMoreIndex = -1; // 首次出现 ) 更多的位置
        int leftMoreFirstIndex = -1; // 首次出现 ( 比 ) 多的位置
        for (int i = node.lastIndex; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftCount++;
                if (leftMoreFirstIndex == -1) {
                    leftMoreFirstIndex = i;
                }
                continue;
            }
            if (ch == ')') {
                rightCount++;
                if (rightCount > leftCount) {
                    rightMoreIndex = i;
                    break;
                } else if (rightCount == leftCount) {
                    leftMoreFirstIndex = -1;
                }
            }
        }

        // 如果并未出现
        if (rightMoreIndex == -1) {
            // 持平，说明匹配
            if (leftCount == rightCount) {
                result.add(node.text + s.substring(node.lastIndex));
                return;
            }
            // 处理因为删除第一个 ( 括号，导致配对不对的问题。例如说 "(((k()(("
            int nextRightIndex = -1;
            for (int i = leftMoreFirstIndex + 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    nextRightIndex = i;
                    break;
                }
            }
            // 找不到下一个 ) 括号，说明无法持平，所以删除从 node.lastIndex 开始的所有 ( 括号
            if (nextRightIndex == -1) {
                result.add(node.text + s.substring(node.lastIndex).replaceAll("\\(", ""));
                return;
            }
            // 尝试删除第一个 ( 括号
            if (leftMoreFirstIndex != -1) {
                for (int i = leftMoreFirstIndex; i < nextRightIndex; i++) {
                    char ch = s.charAt(i);
                    if (ch != '(') {
                        continue;
                    }
                    // 和前一个一样，删除意味着相同。
                    if (i > leftMoreFirstIndex && ch == s.charAt(i - 1)) {
                        continue;
                    }
                    queue.add(new Node(node.text
                            + s.substring(node.lastIndex, leftMoreFirstIndex)
                            + s.substring(leftMoreFirstIndex, i).replaceAll("\\(", "")
                            + "("
                            + s.substring(i + 1, nextRightIndex + 1).replaceAll("\\(", ""),
                            nextRightIndex + 1));
                }
//                queue.add(new Node(node.text + s.substring(node.lastIndex, leftMoreFirstIndex), leftMoreFirstIndex + 1));
            }
            return;
        }

        // 如果出现，尝试删除一个 ) 括号
        for (int i = node.lastIndex; i <= rightMoreIndex; i++) {
            char ch = s.charAt(i);
            if (ch != ')') {
                continue;
            }
            // 和前一个一样，删除意味着相同。
            if (i > node.lastIndex && ch == s.charAt(i - 1)) {
                continue;
            }
            queue.add(new Node(node.text
                    + s.substring(node.lastIndex, i)
                    + s.substring(i + 1, rightMoreIndex + 1),
                    rightMoreIndex + 1));
        }
    }

//    private String removeLeftAndRight() {
//        String str = "";
//        for (int i = 0; i < s.length(); i++) {
//            char ch = s.charAt(i);
//            if (ch == '(' || ch == ')') {
//                continue;
//            }
//            str += ch;
//        }
//        return str;
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.removeInvalidParentheses("()())()"));
//        System.out.println(solution.removeInvalidParentheses("(a)())()"));
//        System.out.println(solution.removeInvalidParentheses(")("));
//        System.out.println(solution.removeInvalidParentheses("x("));
//        System.out.println(solution.removeInvalidParentheses("x)"));
//        System.out.println(solution.removeInvalidParentheses("(()"));
//        System.out.println(solution.removeInvalidParentheses(")()("));
//        System.out.println(solution.removeInvalidParentheses(")()(x"));
//        System.out.println(solution.removeInvalidParentheses(")()(("));
        System.out.println(solution.removeInvalidParentheses(")()(()"));
//        System.out.println(solution.removeInvalidParentheses("(((k()(("));
    }

}
