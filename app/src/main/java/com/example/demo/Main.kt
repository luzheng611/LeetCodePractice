package com.example.demo

import kotlin.math.max

fun main() {
    println("BubbleSort:"+bubbleSort(intArrayOf(20, 23, 89, 3534, 653, 6456, 34324, 324, 2, 34, 24, 465),5).contentToString())
    println("SelectSort:"+ selectSort(intArrayOf(20, 23, 89, 3534, 653, 6456, 34324, 324, 2, 34, 24, 465)).contentToString())
    println("InsertSort:"+ insetSort(intArrayOf(20, 23, 89, 3534, 653, 6456, 34324, 324, 2, 34, 24, 465)).contentToString())
    println("QuickSort:"+ quickSort(intArrayOf(20, 23, 89, 3534, 653,34,2432,234,54))?.contentToString())
    println("HeapSort:"+ heapSort(intArrayOf(20, 23, 89, 3534,3432,45,35,34,63,63,6,3425,54,6756,3,42,4,2,4,2, 653,34,2432,234,54)).contentToString())
}

/**
 * Bubble sort and Top K.
 */
fun bubbleSort(src: IntArray, k: Int = 0): IntArray {
    if (src.size <= 1) {
        return src
    }
    val len = src.size
    var tempArray = src.copyOf(len)
    for (i in 0 until len) {
        var isSorted = true
        for (j in 0 until len - 1 - i) {
            if (tempArray[j] > tempArray[j + 1]) {
                val temp = tempArray[j]
                tempArray[j] = tempArray[j + 1]
                tempArray[j + 1] = temp
                isSorted = false
            }
        }
        //topK resolve
        if (i == k - 1) {
            tempArray = tempArray.sliceArray(len - k until len)
            break
        }
        if (isSorted) {
            break
        }
    }
    return tempArray
}

/**
 * Selelct sort.
 */
fun selectSort(src: IntArray): IntArray{
    if (src.size <= 1) {
        return src
    }
    val len = src.size
    val tempArray = src.copyOf(len)
    for (i in 0 until len) {
        var minIndex = i
        for (j in i + 1 until len) {
            if (tempArray[j] < tempArray[minIndex]) {
                minIndex = j
            }
        }
        if (tempArray[minIndex] < tempArray[i]) {
            val temp = tempArray[i]
            tempArray[i] = tempArray[minIndex]
            tempArray[minIndex] = temp
        }
    }
    return tempArray
}

/**
 * Insert sort.
 */
fun insetSort(src: IntArray): IntArray {
    if (src.size <= 1) {
        return src
    }
    val len = src.size
    val tempArray = src.copyOf(len)
    for(i in 1 until len){
        var j = i
        val target = tempArray[i]
        while (j > 0 && target < tempArray[j - 1]){
            tempArray[j] = tempArray[j - 1]
            j--
        }
        tempArray[j] = target
    }
    return tempArray
}

fun quickSort(src: IntArray?): IntArray?{
    if (src == null || src.size <= 1) {
        return src
    }
    val len = src.size
    val tempArray = src.copyOf(len)
    quickSortInternal(tempArray, 0, tempArray.lastIndex)
    return tempArray
}

fun quickSortInternal(src: IntArray, l: Int, r: Int){
    if(l >= r){
        return
    }
    val key = src[l]
    var left = l
    var right = r
    while (left < right){
        while (left < right && src[right] > key){
            right--
        }
        swap(src, left, right)
        while (left < right && src[left] < key){
            left++
        }
        swap(src, left, right)
    }
    src[left] = key
    quickSortInternal(src, l, left - 1)
    quickSortInternal(src, right + 1, r)
}

private fun swap(src: IntArray, left: Int, right: Int) {
    val temp = src[left]
    src[left] = src[right]
    src[right] = temp
}


private fun heapSort(src: IntArray): IntArray{
    if(src.size <= 1){
        return src
    }
    var len = src.size
    val tempArray = src.copyOf(len)
    buildMaxHeap(tempArray, 0, len--)
    while (len > 1) {
        swap(tempArray, 0, len--)
        buildMaxHeap(tempArray, 0, len)
    }
    return tempArray
}

fun buildMaxHeap(tempArray: IntArray, start: Int, len: Int) {
    val lastParentNodeIndex = len.shr(1) - 1
    for (parentIndex in lastParentNodeIndex downTo 0) {
        val leftIndex = parentIndex.shl(1) + 1
        val rightIndex = parentIndex.shl(1) + 2
        val right = if (rightIndex >= len) 0 else tempArray[rightIndex]
        val left = tempArray[leftIndex]
        var maxIndex = parentIndex
        if (left > tempArray[maxIndex]) maxIndex = leftIndex
        if (right > tempArray[maxIndex]) maxIndex = rightIndex
        if (maxIndex != parentIndex) {
            swap(tempArray, maxIndex, parentIndex)
        }
    }
}

