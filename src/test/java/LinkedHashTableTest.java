import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.Set;

public class LinkedHashTableTest {
    private HashTable<String, Person> hashTable;

    @Before
    public void setUp() throws Exception {
        hashTable = new LinkedHashTable<>();
    }

    @Test
    public void testAdd() throws Exception {
        String name1 = "Mikolaj";
        hashTable.add(name1, new Person(name1, 1));

        String name2 = "Julia";
        hashTable.add(name2, new Person(name2, 4));

        Person mikolaj = hashTable.get(name1);

        MatcherAssert.assertThat(mikolaj.getName(), CoreMatchers.is(name1));
        MatcherAssert.assertThat(mikolaj.getAge(), CoreMatchers.is(1));

        Person julia = hashTable.get(name2);

        MatcherAssert.assertThat(julia.getName(), CoreMatchers.is(name2));
        MatcherAssert.assertThat(julia.getAge(), CoreMatchers.is(4));

        System.out.println("testAdd(): " + hashTable.toString());
    }

    @Test
    public void testRemove() throws Exception {
        String name1 = "Mikolaj";
        hashTable.add(name1, new Person(name1, 1));

        String name2 = "Julia";
        hashTable.add(name2, new Person(name2, 4));

        Person mikolaj = hashTable.remove(name1);

        MatcherAssert.assertThat(mikolaj.getName(), CoreMatchers.is(name1));
        MatcherAssert.assertThat(mikolaj.getAge(), CoreMatchers.is(1));

        Person julia = hashTable.remove(name2);

        MatcherAssert.assertThat(julia.getName(), CoreMatchers.is(name2));
        MatcherAssert.assertThat(julia.getAge(), CoreMatchers.is(4));

        MatcherAssert.assertThat(hashTable.get(name1), CoreMatchers.is((Person) null));
        MatcherAssert.assertThat(hashTable.get(name2), CoreMatchers.is((Person) null));

        System.out.println("testRemove(): " + hashTable.toString());
    }

    @Test
    public void testGetKeys() throws Exception {
        String name1 = "Mikolaj";
        hashTable.add(name1, new Person(name1, 1));

        String name2 = "Julia";
        hashTable.add(name2, new Person(name2, 4));

        Set<String> keys = hashTable.getKeys();
        MatcherAssert.assertThat(keys.contains(name1), CoreMatchers.is(true));
        MatcherAssert.assertThat(keys.contains(name2), CoreMatchers.is(true));

        System.out.println("testGetKeys(): " + hashTable.toString());
    }

    @Test
    public void testMoreThanOneHoundredItems() throws Exception {
        for (int i = 0; i < 100; i++) {
            String name = "Name_" + i;
            hashTable.add(name, new Person(name, i + 1));
        }

        for (int i = 0; i < 100; i++) {
            String name = "Name_" + i;
            Person person = hashTable.get(name);

            MatcherAssert.assertThat(person.getName(), CoreMatchers.is(name));
            MatcherAssert.assertThat(person.getAge(), CoreMatchers.is(i + 1));
        }

        System.out.println("testMoreThanOneHoundredItems(): " + hashTable.toString());
    }

    @Test(expected = InvalidStateException.class)
    public void testAddTheSameKeyThrowsException() throws Exception {
        String name1 = "Mikolaj";
        hashTable.add(name1, new Person(name1, 1));
        hashTable.add(name1, new Person(name1, 1));
    }
}

class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
