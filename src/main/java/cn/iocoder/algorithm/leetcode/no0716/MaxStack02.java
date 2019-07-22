package cn.iocoder.algorithm.leetcode.no0716;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 双向链表 +
 */
public class MaxStack02 {

    private class LinkedList {

        private Node head;
        private Node tail;

        public LinkedList() {

        }

        public void add(Node node) {
            // TODO
        }

        public Node pop() {
            return null; // TODO
        }

        public Node top() {
            return null; // TODO
        }

        public void remove(Node node) {

        }

    }

    private class Node {

        /**
         * 值
         */
        private int val;
        /**
         * 前节点
         */
        private Node prev;
        /**
         * 后节点
         */
        private Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 使用双向链表，模拟栈
     */
    private LinkedList list;

    /**
     * 映射
     *
     * key：值
     */
    private TreeMap<Integer, List<Node>> map;

    /** initialize your data structure here. */
    public MaxStack02() {
    }

    public void push(int x) {
        // 添加到 list 中
        Node node = new Node(x);
        list.add(node);

        // 添加到 map 中
        map.computeIfAbsent(x, integer -> new ArrayList<>(1)).add(node);
    }

    public int pop() {
        // 从 list 移除
        Node node = list.pop();

        // 从 map 移除
        List<Node> values = map.get(node.val);
        if (values.size() == 1) {
            map.remove(node.val);
        } else {
            values.remove(values.size() - 1); // 因为先进后出
        }

        return node.val;
    }

    public int top() {
        return list.top().val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        // 从 map 移除
        List<Node> values = map.lastEntry().getValue();
        Node node = values.get(values.size() - 1);
        if (values.size() == 1) {
            map.remove(node.val);
        } else {
            values.remove(values.size() - 1); // 因为先进后出
        }

        // 从 list 移除
        list.remove(node);

        return node.val;
    }

}
