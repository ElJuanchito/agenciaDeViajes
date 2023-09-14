package co.edu.uniquindio.agenciaViajes.services;

import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
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
	public void sort(Comparator<T> comparable) {
		if (size() == 0 || size() == 1)
			return;
		quickSort(0, size() - 1, comparable);
	}

	/**
	 * Ordena los elementos de la lista usando el algoritmo de quickSort
	 * 
	 * @param a
	 * @param b
	 * @param comparable
	 */
	private void quickSort(int a, int b, Comparator<T> comparable) {
		if (a < b) {
			int p = separatePeabot(a, b, comparable);
			quickSort(a, p - 1, comparable);
			quickSort(p + 1, b, comparable);
		}

	}

	/**
	 * Ordena los elementos de la lista usando el algoritmo de quickSort (separa los
	 * elementos en 2 pibotes)
	 * 
	 * @param a
	 * @param b
	 * @param comparable
	 * @return
	 */
	private int separatePeabot(int a, int b, Comparator<T> comparable) {
		T peabot = (T) elements[b];
		int pos = a - 1;
		pos = separatePeabotAux(a, b, comparable, peabot, pos);
		elements[b] = elements[pos + 1];
		elements[pos + 1] = peabot;
		return pos + 1;
	}

	/**
	 * Separa los elementos del arreglo en 2 pibotes (recursivo)
	 * 
	 * @param a
	 * @param b
	 * @param comparable
	 * @param peabot
	 * @param pos
	 * @return
	 */
	private int separatePeabotAux(int a, int b, Comparator<T> comparable, T peabot, int pos) {
		if (a >= b)
			return pos;
		T el = (T) elements[a];
		if (comparable.compare(peabot, el) >= 0) {
			pos++;
			elements[a] = elements[pos];
			elements[pos] = el;
		}
		return separatePeabotAux(a + 1, b, comparable, peabot, pos);

	}

	@Override
	public boolean add(T element) {
		if (element == null || contains(element))
			return false;
		forceAdd(element);
		return true;
	}
}
