package co.edu.uniquindio.agenciaViajes.services;

import java.util.List;

public class DuplicateRecursionList<T> extends AbstractRecursionList<T> {

	/**
	 * Es el constructor de {@link DuplicateRecursionList} con n elementos como
	 * parámetro separados por comas
	 * 
	 * @param elements
	 */
	@SafeVarargs
	public DuplicateRecursionList(T... elements) {
		super(elements);
	}

	/**
	 * Es el constructor de {@link DuplicateRecursionList} con elementos base los
	 * que tiene la lista
	 * 
	 * @param list
	 */
	public DuplicateRecursionList(List<? extends T> list) {
		super(list);
	}

	@Override
	public boolean add(T element) {
		forceAdd(element);
		return true;
	}
}
