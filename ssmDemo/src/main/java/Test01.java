import java.util.*;
import java.util.stream.Collectors;

public class Test01 {

    public static void main(String[] args) {

        List<Integer> numList = new ArrayList<>();

        for (int i=1;i<=1000;i++){
            int num=(int)(Math.random()*101);
            numList.add(num);
        }
        System.out.println("集合长度："+numList.size());
        Collections.sort(numList);
        System.out.println("***************分割线*********************");


        //删除10%的元素
        for (int i = (numList.size()-(int)(numList.size()*0.1));i<numList.size();i++){

            Iterator<Integer> iterator = numList.iterator();
            while (iterator.hasNext()){

                if (numList.get(i).equals(iterator.next())){
                    iterator.remove();
                }

            }

        }

        System.out.println("集合长度："+numList.size());


        //取最大值   最小值

        int minValue= numList.get(0);
        int maxValue=numList.get(numList.size()-1);

        System.out.println("最小值："+minValue+",最大值："+maxValue);

        System.out.println("*****************遍历元素*************************");

            for (int i : numList){
                System.out.println(i);
            }

            //调用方法
            funStream((ArrayList<Integer>) numList,10);
    }
    private static void funStream(ArrayList<Integer> list, int space) {
        // 使用流处理 把 数字分组
        Map<Integer, List<Integer>> map = list.stream().collect(Collectors.groupingBy((num) -> {
            return num / space;// 分组规则
        }));

        // 获取集合里的最大值
        Optional<Integer> maxop = list.stream().collect(Collectors.maxBy(Integer::compareTo));
        int max = maxop.orElse(0);
        // 计算出区间的数量
        int groups = max % space == 0 ? max / space : max / space + 1;
        // 打印结果
        for (int i = 0; i < groups; i++) {
            System.out.println("区间:[" + i * space + "," + (i + 1) * space + ")");
            System.out.println("\t有" + (map.get(i)==null?0:map.get(i).size()) + "个数据");
            // System.out.println("\t"+map.get(i));//把数据打印出来
        }
    }
}
