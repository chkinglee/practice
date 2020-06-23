import java.util.*;

/**
 * 快手面试
 */
public class KuaiShou {

    public static void main(String[] args) {

        String s = "abcdabc";
        numberOfSubStrings(s);
    }

    /**
     * 输入一个字符串如"abcdabc"  找出所有长度至少为2的子串，并统计子串出现次数，倒序输出所有子串的出现次数
     * @param s
     */
    private static void numberOfSubStrings(String s) {
        char[] chars = s.toCharArray();
        List<String> stringList = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < chars.length; i++) {
            String subString = String.valueOf(chars[i]);
            for (int j = i + 1; j < chars.length; j++) {
                subString += String.valueOf(chars[j]);
                stringList.add(subString);
            }
        }
        stringList.sort(Comparator.naturalOrder()); // 有点多余
        for (String value : stringList) {
            if (map.containsKey(value)) {
                Integer integer = map.get(value);
                integer++;
                map.put(value, integer);
            } else {
                map.put(value, 1);
            }
        }
        //建立Set，由于Set不是排序的，所以要转成List，再对List排序
        Set<String> set = map.keySet();
        List<String> keyList = new ArrayList<String>(set);
        Collections.sort(keyList);

        for (String key : keyList) {
            System.out.println(key +" "+ map.get(key));
        }
        System.out.println(Arrays.toString(chars));
        System.out.println(stringList.toString());

    }

}
