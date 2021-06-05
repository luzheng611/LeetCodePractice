package com.example.demo

import android.util.Log
import java.util.*

fun main() {

}

/**
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点
 */
fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
    root1 ?: return root2
    root2 ?: return root1
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

/**
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 */
fun maxDepth(root: TreeNode?): Int {
    root ?: return 0
    val queue = LinkedList<TreeNode>()
    queue.offer(root)
    var ans = 0
    while (!queue.isEmpty()) {
        val size = queue.size
        if(size > 0 ) ans++
        for (i in 0 until size) {
            val cur = queue.poll()
            cur ?: return ans
            if (cur.left != null) queue.offer(cur.left)
            if (cur.right != null) queue.offer(cur.right)
        }
    }
    return ans
}

fun preOrderRecursive(root: TreeNode?){
    root ?: return
    Log.e("luzheng", "preOrderRecursive: $root" )
    preOrderBinaryTree(root.left)
    preOrderBinaryTree(root.right)
}

fun inOrderRecursive(root: TreeNode?){
    root ?: return
    preOrderBinaryTree(root.left)
    Log.e("luzheng", "preOrderRecursive: $root" )
    preOrderBinaryTree(root.right)
}

fun postOrderRecursive(root: TreeNode?){
    root ?: return
    preOrderBinaryTree(root.left)
    preOrderBinaryTree(root.right)
    Log.e("luzheng", "preOrderRecursive: $root" )
}