package club.charleyking.collection;

public class MyArrayList implements MyArrayListInterface {
	int size;
	Object[] objs;
	
	// construction method
	public MyArrayList() {
		objs = new Object[3];
		size = 0;
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		objs[size++] = obj;
		
	}

	@Override
	public void remove(Object obj) {
		// TODO Auto-generated method stub
		int flag = 0;
		if (size == 0) {
			return;
		} else {
			for (int i=0; i<size; i++) {
				if (objs[i].equals(obj)) {
					flag = i;
					break;
				}
			}
			System.arraycopy(objs, flag+1, objs, flag, size-flag-1);
			objs[size-1] = null;
			size = size -1;
		}
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		if (size == 0) {
			return;
		} else {
			objs[index] = null;
			System.arraycopy(objs, index+1, objs, index, size-index-1);
			objs[size-1] = null;
			size = size-1;
		}
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return objs[index];
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i=0; i<size; i++) {
			objs[i] = null;
		}
		size = 0;
	}
	
	public static void main(String[] args) {
		MyArrayList list = new MyArrayList();
		list.add("hello");
		list.add("world");
		list.add("java");
		list.remove(2);
		list.clear();
		System.out.println(list.getSize());
		System.out.println(list.get(2));
	}
}
