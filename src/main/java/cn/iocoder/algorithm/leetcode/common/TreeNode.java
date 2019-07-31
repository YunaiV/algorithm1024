package cn.iocoder.algorithm.leetcode.common;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public static TreeNode create(int... values) {
        if (values.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);


        return root;
    }

}
