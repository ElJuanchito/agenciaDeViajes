package co.edu.uniquindio.agenciaViajes.services;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

@SuppressWarnings("unchecked")
public abstract class RecurAbstractList<T> implements List<T> {

	/**
	 * Es el arreglo de los elementos de la lista
	 */
	protected Object[] elements = new Object[0];

	@SafeVarargs
	/**
	 * Es el constructor de la lista con n elementos como parámetro separados por
	 * comas
	 * 
	 * @param elements
	 */
	public RecurAbstractList(T... elements) {
		addAll(elements);
	}

	/**
	 * Es el constructor de la lista con elementos base los que tiene la lista
	 * 
	 * @param list
	 */
	public RecurAbstractList(List<? extends T> list) {
		addAll(list);
	}

	/**
	 * Agrega n elementos a la lista separados por comas
	 * 
	 * @param elements
	 */
	public void addAll(T... elements) {
		addAll(elements, 0);
	}

	/**
	 * Agrega los elementos de una lista a esta
	 * 
	 * @param list
	 */
	public boolean addAll(Collection<? extends T> list) {
		Object[] array = list.toArray();
		return addAllAux(array, 0, true);
	}

	/**
	 * Agrega los elementos de una lista a esta (recursivo)
	 * 
	 * @param list
	 * @param i
	 */
	private boolean addAllAux(Object[] array, int i, boolean result) {
		if (i == array.length)
			return result;
		if (!add((T) array[i]))
			result = false;
		return addAllAux(array, i + 1, result);
	}

	@Override
	public boolean add(T e) {
		if (elementValidation(e)) {
			forceAdd(e);
			return true;
		}
		return false;
	}

	/**
	 * Agrega los elementos de un arreglo a este (recursivo)
	 * 
	 * @param elements
	 * @param i
	 */
	private void addAll(T[] elements, int i) {
		if (i == elements.length)
			return;
		add(elements[i]);
		addAll(elements, i + 1);
	}

	/**
	 * Es el constructor de la lista sin parámetros
	 */
	public RecurAbstractList() {
		this.elements = new Object[0];
	}

	/**
	 * Elimina un elemento de la lista, si el indice esta fuera de la lista se
	 * retorna un falso
	 * 
	 * @param index
	 * @return
	 */
	public T remove(int index) {
		if (index < 0 || index >= elements.length)
			return null;
		T e = (T) elements[index];
		Object[] aux = new Object[elements.length - 1];
		System.arraycopy(elements, 0, aux, 0, index);
		System.arraycopy(elements, index + 1, aux, index, elements.length - index - 1);
		elements = aux;
		return e;
	}

	/**
	 * Elimina un elemento de la lista, si no se encuentra se retorna un falso
	 * 
	 * @param element
	 * @return
	 */
	public boolean remove(Object element) {
		int index = indexOf(element);
		if (index == -1)
			return true;
		return nonNull(remove(index));
	}

	/**
	 * Agrega un elemento a la lista sin verificar elementos
	 * 
	 * @param element
	 */
	protected void forceAdd(T element) {
		Object[] aux = new Object[size() + 1];
		System.arraycopy(elements, 0, aux, 0, size());
		aux[elements.length] = element;
		elements = aux;
	}

	/**
	 * Obtiene el tamaño de la lista
	 * 
	 * @return
	 */
	public int size() {
		return elements.length;
	}

	/**
	 * Busca el indice de un elemento
	 * 
	 * @param element
	 * @return
	 */
	public int indexOf(Object element) {
		return indexOf(element, 0);
	}

	/**
	 * Busca el indice de un elemento (recursivo)
	 * 
	 * @param element
	 * @param i
	 * @return
	 */
	private int indexOf(Object element, int i) {
		if (i == size())
			return -1;
		if (Objects.equals(elements[i], element))
			return i;
		return indexOf(element, i + 1);
	}

	/**
	 * Determina si un elemento está en la lista
	 * 
	 * @param element
	 * @return
	 */
	public boolean contains(Object element) {
		return indexOf(element) != -1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		generateToString(sb, 0);
		return sb.toString();
	}

	/**
	 * Es el metodo toString recursivo
	 * 
	 * @param sb
	 * @param i
	 */
	private void generateToString(StringBuilder sb, int i) {
		if (i == size()) {
			sb.append(']');
			return;
		}
		sb.append(elements[i] == this ? "(esta lista)" : elements[i]); // tomado del ArrayList
		if (i != size() - 1)
			sb.append(',').append(' ');
		generateToString(sb, i + 1);
	}

