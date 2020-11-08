package LojaVirtual.Cardapio;

import LojaVirtual.BD;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuCardapio extends JInternalFrame {

    private JButton btConsultar;
    private BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private JTable table;
    private JScrollPane scrollTable;
    private JLabel label2;

    private JLabel limage, l_menu, l_descricao, l_altcliente, l_altprodut,
            l_altforn;
    private JPanel panel, panel2, barra_traseira;

    public MenuCardapio() {
        inicializarComponentes();
        definirEventos();
    }

    public void setNome(String Usuario) {
        label2.setText(Usuario);
    }

    public void inicializarComponentes() {
        setBounds(150, 20, 1050, 680);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setClosable(true);
        setLocation(-2, -30);

        btConsultar = new JButton("Atualizar");
        btConsultar.setToolTipText("Atualizar");
        btConsultar.setBounds(5, 5, 100, 25);
        btConsultar.setBackground(Color.BLUE);
        btConsultar.setForeground(Color.WHITE);

        table = new JTable();
        table.setBounds(70, 50, 900, 500);
        scrollTable = new JScrollPane(table);
        scrollTable.setBounds(70, 50, 900, 500);

        add(btConsultar);

        add(scrollTable);

        bd = new BD();

        label2 = new JLabel();
        label2.setForeground(Color.WHITE);
        barra_traseira = new JPanel();
        barra_traseira.setBounds(0, 600, 1050, 38);
        barra_traseira.setBackground(new Color(159, 182, 205));
        this.add(barra_traseira);
        barra_traseira.add(label2);

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
                    statement = bd.connection.prepareStatement("select * from produtos");
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
