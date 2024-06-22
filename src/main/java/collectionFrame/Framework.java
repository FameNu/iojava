package collectionFrame;

import java.util.*;

public class Framework {
    public void arrayListLab() {
        List<String> list = new ArrayList<>();
        list.add("John");
        list.add("Doe");
        list.add("Smith");
        list.add("Jane");
        list.add("Doe");
        System.out.println("List: " + list);
    }

    public void linkListLab() {
        List<String> list = new LinkedList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Mango");
        list.add("Orange");

        System.out.println(list);

        // show value of index 3
        System.out.println(list.get(3));

        // add 'avocado' at index 3
        list.add(3, "avocado");
        System.out.println(list);

        // edit value of index 2 to 'Blueberry'
        list.set(2, "Blueberry");
        System.out.println(list);

        // delete value at index 1
        list.remove(1);
        System.out.println(list);

        // delete 'Mango' from the list
        list.remove("Mango");
        System.out.println(list);
    }

    public void stackLab() {
        Stack<String> stack = new Stack<>();
        stack.push("John");
        stack.push("Doe");
        stack.push("Smith");
        // size
        System.out.println("Size: " + stack.size());
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public void queueLab() {
        Queue<String> queue = new LinkedList<>();
        queue.add("John");
        queue.add("Doe");
        queue.add("Smith");
        // size
        System.out.println("Size: " + queue.size());
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    public void setLab() {
        Set<String> set = new HashSet<>();
        set.add("One");
        set.add("Two");
        set.add("Three");
        set.add("Four");
        System.out.println(set);

        set.clear();
        System.out.println(set);
    }

    public void mapLab() {
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);
        System.out.println(map);
    }
}
