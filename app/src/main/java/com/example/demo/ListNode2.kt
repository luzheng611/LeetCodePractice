package com.example.demo

import java.util.*
import kotlin.collections.HashMap

fun main() {
    splitListToParts(mockLinkedNode(0, 1, 2, 3, 4), 3)
    printlnLinkedNode(reverseBetween2(mockLinkedNode(1, 2, 3, 4, 5), 2, 4))
    printlnLinkedNode(reverseBetween(mockLinkedNode(1, 2, 3, 4, 5), 2, 4))
    printlnLinkedNode(addTwoNumbers(mockLinkedNode(7, 1, 6), mockLinkedNode(5, 9, 2)))
    printlnLinkedNode(rotateRight(mockLinkedNode(0, 1, 2), 4))
    printlnLinkedNode(rotateRight2(mockLinkedNode(0, 1, 2), 4))
    printlnLinkedNode(mergeTwoListNode(mockLinkedNode(0, 4, 6), mockLinkedNode(1,5,9)))
}

/**
 * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 *每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 *这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 *返回一个符合上述规则的链表的列表。
 *举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
 */
fun splitListToParts(root: ListNode?, k: Int): Array<ListNode?> {
    root ?: return arrayOfNulls(k)
    val queue = LinkedList<ListNode?>()
    var cur = root
    while (cur != null) {
        queue.offer(cur)
        cur = cur.next
    }
    val asr: Array<ListNode?> = Array(k) { null }
    if (queue.size <= k) {
        for (i in 0 until k) {
            val node = queue.poll().apply { this?.next = null }
            asr[i] = node ?: return asr
        }
    } else {
        val arraySize: Int = queue.size.div(k)
        val extraSize: Int = queue.size - arraySize * k
        for (i in 0 until k) {
            val realArraySize = arraySize + if (i + 1 <= extraSize) 1 else 0
            val headIndex =
                if (i == 0) 0 else (i * arraySize + if (i + 1 <= extraSize) i else extraSize)
            val head = queue[headIndex]
            val tail = queue[headIndex + realArraySize - 1]
            tail?.next = null
            asr[i] = head
        }
    }
    return asr
}

/**
 * 检测是否是环形链表
 * 通过队列存储遍历路径，发现返回到已存储的节点上时代表环形，遍历到空节点代表不是环形.
 */
fun detectCycle(head: ListNode?): ListNode? {
    head ?: return null
    val map = HashMap<ListNode?, ListNode?>()
    var cur = head
    while (cur != null) {
        if (map.containsKey(cur)) {
            return cur
        }
        map[cur] = cur
        cur = cur.next
    }
    return null
}

/**
 * 检测是否是环形链表
 * 快慢指针，如果存在环的话那么始终会在环中相遇，相遇节点定义一个cur节点，将slow节点从头开始遍历，cur节点也每次遍历一个节点，slow = cur时则为环入口节点
 */
fun detectCycle2(head: ListNode?): ListNode? {
    head ?: return null
    var slow = head
    var fast = head
    var cur: ListNode?
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
        if (fast == slow) {
            cur = fast
            slow = head
            while (cur != slow) {
                cur = cur?.next
                slow = slow?.next
            }
            return cur
        }
    }
    return null
}

/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。 1为头结点
 * Case:先遍历到right，得到preNode，leftNode，rightNode，postNode，然后翻转leftNode到rightNode的节点，将right链接到pre，post链接到left
 */
fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    head ?: return head
    val tempHead = ListNode(0)
    tempHead.next = head
    var leftNode: ListNode? = null
    var rightNode: ListNode? = null
    var preNode: ListNode? = null
    var postNode: ListNode? = null
    var iter: ListNode? = tempHead
    for (i in 0..right) {
        if (left == i) leftNode = iter
        if (right == i) {
            rightNode = iter
            postNode = rightNode?.next
            break
        }
        if (i == left - 1) preNode = iter
        iter = iter?.next
    }
    iter = leftNode?.next
    var pre = leftNode
    while (iter != postNode) {
        val tempNext = iter?.next
        iter?.next = pre
        pre = iter
        iter = tempNext
    }
    preNode?.next = rightNode
    leftNode?.next = postNode
    return tempHead.next
}

