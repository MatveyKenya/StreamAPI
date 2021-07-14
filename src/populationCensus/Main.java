package populationCensus;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        //100 человек всего - потому что в консоль вывожу чтобы проверить
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println(persons);

        //Сколько человек моложе 18 лет
        long countUnder18 = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("\nМоложе 18 лет ---" + countUnder18 + " человек");

        //Список ФАМИЛИЙ призывников - мужчин от 18 до 27 лет включительно
        List<String> surNamesConscripts = persons.stream()
                .filter(person -> person.getAge() > 18 && person.getAge() <= 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("\nПризывники муж от 18 до 27 --- \n" + surNamesConscripts);

        //отсортированный по фамилии список людей с высшим образованием
        // от 18 до 60 лет для женщин и до 65 лет для мужчин
        List<Person> workers = persons.stream()
                .filter(person ->
                        person.getAge() > 18 && person.getEducation() == Education.HIGHER
                                &&
                        (person.getSex() == Sex.MAN && person.getAge() < 65
                                ||
                        person.getSex() == Sex.WOMAN && person.getAge() < 60)
                )
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("\nРаботники с вышкой от 18 и Муж до 65, Жен до 60 --- \n" + workers);

    }
}
