import java.lang.reflect.Array;
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
     *
     * @param s
     */
    private static void numberOfSubStrings(String s) {
        // 将字符串转换成数组
        char[] chars = s.toCharArray();
        // 创建一个list用来存左右子串
        List<String> stringList = new ArrayList<String>();
        // 创建一个map用来统计子串出现次数
        Map<String, Integer> map = new HashMap<String, Integer>();
        // 从第一位作为子串的开头
        for (int i = 0; i < chars.length; i++) {
            String subString = String.valueOf(chars[i]);
            // 从次位作为子串的结尾
            for (int j = i + 1; j < chars.length; j++) {
                subString += String.valueOf(chars[j]);
                stringList.add(subString);
            }
        }
        stringList.sort(Comparator.naturalOrder()); // 有点多余
        // 遍历list，统计出现次数，加入map
        for (String sub : stringList) {
            // 如果子串已经存到了map中，就把value+1；如果不在map中，就put进去，value=1
            if (map.containsKey(sub)) {
                Integer integer = map.get(sub);
                integer++;
                map.put(sub, integer);
            } else {
                map.put(sub, 1);
            }
        }
        //从map中输出所有key-value，先拿到所有的key
        //建立Set，由于Set不是排序的，所以要转成List，再对List排序
        Set<String> set = map.keySet();
        List<String> keyList = new ArrayList<String>(set);
        Collections.sort(keyList);

        // 题意不够明确，暂时认为是key按照从小到大排，value按照从大到小排
        // 当前的keyList相当于是按照字符串的先后顺序做的排序，还要按照value的顺序排
        // 使用冒泡排序，按照value从大到小的顺序排key
        Object[] keyStrings = keyList.toArray();
        for (int i = 0; i < keyStrings.length - 1; i++) {
            for (int j = 0; j < keyStrings.length - i - 1; j++) {
                if (map.get(String.valueOf(keyStrings[j].toString())) < map.get(String.valueOf(keyStrings[j + 1]))) {
                    Object tmp = keyStrings[j];
                    keyStrings[j] = keyStrings[j + 1];
                    keyStrings[j + 1] = tmp;
                }
            }
        }


        for (Object key : keyStrings) {
            System.out.println(String.valueOf(key) + " " + map.get(String.valueOf(key)));
        }
        System.out.println(Arrays.toString(chars));
        System.out.println(stringList.toString());

    }

}