/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。 1为头结点
 * Case:遍历到left到right中节点时一次将节点添加到left标识的位置，并记录下一个节点next，直到next的位置等于right
 */
fun reverseBetween2(head: ListNode?, left: Int, right: Int): ListNode? {
    head ?: return head
    val tempHead = ListNode(0)
    tempHead.next = head
    var pre: ListNode? = tempHead
    for (i in 0 until left - 1) {
        pre = pre?.next
    }
    val cur: ListNode? = pre?.next
    var next: ListNode?
    for (i in left until right) {
        next = cur?.next
        cur?.next = next?.next
        next?.next = pre?.next
        pre?.next = next
    }
    return tempHead.next
}

/**
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 *这些数位是反向存放的，也就是个位排在链表首部。
 *编写函数对这两个整数求和，并用链表形式返回结果。
 */
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null && l2 == null) return null
    var extra = 0
    var cur1 = l1
    var cur2 = l2
    val tempHead = ListNode(0)
    var cur: ListNode? = tempHead
    while (cur1 != null || cur2 != null) {
        val val1 = cur1?.value ?: 0
        val val2 = cur2?.value ?: 0
        val value = (val1 + val2 + extra).rem(10)
        extra = (val1 + val2 + extra).div(10)
        cur?.next = ListNode(value)
        cur = cur?.next
        cur1 = cur1?.next
        cur2 = cur2?.next
    }
    if (extra != 0) {
        cur?.next = ListNode(0)
        cur = cur?.next
        cur?.value = extra
    }
    return tempHead.next
}

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */
fun rotateRight(head: ListNode?, k: Int): ListNode? {
    head ?: return null
    var cur = head
    var pre = head
    var tempHead = head
    var size = 0
    while (cur != null) {
        size++
        cur = cur.next
    }
    for (i in 1..k.rem(size)) {
        cur = tempHead
        while (cur?.next != null) {
            if (cur.next?.next == null) {
                pre = cur
            }
            cur = cur.next
        }
        cur?.next = tempHead
        pre?.next = null
        tempHead = cur
    }
    return tempHead
}

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 解法： 将链表变成环，取size
 */
fun rotateRight2(head: ListNode?, k: Int): ListNode? {
    head ?: return null
    var cur = head
    var tempHead = head
    var size = 0
    while (cur != null) {
        size++
        if (cur.next == null) {
            cur.next = tempHead
            break
        } else {
            cur = cur.next
        }
    }
    for (i in 1..size - k.rem(size)) {
        cur = cur?.next
    }
    tempHead = cur?.next
    cur?.next = null
    return tempHead
}

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 */
fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    if (lists.isEmpty()) return null
    if (lists.size == 1) return lists[0]
    var ans: ListNode? = ListNode(0)
    for (i in lists.indices) {
        ans = mergeTwoListNode(ans, lists[i])
    }
    return ans
}

fun mergeTwoListNode(l1: ListNode?, l2: ListNode?): ListNode? {
    l1 ?: return l2
    l2 ?: return l1
    var cur1 = l1
    var cur2 = l2
    val tempHead = ListNode(0)
    var cur: ListNode? = tempHead
    while (cur1 != null && cur2 != null) {
        if (cur1.value < cur2.value) {
            cur?.next = cur1
            cur1 = cur1.next
        } else {
            cur?.next = cur2
            cur2 = cur2.next
        }
        cur = cur?.next
        if (cur1 == null) {
            cur?.next = cur2
            break
        }
        if (cur2 == null) {
            cur?.next = cur1
            break
        }
    }
    return tempHead.next
}























































