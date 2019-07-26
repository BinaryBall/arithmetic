package com.jamal.arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiang
 * @title: Arithmetic03
 * @projectName arithmetic03
 * @description: 974. 和可被 K 整除的子数组
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2019/7/2616:56
 */
public class Arithmetic03 {

    public static void main(String[] args) {
       int[] A = new int[]{4,5,0,-2,-3,1};
       int K = 5;
       forSolution(A,K);
       subArraysDivByK(A,K);
    }

    /**
     * 暴力解法，枚举所有连续数组的结果
     * @param A
     * @param K
     */
    public static void forSolution(int[] A, int K) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            int sum = A[i];
            if (sum % K == 0) ++res;
            for (int j = i + 1; j < A.length; j++) {
                sum += A[j];
                if (sum % K == 0) ++res;
            }
        }
        System.out.println(res);
    }

    public static int subArraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>(16);
        int sum = 0;
        for (int ele : A) {
            sum += ele;
            sum %= K;
            if (sum < 0) {
                sum += K;
            }
            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1);
            }
        }
        System.out.println(map.toString());
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            count += val * (val - 1) / 2;
            if (entry.getKey() == 0) {
                count += val;
            }
        }
        return count;
    }

    public int subarraysDivByK(int[] A, int K) {
        int[] cnt = new int[K];
        cnt[0] = 1;
        int sum = 0, res = 0;
        for (int n: A) {
            sum = (sum + n) % K;
            if (sum < 0) sum += K;
            res += cnt[sum];
            cnt[sum]++;
        }
        return res;
    }
}
