package LojaVirtual.Produtos;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class GUICadastroProdutos extends JPanel {

    JLabel label1, label2, label3;
    JButton btGravar, btCancelar;
    static JTextField tfCodigo, tfNome, tfPreco;
    private ProdutosDAO produtos;

    private JLabel limage, l_menu, l_descricao, l_altcliente, l_altprodut,
            l_altforn;
    private JPanel panel, panel2, barra_traseira;

    public GUICadastroProdutos() {
        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {

        setLayout(null);

        label1 = new JLabel("Código ");
        label1.setBounds(100, 70, 230, 40);
        label1.setFont(new Font("Arial", Font.BOLD, 15));

        label2 = new JLabel("Nome");
        label2.setBounds(100, 120, 230, 40);
        label2.setFont(new Font("Arial", Font.BOLD, 15));

        label3 = new JLabel("Preço");
        label3.setFont(new Font("Arial", Font.BOLD, 15));
        label3.setBounds(640, 120, 230, 40);

        tfCodigo = new JTextField(15);
        tfCodigo.setBounds(170, 70, 230, 40);

        tfNome = new JTextField(30);
        tfNome.setBounds(170, 120, 450, 40);

        tfPreco = new JTextField(10);
        tfPreco.setBounds(720, 120, 230, 40);

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

        add(label1);
        add(tfCodigo);
        add(label2);
        add(tfNome);
        add(label3);
        add(tfPreco);

        add(btGravar);

        add(btCancelar);

        setBotoes(true, true);
        produtos = new ProdutosDAO();
        if (!produtos.bd.getConnection()) {
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
                if (tfCodigo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O codigo nao pode ser vazio!");
                    tfCodigo.requestFocus();
                    return;
                }
                if (tfNome.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O campo nome nao pode estar vazio!");
                    tfNome.requestFocus();
                    return;
                }
                if (tfPreco.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O campo preco nao pode estar vazio!");
                    tfPreco.requestFocus();
                    return;
                }
                produtos.produto.setCodigo(tfCodigo.getText());
                produtos.produto.setNome(tfNome.getText());
                produtos.produto.setPreco(Double.parseDouble(tfPreco.getText()));
                JOptionPane.showMessageDialog(null, produtos.atualizar(ProdutosDAO.INCLUSAO));
                limpaCampos();
            }
        });

    }

    public void limpaCampos() {
        tfCodigo.setText("");
        tfNome.setText("");
        tfPreco.setText("");
        tfCodigo.requestFocus();
        setBotoes(true, true);
    }

    public void atualizaCampos() {
        produtos.produto.setCodigo(tfCodigo.getText());
        if (produtos.localizar()) {
            tfCodigo.setText(produtos.produto.getCodigo());
            tfNome.setText(produtos.produto.getNome());
            tfPreco.setText("" + produtos.produto.getPreco());
            setBotoes(true, true);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            limpaCampos();
        }
    }

    public void setBotoes(boolean bGravar, boolean bCancelar) {

        btGravar.setEnabled(bGravar);

        btCancelar.setEnabled(bCancelar);
    }

}
