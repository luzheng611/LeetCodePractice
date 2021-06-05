package com.example.demo

fun main() {

}

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点
 */
fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
    root1 ?: return  root2
    root2 ?: return  root1
    val ans = TreeNode(root1.value + root2.value)
    ans.left = mergeTrees(root1.left, root2.left)
    ans.right = mergeTrees(root1.right, root2.right)
    return ans
}

/**
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 */
fun mirrorTree(root: TreeNode?): TreeNode? {
    root ?: return null
    val tempRight = root.right
    root.right = root.left
    root.left = tempRight
    mirrorTree(root.right)
    mirrorTree(root.left)
    return root
}