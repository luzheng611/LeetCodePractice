package com.example.demo

import java.util.*

fun main() {
    splitListToParts(mockLinkedNode(0,1,2,3,4),3)
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
    val asr: Array<ListNode?> = Array(k){null}
    if(queue.size <= k){
        for(i in 0 until k){
            val node = queue.poll().apply { this?.next = null }
            asr[i] = node ?: return asr
        }
    } else {
        val arraySize: Int = queue.size.div(k)
        val extraSize: Int = queue.size - arraySize * k
        for(i in 0 until k) {
            val realArraySize = arraySize + if(i + 1 <= extraSize) 1 else 0
            val headIndex =if(i == 0) 0 else ( i * arraySize + if(i + 1 <= extraSize) i else extraSize )
            val head = queue[headIndex]
            val tail = queue[headIndex + realArraySize - 1]
            tail?.next = null
            asr[i] = head
        }
    }
    return asr
}