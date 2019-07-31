package com.jamal.arithmetic;


import java.util.*;

public class Arithmetic06 {

    //833. 字符串中的查找与替换
    public static void main(String[] args) {
        String S = "hiztefjukhpoypxtllwg";
        int[] indexes = new int[]{4, 15, 2, 13, 11, 18};
        String[] sources = new String[]{"efju", "tll", "zt", "px", "oy", "wg"}, targets = new String[]{"dxlx", "yrnz", "eo", "rt", "y", "vm"};
        System.out.println(findReplaceStringByOrderIndex(S, indexes, sources, targets));
    }

    /**
     * 将需要替换的索引排序，因为替换的值和被替换值得长度不一样，所以在字符串截取的时候会产生偏移量，将需要替换的索引排序后，偏移量只会对后面需要替换的字符串产生影响
     *
     * @param S
     * @param indexes
     * @param sources
     * @param targets
     * @return
     */
    public static String findReplaceStringByOrderIndex(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder(S);
        // new 一个具有排序功能的TreeMap
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        // 查找出需要替换的字符串存入map，key为需要替换字符串的下标，value为原数组下标
        for (int i = 0; i < indexes.length; i++) {
            int index = indexes[i];
            String source = sources[i];
            // 判断是否需要替换
            if (S.substring(index).startsWith(source)) {
                map.put(index, i);
            }
        }
        // 偏移量
        int offset = 0;
        for (Integer key : map.keySet()) {
            Integer value = map.get(key);
            String source = sources[value];
            String target = targets[value];
            sb.replace(key + offset, key + offset + source.length(), target);
            offset += target.length() - source.length();
        }
        return sb.toString();
    }

    /**
     * 将字符串转变成char数组，逐个添加到新字符串中
     * @param S
     * @param indexes
     * @param sources
     * @param targets
     * @return
     */
    public static String findReplaceStringByCharAt(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();

        // 新建一个S.length()长度的数组，初始值为-1，需要替换的地方将数组的值置为indexs数组的下标。
        int[] hash = new int[S.length()];
        Arrays.fill(hash, -1);
        for (int i = 0; i < indexes.length; i++) {
            int idx = indexes[i];
            if (S.indexOf(sources[i], indexes[i]) == indexes[i]) {
                hash[idx] = i;
            }
        }
        // 查找出需要替换的字符串存入map，Map效率没有数组高
//        Map<Integer, Integer> replaceMap = new HashMap<>();
//        for (int i = 0; i < indexes.length; i++) {
//            int index = indexes[i];
//            if (S.indexOf(sources[i],index)==index) {
//                replaceMap.put(index, i);
//            }
//        }
        // 遍历字符串，将字符串转换为字节，添加到StringBuilder的中，需要替换的地方将原来的值替换掉
        for (int i = 0; i < S.length(); ) {
            if (hash[i] >= 0) {
                sb.append(targets[hash[i]]);
                i += sources[hash[i]].length();
            } else {
                sb.append(S.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}
