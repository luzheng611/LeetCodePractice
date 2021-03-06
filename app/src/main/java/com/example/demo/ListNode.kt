package com.example.demo

import java.util.*
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
    printlnLinkedNode(
        mergeInBetween(
            mockLinkedNode(0, 1, 2, 3, 4, 5, 6),
            3,
            5,
            mockLinkedNode(100, 1000, 10009)
        )
    )
    printlnLinkedNode(insertionSortList(mockLinkedNode(32, 234, 45323, 12, 432, 523, 4, 3)))
    printlnLinkedNode(partition(mockLinkedNode(3, 5, 8, 5, 10, 2, 1), 5))
    printlnLinkedNode(oddEvenList(mockLinkedNode(2, 1, 3, 5, 6, 4, 7)))
    printlnLinkedNode(swapNodes(mockLinkedNode(2, 1, 3, 5, 6, 4, 7), 2))
    printlnLinkedNode(reorderList(mockLinkedNode(1, 2, 3, 4, 5, 6)))
    println(numComponents(mockLinkedNode(1, 2, 3, 4, 5, 6), intArrayOf(1, 3, 5, 6)))
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

fun buildBinaryTree(vararg intArray: Int): TreeNode {
    val size = intArray.size
    val nodeList = LinkedList<TreeNode>()
    for (i in 0 until size) {
        nodeList.add(TreeNode(intArray[i]))
    }
    val lastParentIndex = size.shr(1) - 1
    for (i in lastParentIndex downTo 0 step 1) {
        val parent = nodeList[i]
        val leftIndex = i.shl(1) + 1
        val rightIndex = i.shl(1) + 2
        if (leftIndex <= intArray.lastIndex) parent.left = nodeList[leftIndex]
        if (rightIndex <= intArray.lastIndex) parent.right = nodeList[rightIndex]
    }
    return nodeList[0]
}

fun inOrderBinaryTree(root: TreeNode?) {
    if (root != null) {
        inOrderBinaryTree(root.left)
        print("${root.value} ")
        inOrderBinaryTree(root.right)
    }
}

fun preOrderBinaryTree(root: TreeNode?) {
    if (root != null) {
        print("${root.value} ")
        inOrderBinaryTree(root.left)
        inOrderBinaryTree(root.right)
    }
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

    override fun toString(): String {
        return value.toString()
    }
}

fun listOfDepth(tree: TreeNode?): Array<ListNode?> {
    val queue = LinkedList<TreeNode?>()
    queue.add(tree)
    val array = ArrayList<ListNode?>()
    while (queue.isNotEmpty()) {
        var depthListNode: ListNode? = ListNode(0)
        var curNode = depthListNode
        val curQueueSize = queue.size
        for (i in 0 until curQueueSize) {
            val tempTreeNode = queue.pollFirst()
            tempTreeNode?.apply {
                if (left != null) queue.add(left)
                if (right != null) queue.add(right)
                curNode?.next = ListNode(tempTreeNode.value)
                curNode = curNode?.next
            }
        }
        depthListNode = depthListNode?.next
        if (depthListNode != null) array.add(depthListNode)
    }
    return Array(array.size) { array[it] }
}

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 转成数组后再递归左右子树
 */
fun sortedListToBST(head: ListNode?): TreeNode? {
    var cur = head
    val nodeList = ArrayList<Int>()
    while (cur != null) {
        nodeList.add(cur.value)
        cur = cur.next
    }
    val array = nodeList.toIntArray()
    return sortedListToBST(array, 0, array.size - 1)
}

fun sortedListToBST(array: IntArray, left: Int, right: Int): TreeNode? {
    if (left >= right) return null
    val mid = (right + left).shr(1) + 1.shr(1)
    val root = TreeNode(array[mid])
    root.left = sortedListToBST(array, left, mid - 1)
    root.right = sortedListToBST(array, mid + 1, right)
    return root
}

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 直接使用快慢指针查找中位节点后递归左右子树
 */
fun sortedListToBST2(head: ListNode?): TreeNode? {
    return buildBST(head, null)
}

fun buildBST(left: ListNode?, right: ListNode?): TreeNode? {
    if (left == right) return null
    val mid = getMidNode(left, right) ?: return null
    val root = TreeNode(mid.value)
    root.left = buildBST(left, mid)
    root.right = buildBST(mid.next, right)
    return root
}

fun getMidNode(left: ListNode?, right: ListNode?): ListNode? {
    if (left == right) return null
    var slow = left
    var fast = left
    while (fast != right && fast?.next != right) {
        slow = slow?.next
        fast = fast?.next?.next
    }
    return slow
}

/**
 * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。

请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。

 */
fun mergeInBetween(list1: ListNode?, a: Int, b: Int, list2: ListNode?): ListNode? {
    list1 ?: return list2
    list2 ?: return list1
    var pre = list1
    var next: ListNode? = null
    var cur = list1
    var tempA = a - 1
    var tempB = b
    while (tempA != 0 || tempB != 0) {
        cur = cur?.next
        if (tempA != 0) {
            if (--tempA == 0) {
                pre = cur
            }
        }
        if (tempB != 0) {
            if (--tempB == 0) {
                next = cur?.next
                cur?.next = null
            }
        }
    }
    pre?.next = list2
    var cur2 = list2
    while (cur2?.next != null) {
        cur2 = cur2.next
    }
    cur2?.next = next
    return list1
}

