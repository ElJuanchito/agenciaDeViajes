package co.edu.uniquindio.agenciaViajes.services;

import java.util.Comparator;

public class RecurSortedList<T extends Comparable<? super T>> extends RecurStrictList<T> {

	/**
	 * Ordena los elementos de la lista, el tipo de dato tiene que extender de
	 * Comparable
	 */
	public void sort() {
		sort(false);
	}

	/**
	 * Ordena los elementos de la lista de atras para adelante
	 * 
	 * @param backwards
	 */
	public void sort(final boolean backwards) {
		Comparator<T> comparator = new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return ((backwards ? -1 : 1)) * o1.compareTo(o2);
			}
		};
		sort(comparator);
	}
}
