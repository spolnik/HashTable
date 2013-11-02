import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {
    private List<String> arrayList;

    @Before
    public void setUp() throws Exception {
        arrayList = new ArrayList<>();
    }

    @Test
    public void testAdd() throws Exception {
        String first = "First";

        arrayList.add(first);
        String result = arrayList.get(0);

        assertThat(result, is(first));
    }

    @Test
    public void testRemove() throws Exception {
        String first = "First";
        String second = "Second";
        String third = "Third";

        arrayList.add(first);
        arrayList.add(second);
        arrayList.add(third);

        assertThat(arrayList.size(), is(3));

        String removedItem = arrayList.remove(1);
        assertThat(removedItem, is(second));

        assertThat(arrayList.size(), is(2));

        assertThat(arrayList.remove(0), is(first));
        assertThat(arrayList.remove(0), is(third));

        assertThat(arrayList.size(), is(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveItemFromEmptyList() throws Exception {
        arrayList.remove(0);
    }

    @Test
    public void testAutoIncreasingCapacityOfArray() throws Exception {
        int count = 1024;

        for (int i = 0; i < count; i++) {
            arrayList.add(Integer.toString(i));
        }

        assertThat(arrayList.size(), is(count));

        for (int i = 0; i < count; i++) {
            assertThat(arrayList.get(i), is(Integer.toString(i)));
        }

        for (int i = 0; i < count; i++) {
            arrayList.remove(0);
        }

        assertThat(arrayList.size(), is(0));
    }

    @Test
    public void testSettingValueToExactIndex() throws Exception {
        String first = "First";
        String second = "Second";
        String third = "Third";

        arrayList.add(first);
        arrayList.add(second);
        arrayList.add(third);

        assertThat(arrayList.size(), is(3));

        String newValue = "NewValue";
        arrayList.set(1, newValue);
        assertThat(arrayList.get(1), is(newValue));
    }

    @Test
    public void testSetValueToExactIndexOnEmptyList() throws Exception {
        String first = "First";
        arrayList.set(2, first);

        assertThat(arrayList.size(), is(3));
        assertThat(arrayList.get(0), is((String)null));
        assertThat(arrayList.get(1), is((String)null));
        assertThat(arrayList.get(2), is(first));

    }
}
