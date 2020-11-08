package LojaVirtual.Administrador;

import LojaVirtual.BD;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class MostraUsuarios extends JPanel {

    private JButton btConsultar, btConsultar2;
    private BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private JTable table;
    private JScrollPane scrollTable;
    private JLabel label1, label2;

    private JLabel limage, l_menu, l_descricao, l_altcliente, l_altprodut,
            l_altforn;
    private JPanel panel, panel2, barra_traseira;

    public MostraUsuarios() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {
        setLayout(null);
        btConsultar = new JButton("Atualizar");
        btConsultar.setToolTipText("Atualizar");
        btConsultar.setBounds(110, 5, 100, 25);
        btConsultar.setBackground(Color.BLUE);
        btConsultar.setForeground(Color.WHITE);

        btConsultar2 = new JButton("Atualizar");
        btConsultar2.setToolTipText("Atualizar");
        btConsultar2.setBounds(380, 5, 100, 25);
        btConsultar2.setBackground(Color.BLACK);
        btConsultar2.setForeground(Color.WHITE);

        label1 = new JLabel("Funcionários:");

        label1.setBounds(5, 5, 100, 25);
        label1.setFont(new Font("", Font.BOLD, 14));
        label1.setForeground(Color.black);

        label2 = new JLabel("Administradores:");

        label2.setBounds(250, 5, 120, 25);
        label2.setFont(new Font("", Font.BOLD, 14));
        label2.setForeground(Color.black);

        setBounds(0, 0, 800, 600);
        table = new JTable();
        table.setBounds(70, 50, 900, 500);
        scrollTable = new JScrollPane(table);
        scrollTable.setBounds(70, 50, 900, 500);

        add(label1);
        add(btConsultar);
        add(label2);
        add(btConsultar2);

        add(scrollTable);

        bd = new BD();
//        TROCAR CAMINHO DA IMAGEM
        limage = new JLabel();
        Image img = new ImageIcon("C:\\Users\\Carlos Henrique\\Documents\\NetBeansProjects\\teste\\src\\LojaVirtual\\Imagens\\doces2.jpg").getImage();
        limage.setIcon(new ImageIcon(img));
        limage.setBounds(0, 0, 1050, 680);
        this.add(limage);
    }

    public void definirEventos() {

        btConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!bd.getConnection()) {
                    JOptionPane.showMessageDialog(null, "Falha ao conectar, o sistema sera fechado!");
                    System.exit(0);
                }

                try {
                    statement = bd.connection.prepareStatement("select * from usuarios");
                    resultSet = statement.executeQuery();
                    DefaultTableModel tableModel = new DefaultTableModel(new String[]{}, 0) {
                    };
                    int qtdeColunas = resultSet.getMetaData().getColumnCount();

                    for (int indice = 1; indice <= qtdeColunas; indice++) {
                        tableModel.addColumn(resultSet.getMetaData().getColumnName(indice));
                    }
                    table = new JTable(tableModel);
                    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                    while (resultSet.next()) {
                        try {
                            String[] dados = new String[qtdeColunas];
                            for (int i = 1; i <= qtdeColunas; i++) {
                                dados[i - 1] = resultSet.getString(i);
                            }
                            dtm.addRow(dados);
                            System.out.println();
                        } catch (SQLException erro) {
                        }
                        scrollTable.setViewportView(table);
                    }
                    resultSet.close();
                    statement.close();
                    bd.close();
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Comando SQL invalido !" + erro.toString());
                }
            }
        });

        btConsultar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!bd.getConnection()) {
                    JOptionPane.showMessageDialog(null, "Falha ao conectar, o sistema sera fechado!");
                    System.exit(0);
                }

                try {
                    statement = bd.connection.prepareStatement("select * from administradores");
                    resultSet = statement.executeQuery();
                    DefaultTableModel tableModel = new DefaultTableModel(new String[]{}, 0) {
                    };
                    int qtdeColunas = resultSet.getMetaData().getColumnCount();

                    for (int indice = 1; indice <= qtdeColunas; indice++) {
                        tableModel.addColumn(resultSet.getMetaData().getColumnName(indice));
                    }
                    table = new JTable(tableModel);
                    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                    while (resultSet.next()) {
                        try {
                            String[] dados = new String[qtdeColunas];
                            for (int i = 1; i <= qtdeColunas; i++) {
                                dados[i - 1] = resultSet.getString(i);
                            }
                            dtm.addRow(dados);
                            System.out.println();
                        } catch (SQLException erro) {
                        }
                        scrollTable.setViewportView(table);
                    }
                    resultSet.close();
                    statement.close();
                    bd.close();
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Comando SQL invalido !" + erro.toString());
                }
            }
        });
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10 || e.getKeyCode() == 9) {
            e.setKeyCode(9);
        }
    }

}
