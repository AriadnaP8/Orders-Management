package Presentation;

import Business_Logic.ClientBLL;
import Data_Access.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfataClient extends JFrame{
    private JTable table;
    private JTextField fieldId;
    private JTextField fieldVarsta;
    private JTextField fieldNume;
    private JTextField fieldEmail;

    public InterfataClient()
    {
        getContentPane().setBackground(new Color(186, 248, 200));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1000, 700);
        getContentPane().setLayout(null);

        JLabel lblIntroducereClienti = new JLabel("Introducere clienti");
        lblIntroducereClienti.setFont(new Font("Goudy Old Style", Font.BOLD, 38));
        lblIntroducereClienti.setBackground(new Color(230, 220, 235));
        lblIntroducereClienti.setBounds(363, 35, 281, 64);
        getContentPane().add(lblIntroducereClienti);

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

        JLabel lblNume = new JLabel("Varsta:");
        lblNume.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblNume.setBounds(48, 190, 76, 38);
        getContentPane().add(lblNume);

        fieldVarsta = new JTextField();
        fieldVarsta.setHorizontalAlignment(SwingConstants.CENTER);
        fieldVarsta.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldVarsta.setColumns(10);
        fieldVarsta.setBounds(146, 192, 258, 38);
        getContentPane().add(fieldVarsta);

        JLabel lblNume_2 = new JLabel("Nume:");
        lblNume_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblNume_2.setBounds(48, 252, 76, 38);
        getContentPane().add(lblNume_2);

        fieldNume = new JTextField();
        fieldNume.setHorizontalAlignment(SwingConstants.CENTER);
        fieldNume.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldNume.setColumns(10);
        fieldNume.setBounds(146, 254, 258, 38);
        getContentPane().add(fieldNume);

        JLabel lblNume_2_1 = new JLabel("Email:");
        lblNume_2_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblNume_2_1.setBounds(48, 314, 76, 38);
        getContentPane().add(lblNume_2_1);

        fieldEmail = new JTextField();
        fieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
        fieldEmail.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldEmail.setColumns(10);
        fieldEmail.setBounds(146, 314, 258, 38);
        getContentPane().add(fieldEmail);

        JButton butonInsert = new JButton("INSERT");
        butonInsert.setBackground(new Color(245, 228, 75));
        butonInsert.setBorderPainted(false);
        butonInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(fieldId.getText());
                int b = Integer.parseInt(fieldVarsta.getText());
                Client cl = new Client(a, b, fieldNume.getText(), fieldEmail.getText());
                ClientBLL bll = new ClientBLL();
                bll.insertClient(cl);

            }
        });
        butonInsert.setFont(new Font("Times New Roman", Font.BOLD, 15));
        butonInsert.setBounds(53, 402, 168, 91);
        getContentPane().add(butonInsert);

        JButton butonUpdate = new JButton("UPDATE");
        butonUpdate.setBackground(new Color(128, 255, 0));
        butonUpdate.setFont(new Font("Times New Roman", Font.BOLD, 15));
        butonUpdate.setBounds(236, 402, 168, 91);
        butonUpdate.setBorderPainted(false);
        butonUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(fieldId.getText());
                int b = Integer.parseInt(fieldVarsta.getText());
                Client cl = new Client(a, b, fieldNume.getText(), fieldEmail.getText());
                ClientBLL bll = new ClientBLL();
                bll.updateClient(cl);
            }
        });
        getContentPane().add(butonUpdate);

        JButton butonDisplay = new JButton("DISPLAY");
        butonDisplay.setBackground(new Color(47, 208, 159));
        butonDisplay.setFont(new Font("Times New Roman", Font.BOLD, 15));
        butonDisplay.setBorderPainted(false);
        butonDisplay.setBounds(53, 517, 168, 91);
        butonDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientDAO dao = new ClientDAO();
                ClientBLL bll = new ClientBLL();
                try {
                    dao.tabelInterfata(bll.findAllClients(),table);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        getContentPane().add(butonDisplay);

        JButton butonDelete = new JButton("DELETE");
        butonDelete.setBackground(new Color(255, 0, 0));
        butonDelete.setFont(new Font("Times New Roman", Font.BOLD, 15));
        butonDelete.setBorderPainted(false);
        butonDelete.setBounds(236, 517, 168, 91);
        butonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(fieldId.getText());
                ClientBLL bll = new ClientBLL();
                bll.stergeClient(a);
            }
        });
        getContentPane().add(butonDelete);

        this.setVisible(true);
    }
}