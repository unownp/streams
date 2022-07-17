import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static final String PATH="src/main/resources/1.txt";
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");
        list.add("Five");
        list.add("Six");
        list.add("Seven");
        list.add("Eight");
        list.add("Nine");
        list.add("Ten");
        Stream stream = list.stream();
       // stream.filter(x-> x.toString().length() == 3).forEach(System.out::println);
        //Stream<String> streamFromFiles = Files.lines(Paths.get(PATH));
        //streamFromFiles.forEach(System.out::println);

        Person Vasya=new Person();
        Person Petya=new Person();

        Vasya.setAge(29);
        Vasya.setName("Vasya");
        Vasya.setSurname("Ivanov");

        Petya.setAge(39);
        Petya.setName("Petya");
        Petya.setSurname("Kuznecov");

        List<Person> personList=new ArrayList();
        List<Person> personListFiltered=new ArrayList();
        personList.add(Petya);
        personList.add(Vasya);
        personList.add(Vasya);
        Stream<Person> streamPerson=personList.stream();
        personListFiltered=streamPerson.filter((p)-> p.getAge() < 35
                && p.getName().contains("V")).distinct().collect(Collectors.toList());
        System.out.println(personListFiltered);
    }
}
