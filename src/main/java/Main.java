import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static final String PATH = "src/main/resources/1.txt";

    public static void main(String[] args) throws IOException {

        Person vasya = new Person(29, "Vasya", "Ivanov");
        Person petya = new Person(39, "Petya", "Ivanov");
        Person masha = new Person(17, "Masha", "Petrova");
        Person elena = new Person(52, "Elena", "Alexeeva");
        Person nikolay = new Person(75, "Nikolay", "Kuznecov");
        Person ruslan = new Person(12, "Ruslav", "Aliev");

        List<Person> personList = new ArrayList<>();
        personList.add(vasya);
        personList.add(petya);
        personList.add(masha);
        personList.add(elena);
        personList.add(nikolay);
        personList.add(ruslan);

        List<Person> personList1 = personList.stream()
                .map(Person::new).collect(Collectors.toList());
        personList1.forEach(person -> person.setAge(person.getAge() + 10));
        System.out.println(personList);
        System.out.println(personList1);

        List<Integer> ageList = personList.stream().map(Person::getAge).toList();
        System.out.println(ageList);

        personList1 = personList1.stream().filter(p -> p.getAge() < 30 && p.getAge() > 20).collect(Collectors.toList());
        System.out.println(personList1);

        int product = ageList.stream().reduce((accumulator, element) -> (accumulator * element)).get();
        System.out.println(product);

        personList = personList.stream().sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList());
        System.out.println(personList);

        //не united и не terminal
        List<Person> unitedList = Stream.concat(personList.stream(), personList1.stream()).toList();
        System.out.println(unitedList);

        boolean b1 = unitedList.stream().allMatch(x -> x.getAge() < 50);
        System.out.println(b1);

        boolean b2 = unitedList.stream().anyMatch(x -> x.getAge() < 0);
        System.out.println(b2);

        boolean b3 = unitedList.stream().noneMatch(x -> x.getName().startsWith("x"));
        System.out.println(b3);

        Long l = unitedList.stream().distinct().count();
        System.out.println(l);

        int min = ageList.stream().max(Comparator.reverseOrder()).get();
        System.out.println(min);

        unitedList = unitedList.stream().peek(p -> {
            p.setName(p.getName() + p.getSurname());
            System.out.print(p.getName() + " ");
        }).collect(Collectors.toList());
        System.out.println();

        List<List<Person>> listListPerson = new ArrayList<>();
        listListPerson.add(unitedList);
        listListPerson.add(personList);
        List<Person> flatMappedList = listListPerson.stream().flatMap(Collection::stream).toList();
        System.out.println(flatMappedList.size());

        Map<Integer, List<Person>> groupMap = flatMappedList.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(groupMap);

        Map<Boolean, List<Person>> partitionMap = flatMappedList.stream()
                .collect(Collectors.partitioningBy(e -> e.getSurname().length() < 7));
        System.out.println(partitionMap);

        Stream<String> streamFromFiles = Files.lines(Paths.get(PATH));
        streamFromFiles.forEach(System.out::println);
    }
}
