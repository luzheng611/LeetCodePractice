package com.example.demo

import android.util.Log
import java.lang.Integer.min
import java.util.*
import kotlin.math.max

fun main() {
    postOrderNoRecursive(TreeNode(1).apply {
        left = null;right = TreeNode(2).apply {
        left = TreeNode(3)
    }
    })
    postOrderNoRecursive(mockTreeNode(5, 2, 6, 1, 3, 4))
    println(depth(mockTreeNode(5,2,6,1,3,4,5,6)))
}

fun mockTreeNode(vararg value: Int): TreeNode? {
    return fillSubTree(0, value)
}

fun fillSubTree(index: Int, levelOrderArray: IntArray): TreeNode? {
    val value =
        (if (index <= levelOrderArray.lastIndex) levelOrderArray[index] else null) ?: return null
    val temp = TreeNode(value)
    temp.left = fillSubTree(index * 2 + 1, levelOrderArray)
    temp.right = fillSubTree(index * 2 + 2, levelOrderArray)
    return temp
}

fun levelOrder(treeNode: TreeNode?) {
    treeNode ?: return
    val queue = LinkedList<TreeNode>()
    queue.offer(treeNode)
    while (!queue.isEmpty()) {
        val node = queue.poll()
        print("${node?.value} ")
        if (node?.left != null) queue.offer(node.left)
        if (node?.right != null) queue.offer(node.right)
    }
    println()
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
        if (size > 0) ans++
        for (i in 0 until size) {
            val cur = queue.poll()
            cur ?: return ans
            if (cur.left != null) queue.offer(cur.left)
            if (cur.right != null) queue.offer(cur.right)
        }
    }
    return ans
}

fun depth(root: TreeNode?): Int{
    if(root == null) return 0
    return max(depth(root.left), depth(root.right)) + 1
}

fun preOrderRecursive(root: TreeNode?) {
    root ?: return
    Log.e("luzheng", "preOrderRecursive: $root")
    preOrderBinaryTree(root.left)
    preOrderBinaryTree(root.right)
}

fun preOrderNoRecursive(root: TreeNode?) {
    root ?: return
    val stack = Stack<TreeNode>()
    var cur: TreeNode? = root
    while (cur != null || !stack.isEmpty()) {
        while (cur != null) {
            Log.e("luzheng", "preOrderNoRecursive: $cur")
            stack.push(cur)
            cur = cur.left
        }
        if (!stack.isEmpty()) {
            cur = stack.pop()
            cur = cur.right
        }
    }
}

fun inOrderRecursive(root: TreeNode?) {
    root ?: return
    preOrderBinaryTree(root.left)
    Log.e("luzheng", "preOrderRecursive: $root")
    preOrderBinaryTree(root.right)
}

fun inOrderNoRecursive(root: TreeNode?) {
    root ?: return
    val stack = Stack<TreeNode>()
    var cur: TreeNode? = root
    while (cur != null || !stack.isEmpty()) {
        while (cur != null) {
            stack.push(cur)
            cur = cur.left
        }
        if (!stack.isEmpty()) {
            cur = stack.pop()
            Log.e("luzheng", "preOrderNoRecursive: $cur")
            cur = cur.right
        }
    }
}

fun postOrderRecursive(root: TreeNode?) {
    root ?: return
    preOrderBinaryTree(root.left)
    preOrderBinaryTree(root.right)
    Log.e("luzheng", "preOrderRecursive: $root")
}

fun postOrderNoRecursive(root: TreeNode?) {
    root ?: return
    val stack = Stack<TreeNode>()
    var cur: TreeNode? = root
    var pre: TreeNode? = null
    while (cur != null || !stack.isEmpty()) {
        while (cur != null) {
            stack.push(cur)
            cur = cur.left
        }
        if (!stack.isEmpty()) {
            cur = stack.pop()
            if (cur.right == null || cur.right == pre) {
                print("$cur ")
                pre = cur
                cur = null
            } else {
                stack.push(cur)
                cur = cur.right
            }
        }
    }
    println()
}

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * 递归实现
 */
fun verifyPostorder(postorder: IntArray): Boolean {
    return verifyBSTPostOrder(postorder, 0, postorder.size - 1)
}

fun verifyBSTPostOrder(postorder: IntArray, leftIndex: Int, rightIndex: Int): Boolean {
    if (leftIndex >= rightIndex) return true

    var left = leftIndex
    while (postorder[left] < postorder[rightIndex]) {
        left++
    }
    var right = left
    while (postorder[right] > postorder[rightIndex]) {
        right++
    }
    return right == rightIndex && verifyBSTPostOrder(postorder, leftIndex, left - 1) && verifyBSTPostOrder(postorder, left  , rightIndex - 1)
}

/**
 * 翻转二叉树
 */
fun invertTree(root: TreeNode?): TreeNode? {
    root ?: return null
    val tempRight = root.right
    root.right = root.left
    root.left = tempRight
    invertTree(root.left)
    invertTree(root.right)
    return root
}