package Presentation;

import javax.swing.JFrame;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfataPrincipala extends JFrame{

    public InterfataPrincipala()
    {
        getContentPane().setBackground(new Color(254, 255, 219));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        getContentPane().setLayout(null);

        JLabel titlu = new JLabel("Gestionarea comenziilor");
        titlu.setBackground(new Color(230, 220, 235));
        titlu.setFont(new Font("Goudy Old Style", Font.BOLD, 40));
        titlu.setBounds(293, 63, 414, 64);
        getContentPane().add(titlu);

        JButton butonClient = new JButton("Tabel Clienti");
        butonClient.setForeground(new Color(255, 255, 255));
        butonClient.setBackground(new Color(71, 230, 26));
        butonClient.setBorderPainted(false);
        butonClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfataClient client = new InterfataClient();
            }
        });
        butonClient.setFont(new Font("Times New Roman", Font.BOLD, 24));
        butonClient.setBounds(53, 229, 234, 217);
        getContentPane().add(butonClient);

        JLabel linie = new JLabel("_____________________________________________");
        linie.setBounds(364, 559, 323, 13);
        getContentPane().add(linie);

        JLabel linie_1 = new JLabel("___________");
        linie_1.setBounds(481, 605, 162, 13);
        getContentPane().add(linie_1);

        JLabel linie_2 = new JLabel("__________________________");
        linie_2.setBounds(424, 582, 323, 13);
        getContentPane().add(linie_2);

        JButton butonProdus = new JButton("Tabel Produse");
        butonProdus.setForeground(new Color(255, 255, 255));
        butonProdus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfataProdus produs = new InterfataProdus();
            }
        });
        butonProdus.setFont(new Font("Times New Roman", Font.BOLD, 24));
        butonProdus.setBorderPainted(false);
        butonProdus.setBackground(new Color(169, 90, 216));
        butonProdus.setBounds(390, 229, 234, 217);
        getContentPane().add(butonProdus);

        JButton butonComenzi = new JButton("Tabel Comenzi");
        butonComenzi.setForeground(new Color(255, 255, 255));
        butonComenzi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfataComenzi comanda = new InterfataComenzi();
            }
        });
        butonComenzi.setFont(new Font("Times New Roman", Font.BOLD, 24));
        butonComenzi.setBorderPainted(false);
        butonComenzi.setBackground(new Color(114, 211, 243));
        butonComenzi.setBounds(709, 229, 234, 217);
        getContentPane().add(butonComenzi);

        this.setVisible(true);
    }
    // main care porneste celelalte interfete din interfata principala
    public static void main(String[] args)
    {
        InterfataPrincipala interfata = new InterfataPrincipala();
    }
}