/**
 * 对链表进行插入排序。
 */
fun insertionSortList(head: ListNode?): ListNode? {
    head ?: return null
    var stack = Stack<ListNode?>()
    val tempHead = ListNode(0)
    tempHead.next = head
    var cur = head.next
    var lastSorted: ListNode? = head
    while (cur != null) {
        if (lastSorted!!.value <= cur.value) {
            lastSorted = lastSorted.next
        } else {
            var pre: ListNode = tempHead
            while (pre.next!!.value < cur.value) {
                pre = pre.next!!
            }
            val temp = pre.next
            lastSorted.next = cur.next
            pre.next = cur
            cur.next = temp
        }
        cur = lastSorted?.next
    }
    return tempHead.next
}

/**
 * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
 * 输入: head = 3->5->8->5->10->2->1, x = 5
 * 输出: 3->1->2->10->5->5->8
 */
fun partition(head: ListNode?, x: Int): ListNode? {
    head ?: return null
    val smallHead = ListNode(0)
    val largeHead = ListNode(0)
    var large: ListNode? = largeHead
    var small: ListNode? = smallHead
    var cur = head
    while (cur != null) {
        if (cur.value < x) {
            small?.next = cur
            small = cur
        } else {
            large?.next = cur
            large = cur
        }
        cur = cur.next
    }
    small?.next = largeHead.next
    large?.next = null
    return smallHead.next
}

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 */
fun oddEvenList(head: ListNode?): ListNode? {
    head ?: return null
    val oddHead = ListNode(0)
    val evenHead = ListNode(0)
    var odd = oddHead
    var even = evenHead
    var cur = head
    var index = 1
    while (cur != null) {
        if (index++.rem(2) == 1) {
            odd.next = cur
            odd = cur
        } else {
            even.next = cur
            even = cur
        }
        cur = cur.next
    }
    odd.next = evenHead.next
    even.next = null
    return oddHead.next
}

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
fun treeToDoublyList(root: TreeNode?): TreeNode? {
    if (root == null) return root
    val stack = Stack<TreeNode>()
    var head: TreeNode? = null //头节点
    var point = root //遍历节点
    var pre: TreeNode? = null //保存父节点
    while (!stack.isEmpty() || point != null) {
        while (point != null) {
            stack.push(point)
            point = point.left
        }
        if (!stack.isEmpty()) {
            val cur = stack.pop() as TreeNode
            if (pre == null) { //如果上一个节点为空意味着刚开始回溯节点，初始化头节点和父节点
                head = cur
                pre = cur
            } else { //否则将父节点的right指向当前节点，当前节点的left指向父节点，更新父节点
                pre.right = cur
                cur.left = pre
                pre = cur
            }
            point = cur.right
            if (stack.isEmpty() && point == null) { //如果栈空了和当前遍历节点也为空意味着遍历到了最后一个节点，那么将为节点的right指向头节点，头节点的left指向为节点
                cur.right = head
                head?.left = cur
            }
        }
    }
    return head
}

/**
 * 给你链表的头节点 head 和一个整数 k 。交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
 */
fun swapNodes(head: ListNode?, k: Int): ListNode? {
    head ?: return null
    var firstKNode = head
    var slow: ListNode? = head
    var fast: ListNode? = head
    for (i in k - 1 downTo 1) {
        fast = fast?.next
        firstKNode = fast
    }
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next
    }
    val temp = firstKNode!!.value
    firstKNode.value = slow!!.value
    slow.value = temp
    return head
}

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 *将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
fun reorderList(head: ListNode?): ListNode? {
    head?.next ?: return null
    val tempHead = ListNode(0)
    tempHead.next = head
    var slow: ListNode? = tempHead
    var fast: ListNode? = tempHead
    val stack = Stack<ListNode?>()
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }
    var point = slow?.next
    while (point != null) {
        stack.push(point)
        point = point.next
    }
    point = head
    var tail: ListNode? = null
    var next: ListNode? = null
    while (!stack.isEmpty()) {
        next = point?.next
        tail = stack.pop()
        point?.next = tail
        tail?.next = next
        point = next
    }
    if (next != slow) {
        tail?.next = null
    } else {
        next?.next = null
    }
    return tempHead.next
}

/**
 * 给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。
 *同时给定列表 G，该列表是上述链表中整型值的一个子集。
 *返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。
 */
fun numComponents(head: ListNode?, nums: IntArray): Int {
    head ?: return 0
    val arrayList = nums.toMutableList()
    var cur = head
    var asr = 0
    while (cur != null) {
        if (arrayList.contains(cur.value) && (cur.next == null || !arrayList.contains(cur.next?.value))) {
            asr++
        }
        cur = cur.next
    }
    return asr
}











