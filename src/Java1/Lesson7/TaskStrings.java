package Java1.Lesson7;

public class TaskStrings {
    private static final String EXAMPLE_STRING = "I like Java!!!";
    public static void main(String[] args) {
        //1
        receiveString(EXAMPLE_STRING);
        //2
        System.out.println(EXAMPLE_STRING.charAt(EXAMPLE_STRING.length()-1));
        //3
        System.out.println(EXAMPLE_STRING.endsWith("!!!"));
        //4
        System.out.println(EXAMPLE_STRING.startsWith("I like"));
        //5
        System.out.println(EXAMPLE_STRING.contains("Java"));
        //6
        System.out.println(EXAMPLE_STRING.indexOf("Java"));
        //7
        System.out.println(EXAMPLE_STRING.replaceAll("a","o"));
        //8
        System.out.println(EXAMPLE_STRING.toUpperCase());
        //9
        System.out.println(EXAMPLE_STRING.toLowerCase());
        //10
        System.out.println(EXAMPLE_STRING.substring(EXAMPLE_STRING.indexOf("Java"), EXAMPLE_STRING.indexOf("Java") + 4));


    }

    public static void receiveString(String s){
        System.out.println(s);
    }

}
