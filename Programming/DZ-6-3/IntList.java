import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class IntList implements Iterable<Integer>{
    final private int DEFAULT_SIZE = 128;
    private int pos;
    private int[] list;

    IntList(){
        list = new int[DEFAULT_SIZE];
        pos = 0;
    }

    void add(int x){
        if (pos >= list.length){
            list = Arrays.copyOf(list, list.length*2);
        }
        list[pos++] = x;
    }

    int getInt(int index){
        return list[index];
    }

    void removeInt(int index){
        int i = index;
        while (i < pos){
            list[i] = list[i+1];
            i++;
        }
        pos--;
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            int ind = 0;

            @Override
            public boolean hasNext() {
                return ind < pos;
            }

            @Override
            public Integer next() {
                return list[ind++];
            }
        };
    }

}
