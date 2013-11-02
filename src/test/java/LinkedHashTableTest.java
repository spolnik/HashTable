import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

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

        MatcherAssert.assertThat(hashTable.get(name1), CoreMatchers.is((Person)null));
        MatcherAssert.assertThat(hashTable.get(name2), CoreMatchers.is((Person)null));
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
}
