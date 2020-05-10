package nowcode.huawei.practice;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        scordSort();
    }


    public static void scordSort() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = Integer.parseInt(scanner.nextLine());//这里不能用in.nextInt,容易出现格式错误
            int flag = Integer.parseInt(scanner.nextLine());
            Person[] personArr = new Person[n];
            for (int i = 0; i < n; i++) {
                String line = scanner.nextLine();
                String[] split = line.split("\\s+");
                Person person = new Person(split[0], Integer.parseInt(split[1]));
                personArr[i] = person;
            }
            Arrays.sort(personArr);
            if (flag == 0) {
                for (int i = personArr.length - 1; i > -1; i--) {
                    System.out.println(personArr[i].name + " " + personArr[i].scor);
                }
            } else {
                Arrays.asList(personArr).stream().forEach(p -> {
                    System.out.println(p.name + " " + p.scor);
                });
            }
        }
    }

    static class Person implements Comparable<Person> {

        private String name;
        private int scor;

        public Person() {
        }

        public Person(String name, int scor) {
            this.name = name;
            this.scor = scor;
        }


        @Override
        public int compareTo(Person o) {
            return this.scor - o.scor;
        }
    }


    // 输出单向链表中倒数第K个节点
    public static void testLinked() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int totalN = scanner.nextInt();
            List<Integer> linkLst = new LinkedList<>();
            for (int i = 0; i < totalN; i++) {
                linkLst.add(scanner.nextInt());
            }
            int targetIndex = scanner.nextInt();
            System.out.println(linkLst.get(totalN - targetIndex));
        }
    }


    // 明文加密
    public static void encrypt() {
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            // 秘钥
            String key = input.nextLine();
            // 明文
            String word = input.nextLine();

            System.out.println(encrypt(key, word));
        }
    }

    /**
     * 加密
     *
     * @param key  秘钥
     * @param word 明文
     * @return 密文
     */
    private static String encrypt(String key, String word) {
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // 先将 key 中的字符变成只出现一次
        StringBuilder keyTemp = new StringBuilder();
        char[] keyChars = key.toCharArray();
        for (char aChar : keyChars) {
            if (keyTemp.indexOf(String.valueOf(aChar)) == -1) {
                keyTemp.append(aChar);
            }
        }

        // 拼接：字符只出现一次的完整的 key
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            if (keyTemp.length() == 26) {
                break;
            }

            if (keyTemp.indexOf(String.valueOf(aChar)) == -1 &&
                    !keyTemp.toString().toUpperCase().contains(String.valueOf(aChar))) {
                keyTemp.append(aChar);
            }
        }

        // 最终完整的 key
        key = keyTemp.toString();
        StringBuilder result = new StringBuilder();
        // 拼接：密文
        for (int i = 0; i < word.length(); i++) {
            // 大写
            if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                result.append(String.valueOf(key.charAt(word.charAt(i) - 65)).toUpperCase());
                continue;
            }

            // 小写
            if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
                result.append(String.valueOf(key.charAt(word.charAt(i) - 97)).toLowerCase());
                continue;
            }

            if (word.charAt(i) == ' ') {
                result.append(' ');
            }
        }

        return result.toString();
    }


}
