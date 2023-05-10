import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private int age;
    private String name;
    private String surname;

    //конструктор копирования
    public Person(Person other) {
        this(other.age, other.name, other.surname);
    }

}
