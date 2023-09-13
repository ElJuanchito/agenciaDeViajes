package co.edu.uniquindio.agenciaViajes.services;

import java.util.List;

public abstract class AbstractRecursionList<T> {

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
	public AbstractRecursionList(T... elements) {
		addAll(elements);
	}

	/**
	 * Es el constructor de la lista con elementos base los que tiene la lista
	 * 
	 * @param list
	 */
	public AbstractRecursionList(List<? extends T> list) {
		addAll(list);
	}

	@SuppressWarnings("unchecked")
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
	public void addAll(List<? extends T> list) {
		addAllAux(list, 0);
	}

	/**
	 * Agrega los elementos de una lista a esta (recursivo)
	 * 
	 * @param list
	 * @param i
	 */
	private void addAllAux(List<? extends T> list, int i) {
		if (i == list.size())
			return;
		add(list.get(i));
		addAllAux(list, i + 1);
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
	public AbstractRecursionList() {
		this.elements = new Object[0];
	}

	/**
	 * Agrega un elemento a la lista
	 * 
	 * @param element
	 * @return
	 */
	public abstract boolean add(T element);

	/**
	 * Elimina un elemento de la lista, si el indice esta fuera de la lista se
	 * retorna un falso
	 * 
	 * @param index
	 * @return
	 */
	public boolean remove(int index) {
		if (index < 0 || index >= elements.length)
			return false;
		Object[] aux = new Object[elements.length - 1];
		System.arraycopy(elements, 0, aux, 0, index);
		System.arraycopy(elements, index + 1, aux, index, elements.length - index - 1);
		elements = aux;
		return true;
	}

	/**
	 * Elimina un elemento de la lista, si no se encuentra se retorna un falso
	 * 
	 * @param element
	 * @return
	 */
	public boolean remove(T element) {
		int index = indexOf(element);
		if (index == -1)
			return false;
		return remove(index);
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
	public int indexOf(T element) {
		return indexOf(element, 0);
	}

	/**
	 * Busca el indice de un elemento (recursivo)
	 * 
	 * @param element
	 * @param i
	 * @return
	 */
	private int indexOf(T element, int i) {
		if (i == size())
			return -1;
		if (elements[i] == element)
			return i;
		if (elements[i] != null && elements[i].equals(element))
			return i;
		return indexOf(element, i + 1);
	}

	/**
	 * Determina si un elemento está en la lista
	 * 
	 * @param element
	 * @return
	 */
	public boolean contains(T element) {
		return indexOf(element) != -1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		toString(sb, 0);
		return sb.toString();
	}

	/**
	 * Es el metodo toString recursivo
	 * 
	 * @param sb
	 * @param i
	 */
	private void toString(StringBuilder sb, int i) {
		if (i == size()) {
			sb.append(']');
			return;
		}
		sb.append(elements[i] == this ? "(esta lista)" : elements[i]); // tomado del ArrayList
		if (i != size() - 1)
			sb.append(',').append(' ');
		toString(sb, i + 1);
	}

}
