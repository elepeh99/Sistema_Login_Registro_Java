/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio.utils;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.regex.Pattern;

public class SwingUtils {

    public static void centrarVentana(JFrame frame) {
        frame.setLocationRelativeTo(null);
    }

    public static void cambiarColorLabel(JLabel label, Color color) {
        label.setOpaque(true);
        label.setBackground(color);
    }

    public static void cambiarColorTexto(JLabel label, Color color) {
        label.setForeground(color);
    }

    public static void cambiarFuente(JLabel label, String nombreFuente, int estilo, int tamano) {
        label.setFont(new Font(nombreFuente, estilo, tamano));
    }

    public static void cambiarTitulo(JFrame frame, String titulo) {
        frame.setTitle(titulo);
    }

    public static void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipo);
    }

    public static void confirmarSalida() {
        int opc = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de salir?",
                "Salir",
                JOptionPane.YES_NO_OPTION);
        if (opc == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void notificarTemporal(JLabel label, String mensaje, Color color, int milisegundos) {
        label.setText(mensaje);
        label.setForeground(color);
        new Thread(() -> {
            try {
                Thread.sleep(milisegundos);
                label.setText("");
            } catch (InterruptedException ignored) {}
        }).start();
    }

    public static void agregarFila(JTable tabla, Object[] datos) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.addRow(datos);
    }

    public static void eliminarFila(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        int fila = tabla.getSelectedRow();

        if (fila != -1) {
            model.removeRow(fila);
        } else {
            mostrarMensaje("No ha seleccionado ninguna fila",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void modificarFila(JTable tabla, Object[] datos) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        int fila = tabla.getSelectedRow();

        if (fila != -1) {
            for (int i = 0; i < datos.length; i++) {
                model.setValueAt(datos[i], fila, i);
            }
        } else {
            mostrarMensaje("Seleccione una fila para modificar",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static boolean hayFilaSeleccionada(JTable tabla) {
        return tabla.getSelectedRow() != -1;
    }

    public static void limpiarTabla(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
    }

    public static void exportarTablaCSV(JTable tabla, String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            int cols = model.getColumnCount();
            int rows = model.getRowCount();

            for (int i = 0; i < cols; i++) {
                writer.append(model.getColumnName(i));
                if (i < cols - 1) writer.append(",");
            }
            writer.append("\n");

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    writer.append(String.valueOf(model.getValueAt(r, c)));
                    if (c < cols - 1) writer.append(",");
                }
                writer.append("\n");
            }

            mostrarMensaje("Tabla exportada exitosamente a: " + rutaArchivo,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            mostrarMensaje("Error al exportar: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void limpiarComponentes(JComponent... componentes) {
        for (JComponent comp : componentes) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            } else if (comp instanceof JComboBox) {
                ((JComboBox<?>) comp).setSelectedIndex(-1);
            } else if (comp instanceof JTextArea) {
                ((JTextArea) comp).setText("");
            }
        }
    }

    public static void bloquearComponentes(boolean bloquear, JComponent... componentes) {
        for (JComponent comp : componentes) {
            comp.setEnabled(!bloquear);
        }
    }

    public static void modoEdicion(boolean modo, JButton btnAgregar, JButton btnEliminar, JButton btnModificar) {
        btnAgregar.setEnabled(!modo);
        btnEliminar.setEnabled(modo);
        btnModificar.setEnabled(modo);
    }

    public static boolean validarCampos(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean esNumero(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esCorreoValido(String correo) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, correo);
    }

    public static boolean comboSeleccionValida(JComboBox<?> combo) {
        return combo.getSelectedIndex() != -1;
    }
}
