package collection.list;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Grady on 2017/8/3.
 */
public class ArrayListPractice {
    static ArrayList<Integer> list = new ArrayList<>();
    static List<Entity> eList = new ArrayList<>();
    public static void main(String[] args) {
        List<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("1");
        arrayList1.add("2");
        SOUT: for (String s : arrayList1) {
            System.out.println("s = " + s);
            for (String s1 : arrayList1) {
                System.out.println("s1 = " + s);
                if ("1".equals(s1)) {
                    System.out.println("11111");
                    break SOUT;
                }
            }
        }
//        List<String> arrayList2 = new ArrayList<String>();
//        arrayList2.add("2");
//        arrayList2.add("1");
//        for (String s : arrayList2) {
//            if("1".equals(s)){
//                arrayList2.remove(s);
//            }
//        }

//        getList();
//        list.forEach(i->i = new Integer(i+1));
//        System.out.println(list);
//        list.forEach(i->{
//            if(i<4){
//                return;
//            }
//            System.out.println("i = " + i);
//        });
//        list = (ArrayList<Integer>) list.stream().map(i-> i.equals(2)?i+1:i).collect(Collectors.toList());
//        System.out.println(list);
//        while (CollectionUtils.isNotEmpty(list)){
//            List sub = list.subList(0,1);
//            System.out.println(sub);
//            sub.clear();
//            System.out.println(list);
//        }

//        Calendar calendar = Calendar.getInstance();
//       int week = calendar.get(Calendar.DAY_OF_WEEK);
//        System.out.println("week = " + week);
//       List t  =   Stream.of("one", "two", "three", "four")
//                .filter(p -> p.length() > 3)
//                .peek(v -> System.out.println("Filtered Value:" + v))
//                .map(String::toUpperCase)
//                .peek(v -> System.out.println("Value:" + v))
//                .collect(Collectors.toList());
//
//        System.out.println("Arrays.toString(list.toArray()) = " + Arrays.toString(t.toArray()));
//        getList();
//        List a =  list.stream().filter(l-> l==2||l==5).peek(l->{
//
//        }).collect(Collectors.toList());
//        System.out.println("Arrays.toString(list.toArray()) = " + Arrays.toString(a.toArray()));

    }

    static void listFor(){
        for (Integer v:list)
            System.out.println("v = " + v);
    }
    static void listLambda(){
        list.forEach(item-> System.out.println("v = " + item));
        list.forEach(System.out::println);
    }

    //===========================List 操作 ===========================//
    /**
     * list 排序
     */
    static void sortList(){
        Collections.sort(list);
        list.forEach(v->{
            System.out.println(v);
        });
    }
    static void sortEntity(){
        getList(5);
        eList.forEach(v->{
            System.out.println(v.getName()+"   "+v.getAge());
        });
        System.out.println("------------------------------------------------------");
        //排序 1compareto2 生序
//        Collections.sort(eList, new Comparator<Entity>() {
//            @Override
//            public int compare(Entity o1, Entity o2) {
//                return o2.getAge().compareTo(o1.getAge());
//            }
//        });
        //stream
//        eList.sort(Comparator.comparing(Entity::getAge).reversed());
        eList = eList.stream().sorted(Comparator.comparing(Entity::getAge)).collect(Collectors.toList());
        eList.forEach(v->{
            System.out.println(v.getName()+"   "+v.getAge());
        });
    }
    static void remove(){

        list.subList(2,4).clear();
        System.out.println(list.toString());
    }


    static void getList(){
        list.add(1232333);
        list.add(2);
        list.add(2);
        list.add(9444);
        list.add(34555);
        list.add(4);
        list.add(5);
        list.add(7);
    }

    static void getList(int size){
        int max = 40;
        Random random = new Random();
        for(int i = 1;i<= size;i++){
            eList.add(new Entity("entity_"+i, random.nextInt(max)));
        }
    }

    static class Entity{
        public Entity(String name,Integer age){
            this.age=age;
            this.name=name;
        }
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
