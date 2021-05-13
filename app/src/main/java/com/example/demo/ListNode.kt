package com.example.demo

import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.collections.ArrayList

fun main() {
    printlnLinkedNode(reverseLinkedList(mockLinkedNode(1, 2, 3, 4, 5)))
    printlnLinkedNode(deleteNode(mockLinkedNode(1, 2, 3, 4, 5, 8, 6), 8))
    println(reversePrint(mockLinkedNode(1, 3, 2, 3, 5, 345, 43, 564))?.contentToString())
    printlnLinkedNode(mergeTwoLists(mockLinkedNode(2, 4, 7), mockLinkedNode(1, 3, 4)))
    printlnLinkedNode(getKthFromEnd(mockLinkedNode(1, 3, 5, 6, 8, 29, 42), 2))
    printlnLinkedNode(getKthFromEndDoublePointer(mockLinkedNode(1, 3, 5, 6, 8, 29, 42), 2))
    printlnLinkedNode(getKthFromEndRecursive(mockLinkedNode(1, 3, 5, 6, 8, 29, 42), 2))
    printlnLinkedNode(bubbleSortList(mockLinkedNode(243, 234, 32390, 343, 23, 231, 2, 4, 6)))
    printlnLinkedNode(middleNode(mockLinkedNode(243, 234, 32390, 343, 23, 231, 2, 4, 6)))
    println(isPalindrome2(mockLinkedNode(1, 2, 1)))
    println(getDecimalValue(mockLinkedNode(1, 0, 1)))
    println(getDecimalValue2(mockLinkedNode(1, 0, 1)))
    printlnLinkedNode(removeElements(mockLinkedNode(1, 0, 1, 23, 43, 545), 23))
    printlnLinkedNode(removeElements2(mockLinkedNode(1, 0, 1, 23, 43, 545), 43))
    println(listOfDepth())
}

class ListNode(var value: Int) {
    var next: ListNode? = null
    override fun toString(): String {
        return value.toString()
    }
}

fun mockLinkedNode(vararg args: Int): ListNode {
    var head: ListNode? = null
    var cur: ListNode? = null
    for (i in args.indices) {
        if (head == null) {
            head = ListNode(args[i])
            cur = head
            continue
        }
        cur?.next = ListNode(args[i])
        cur = cur?.next
    }
    return head!!
}

// TODO: 2021/5/13 构建二叉树
fun mockTreeNode(vararg args: Int){

}

fun printlnLinkedNode(head: ListNode?, ln: Boolean = false) {
    var cur: ListNode? = head
    while (cur != null) {
        if (ln) {
            println("${cur.value} ")
        } else {
            print("${cur.value} ")
        }
        cur = cur.next
    }
    println()
}

/**
 * 链表翻转
 */
fun reverseLinkedList(head: ListNode?): ListNode? {
    var cur: ListNode? = null
    var pre: ListNode? = head
    while (pre != null) {
        val temp = pre.next
        pre.next = cur
        cur = pre
        pre = temp
    }
    return cur
}

/**
 * 删除指定节点
 */
fun deleteNode(head: ListNode?, value: Int): ListNode? {
    head ?: return head
    var root = head
    var cur = head
    var pre: ListNode? = null
    while (cur != null) {
        if (cur.value == value) {
            val next = cur.next
            if (pre == null) {
                root = next
            } else {
                pre.next = next
            }
            break
        } else {
            pre = cur
            cur = cur.next
        }

    }
    return root
}

/**
 * 从尾到头打印链表
 */
var anr: IntArray? = null
fun reversePrint(head: ListNode?): IntArray? {
    head ?: return intArrayOf()
    reGetSize(head, 1)
    return anr
}

private fun reGetSize(head: ListNode, size: Int): Int {
    val next = head.next
    if (next == null) {
        anr = IntArray(size)
        anr!![0] = head.value
        return size
    }
    val maxSize = reGetSize(next, size + 1)
    anr!![maxSize - size] = head.value
    return maxSize
}

/**
 * 合并两个有序链表
 */
fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null && l2 == null) return null
    var cur1 = l1
    var cur2 = l2
    val anr = ListNode(-1)
    var curNode: ListNode? = anr
    while (cur1 != null || cur2 != null) {
        if (cur1 == null) {
            curNode?.next = cur2
            break
        }
        if (cur2 == null) {
            curNode?.next = cur1
            break
        }
        if (cur1.value < cur2.value) {
            curNode?.next = cur1
            cur1 = cur1.next
            curNode = curNode?.next
            curNode?.next = null
        } else {
            curNode?.next = cur2
            cur2 = cur2.next
            curNode = curNode?.next
            curNode?.next = null
        }
    }
    return anr.next
}

/**
 * 返回倒数第k个节点，最后一个节点为倒数第一个节点
 */
fun getKthFromEnd(head: ListNode?, k: Int): ListNode? {
    head ?: return null
    val stack = Stack<ListNode>()
    var cur: ListNode? = head
    while (cur != null) {
        stack.push(cur)
        cur = cur.next
    }
    if (stack.size < k) {
        return null
    }
    for (i in k downTo 1) {
        cur = stack.pop()
    }
    return cur
}

/**
 * 返回倒数第k个节点，最后一个节点为倒数第一个节点
 * 快慢指针实现
 */
fun getKthFromEndDoublePointer(head: ListNode?, k: Int): ListNode? {
    head ?: return null
    var fastPointer = head
    var slowPointer = head
    for (i in k downTo 1) {
        fastPointer = fastPointer?.next
    }
    while (fastPointer != null) {
        fastPointer = fastPointer.next
        slowPointer = slowPointer?.next
    }
    return slowPointer
}

/**
 * 返回倒数第k个节点，最后一个节点为倒数第一个节点
 * 递归实现
 */
var recurSize = 1
fun getKthFromEndRecursive(head: ListNode?, k: Int): ListNode? {
    if (head?.next == null) {
        return head
    }
    val node = getKthFromEndRecursive(head.next, k)
    if (++recurSize == k) {
        return head
    }
    return node
}

/**
 * 链表冒泡排序
 * 仅限于节点交换的情况下必须是双向链表才能冒泡
 */
fun bubbleSortList(head: ListNode?): ListNode? {
    head?.next ?: return head
    var end: ListNode? = null
    var cur = head.next
    var pre = head
//    var start = head
//    while (cur != end) {
//        while (cur != null && cur != end) {
//            if (pre?.value ?: 0 > cur.value) {
//                val temp = cur.next
//                if (pre == start) {
//                    start = cur
//                }
//                cur.pre = pre.pre
//                cur.next = pre
//                pre.pre = cur
//                pre?.next = temp
//                temp.pre = pre
//                cur = temp
//            } else {
//                pre = cur
//                cur = cur.next
//            }
//            if (cur == null || cur == end) {
//                end = pre
//                pre = start
//                cur = start?.next
//                printlnLinkedNode(start, false)
//            }
//        }
//    }
    while (cur != end) {
        while (cur != null && cur != end) {
            if (cur.value < pre!!.value) {
                val temp = cur.value
                cur.value = pre.value
                pre.value = temp
            }
            val tempNode = cur
            cur = cur.next
            pre = tempNode
            if (cur == null || cur == end) {
                end = pre
                pre = head
                cur = head.next
            }
        }
    }
    return head
}


/**
 * 查找链表的中间节点， 中间为两个的选择第二个作为中间节点返回。
 * 快慢指针实现
 */
fun middleNode(head: ListNode?): ListNode? {
    var fast: ListNode? = head
    var slow = head
    while (fast?.next != null) {
        fast = fast.next?.next
        slow = slow?.next
    }
    return slow
}

/**
 * 判断是否是环形链表, HashMap辅助查找实现 O(N) O(N)
 */
fun hasCycle(head: ListNode?): Boolean {
    head?.next ?: return false
    val hashMap = hashMapOf<Int, ListNode>()
    var node = head
    while (node != null) {
        if (hashMap.containsValue(node)) {
            return true
        }
        hashMap[node.value] = node
        node = node.next
    }
    return false
}

