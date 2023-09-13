package co.edu.uniquindio.agenciaViajes.services;

import java.util.List;

public class RecurArrayList<T> extends RecurAbstractList<T> {

	/**
	 * Es el constructor de {@link RecurArrayList} con n elementos como
	 * parámetro separados por comas
	 * 
	 * @param elements
	 */
	@SafeVarargs
	public RecurArrayList(T... elements) {
		super(elements);
	}

	/**
	 * Es el constructor de {@link RecurArrayList} con elementos base los
	 * que tiene la lista
	 * 
	 * @param list
	 */
	public RecurArrayList(List<? extends T> list) {
		super(list);
	}

	@Override
	public boolean add(T element) {
		forceAdd(element);
		return true;
	}
}
