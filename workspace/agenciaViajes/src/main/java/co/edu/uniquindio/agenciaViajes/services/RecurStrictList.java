package co.edu.uniquindio.agenciaViajes.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RecurStrictList<T> extends RecurAbstractList<T> {

	/**
	 * Es el constructor de {@link RecurStrictList} con n elementos como parámetro
	 * separados por comas
	 * 
	 * @param elements
	 */
	@SafeVarargs
	public RecurStrictList(T... elements) {
		super(elements);
	}

	/**
	 * Es el constructor de {@link RecurStrictList} con elementos base los que tiene
	 * la lista
	 * 
	 * @param list
	 */
	public RecurStrictList(List<? extends T> list) {
		super(list);
	}

	/**
	 * Ordena los elementos de una lista con un comparable
	 * 
	 * @param comparable
	 */
	@SuppressWarnings("unchecked")
	public void sort(Comparator<? super T> comparable) {
		T[] aux = (T[]) subList(0, size()).toArray();
		Arrays.sort(aux, comparable);
		elements = aux;
	}

	@Override
	protected boolean elementValidation(T element) {
		return element != null && !contains(element);
	}
}
