package LojaVirtual;

import LojaVirtual.Cardapio.MenuCardapio;
import LojaVirtual.Produtos.MenuProdutos;
import LojaVirtual.Clientes.MenuCliente;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tela_principal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel limage, l_menu, l_descricao, l_altcliente, l_altprodut,
            l_altforn;
    private JPanel panel, panel2, barra_traseira;
    private JButton bt_clientes, bt_produtos, bt_cardapio, home;
    private JDesktopPane desktop = new JDesktopPane();
    private int resposta;
    private JLabel horas, data;

    InputMap bla = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

    public void setNome(String Usuario) {
        l_descricao.setText(Usuario);
    }

    public Tela_principal() {

        setTitle("Bem Vindo a doceria Swet");
        setBounds(150, 20, 1050, 680);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        desktop = new JDesktopPane();
        desktop.setLayout(null);
        desktop.setBounds(0, 0, 1050, 630);
        this.add(desktop);

        Date data = new Date();
        DateFormat formato = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = formato.format(data);

        horas = new JLabel();
        horas.setText(" Hora do sistema: " + formattedDate);

        JMenuBar menu = new JMenuBar();
        home = new JButton("Home");
        home.setBackground(new Color(245, 245, 245));

        JButton sair = new JButton("Sair");
        sair.setBackground(new Color(245, 245, 245));

        JButton clientes = new JButton("Clientes");
        clientes.setBackground(new Color(245, 245, 245));
        JButton produtos = new JButton("Produtos");
        produtos.setBackground(new Color(245, 245, 245));
        JButton cardapio = new JButton("Cardápio");
        cardapio.setBackground(new Color(245, 245, 245));

        menu.add(home);
        menu.add(clientes);
        menu.add(produtos);
        menu.add(cardapio);
        menu.add(sair);
        menu.add(horas);
        setJMenuBar(menu);

        l_menu = new JLabel("Menu Rápido");
        l_menu.setBounds(130, 60, 200, 20);
        l_menu.setFont(new Font("Arial", Font.BOLD, 15));
        desktop.add(l_menu);

        l_descricao = new JLabel("Logado como: ");
        l_descricao.setForeground(Color.WHITE);
        desktop.add(l_descricao);

        l_altcliente = new JLabel("Alt+C = Clientes ");
        l_altcliente.setForeground(Color.WHITE);
        desktop.add(l_altcliente);

        l_altprodut = new JLabel("Alt+P = Produtos ");
        l_altprodut.setForeground(Color.WHITE);
        desktop.add(l_altprodut);

        l_altforn = new JLabel("Alt+F = Cardápio ");
        l_altforn.setForeground(Color.WHITE);
        desktop.add(l_altforn);

        panel = new JPanel();
        panel.setBounds(0, 0, 1050, 30);
        panel.setBackground(new Color(159, 182, 205));
        desktop.add(panel);
        panel.add(l_altcliente);
        panel.add(l_altprodut);
        panel.add(l_altforn);

        barra_traseira = new JPanel();
        barra_traseira.setBounds(0, 600, 1050, 30);
        barra_traseira.setBackground(new Color(159, 182, 205));
        desktop.add(barra_traseira);
        barra_traseira.add(l_descricao);
//        TROCAR CAMINHO DA IMAGEM
        bt_clientes = new JButton("Clientes", new ImageIcon("C:\\Users\\Carlos Henrique\\Documents\\NetBeansProjects\\teste\\src\\LojaVirtual\\Imagens\\cliente.png"));
        bt_clientes.setBounds(60, 140, 230, 40);
        bt_clientes.setBackground(new Color(245, 245, 245));
        bt_clientes.setMnemonic('C');
        desktop.add(bt_clientes);
//        TROCAR CAMINHO DA IMAGEM
        bt_produtos = new JButton("Produtos", new ImageIcon("C:\\Users\\Carlos Henrique\\Documents\\NetBeansProjects\\teste\\src\\LojaVirtual\\Imagens\\produtos.png"));
        bt_produtos.setBounds(60, 190, 230, 40);
        bt_produtos.setBackground(new Color(245, 245, 245));
        bt_produtos.setMnemonic('P');
        desktop.add(bt_produtos);
//        TROCAR CAMINHO DA IMAGEM
        bt_cardapio = new JButton("Cardápio", new ImageIcon("C:\\Users\\Carlos Henrique\\Documents\\NetBeansProjects\\teste\\src\\LojaVirtual\\Imagens\\cardapio.png"));
        bt_cardapio.setBounds(60, 240, 230, 40);
        bt_cardapio.setBackground(new Color(245, 245, 245));
        bt_cardapio.setMnemonic('F');
        desktop.add(bt_cardapio);

        panel2 = new JPanel();
        panel2.setBounds(50, 30, 250, 570);
        panel2.setBackground(new Color(245, 245, 245));
        desktop.add(panel2);
        panel2.setLayout(null);

        limage = new JLabel();
//        TROCAR CAMINHO DA IMAGEM
        Image img = new ImageIcon("C:\\Users\\Carlos Henrique\\Documents\\NetBeansProjects\\teste\\src\\LojaVirtual\\Imagens\\doces2.jpg").getImage();
        limage.setIcon(new ImageIcon(img));
        limage.setBounds(0, 0, 1050, 680);
        desktop.add(limage);

        bt_clientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                MenuCliente cliente;
                cliente = new MenuCliente();
                cliente.setNome(l_descricao.getText());

                desktop.add(cliente);
                cliente.setVisible(true);
            }
        });

        bt_produtos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                MenuProdutos produtos;
                produtos = new MenuProdutos();
                produtos.setNome(l_descricao.getText());
                desktop.add(produtos);
                produtos.setVisible(true);
            }
        });

        bt_cardapio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                MenuCardapio cardapio;
                cardapio = new MenuCardapio();
                cardapio.setNome(l_descricao.getText());
                desktop.add(cardapio);
                cardapio.setVisible(true);
            }
        });

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Tela_principal show = new Tela_principal();
                show.setNome(l_descricao.getText());

                show.setVisible(true);
                show.setLayout(null);
                dispose();
            }
        });

        clientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                MenuCliente show = new MenuCliente();
                show.setNome(l_descricao.getText());
                desktop.add(show);
                show.setVisible(true);
            }
        });

        produtos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                MenuProdutos show = new MenuProdutos();
                show.setNome(l_descricao.getText());
                desktop.add(show);
                show.setVisible(true);
            }
        });

        cardapio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                MenuCardapio show = new MenuCardapio();
                show.setNome(l_descricao.getText());
                desktop.add(show);
                show.setVisible(true);
            }
        });

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evto) {
                if (evto.getSource() == sair) {
                    resposta = JOptionPane.showOptionDialog(null, "Tem Certeza que Deseja Sair?", "Deseja Sair", JOptionPane.YES_NO_OPTION, DISPOSE_ON_CLOSE, null, null, null);
                    if (resposta == JOptionPane.YES_OPTION) {
                        // verifica se o usuário clicou no botão YES
                        GuiLogin login = new GuiLogin();
                        login.setVisible(true);
                        dispose();
                    }
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
