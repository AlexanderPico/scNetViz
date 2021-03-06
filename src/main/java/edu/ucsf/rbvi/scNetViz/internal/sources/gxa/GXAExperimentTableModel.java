package edu.ucsf.rbvi.scNetViz.internal.sources.gxa;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.ucsf.rbvi.scNetViz.internal.model.ScNVManager;
import edu.ucsf.rbvi.scNetViz.internal.model.MatrixMarket;
import edu.ucsf.rbvi.scNetViz.internal.view.SortableTableModel;

public class GXAExperimentTableModel extends SortableTableModel {
	final ScNVManager manager;
	final GXAExperiment gxaExperiment;
	final MatrixMarket matrixMarket;

	public GXAExperimentTableModel (final ScNVManager manager, 
	                                final GXAExperiment experiment) {
		super(1);
		this.manager = manager;
		this.gxaExperiment = experiment;
		this.matrixMarket = (MatrixMarket)experiment.getMatrix();
	}

	@Override
	public int getColumnCount() { return matrixMarket.getNCols(); }

	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return matrixMarket.isTransposed() ? "Barcodes" : "Genes";
		}
		if (columnIndex != null) {
			return matrixMarket.getColumnLabel(columnIndex[column-1]);
		}
		return matrixMarket.getColumnLabel(column-1);
	}

	@Override
	public int getRowCount() { 
		return matrixMarket.getNRows(); 
	}

	@Override
	public Class getColumnClass(int column) {
		switch (column) {
			case 0:
				return String.class;
			default:
				return Double.class;
		}
	}

	@Override
	public Object getValueAt(int row, int column) {
		//System.out.println("getValueAt: "+row+","+column);
		switch (column) {
			case 0:
				return matrixMarket.getRowLabel(row);
			default:
				double v;
			 	if (columnIndex != null)
					v	= matrixMarket.getDoubleValue(row, columnIndex[column]);
				else
					v = matrixMarket.getDoubleValue(row, column);
				if (Double.isNaN(v)) return null;
				return new Double(v);
		}
	}

}
