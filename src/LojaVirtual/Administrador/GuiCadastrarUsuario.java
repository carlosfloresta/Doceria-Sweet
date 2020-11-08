package LojaVirtual.Administrador;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GuiCadastrarUsuario extends JPanel {

    JLabel label1, label2, label3, label4, label5, label6;
    JTextField label0, login, login2, espaco, adm;
    JButton btGravar, btCancelar, btGravar2, btCancelar2;
    static JTextField tfCodigo, tfLogin, tfSenha, tfCodigo2, tfLogin2, tfSenha2;
    private UsuarioDAO usuarios;
    private AdministradorDAO administradores;

   

    public GuiCadastrarUsuario() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        setBounds(0, 0, 800, 600);
        label0 = new JTextField(120);
        label0.setEditable(false);
        label0.setText(" Cadastrar Funcionário: ");
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
        adm.setText(" Cadastrar Administrador: ");
        adm.setFont(new Font("", Font.BOLD, 14));
        adm.setBackground(Color.BLACK);
        adm.setForeground(Color.WHITE);

        label1 = new JLabel("Código");
        label2 = new JLabel("Login");
        label3 = new JLabel("Senha");
        tfCodigo = new JTextField(15);
        tfLogin = new JTextField(30);
        tfSenha = new JTextField(10);

        btGravar = new JButton("Gravar");
        btGravar.setToolTipText("Gravar");
        btGravar.setBackground(Color.BLUE);
        btGravar.setForeground(Color.WHITE);

        btCancelar = new JButton("Limpar");
        btCancelar.setToolTipText("Limpar");
        btCancelar.setBackground(Color.BLUE);
        btCancelar.setForeground(Color.WHITE);

        label4 = new JLabel("Código");
        label5 = new JLabel("Login");
        label6 = new JLabel("Senha");
        tfCodigo2 = new JTextField(15);
        tfLogin2 = new JTextField(30);
        tfSenha2 = new JTextField(10);

        btGravar2 = new JButton("Gravar");
        btGravar2.setToolTipText("Gravar");
        btGravar2.setBackground(Color.BLACK);
        btGravar2.setForeground(Color.WHITE);

        btCancelar2 = new JButton("Limpar");
        btCancelar2.setToolTipText("Limpar");
        btCancelar2.setBackground(Color.BLACK);
        btCancelar2.setForeground(Color.WHITE);

        add(label0);

        add(label1);
        add(tfCodigo);
        add(label2);
        add(tfLogin);
        add(label3);
        add(tfSenha);

        add(btGravar);

        add(btCancelar);
        add(login);
        add(espaco);
        add(adm);
        add(label4);
        add(tfCodigo2);
        add(label5);
        add(tfLogin2);
        add(label6);
        add(tfSenha2);
        add(btGravar2);
        add(btCancelar2);
        add(login2);

        setBotoes(true, true);
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
        btGravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfCodigo.getText().equals("")) {
                    login.setText(" O codigo nao pode ser vazio! ");

                    tfCodigo.requestFocus();
                    return;
                }
                if (tfLogin.getText().equals("")) {
                    login.setText(" O campo login nao pode estar vazio!");

                    tfLogin.requestFocus();
                    return;
                }
                if (tfSenha.getText().equals("")) {
                    login.setText(" O campo senha nao pode estar vazio!");

                    tfSenha.requestFocus();
                    return;
                }
                usuarios.usuario.setCodigo(tfCodigo.getText());
                usuarios.usuario.setLogin(tfLogin.getText());
                usuarios.usuario.setSenha(tfSenha.getText());
                JOptionPane.showMessageDialog(null, usuarios.atualizar(UsuarioDAO.INCLUSAO));
                limpaCampos();
            }
        });

        btCancelar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpaCampos2();
            }
        });
        btGravar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfCodigo2.getText().equals("")) {
                    login2.setText(" O codigo nao pode ser vazio! ");

                    tfCodigo2.requestFocus();
                    return;
                }
                if (tfLogin2.getText().equals("")) {
                    login2.setText(" O campo login nao pode estar vazio!");

                    tfLogin2.requestFocus();
                    return;
                }
                if (tfSenha2.getText().equals("")) {
                    login2.setText(" O campo senha nao pode estar vazio!");

                    tfSenha2.requestFocus();
                    return;
                }
                administradores.administrador.setCodigo(tfCodigo2.getText());
                administradores.administrador.setLogin(tfLogin2.getText());
                administradores.administrador.setSenha(tfSenha2.getText());
                JOptionPane.showMessageDialog(null, administradores.atualizar(UsuarioDAO.INCLUSAO));
                limpaCampos2();
            }
        });

    }

    public void limpaCampos() {
        tfCodigo.setText("");
        tfLogin.setText("");
        tfSenha.setText("");
        login.setText("");
        tfCodigo.requestFocus();
        setBotoes(true, true);
    }

    public void limpaCampos2() {
        tfCodigo2.setText("");
        tfLogin2.setText("");
        tfSenha2.setText("");
        login2.setText("");
        tfCodigo2.requestFocus();
        setBotoes(true, true);
    }

    public void atualizaCampos() {
        usuarios.usuario.setCodigo(tfCodigo.getText());
        if (usuarios.checkLogin()) {
            tfCodigo.setText(usuarios.usuario.getCodigo());
            tfLogin.setText(usuarios.usuario.getLogin());
            tfSenha.setText("" + usuarios.usuario.getSenha());
            setBotoes(true, true);
        } else {
            login.setText(" Usuario não encontrado!");

            limpaCampos();
        }
    }

    public void setBotoes(boolean bGravar, boolean bCancelar) {

        btGravar.setEnabled(bGravar);

        btCancelar.setEnabled(bCancelar);
    }

}
