package org.robotframework.swing.table;

import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.jretrofit.AllMethodsNotImplementedException;
import org.jretrofit.Retrofit;
import org.netbeans.jemmy.operators.JTableOperator;
import org.robotframework.swing.chooser.WithText;
import org.robotframework.swing.common.SmoothInvoker;

public class CellValueExtractor {
    public enum TextSource {AUTO, MODEL}

    private JTableOperator jTableOperator;

    public CellValueExtractor(JTableOperator jTableOperator) {
        this.jTableOperator = jTableOperator;
    }

    public String textOf(int row, int col, TextSource source) {
        if (source == TextSource.MODEL)
            return getTextFromTableModel(row, col);
        else
            return getTextWithDefaultStrategy(row, col);
    }

    public String textOf(int row, int col) {
        return textOf(row, col, TextSource.AUTO);
    }

    public String getTextWithDefaultStrategy(int row, int col) {
        try {
            return getTextFromCellComponent(row, col);
        } catch (AllMethodsNotImplementedException e) {
            return getTextFromTableModel(row, col);
        }
    }

    private String getTextFromTableModel(int row, int col) {
        return wrapElementToWithText(row, col).getText();
    }

    private String getTextFromCellComponent(int row, int col) {
        Component cellRendererComponent = getCellRendererComponent(row, col);
        if (isButtonBasedRenderer(cellRendererComponent))
            return new Boolean(((AbstractButton) cellRendererComponent).isSelected()).toString();
        return coerceToWithText(cellRendererComponent).getText();
    }

    public Component getCellRendererComponent(int row, int column) {
        TableCellRenderer renderer = jTableOperator.getCellRenderer(row, column);
        JTable table = (JTable) jTableOperator.getSource();
        Object value = jTableOperator.getValueAt(row, column);
        boolean isSelected = jTableOperator.isCellSelected(row, column);
        boolean hasFocus = jTableOperator.hasFocus();
        return getTableCellRendererComponentSmoothly(row, column, renderer, table, value, isSelected, hasFocus);
    }

    private boolean isButtonBasedRenderer(Component cellRendererComponent) {
        TableCellRenderer defaultCheckboxRenderer = ((JTable) jTableOperator.getSource()).getDefaultRenderer(Boolean.class);
        return (defaultCheckboxRenderer.getClass().isInstance(cellRendererComponent) &&
                cellRendererComponent instanceof AbstractButton);
    }

    private WithText coerceToWithText(Object element) {
        return Retrofit.complete(element, WithText.class);
    }

    private WithText wrapElementToWithText(final int rowIndex, final int columnIndex) {
        return () -> jTableOperator.getModel()
                .getValueAt(rowIndex, columnIndex)
                .toString();
    }

    private Component getTableCellRendererComponentSmoothly(final int row, final int column, final TableCellRenderer renderer,
                                                            final JTable table, final Object value, final boolean isSelected, final boolean hasFocus) {
        return new SmoothInvoker<Component>() {
            public Object work() {
                return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }.invoke();
    }
}
