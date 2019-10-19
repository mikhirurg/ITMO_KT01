import java.io.*;
import java.util.*;
import java.lang.*;
public class IntList {
	int[] array;
	int size;
	public IntList() {
		array = new int[2];
		size = 0;
	}
	public void add(int x) {
		if (array.length == size) {
                    array =  Arrays.copyOf(array, (int)(array.length * 1.5));
        	}
		array[size] = x;
		size++;
	}
	public void set(int index, int x) {
		array[index] = x;
	}
	public int get(int index) {
		return array[index];
	}
	public void remove() {
        size--;
    }
	public int size() {
		return size;
	}
}