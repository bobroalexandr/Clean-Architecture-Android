package demo.util;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created on 31.10.2015.
 */
public class ImmutableList<E> implements List<E> {

	private final List<E> items;

	ImmutableList(List<E> items) {
		this.items = items;
	}

	@Override
	public void add(int location, E object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(E object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int location, @NonNull Collection<? extends E> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(@NonNull Collection<? extends E> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object object) {
		return items.contains(object);
	}

	@Override
	public boolean containsAll(@NonNull Collection<?> collection) {
		return items.containsAll(collection);
	}

	@Override
	public E get(int location) {
		return items.get(location);
	}

	@Override
	public int indexOf(Object object) {
		return items.indexOf(object);
	}

	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	@NonNull
	@Override
	public Iterator<E> iterator() {
		return new ImmutableIterator<>(items.iterator());
	}

	@Override
	public int lastIndexOf(Object object) {
		return items.lastIndexOf(object);
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ImmutableListIterator<>(items.listIterator());
	}

	@NonNull
	@Override
	public ListIterator<E> listIterator(int location) {
		return new ImmutableListIterator<>(items.listIterator(location));
	}

	@Override
	public E remove(int location) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int location, E object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return items.size();
	}

	@NonNull
	@Override
	public List<E> subList(int start, int end) {
		return new ImmutableList<>(items.subList(start, end));
	}

	@NonNull
	@Override
	public Object[] toArray() {
		return items.toArray();
	}

	@NonNull
	@Override
	public <T> T[] toArray(@NonNull T[] array) {
		//noinspection SuspiciousToArrayCall
		return items.toArray(array);
	}

	private static class ImmutableIterator<T> implements Iterator<T> {

		private final Iterator<T> iterator;

		public ImmutableIterator(Iterator<T> iterator) {
			this.iterator = iterator;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public T next() {
			return iterator.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private static class ImmutableListIterator<T> implements ListIterator<T> {

		final ListIterator<T> iterator;

		public ImmutableListIterator(ListIterator<T> iterator) {
			this.iterator = iterator;
		}

		@Override
		public void add(T object) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public boolean hasPrevious() {
			return iterator.hasPrevious();
		}

		@Override
		public T next() {
			return iterator.next();
		}

		@Override
		public int nextIndex() {
			return iterator.nextIndex();
		}

		@Override
		public T previous() {
			return iterator.previous();
		}

		@Override
		public int previousIndex() {
			return iterator.previousIndex();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T object) {
			throw new UnsupportedOperationException();
		}
	}
}
