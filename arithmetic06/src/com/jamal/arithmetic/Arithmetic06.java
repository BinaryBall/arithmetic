package com.jamal.arithmetic;

public class Arithmetic06 {
    //833. 字符串中的查找与替换
    public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        String nstr = S;
        System.out.println(S);
        int replace_min_index = 0;
        int replace_lenth = 0;
        for (int i = 0;i<indexes.length;i++){
            int index = indexes[i];
            String source = sources[i];
            String target = targets[i];
            int length = 0;
            if (index > replace_min_index && i !=0 && index !=0){
                length = replace_lenth;
                replace_lenth +=target.length()-source.length();
            }else if (i ==0){
                replace_min_index = index;
                replace_lenth +=target.length()-source.length();
            }else if (index <replace_min_index){
                replace_min_index = index;
                length = 0;
                replace_lenth +=target.length()-source.length();
            }
            if (S.substring(index).startsWith(source)){
                System.out.println(i+"------ "+nstr);
                System.out.println(i+"------0 "+nstr.substring(0,index+length));
                System.out.println(i+"------1 "+nstr.substring(index+length));
                nstr = nstr.substring(0,index+length)+nstr.substring(index+length).replaceFirst(source,target);
            }
            System.out.println(i+"-----------nstr "+nstr);
        }
        return nstr;
    }
}
