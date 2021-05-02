package com.example.demo

import java.util.*

fun main() {
    printlnLinkedNode(reverseLinkedList(mockLinkedNode(1, 2, 3, 4, 5)))
    printlnLinkedNode(deleteNode(mockLinkedNode(1, 2, 3, 4, 5, 8, 6), 8))
    println(reversePrint(mockLinkedNode(1, 3, 2, 3, 5, 345, 43, 564))?.contentToString())
    printlnLinkedNode(mergeTwoLists(mockLinkedNode(2,4,7), mockLinkedNode(1, 3, 4)))
    printlnLinkedNode(getKthFromEnd(mockLinkedNode(1,3,5,6,8,29,42),2))
    printlnLinkedNode(getKthFromEndDoublePointer(mockLinkedNode(1,3,5,6,8,29,42),2))
    printlnLinkedNode(getKthFromEndRecursive(mockLinkedNode(1,3,5,6,8,29,42),2))
}

class ListNode(val value: Int) {
    var next: ListNode? = null
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
    var cur : ListNode? = head
    while (cur != null) {
        stack.push(cur)
        cur = cur.next
    }
    if(stack.size < k){
        return null
    }
    for(i in k downTo 1){
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
    for(i in k downTo 1){
        fastPointer = fastPointer?.next
    }
    while (fastPointer != null){
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



