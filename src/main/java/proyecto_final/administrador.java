/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author izabe
 */
public class administrador extends JFrame {

    JTable tabla = new JTable();
    JScrollPane sp = new JScrollPane();
    JLabel usuario_inicio = new JLabel();
    JPanel p1 = new JPanel();
    Object usuarios[][] = new Object[50][10];

    private void crear(String usuario) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setMaximumSize(new Dimension(1000, 1000));
        setVisible(true);
        p1.setBackground(Color.GRAY);
        p1.setLayout(null);
        add(p1);
        //etiqueta usuario
        usuario_inicio.setText("Bienvenido" + usuario);
        usuario_inicio.setBounds(700, 10, 100, 40);
        JButton crear = new JButton("Agregar Usuario");
        crear.setBounds(70, 550, 200, 30);
        p1.add(crear);
        //agregar funcionalidad
        ActionListener accion_crear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                form_usuario s = new form_usuario();
                s.agregar_usuario();
                sp.setVisible(false);

                try {
                    tabla();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };

        //Acción del evento
        crear.addActionListener(accion_crear);

        JButton modificar = new JButton("Modificar Usuario");
        modificar.setBounds(300, 550, 200, 30);
        p1.add(modificar);

        ActionListener accion_modificar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int fila = tabla.getSelectedRow();

                if (fila >= 0) {
                    modificar m = new modificar();
                    m.agregar_usuario(fila);

                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
                }

            }
        };

        modificar.addActionListener(accion_modificar);

        JButton eliminar = new JButton("Eliminar");
        eliminar.setBounds(520, 550, 200, 30);
        p1.add(eliminar);
        ActionListener accion_eliminar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    eliminar el = new eliminar();
                    el.eliminar_usuario(fila);

                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
                }
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

            }
        };
        eliminar.addActionListener(accion_eliminar);

        JButton salir = new JButton("Salir");
        salir.setBounds(0, 0, 100, 30);
        p1.add(salir);

        ActionListener accion_salir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login l = new login();
                try {
                    l.ejecutar();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
                }

                dispose();
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

            }

        };

    }

    public void tabla() throws ClassNotFoundException, IOException {

        load();
        String[] columnas = {"No.", "Nombre", "Apellido", "Rol", "Correo", "Teléfono"};
        tabla = new JTable(usuarios, columnas);
        sp = new JScrollPane(tabla);
        sp.setSize(820, 450);
        sp.setLocation(20, 40);
        sp.setVisible(true);
        p1.add(sp);

    }

    public void load() throws ClassNotFoundException, FileNotFoundException, IOException {

        try {

            ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("usuarios.dat"));

            usuarios = (Object[][]) recuperar.readObject();
            recuperar.close();

        } catch (IOException e) {
        }

    }

    public void ejecutar(String usuario) throws ClassNotFoundException, IOException {

        crear(usuario);
        tabla();
    }

}
