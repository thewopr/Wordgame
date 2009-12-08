/**
 * An implementation of a minimum heap with handles
 */

public class Heap {

	private HeapElt[] array;
	int heapsize;
	int arraysize;

	/*
	 * The constructor has been set up with an initial array of size 4 so that
	 * your doubleHeap() method will be tested. Don't change this!
	 */
	public Heap() {
		setArray(new HeapElt[4]);
		heapsize = 0;
		arraysize = 4;
	}

	public HeapElt get(int i) {
		return array[i];
	}

	/*
	 * Exchanges that values at positions pos1 and pos2 in the heap array.
	 * Handles must be exchanged correctly as well.
	 * 
	 * Running time = O(1)
	 */
	private void exchange(int pos1, int pos2) {

		HeapElt temp = getArray()[pos1];
		getArray()[pos1] = getArray()[pos2];
		getArray()[pos2] = temp;

		int tempH = getArray()[pos1].getHandle();
		getArray()[pos1].setHandle(getArray()[pos2].getHandle());
		getArray()[pos2].setHandle(tempH);
	}

	/*
	 * Doubles the size of the array. A new array is created, the elements in
	 * the heap are copied to the new array, and the array data member is set to
	 * the new array. Data member arraysize is set to the size of the new array.
	 * 
	 * Running time = O(n)
	 */
	private void doubleHeap() {

		// PROVIDE YOUR IMPLEMENTATION HERE

		arraysize = arraysize * 2;

		HeapElt[] newArray = new HeapElt[arraysize];

		for (int i = 1; i < getHeapsize() + 1; i++)
			newArray[i] = getArray()[i];

		setArray(newArray);
	}

	/*
	 * Fixes the heap if the value at position pos may be smaller than its
	 * parent. Using exchange() to swap elements will simplify your
	 * implementation. Heap elements contain records that are Comparable; you
	 * will need to figure out what to test and how to test it.
	 * 
	 * Running time = O(log(n))
	 */
	public void heapifyUp(int pos) {

		if (pos <= 1)
			return;

		int parent = (int) (pos / 2);

		if (getArray()[pos].getRecord().compareTo(
				getArray()[parent].getRecord()) < 0) {
			exchange(pos, parent);
			heapifyUp(parent);
		}
	}

	/*
	 * Fixes the heap if the value at position pos may be bigger than one of its
	 * children. Using exchange() to swap elements will simplify your
	 * implementation. Heap elements contain records that are Comparable; you
	 * will need to figure out what to test and how to test it.
	 * 
	 * Running time = O(log(n))
	 */
	public void heapifyDown(int pos) {

		int smaller;

		int c1 = pos * 2;
		int c2 = c1 + 1;

		if (c2 > arraysize)
			return;

		if (getArray()[c1] == null && getArray()[c2] == null)
			return;
		else if (getArray()[c1] == null && getArray()[c2] != null)
			smaller = c2;
		else if (getArray()[c1] != null && getArray()[c2] == null)
			smaller = c1;
		else if (getArray()[c1].getRecord().compareTo(
				getArray()[c2].getRecord()) < 0)
			smaller = c1;
		else
			smaller = c2;

		if (getArray()[smaller].getRecord().compareTo(
				getArray()[pos].getRecord()) < 0) {
			exchange(smaller, pos);
			heapifyDown(smaller);
		}
	}

	public void add(HeapElt inElt) {
		insert(inElt);
	}

	/*
	 * Insert inElt into the heap. Before doing so, make sure that there is an
	 * open spot in the array for doing so. If you need more space, call
	 * doubleHeap() before doing the insertion.
	 * 
	 * Running time = O(log(n)) (NOTE that there are a couple of different cases
	 * here!)
	 */
	public void insert(HeapElt inElt) {

		if (getHeapsize() + 1 >= arraysize) {
			doubleHeap();
		}

		heapsize++;

		inElt.setHandle(getHeapsize());

		getArray()[getHeapsize()] = inElt;

		heapifyUp(getHeapsize());
	}

	/*
	 * Remove the minimum element from the heap and return it. Restore the heap
	 * to heap order. Assumes heap is not empty, and will cause an exception if
	 * the heap is empty.
	 * 
	 * Running time = O(log(n))
	 */

	public HeapElt removeMin() {

		HeapElt ret = getArray()[1];

		getArray()[1] = getArray()[getHeapsize()];
		getArray()[getHeapsize()] = null;
		this.heapsize--;

		this.heapifyDown(1);

		return ret;

	}

	/*
	 * Return the number of elements in the heap..
	 * 
	 * Running time = O(1)
	 */
	public int getHeapsize() {
		return heapsize;
	}

	/*
	 * Print out the heap for debugging purposes. It is recommended to print
	 * both the key from the record and the handle.
	 * 
	 * Running time = O(n)
	 */
	public void printHeap() {
		for (int i = 1; i < heapsize + 1; i++) {
			if (getArray()[i] != null)
				System.out.println(getArray()[i].toString());
		}
		System.out.print("\n");
	}

	public void printFullHeap() {
		for (int i = 1; i < array.length; i++) {
			if (getArray()[i] != null)
				System.out.println(getArray()[i].toString());
		}
		System.out.print("\n");

	}
	
	public boolean isEmpty() {
		return getHeapsize() <= 0;
	}

	public void setArray(HeapElt[] array) {
		this.array = array;
	}

	public HeapElt[] getArray() {
		return array;
	}

}
