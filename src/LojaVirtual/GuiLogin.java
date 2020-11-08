package LojaVirtual;

import LojaVirtual.Administrador.AdministradorDAO;
import LojaVirtual.Administrador.UsuarioDAO;
import LojaVirtual.Produtos.Produtos;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//Esta classe é a classe principal(main) responsavel pelo login

public class GuiLogin extends JFrame {

    JTextField tfLogin;
    JLabel area, label;
    JLabel lbSenha;
    JLabel lbLogin;
    JLabel lbUsuario;
    JButton btLogar;
    Produtos produto;
    BD bd;
    UsuarioDAO usuarios;
    AdministradorDAO administradores;
    JRadioButton adm, funcionario;
    ButtonGroup grupo;
    JPasswordField pfSenha;
    Container contentPane;
    JMenuBar mnBarra;
    JLabel limage, l_menu, l_descricao, l_altcliente, l_altprodut,
            l_altforn;
    JPanel panel, panel2, barra_traseira;

    public GuiLogin() {
        inicializarComponentes();
        definirEventos();
    }

    private void inicializarComponentes() {
        setTitle("Bem Vindo a doceria Swet");
        setBounds(150, 20, 1050, 680);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        barra_traseira = new JPanel();
        barra_traseira.setBounds(0, 600, 1050, 30);
        barra_traseira.setBackground(new Color(159, 182, 205));
        this.add(barra_traseira);

        adm = new JRadioButton("Administrador", false);
        funcionario = new JRadioButton("Funcionário", false);
        tfLogin = new JTextField(5);
        area = new JLabel();
        area.setForeground(Color.red);
        pfSenha = new JPasswordField(5);
        lbUsuario = new JLabel("");

        lbSenha = new JLabel("Senha:", JLabel.CENTER);
        lbLogin = new JLabel("Login:", JLabel.CENTER);
        btLogar = new JButton("Logar");
        btLogar.setBackground(Color.BLUE);
        btLogar.setForeground(Color.WHITE);

        lbSenha.setFont(new Font("", Font.BOLD, 14));
        lbLogin.setFont(new Font("", Font.BOLD, 14));
        lbSenha.setForeground(Color.black);
        lbLogin.setForeground(Color.black);
        lbUsuario.setFont(new Font("", Font.BOLD, 20));
        lbUsuario.setForeground(Color.black);

        funcionario.setFont(new Font("", Font.BOLD, 14));
        funcionario.setForeground(Color.black);

        adm.setFont(new Font("", Font.BOLD, 14));
        adm.setForeground(Color.black);

        area.setBounds(130, 250, 180, 25);
        tfLogin.setBounds(130, 140, 230, 40);
        lbLogin.setBounds(40, 140, 80, 25);
        lbSenha.setBounds(40, 200, 80, 25);
        pfSenha.setBounds(130, 200, 230, 40);
        funcionario.setBounds(80, 300, 120, 25);
        adm.setBounds(210, 300, 140, 25);
        btLogar.setBounds(100, 370, 230, 30);
        btLogar.setEnabled(false);
        lbUsuario.setBounds(160, 60, 200, 20);
        lbUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(lbUsuario);
        this.add(area);
        this.add(tfLogin);
        this.add(lbSenha);
        this.add(lbLogin);
        this.add(btLogar);
        this.add(pfSenha);
        this.add(funcionario);
        this.add(adm);

        panel2 = new JPanel();
        panel2.setBounds(50, 30, 330, 570);
        panel2.setBackground(new Color(245, 245, 245));
        this.add(panel2);
        panel2.setLayout(null);

        limage = new JLabel();
        Image img = new ImageIcon("C:\\Users\\Carlos\\Documents\\NetBeansProjects\\doceria\\src\\LojaVirtual\\Imagens\\doces2.jpg").getImage();
        limage.setIcon(new ImageIcon(img));
        limage.setBounds(0, 0, 1050, 680);
        this.add(limage);

        grupo = new ButtonGroup();
        grupo.add(funcionario);
        grupo.add(adm);

    }

    private void definirEventos() {
        funcionario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                lbUsuario.setText("");
                lbUsuario.setText("Funcionário");
                btLogar.setEnabled(true);
                btLogar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String senha = String.valueOf(pfSenha.getPassword());
                        usuarios = new UsuarioDAO();
                        if (!usuarios.bd.getConnection()) {
                            JOptionPane.showMessageDialog(null, "Falha na conexao, o sistema sera fechado!");
                            System.exit(0);
                        }
                        usuarios.usuario.setLogin(tfLogin.getText());
                        usuarios.usuario.setSenha(senha);

                        if (usuarios.checkLogin()) {
                            Tela_principal menu = new Tela_principal();
                            menu.setNome("Logado como: " + tfLogin.getText());

                            menu.setVisible(true);
                            GuiLogin.this.dispose();
                        } else {
                            area.setText("Login ou senha incorretas!");
                        }
                    }
                });
            }
        }
        );

        adm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lbUsuario.setText("");
                lbUsuario.setText("Administrador");
                btLogar.setEnabled(true);

                btLogar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String senha = String.valueOf(pfSenha.getPassword());
                        administradores = new AdministradorDAO();
                        if (!administradores.bd.getConnection()) {
                            JOptionPane.showMessageDialog(null, "Falha na conexao, o sistema sera fechado!");
                            System.exit(0);
                        }
                        administradores.administrador.setLogin(tfLogin.getText());
                        administradores.administrador.setSenha(senha);

                        if (administradores.checkLogin()) {

                            Tela_principalAdm menu = new Tela_principalAdm();
                            menu.setNome("Logado como: " + tfLogin.getText());
                            menu.setVisible(true);
                            GuiLogin.this.dispose();

                        } else {
                            area.setText("Login ou senha incorretas!");
                        }
                    }
                });
            }
        });
    }

    public static void main(String[] args) {
        GuiLogin frame = new GuiLogin();
        frame.setVisible(true);
        frame.setLayout(null);
    }
}
