package prll;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public void withStream() {
        System.out.println("1");
        IntStream
                .of(1,7,4,3,6,8)
                .sequential()
                .filter( el -> el > 10 )

                .forEach( el -> System.out.println(el));

        Stream.of("abc", "pqr", "lmn");
    }

    public void withoutStream() {
        System.out.println("2");
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(7);
        nums.add(4);
        nums.add(3);
        nums.add(6);
        nums.add(8);

        for (int num : nums ) {
            System.out.println(num * num);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.withoutStream();
        main.withStream();
    }
}