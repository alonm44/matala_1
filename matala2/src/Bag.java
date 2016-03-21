import java.util.Iterator;
import java.util.NoSuchElementException;


public class Bag<Item> implements Iterable<Item> {
	public Node<Item> first;    // beginning of bag
	public int N;               // number of elements in bag

	// helper linked list class
	static class Node<Item> {
		public Item item;
		public Node<Item> next;
	}

	/**
	 * Initializes an empty bag.
	 */
	public Bag() {
		first = null;
		N = 0;
	}

	/**
	 * Returns true if this bag is empty.
	 *
	 * @return <tt>true</tt> if this bag is empty;
	 *         <tt>false</tt> otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Returns the number of items in this bag.
	 *
	 * @return the number of items in this bag
	 */
	public int size() {
		return N;
	}

	/**
	 * Adds the item to this bag.
	 *
	 * @param  item the item to add to this bag
	 */
	public void add(Item item) {
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	


	/**
	 * Returns an iterator that iterates over the items in this bag in arbitrary order.
	 *
	 * @return an iterator that iterates over the items in this bag in arbitrary order
	 */
	public Iterator<Item> iterator()  {
		return new ListIterator<Item>(first);  
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;

		public ListIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext()  { return current != null;                     }
		public void remove()      { throw new UnsupportedOperationException();  }

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next; 
			return item;
		}
	}

	/**
	 * Unit tests the <tt>Bag</tt> data type.
	 */
	public static void main(String[] args) {
		Bag<String> bag = new Bag<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			bag.add(item);
		}

		StdOut.println("size of bag = " + bag.size());
		for (String s : bag) {
			StdOut.println(s);
		}
	}


}

