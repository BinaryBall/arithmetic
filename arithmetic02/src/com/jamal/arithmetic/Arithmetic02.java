package com.jamal.arithmetic;

import java.util.*;

/**
 * 811
 * 一个网站域名，如"discuss.leetcode.com"，包含了多个子域名。作为顶级域名，常用的有"com"，下一级则有"leetcode.com"，最低的一级为"discuss.leetcode.com"。当我们访问域名"discuss.leetcode.com"时，也同时访问了其父域名"leetcode.com"以及顶级域名 "com"。
 * <p>
 * 给定一个带访问次数和域名的组合，要求分别计算每个域名被访问的次数。其格式为访问次数+空格+地址，例如："9001 discuss.leetcode.com"。
 * <p>
 * 接下来会给出一组访问次数和域名组合的列表cpdomains 。要求解析出所有域名的访问次数，输出格式和输入格式相同，不限定先后顺序。
 * <p>
 * 示例 1:
 * 输入:
 * ["9001 discuss.leetcode.com"]
 * 输出:
 * ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
 * 说明:
 * 例子中仅包含一个网站域名："discuss.leetcode.com"。按照前文假设，子域名"leetcode.com"和"com"都会被访问，所以它们都被访问了9001次。
 * <p>
 * cpdomains 的长度小于 100。
 * 每个域名的长度小于100。
 * 每个域名地址包含一个或两个"."符号。
 * 输入中任意一个域名的访问次数都小于10000。
 */
public class Arithmetic02 {

    public static void main(String[] args) {
//        String[] cpdomains = new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};

        String[] cpdomains = new String[]{"9001 discuss.leetcode.com","300 baidu.com","30 google.com","60 pan.baidu.com"};
        System.out.println(forSolution(cpdomains).toString());
        System.out.println(whileSolution(cpdomains).toString());
        System.out.println(recurveSolution(cpdomains).toString());
    }

    /**
     * for +while 方法
     * @param cpdomains
     * @return
     */
    public static List<String> whileSolution(String[] cpdomains){
        List<String> list = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<>();
        for (String cpdomain : cpdomains) {
//            // 先截取访问数和域名
//            String[] temp = cpdomain.split(" ");
//            // 访问数
//            int viewCount = Integer.valueOf(temp[0]);
//            // 域名
//            String domain = temp[1];
            int indexOf = cpdomain.indexOf(" ");
            // 访问数
            int viewCount = Integer.valueOf(cpdomain.substring(0, indexOf));
            // 域名
            String domain = cpdomain.substring(indexOf + 1);

            while (domain.indexOf(".") !=-1){
                mapPutOrAdd(map,domain,viewCount);
                domain = domain.substring(domain.indexOf(".")+1);
            }
            mapPutOrAdd(map,domain,viewCount);
        }
        for (String key :map.keySet())
            list.add(map.get(key)+" "+key);
        return list;
    }

    /**
     * 三层for 循环
     * @param cpdomains
     * @return
     */
    public static List<String> forSolution(String[] cpdomains) {

        List<String> list = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<>();
        // 遍历数组
        for (String cpdomain : cpdomains) {
//            // 先截取访问数和域名
//            String[] temp = cpdomain.split(" ");
//            // 访问数
//            int viewCount = Integer.valueOf(temp[0]);
//            // 域名
//            String domain = temp[1];
            int indexOf = cpdomain.indexOf(" ");
            // 访问数
            int viewCount = Integer.valueOf(cpdomain.substring(0, indexOf));
            // 域名
            String domain = cpdomain.substring(indexOf + 1);

            String[] domains = domain.split("\\.");
            if (domains.length == 0) continue;
            // 拼接出多级域名，都塞入map
            for (int i = 0; i < domains.length; i++) {
                StringBuilder key = new StringBuilder(domains[i]);
                for (int j = i + 1; j < domains.length; j++) {
                    key.append('.');
                    key.append(domains[j]);
                }
                mapPutOrAdd(map,key.toString(), viewCount);
            }
        }
        for (String key :map.keySet())
            list.add(map.get(key)+" "+key);

        return list;
    }

    /**
     * for+ 递归方法
     * @param cpdomains
     * @return
     */
    public static List<String> recurveSolution(String[] cpdomains){
        List<String> list = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<>();
        // 遍历数组
        for (String cpdomain : cpdomains) {
            // 先截取访问数和域名
            String[] temp = cpdomain.split(" ");
            // 访问数
            int viewCount = Integer.valueOf(temp[0]);
            // 域名
            String domain = temp[1];
            // 递归方法
            recurve(map,domain,viewCount);
        }
        for (String key :map.keySet())
            list.add(map.get(key)+" "+key);

        return list;
    }

    /**
     * 递归方法
     * @param map map集合
     * @param domain 网站域名
     * @param viewCount 访问数
     * @return
     */
    public static Map<String,Integer> recurve(Map<String,Integer> map,String domain,Integer viewCount){
        if (domain.indexOf(".") != -1){
            mapPutOrAdd(map,domain,viewCount);
            return recurve(map, domain.substring(domain.indexOf(".")+1),viewCount);
        }else {
            mapPutOrAdd(map,domain,viewCount);
            return map;
        }

    }

    private static void mapPutOrAdd(Map<String,Integer> map, String key, Integer val) {
        if (map.containsKey(key)) {
            map.put(key,map.get(key) + val);
        } else {
            map.put(key,val);
        }
    }
}
