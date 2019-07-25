package com.jamal.arithmetic;

import java.util.*;

/**
 * @author xiaoxiang
 * @title: Arithmetic01
 * @projectName arithmetic01
 * @description: 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * @date 2019/7/2510:24
 */
public class Arithmetic01 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 2, 5, 3, 5, 6};
        System.out.println(forSoution(nums));
        System.out.println(mapSolution(nums));
        System.out.println(listSolution(nums));
        System.out.println(xorSolution(nums));
        System.out.println(sortSolution(nums));
    }
    /**
     * 双层for循环
     *
     * @param nums
     * @return
     */
    public static Integer forSoution(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //相同个数
            int equalCount = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    equalCount += 1;
                }
            }
            // 除本身外没有其他相同的数字了
            if (equalCount == 1)
                return nums[i];
        }

        return -1;
    }
    /**
     * list
     *
     * @param nums
     * @return
     */
    public static Integer listSolution(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!list.remove(Integer.valueOf(nums[i]))) {
                list.add(Integer.valueOf(nums[i]));
            }
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return -1;
    }

    /**
     * 利用 hashmap 实现
     *
     * @param nums
     * @return
     */
    public static Integer mapSolution(int[] nums) {
        Map<Integer, Object> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.remove(nums[i]);
            } else {
                map.put(nums[i], 1);
            }
        }
        if (map.keySet().size() == 0 || map.keySet().size() > 1) {
            return -1;
        }
        return map.keySet().iterator().next();
    }

    /**
     * 数组排序
     *
     * @param nums
     * @return
     */
    public static Integer sortSolution(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i = i + 2) {
            // 如果要找的元素为最后一个就会报 ArrayIndexOutOfBoundsException 异常
            if (i != nums.length - 1) {
                if (nums[i] != nums[i + 1])
                    return nums[i];
            } else {//如果前面没有找到，必然是最后一个
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 异或运算
     *
     * @param nums
     * @return
     */
    public static Integer xorSolution(int[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        }
        return ans;
    }

}
