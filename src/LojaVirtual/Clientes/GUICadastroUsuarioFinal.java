package LojaVirtual.Clientes;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class GUICadastroUsuarioFinal extends JPanel {

    JLabel label1, label2, label3, label4, label5;
    JButton btGravar, btCancelar;
    static JTextField tfCodigo, tfNome, tfCPF, tfEndereço, tfCEP;
    private UsuarioFinalDAO UsuarioFinal;

    private JLabel limage, l_menu, l_descricao, l_altcliente, l_altprodut,
            l_altforn;
    private JPanel panel, panel2, barra_traseira;

    public GUICadastroUsuarioFinal() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {

        setLayout(null);

        label2 = new JLabel("Cliente");
        label2.setBounds(100, 120, 230, 40);
        label2.setFont(new Font("Arial", Font.BOLD, 15));

        label3 = new JLabel("CPF/CNPJ");
        label3.setFont(new Font("Arial", Font.BOLD, 15));
        label3.setBounds(640, 120, 230, 40);

        label4 = new JLabel("Endereço");
        label4.setBounds(100, 200, 230, 40);
        label4.setFont(new Font("Arial", Font.BOLD, 15));

        label5 = new JLabel("CEP");
        label5.setBounds(100, 280, 230, 40);
        label5.setFont(new Font("Arial", Font.BOLD, 15));

        tfNome = new JTextField(30);
        tfNome.setBounds(180, 120, 450, 40);

        tfCPF = new JTextField(20);
        tfCPF.setBounds(720, 120, 230, 40);

        tfEndereço = new JTextField(30);
        tfEndereço.setBounds(180, 200, 600, 40);

        tfCEP = new JTextField(8);
        tfCEP.setBounds(180, 280, 230, 40);

        btGravar = new JButton("Gravar");
        btGravar.setBounds(230, 400, 230, 40);
        btGravar.setToolTipText("Gravar");
        btGravar.setBackground(Color.BLUE);
        btGravar.setForeground(Color.WHITE);

        btCancelar = new JButton("Limpar");
        btCancelar.setBounds(600, 400, 230, 40);
        btCancelar.setToolTipText("Limpar");
        btCancelar.setBackground(Color.BLUE);
        btCancelar.setForeground(Color.WHITE);

        add(label2);
        add(tfNome);
        add(label3);
        add(tfCPF);
        add(label4);
        add(tfEndereço);
        add(label5);
        add(tfCEP);

        add(btGravar);

        add(btCancelar);

        setBotoes(true, true);
        UsuarioFinal = new UsuarioFinalDAO();
        if (!UsuarioFinal.bd.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexao, o sistema sera fechado!");
            System.exit(0);
        }

        panel2 = new JPanel();
        panel2.setBounds(70, 50, 900, 500);
        panel2.setBackground(new Color(245, 245, 245));
        this.add(panel2);
        panel2.setLayout(null);
//        TROCAR CAMINHO DA IMAGEM
        limage = new JLabel();
        Image img = new ImageIcon("C:\\Users\\Carlos Henrique\\Documents\\NetBeansProjects\\teste\\src\\LojaVirtual\\Imagens\\doces2.jpg").getImage();
        limage.setIcon(new ImageIcon(img));
        limage.setBounds(0, 0, 1050, 680);
        this.add(limage);
    }

    public void definirEventos() {

        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpaCampos();
            }
        });
        btGravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (tfNome.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O campo nome nao pode estar vazio!");
                    tfNome.requestFocus();
                    return;
                }
                if (tfCPF.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O campo CPF nao pode estar vazio!");
                    tfCPF.requestFocus();
                    return;
                }

                if (tfEndereço.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O campo endereço nao pode estar vazio!");
                    tfEndereço.requestFocus();
                    return;
                }

                if (tfCEP.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O campo CEP nao pode estar vazio!");
                    tfCEP.requestFocus();
                    return;
                }

                UsuarioFinal.usuariofinal.setNome(tfNome.getText());
                UsuarioFinal.usuariofinal.setCPF(Double.parseDouble(tfCPF.getText()));
                UsuarioFinal.usuariofinal.setEndereço(tfEndereço.getText());
                UsuarioFinal.usuariofinal.setCEP(Double.parseDouble(tfCEP.getText()));

                JOptionPane.showMessageDialog(null, UsuarioFinal.atualizar(UsuarioFinalDAO.INCLUSAO));
                limpaCampos();
            }
        });

    }

    public void limpaCampos() {

        tfNome.setText("");
        tfCPF.setText("");
        tfEndereço.setText("");
        tfCEP.setText("");

        tfCodigo.requestFocus();
        setBotoes(true, true);
    }

    public void atualizaCampos() {
        UsuarioFinal.usuariofinal.setCodigo(tfCodigo.getText());
        if (UsuarioFinal.localizar()) {
            tfCodigo.setText(UsuarioFinal.usuariofinal.getCodigo());
            tfNome.setText(UsuarioFinal.usuariofinal.getNome());
            tfCPF.setText("" + UsuarioFinal.usuariofinal.getCPF());
            tfEndereço.setText(UsuarioFinal.usuariofinal.getEndereço());
            tfCEP.setText("" + UsuarioFinal.usuariofinal.getCEP());
            setBotoes(true, true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario não encontrado!");
            limpaCampos();
        }
    }

    public void setBotoes(boolean bGravar, boolean bCancelar) {

        btGravar.setEnabled(bGravar);

        btCancelar.setEnabled(bCancelar);
    }

}
