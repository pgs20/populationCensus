import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // Количество несовершеннолетних детей
//        long countChildren = persons.stream().filter(person -> person.getAge() < 18).count();

        // Получение списка фамилий призывников (мужчин в возрасте от 18 до 27)
//        List<String> listConscripts = persons.stream()
//                .filter(person -> person.getSex().equals(Sex.MAN))
//                .filter(person -> (person.getAge() > 17 && person.getAge() < 28))
//                .map(person -> person.getFamily())
//                .collect(Collectors.toList());

        // Получение отсортированного по фамилиям списка людей
        // с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин
        List<Person> listPersonWithHE = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> {
                    if (person.getSex().equals(Sex.WOMAN)) {
                        return (person.getAge() > 17) && (person.getAge() < 61);
                    } else {
                        return (person.getAge() > 17) && (person.getAge() < 66);
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println(listPersonWithHE);
    }
}
