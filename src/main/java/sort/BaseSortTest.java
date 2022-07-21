package sort;

import java.util.Arrays;

/**
 * @author dengchao
 * @title: BaseSortTest
 * @projectName base-sort
 * @description: TODO
 * @date 2022/7/19 09:59
 */

public class BaseSortTest {


    public static void main(String[] args) {
        int arr[] = new int[]{21, 2, 1, 31, 10, 0};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序
     * 解读：每次选取最小的（或最大，降序）
     * 时间复杂度：O(n2)
     * 空间复杂度o(1)
     */
    public static void selectionSort(int[] arr) {
        int len = arr.length;
        //遍历要排序的数组，len-1是指在对最后一个元素之前的所有元素排序后，最后一个原序无需在比较
        for (int i = 0; i < len - 1; i++) {
            //minCoord指向最小元素的位置
            int minCoord = i;
            for (int k = i + 1; k < len; k++) {
                //每次拿最小位置上的元素与当前元素比，如果当前元素小则替换最小元素的坐标
                if (arr[minCoord] > arr[k]) {
                    minCoord = k;
                }
            }
            //将最小位置与目前排序到的元素位置交换
            int tep = arr[minCoord];
            arr[minCoord] = arr[i];
            arr[i] = tep;
        }
    }

    /**
     * 插入排序
     * 就像打牌一样，每次将当前牌插入到对应的位置
     * 时间复杂度o(n2)
     * 空间复杂度o(1)
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int insertNum = arr[i];
            int k = i - 1;
            while (k >= 0 && insertNum < arr[k]) {
                //当前元素与前面的元素做比较，小于前面元素则将前面元素后移
                arr[k + 1] = arr[k--];
            }
            arr[k + 1] = insertNum;
        }
    }

    /**
     * 21, 2, 1, 31, 10, 0
     * 2 21 1 31 10 0
     * <p>
     * 冒泡排序
     * 时间复杂度 O(n2)
     * 空间复杂度O(1)
     */
    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        boolean flag = true;
        for (int i = 0; i < len - 1; i++) {
            for (int k = 1; k < len - i; k++) {
                if (arr[k] < arr[k - 1]) {
                    int tep = arr[k];
                    arr[k] = arr[k - 1];
                    arr[k - 1] = tep;
                    flag = false;
                }
            }
            //本次冒泡，元素没有发生过移动，证明剩余元素已经是有序的，无需在遍历
            if (flag) {
                return;
            }
        }
    }

    /**
     * 21, 2, 1, 31, 10, 0 9 9
     * 希尔排序 插入排序的升级版，先让局部的元素有序，减少后续的位移操作
     * 时间复杂度O(logn)
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int len = arr.length;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int tep = arr[i];
                int k = i - gap;
                for (; k >= 0 && tep < arr[k]; k -= gap) {
                    arr[k + gap] = arr[k];
                }
                arr[k + gap] = tep;
            }

        }
    }

    /**
     * 归并排序
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        int len = arr.length;
        int mid = len / 2;
        part(0, len - 1, arr);
    }


    /**
     * 分的动作
     * @param left
     * @param right
     * @param arr
     */
    public static void part(int left, int right, int[] arr) {
        //拆分到只剩一个元素
        if (left < right) {
            int mid = (left + right) / 2;
            //拆分左边部分
            part(left, mid, arr);
            //拆分右边部分
            part(mid + 1, right, arr);
            //合并左右两边部分
            marge(left, mid, right, arr);
        }

    }

    /**
     * 合的动作
     * @param left
     * @param mid
     * @param right
     * @param arr
     */
    public static void marge(int left, int mid, int right, int[] arr) {
        int l = left;
        int r = mid + 1;
        //获取一个足以存放左右两边部分的临时数组
        int[] temp = new int[right - left + 1];
        //临时数组的初始化指针
        int k = 0;
        while (l <= mid && r <= right) {
            if (arr[l] > arr[r]) {
                temp[k++] = arr[r++];
            } else {
                temp[k++] = arr[l++];
            }
        }
        while (l <= mid) {
            temp[k++] = arr[l++];
        }
        while (r <= right) {
            temp[k++] = arr[r++];
        }
        k = 0;
        for (int i = left; i <= right; i++) {
            arr[i] = temp[k++];
        }
    }


}
