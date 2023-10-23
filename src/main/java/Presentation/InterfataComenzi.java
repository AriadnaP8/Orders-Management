package Presentation;

import Business_Logic.BillBLL;
import Business_Logic.ClientBLL;
import Business_Logic.ComandaBLL;
import Business_Logic.ProductBLL;
import Data_Access.ComandaDAO;
import Data_Access.ProductDAO;
import Model.Comanda;
import Model.Produs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InterfataComenzi extends JFrame{
    private JTable table;
    private JTextField fieldId;
    private JTextField fieldIdClient;
    private JTextField fieldIdProdus;
    private JTextField fieldCantitate;

    public InterfataComenzi()
    {
        getContentPane().setBackground(new Color(215, 255, 255));
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(1000, 700);
        getContentPane().setLayout(null);

        JLabel titluComanda = new JLabel("Introducere comenzi");
        titluComanda.setFont(new Font("Goudy Old Style", Font.BOLD, 38));
        titluComanda.setBackground(new Color(230, 220, 235));
        titluComanda.setBounds(343, 33, 315, 64);
        getContentPane().add(titluComanda);

        table = new JTable();
        table.setBackground(new Color(255, 255, 255));
        table.setBounds(437, 135, 525, 498);
        JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(437, 135, 525, 498);
        getContentPane().add(scroll);
        table.setEnabled(false);

        JLabel id = new JLabel("ID\r\n:");
        id.setFont(new Font("Times New Roman", Font.BOLD, 22));
        id.setBounds(48, 135, 51, 38);
        getContentPane().add(id);

        fieldId = new JTextField();
        fieldId.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldId.setHorizontalAlignment(SwingConstants.CENTER);
        fieldId.setBounds(156, 136, 258, 38);
        getContentPane().add(fieldId);
        fieldId.setColumns(10);

        JLabel lbIdClient = new JLabel("ID Client:");
        lbIdClient.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lbIdClient.setBounds(48, 190, 103, 38);
        getContentPane().add(lbIdClient);

        fieldIdClient = new JTextField();
        fieldIdClient.setHorizontalAlignment(SwingConstants.CENTER);
        fieldIdClient.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldIdClient.setColumns(10);
        fieldIdClient.setBounds(156, 191, 258, 38);
        getContentPane().add(fieldIdClient);

        JLabel lblPret = new JLabel("ID Produs:");
        lblPret.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblPret.setBounds(48, 252, 103, 38);
        getContentPane().add(lblPret);

        fieldIdProdus = new JTextField();
        fieldIdProdus.setHorizontalAlignment(SwingConstants.CENTER);
        fieldIdProdus.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldIdProdus.setColumns(10);
        fieldIdProdus.setBounds(156, 253, 258, 38);
        getContentPane().add(fieldIdProdus);

        JLabel lblStoc = new JLabel("Cantitate:");
        lblStoc.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblStoc.setBounds(48, 314, 103, 38);
        getContentPane().add(lblStoc);

        fieldCantitate = new JTextField();
        fieldCantitate.setHorizontalAlignment(SwingConstants.CENTER);
        fieldCantitate.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        fieldCantitate.setColumns(10);
        fieldCantitate.setBounds(156, 315, 258, 38);
        getContentPane().add(fieldCantitate);

        JButton butonInsert = new JButton("INSERT");
        butonInsert.setBackground(new Color(255, 101, 0));
        butonInsert.setBorderPainted(false);
        butonInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(fieldId.getText());
                int b = Integer.parseInt(fieldIdClient.getText());
                int c = Integer.parseInt(fieldIdProdus.getText());
                int d = Integer.parseInt(fieldCantitate.getText());
                ComandaBLL bll = new ComandaBLL();
                ClientBLL bll1 = new ClientBLL();
                BillBLL bll2 = new BillBLL();
                ProductBLL bll3 = new ProductBLL();
                try {
                    bll.placeOrder(b, c, d);
                    bll2.billInsert(0, a, bll1.cautaClientDupaId(b).getNume(), bll1.cautaClientDupaId(b).getEmail(), bll3.findProductById(c).getPret());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        butonInsert.setFont(new Font("Times New Roman", Font.BOLD, 20));
        butonInsert.setBounds(53, 402, 361, 91);
        getContentPane().add(butonInsert);

        JButton butonDisplay = new JButton("DISPLAY");
        butonDisplay.setBackground(new Color(145, 220, 84));
        butonDisplay.setFont(new Font("Times New Roman", Font.BOLD, 20));
        butonDisplay.setBorderPainted(false);
        butonDisplay.setBounds(53, 517, 361, 91);
        butonDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ComandaDAO dao = new ComandaDAO();
                ComandaBLL bll = new ComandaBLL();
                try {
                    dao.tabelInterfata(bll.findAllOrders(),table);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        getContentPane().add(butonDisplay);

        this.setVisible(true);
    }
}