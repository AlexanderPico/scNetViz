package edu.ucsf.rbvi.scNetViz.internal.api;

/*
 * A Category is a matrix of values organized with
 * the category key in the first column, and the
 * categorical values in each subsequent column.
 */
public interface Category {
	public String getCategoryType();
	public Matrix getMatrix();
}
