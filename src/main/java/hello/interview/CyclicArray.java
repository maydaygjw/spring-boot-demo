package hello.interview;

/**
 * Created by Junwen on 7/10/15.
 */
public class CyclicArray {


    /**
     * 面试题：循环序列，查找元素的索引
     * @param args
     */

    public static void main(String[] args) {
        int a[] = {3, 4, 5, 6, 7, 8, 9, 10, 1, 2};

        int index = new CyclicArray().search(a, 11, 0, a.length - 1);

        System.out.println(index);


    }

    int search(int[] a, int data, int start, int end)
    {

        if(start == end)
        {
            if(a[start] == data)
                return start;
            return -1;
        }

        int pivot = start + (end - start) / 2;

        if(a[pivot] == data)
        {
            return pivot;
        } else if(data > a[pivot])
        {
            if(a[end] >= data)
            {
                return search(a, data, pivot + 1, end);
            } else {
                return search(a, data, start, pivot - 1);
            }
        } else {
            if(a[start] <= data)
            {
                return search(a, data, start, pivot - 1);
            } else
            {
                return search(a, data, pivot + 1, end);
            }
        }

    }
}
