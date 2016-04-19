import java.util.*;

/**
 *
 * @author Heather Bradfield
 * @version 11/01/2015
 */

public class SetDictionary<K extends Comparable<? super K>> implements SetInterface<K>, Iterable<K>
{
    private TreeMap<K, Boolean> items;

    public SetDictionary()
    {
        this.items = new TreeMap<>();

    } // end default constructor

    public boolean add(K newEntry)
    {
        return this.items.put(newEntry,true) != null;
    } // end add

    public boolean remove(K anEntry)
    {
        return this.items.remove(anEntry) != null;
    } // end remove

    public void clear()
    {
        this.items.clear();
    } // end clear

    public boolean contains(K anEntry)
    {
        return this.items.containsKey(anEntry);
    } // end contains

    public int getCurrentSize()
    {
        return this.items.size();
    } // end getCurrentSize

    public boolean isEmpty()
    {
        return this.items.isEmpty();
    } // end isEmpty

    public Iterator<K> getIterator()
    {
        return this.items.keySet().iterator();
    } // end getIterator

    public Iterator<K> iterator()
    {
        return getIterator();
    } // end iterator

    public K[] toArray()
    {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")

        K[] result = (K[]) new Comparable[getCurrentSize()]; // unchecked cast

        //MUST BE IMPLEMENTED WITH ITERATOR

        Iterator<K> keyIter = getIterator();
        int i = 0;
        while(keyIter.hasNext())
        {
            result[i] = keyIter.next();
            i++;
        }
        return result;
    } // end toArray

    public SetInterface<K> union(SetInterface<K> otherSet)
    {
        SetInterface<K> result = new SetDictionary<>();

        //MUST BE IMPLEMENTED WITH ITERATORS
        Iterator<K> thisIter = getIterator();
        Iterator<K> otherIter = otherSet.getIterator();
        while(thisIter.hasNext())
        {
            result.add(thisIter.next());
        }
        while(otherIter.hasNext())
        {
            result.add(otherIter.next());
        }
        return result;
    } // end union

    public SetInterface<K> intersection(SetInterface<K> otherSet)
    {
        SetInterface<K> result = new SetDictionary<>();

        //MUST BE IMPLEMENTED WITH ITERATORS
        Iterator<K> thisIter = getIterator();
        Iterator<K> otherIter = otherSet.getIterator();

        //UTILIZE TRY_CATCH BLOCK
        try
        {
            K thisKey = thisIter.next();
            K otherKey = otherIter.next();

            while (true)
            {
                int compare = thisKey.compareTo(otherKey);
                if (compare > 0)
                {
                    otherKey = otherIter.next();
                }
                else if (compare < 0)
                {
                    thisKey = thisIter.next();
                }
                else
                {
                    result.add(thisKey);
                    thisKey = thisIter.next();
                    otherKey = otherIter.next();
                }

            } // end while
        } catch (NoSuchElementException nsee)
        {
            System.out.println("Finished creating intersection.");
        }

        return result;
    } // end intersection

    public static void main(String args[])
    {
        System.out.println("CREATING set1");
        SetInterface<Integer> set1 = new SetDictionary<>();
        set1.add(1);
        set1.add(3);
        set1.add(2);
        set1.add(0);
        set1.add(-1);

        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("-->contains for -1 yields " + set1.contains(-1));
        System.out.println("-->contains for -2 yields " + set1.contains(-2));
        System.out.println("-->contains for 3 yields " + set1.contains(3));
        System.out.println("-->contains for 4 yields " + set1.contains(4));

        set1.add(1);
        System.out.println("-->Added 1 again to the set1, should be the same");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        Iterator<Integer> kIter = set1.getIterator();
        System.out.println("-->Iterating over the set1 ");

        while (kIter.hasNext())
        {
            System.out.println("\t" + kIter.next());
        } // end while

        System.out.println("-->Removing -1 20 3 from set1");
        set1.remove(-1);
        set1.remove(20);
        set1.remove(3);

        System.out.println("-->Should just have 0 1 and 2 now");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("CREATING set2");
        SetInterface<Integer> set2 = new SetDictionary<>();
        set2.add(1);
        set2.add(3);
        set2.add(2);
        set2.add(-1);
        set2.add(5);
        set2.add(8);

        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING UNION OF set1 and set2");
        SetInterface<Integer> testUnion = set1.union(set2);
        System.out.print("-->The union of set1 and set2 has " + testUnion.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testUnion.toArray()));
        System.out.println();

        System.out.println("-->set1 should be unchanged");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("-->set2 should be unchanged");
        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING INTERSECTION OF set1 and set2");
        SetInterface<Integer> testIntersection = set1.intersection(set2);
        System.out.print("-->The intersection of set1 and set2 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("-->set1 should be unchanged");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("-->set2 should be unchanged");
        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING INTERSECTION OF set2 and set1");
        testIntersection = set2.intersection(set1);
        System.out.print("-->The intersection of set2 and set1 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("-->set1 should be unchanged");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("-->set2 should be unchanged");
        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING INTERSECTION OF set2 and set2");
        testIntersection = set2.intersection(set2);
        System.out.print("-->The intersection of set2 and set2 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("-->set1 should be unchanged");
        System.out.println("-->set1 has " + set1.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set1.toArray()));
        System.out.println();

        System.out.println("-->set2 should be unchanged");
        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();

        System.out.println("CREATING INTERSECTION OF testUnion and set2");
        testIntersection = testUnion.intersection(set2);
        System.out.print("-->The intersection of testUnion and set2 has " + testIntersection.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testIntersection.toArray()));
        System.out.println();

        System.out.println("-->testUnion should be unchanged");
        System.out.println("-->testUnion has " + testUnion.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(testUnion.toArray()));
        System.out.println();

        System.out.println("-->set2 should be unchanged");
        System.out.println("-->set2 has " + set2.getCurrentSize() + " items: ");
        System.out.println(Arrays.toString(set2.toArray()));
        System.out.println();
    } // end main
}