	@Override
	public boolean isEmpty() {
		return elements.length == 0;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, size());
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterador(this, -1);
	}

	private class Iterador implements Iterator<T> {

		private int index;
		private RecurAbstractList<T> recurAbstractList;

		public Iterador(RecurAbstractList<T> recurAbstractList, int index) {
			this.recurAbstractList = recurAbstractList;
			this.index = index;
		}

		@Override
		public boolean hasNext() {
			try {
				Objects.checkIndex(index + 1, size());
				return true;
			} catch (IndexOutOfBoundsException e) {
				return false;
			}
		}

		@Override
		public T next() {
			if (hasNext()) {
				index++;
				T t = (T) elements[index];
				return t;
			}
			throw new ConcurrentModificationException();
		}

		@Override
		public void remove() {
			if (index == -1)
				throw new IllegalStateException();
			if (isNull(recurAbstractList.remove(index)))
				throw new ConcurrentModificationException();
		}
	}

	@Override
	public <E> E[] toArray(E[] a) {
		return (E[]) Arrays.copyOfRange(elements, 0, a.length, a.getClass());
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return containsAllAux(c.iterator());
	}

	private boolean containsAllAux(Iterator<?> iterator) {
		if (!iterator.hasNext())
			return true;
		if (!contains(iterator.next()))
			return false;
		return containsAllAux(iterator);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return addAllCollectionAux(index, c.iterator(), false);
	}

	private boolean addAllCollectionAux(int index, Iterator<? extends T> iterator, boolean result) {
		if (!iterator.hasNext())
			return result;
		try {
			add(index, iterator.next());
			result = true;
		} catch (Exception e) {
		}
		return addAllCollectionAux(index, iterator, result);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return removeAllAux(c.iterator(), false);
	}

	private boolean removeAllAux(Iterator<?> iterator, boolean result) {
		if (!iterator.hasNext())
			return result;
		if (remove(iterator))
			result = true;
		return removeAllAux(iterator, result);
	}

	protected abstract boolean elementValidation(T element);

	@Override
	public void clear() {
		elements = new Object[0];
	}

	@Override
	public void add(int index, T element) {
		Objects.checkIndex(index, size() + 1);
		if (elementValidation(element)) {
			Object[] elements2 = new Object[elements.length + 1];
			if (index == size()) {
				System.arraycopy(elements, 0, elements2, 0, elements.length);
			} else {
				System.arraycopy(elements, 0, elements2, 0, index);
				System.arraycopy(elements, index - 1, elements2, index, size() - index + 1);
			}
			elements2[index] = element;
			elements = elements2;
		}
	}

	@Override
	public T set(int index, T element) {
		Objects.checkIndex(index, size());
		T t = (T) elements[index];
		if (elementValidation(element))
			elements[index] = element;
		return t;
	}

	@Override
	public T get(int index) {
		Objects.checkIndex(index, size());
		return (T) elements[index];
	}

	protected T forceSet(int index, T element) {
		if (index < 0 || index >= elements.length)
			return null;
		elements[index] = element;
		return element;
	}

	@Override
	public int lastIndexOf(Object o) {
		return lastIndexOfAux(o, size() - 1);
	}

	private int lastIndexOfAux(Object o, int i) {
		try {
			Objects.checkIndex(i, size());
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
		if (Objects.equals(elements[i], o))
			return i;
		return lastIndexOfAux(o, i - 1);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		final RecurArrayList<T> list = new RecurArrayList<>();
		createSubList(list, fromIndex, toIndex);
		return list;
	}

	private void createSubList(RecurArrayList<T> list, int fromIndex, int toIndex) {
		try {
			list.add(get(fromIndex));
			createSubList(list, fromIndex + 1, toIndex);
		} catch (IndexOutOfBoundsException e) {
		}
	}

	/**
	 * Muestra una {@link UnsupportedOperationException} diciendo que no se ha
	 * implementado la funcion
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("retainAll(Collection<?>)");
	}

	/**
	 * Muestra una {@link UnsupportedOperationException} diciendo que no se ha
	 * implementado la funcion
	 */
	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("listIterator()");
	}

	/**
	 * Muestra una {@link UnsupportedOperationException} diciendo que no se ha
	 * implementado la funcion
	 */
	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException("listIterator(int)");
	}

}
