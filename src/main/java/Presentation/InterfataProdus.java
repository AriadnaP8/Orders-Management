package Presentation;

import Business_Logic.ClientBLL;
import Business_Logic.ProductBLL;
import Data_Access.ClientDAO;
import Data_Access.ProductDAO;
import Model.Client;
import Model.Produs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InterfataProdus extends JFrame{
    private JTable table;
    private JTextField fieldId;
    private JTextField fieldNume;
    private JTextField fieldPret;
    private JTextField fieldStoc;

    public InterfataProdus()
    {
        getContentPane().setBackground(new Color(241, 227, 253));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1000, 700);
        getContentPane().setLayout(null);

        JLabel titluProdus = new JLabel("Introducere produse");
        titluProdus.setFont(new Font("Goudy Old Style", Font.BOLD, 38));
        titluProdus.setBackground(new Color(230, 220, 235));
        titluProdus.setBounds(343, 33, 315, 64);
        getContentPane().add(titluProdus);

        table = new JTable();
        table.setBackground(new Color(255, 255, 255));
        table.setBounds(437, 135, 525, 498);
        JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(437, 135, 525, 498);
        getContentPane().add(scroll);
        table.setEnabled(false);

        JLabel id = new JLabel("ID\r\n:");
        id.setFont(new Font("Times New Roman", Font.BOLD, 24));
        id.setBounds(53, 136, 51, 38);
        getContentPane().add(id);

        fieldId = new JTextField();
        fieldId.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldId.setHorizontalAlignment(SwingConstants.CENTER);
        fieldId.setBounds(146, 135, 258, 38);
        getContentPane().add(fieldId);
        fieldId.setColumns(10);

        JLabel lblNume = new JLabel("Nume:");
        lblNume.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblNume.setBounds(48, 190, 76, 38);
        getContentPane().add(lblNume);

        fieldNume = new JTextField();
        fieldNume.setHorizontalAlignment(SwingConstants.CENTER);
        fieldNume.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldNume.setColumns(10);
        fieldNume.setBounds(146, 192, 258, 38);
        getContentPane().add(fieldNume);

        JLabel lblPret = new JLabel("Pret:");
        lblPret.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblPret.setBounds(48, 252, 76, 38);
        getContentPane().add(lblPret);

        fieldPret = new JTextField();
        fieldPret.setHorizontalAlignment(SwingConstants.CENTER);
        fieldPret.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldPret.setColumns(10);
        fieldPret.setBounds(146, 254, 258, 38);
        getContentPane().add(fieldPret);

        JLabel lblStoc = new JLabel("Stoc:");
        lblStoc.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblStoc.setBounds(48, 314, 76, 38);
        getContentPane().add(lblStoc);

        fieldStoc = new JTextField();
        fieldStoc.setHorizontalAlignment(SwingConstants.CENTER);
        fieldStoc.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldStoc.setColumns(10);
        fieldStoc.setBounds(146, 314, 258, 38);
        getContentPane().add(fieldStoc);

        JButton butonInsert = new JButton("INSERT");
        butonInsert.setBackground(new Color(255, 128, 192));
        butonInsert.setBorderPainted(false);
        butonInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(fieldId.getText());
                int b = Integer.parseInt(fieldStoc.getText());
                int c = Integer.parseInt(fieldPret.getText());
                Produs prod = new Produs(a, fieldNume.getText(), c, b);
                ProductBLL bll = new ProductBLL();
                bll.insertProduct(prod);
            }
        });
        butonInsert.setFont(new Font("Times New Roman", Font.BOLD, 15));
        butonInsert.setBounds(53, 402, 168, 91);
        getContentPane().add(butonInsert);

        JButton butonUpdate = new JButton("UPDATE");
        butonUpdate.setBackground(new Color(176, 123, 242));
        butonUpdate.setFont(new Font("Times New Roman", Font.BOLD, 15));
        butonUpdate.setBounds(236, 402, 168, 91);
        butonUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(fieldId.getText());
                int b = Integer.parseInt(fieldStoc.getText());
                int c = Integer.parseInt(fieldPret.getText());
                Produs prod = new Produs(a, fieldNume.getText(), c, b);
                ProductBLL bll = new ProductBLL();
                try {
                    bll.updateProduct(prod);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        butonUpdate.setBorderPainted(false);
        getContentPane().add(butonUpdate);

        JButton butonDisplay = new JButton("DISPLAY");
        butonDisplay.setBackground(new Color(255, 255, 128));
        butonDisplay.setFont(new Font("Times New Roman", Font.BOLD, 15));
        butonDisplay.setBorderPainted(false);
        butonDisplay.setBounds(53, 517, 168, 91);
        butonDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductDAO dao = new ProductDAO();
                ProductBLL bll = new ProductBLL();
                try {
                    dao.tabelInterfata(bll.findAllProducts(),table);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        getContentPane().add(butonDisplay);

        JButton butonDelete = new JButton("DELETE");
        butonDelete.setBackground(new Color(169, 238, 100));
        butonDelete.setFont(new Font("Times New Roman", Font.BOLD, 15));
        butonDelete.setBorderPainted(false);
        butonDelete.setBounds(236, 517, 168, 91);
        butonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(fieldId.getText());
                ProductBLL bll = new ProductBLL();
                try {
                    bll.deleteProduct(a);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        getContentPane().add(butonDelete);

        this.setVisible(true);
    }
}