/**
 * 判断是否是环形链表, 快慢指针实现  O(N) O(1)
 */
fun hasCycle2(head: ListNode?): Boolean {
    head?.next ?: return false
    var fast = head.next
    var slow = head
    while (fast != slow) {
        if (fast?.next == null) {
            return false
        }
        fast = fast.next?.next
        slow = slow?.next
    }
    return true
}

fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
    var nodeA = headA
    var nodeB = headB
    while (nodeB != nodeA) {
        nodeA = if (nodeA != null) nodeA.next else headB
        nodeB = if (nodeB != null) nodeB.next else headA
    }
    return nodeA
}

/**
 * 判断是否是回文链表   先赋值到集合，然后数组两头一一对比
 */
fun isPalindrome(head: ListNode?): Boolean {
    head?.next ?: return true
    var node = head
    val array = arrayListOf<Int>()
    while (node != null) {
        array.add(node.value)
        node = node.next
    }
    val halfSize = array.size / 2
    for (i in 0 until halfSize) {
        if (array[i] != array[array.lastIndex - i]) {
            return false
        }
    }
    return true
}

/**
 * 判断是否是回文链表   递归实现，记录一个front指针，跟随递归的归路劲向后迭代并比对
 */
fun isPalindrome2(head: ListNode?): Boolean {
    front = head
    return checkPalindrome(head)
}

var front: ListNode? = null
fun checkPalindrome(head: ListNode?): Boolean {
    if (head != null) {
        if (!checkPalindrome(head.next)) return false
        if (front?.value != head.value) return false
        front = front?.next
    }

    return true
}

/**
 * 链表表示的二进制整数，求十进制数
 * 递归实现
 */
var bit = 0
fun getDecimalValue(head: ListNode?): Int {
    if (head == null) return 0
    val value = getDecimalValue(head.next)
    if (head.value == 0) {
        bit++
        return value
    }
    return value + times(bit++)
}

fun times(times: Int): Int {
    if (times == 0) return 1
    return 2 * times(times - 1)
}

/**
 * 链表表示的二进制整数，求十进制数
 * 顺序移位迭代实现
 */
fun getDecimalValue2(head: ListNode?): Int {
    var node = head
    var asr = 0
    while (node != null) {
        asr = asr.shl(1)
        asr = asr.or(node.value)
        node = node.next
    }
    return asr
}

/**
 * 链表移除指定value的节点, 直接迭代删除，考虑删除头部，实现比较复杂
 */
fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    head ?: return null
    var node = head
    var asr = head
    var pre: ListNode? = null
    while (node != null) {
        if (node.value == `val`) {
            if (pre == null) {
                asr = node.next
                pre = null
                node = node.next
            } else {
                pre.next = node.next
                node = node.next
            }
        } else {
            pre = node
            node = node.next
        }

    }
    return asr
}

/**
 * 链表移除指定value的节点, 增加哨兵节点头部，使链表永不为空，永不无头
 */
fun removeElements2(head: ListNode?, `val`: Int): ListNode? {
    head ?: return null
    val fakeHead = ListNode(0)
    fakeHead.next = head
    var cur = head
    var pre = fakeHead
    while (cur != null) {
        if (cur.value == `val`) {
            pre.next = cur.next
        } else {
            pre = cur
        }
        cur = cur.next
    }
    return fakeHead.next
}

/**
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 * 二叉树层序遍历
 */
class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun listOfDepth(tree: TreeNode?): Array<ListNode?> {
    val queue = LinkedList<TreeNode?>()
    queue.add(tree)
    val array = ArrayList<ListNode?>()
    while (queue.isNotEmpty()) {
        var depthListNode: ListNode? = ListNode(0)
        val curQueueSize = queue.size
        for( i in 0 until curQueueSize) {
            val tempTreeNode = queue.poll()
            queue.offer(tempTreeNode!!.left)
            queue.offer(tempTreeNode.right)
            depthListNode?.next = ListNode(tempTreeNode.value)
        }
        depthListNode = depthListNode?.next
        if(depthListNode != null) array.add(depthListNode)
    }
    return Array(array.size){ array[it] }
}



