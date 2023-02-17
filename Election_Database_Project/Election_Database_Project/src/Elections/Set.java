package Elections;

import java.io.Serializable;
//import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

public class Set<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	int size;
	int index;
	Vector<T> collection;

	public Set() {
		this.size = 10;
		this.index=0;//num of items in the set 
		this.collection = new Vector<T>(size);
	}
	//TODO
	public boolean add(T E) throws Exception  {
		if (!(E instanceof Voter)) {
			throw new Exception("the element to insert is not from the same type");
		}
		this.collection.add(index, E);
		this.index++;
		if(index==size) {
			size*=2;
		}
		return true;
	}

	public boolean addAll(Vector<T> collection) {
		Iterator<T> iter = (Iterator<T>) collection.iterator();
		while (iter.hasNext()) {
			if (iter == this.collection.elementAt(this.index)) {
				return false;
			}
			this.collection.add((T)(collection.elementAt(this.index)));
			index++;
			iter.next();
		}
		return false;
	}

	public void clear() {
		this.collection.clear();
		this.size = 0;
	}

	public boolean contains(T E) {
		if (this.collection.contains(E)) {
			return true;
		}
		return false;
	}

	public boolean containsAll(Vector<T> collection) {
		Iterator<T> iter = collection.iterator();
		while (iter.hasNext()) {
			if (!this.collection.contains(iter)) {
				return false;
			}
			iter.next();
		}
		return true;
	}

	public boolean equals(Vector<Object> obj) {
		if (!super.equals(obj))
			return false;

		return this.collection.size() == obj.size();
	}

	public int hashCode() {
		return this.collection.hashCode();
	}

	public boolean isEmpty() {
		if (this.collection == null) {
			return true;
		}
		return false;
	}

	public Iterator<T> iterator() {
		Iterator<T> iter = this.collection.iterator();
		return iter;
	}

	public boolean remove(T E) {
		if (isEmpty() || !this.collection.contains(E)) {
			return false;
		}
		this.collection.remove(E);
		this.size--;
		return true;
	}

	public boolean removeAll(Vector<T> collection) {
		Iterator<T> iter = collection.iterator();
		while (iter.hasNext() || this.collection.size() == 0) {
			if (this.collection.contains(iter)) {
				this.collection.remove(iter);
				this.size--;
			}
			iter.next();
		}
		return true;
	}

	public boolean retainAll(Vector<T> Set) {
		Iterator<T> iter=collection.iterator();
		int i=0;//index for set
		while(iter.hasNext() || this.size!=0) {
			if(!this.collection.contains(iter)) {
				this.collection.remove(i);
				this.size--;
			}
			iter.next();
		}
		return true;
	}
	
	public int size() {
		return this.size;
	}
	
	public Object[] toArray() {
		return this.collection.toArray();
	}

	public <T> T[] toArray(T[] array) {
		Object[] tempArray=new Object[array.length];
		int index=0;
		int indexElement;
		for (int i = 0; i < array.length; i++) {
			if(this.collection.contains(array[i])) {
				indexElement=this.collection.indexOf(array[i]);
				tempArray[index++]=this.collection.elementAt(indexElement);
			}
		}
		return (T[]) tempArray;
	}

}