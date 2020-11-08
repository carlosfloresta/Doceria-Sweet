package LojaVirtual.Administrador;

import LojaVirtual.Produtos.ProdutosDAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuiLocalizarUsuario extends JPanel {

    JLabel label1, label2, label3, label4, label5, label6;
    JButton btAlterar, btExcluir, btLocalizar, btCancelar, btAlterar2, btExcluir2, btLocalizar2, btCancelar2;
    static JTextField tfCodigo, tfNome, tfPreco, tfCodigo2, tfNome2, tfPreco2;
    private UsuarioDAO usuarios;
    private AdministradorDAO administradores;
    JTextField label0, login, login2, espaco, adm;

    public GuiLocalizarUsuario() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBounds(0, 0, 800, 600);
        label1 = new JLabel("Código ");
        label2 = new JLabel("Login");
        label3 = new JLabel("Senha");
        tfCodigo = new JTextField(15);
        tfNome = new JTextField(30);
        tfPreco = new JTextField(10);

        label4 = new JLabel("Código ");
        label5 = new JLabel("Login");
        label6 = new JLabel("Senha");
        tfCodigo2 = new JTextField(15);
        tfNome2 = new JTextField(30);
        tfPreco2 = new JTextField(10);

        btAlterar = new JButton("Alterar");
        btAlterar.setToolTipText("Alterar");
        btExcluir = new JButton("Excluir");
        btExcluir.setToolTipText("Excluir");
        btLocalizar = new JButton("Localizar");
        btLocalizar.setToolTipText("Localizar");

        btCancelar = new JButton("Limpar");
        btCancelar.setToolTipText("Limpar");

        btAlterar2 = new JButton("Alterar");
        btAlterar2.setToolTipText("Alterar");
        btExcluir2 = new JButton("Excluir");
        btExcluir2.setToolTipText("Excluir");
        btLocalizar2 = new JButton("Localizar");
        btLocalizar2.setToolTipText("Localizar");

        btCancelar2 = new JButton("Limpar");
        btCancelar2.setToolTipText("Limpar");

        btAlterar.setBackground(Color.BLUE);
        btAlterar.setForeground(Color.WHITE);
        btExcluir.setBackground(Color.BLUE);
        btExcluir.setForeground(Color.WHITE);
        btLocalizar.setBackground(Color.BLUE);
        btLocalizar.setForeground(Color.WHITE);
        btCancelar.setBackground(Color.BLUE);
        btCancelar.setForeground(Color.WHITE);

        btAlterar2.setBackground(Color.BLACK);
        btAlterar2.setForeground(Color.WHITE);
        btExcluir2.setBackground(Color.BLACK);
        btExcluir2.setForeground(Color.WHITE);
        btLocalizar2.setBackground(Color.BLACK);
        btLocalizar2.setForeground(Color.WHITE);
        btCancelar2.setBackground(Color.BLACK);
        btCancelar2.setForeground(Color.WHITE);

        label0 = new JTextField(120);
        label0.setEditable(false);
        label0.setText(" Localizar/Alterar/Excluir Funcionário: ");
        label0.setFont(new Font("", Font.BOLD, 14));
        label0.setBackground(Color.BLUE);
        label0.setForeground(Color.WHITE);

        login = new JTextField(120);

        login.setEditable(false);
        login.setFont(new Font("", Font.BOLD, 14));
        login.setForeground(Color.red);

        login2 = new JTextField(120);

        login2.setEditable(false);
        login2.setFont(new Font("", Font.BOLD, 14));
        login2.setForeground(Color.red);

        espaco = new JTextField(120);
        espaco.setEditable(false);
        espaco.setBackground(Color.BLUE);

        adm = new JTextField(120);
        adm.setEditable(false);
        adm.setText(" Localizar/Alterar/Excluir Administrador: ");
        adm.setFont(new Font("", Font.BOLD, 14));
        adm.setBackground(Color.BLACK);
        adm.setForeground(Color.WHITE);

        add(label0);
        add(label1);
        add(tfCodigo);
        add(label2);
        add(tfNome);
        add(label3);
        add(tfPreco);
        add(btLocalizar);
        add(btAlterar);
        add(btExcluir);
        add(btCancelar);
        add(login);
        add(espaco);
        add(adm);

        add(label4);
        add(tfCodigo2);
        add(label5);
        add(tfNome2);
        add(label6);
        add(tfPreco2);
        add(btLocalizar2);
        add(btAlterar2);
        add(btExcluir2);
        add(btCancelar2);
        add(login2);

        setBotoes(true, false, false, true);
        setBotoes2(true, false, false, true);

        usuarios = new UsuarioDAO();
        if (!usuarios.bd.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexao, o sistema sera fechado!");
            System.exit(0);
        }

        administradores = new AdministradorDAO();
        if (!administradores.bd.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexao, o sistema sera fechado!");
            System.exit(0);
        }
    }

    public void definirEventos() {

        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpaCampos();
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usuarios.usuario.setCodigo(tfCodigo.getText());
                usuarios.usuario.setLogin(tfNome.getText());
                usuarios.usuario.setSenha(tfPreco.getText());
                JOptionPane.showMessageDialog(null, usuarios.atualizar(ProdutosDAO.ALTERACAO));
                limpaCampos();
            }
        });

        btExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usuarios.usuario.setCodigo(tfCodigo.getText());
                usuarios.checkLogin();
                int n = JOptionPane.showConfirmDialog(null, usuarios.usuario.getLogin(),
                        " Excluir o Usuario? ", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, usuarios.atualizar(ProdutosDAO.EXCLUSAO));
                    limpaCampos();
                }
            }
        });
        btLocalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizaCampos();
            }
        });

        btCancelar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpaCampos2();
            }
        });

        btAlterar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                administradores.administrador.setCodigo(tfCodigo2.getText());
                administradores.administrador.setLogin(tfNome2.getText());
                administradores.administrador.setSenha(tfPreco2.getText());
                JOptionPane.showMessageDialog(null, administradores.atualizar(AdministradorDAO.ALTERACAO));
                limpaCampos2();
            }
        });

        btExcluir2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                administradores.administrador.setCodigo(tfCodigo2.getText());
                administradores.checkLogin();
                int n = JOptionPane.showConfirmDialog(null, administradores.administrador.getLogin(),
                        " Excluir o Usuario? ", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, administradores.atualizar(AdministradorDAO.EXCLUSAO));
                    limpaCampos2();
                }
            }
        });
        btLocalizar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizaCampos2();
            }
        });
    }

    public void limpaCampos() {
        tfCodigo.setText("");
        tfNome.setText("");
        tfPreco.setText("");
        login.setText("");
        tfCodigo.requestFocus();
        setBotoes(true, false, false, true);
    }

    public void limpaCampos2() {
        tfCodigo2.setText("");
        tfNome2.setText("");
        tfPreco2.setText("");
        login2.setText("");
        tfCodigo2.requestFocus();
        setBotoes2(true, false, false, true);
    }

    public void atualizaCampos() {
        usuarios.usuario.setCodigo(tfCodigo.getText());
        if (usuarios.localizar()) {
            tfCodigo.setText(usuarios.usuario.getCodigo());
            tfNome.setText(usuarios.usuario.getLogin());
            tfPreco.setText("" + usuarios.usuario.getLogin());
            setBotoes(true, true, true, true);
        } else {
            login.setText(" Usuario não encontrado! ");

        }
    }

    public void atualizaCampos2() {
        administradores.administrador.setCodigo(tfCodigo2.getText());
        if (administradores.localizar()) {
            tfCodigo2.setText(administradores.administrador.getCodigo());
            tfNome2.setText(administradores.administrador.getLogin());
            tfPreco2.setText("" + administradores.administrador.getLogin());
            setBotoes2(true, true, true, true);
        } else {
            login2.setText(" Usuario não encontrado! ");

        }
    }

    public void setBotoes(boolean bLocalizar, boolean bAlterar, boolean bExcluir, boolean bCancelar) {

        btLocalizar.setEnabled(bLocalizar);

        btAlterar.setEnabled(bAlterar);
        btExcluir.setEnabled(bExcluir);
        btCancelar.setEnabled(bCancelar);
    }

    public void setBotoes2(boolean bLocalizar, boolean bAlterar, boolean bExcluir, boolean bCancelar) {

        btLocalizar2.setEnabled(bLocalizar);

        btAlterar2.setEnabled(bAlterar);
        btExcluir2.setEnabled(bExcluir);
        btCancelar2.setEnabled(bCancelar);
    }

}
