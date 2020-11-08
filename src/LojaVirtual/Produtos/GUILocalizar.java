package LojaVirtual.Produtos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUILocalizar extends JPanel {

    JLabel label1, label2, label3;
    JButton btAlterar, btExcluir, btLocalizar, btCancelar;
    static JTextField tfCodigo, tfNome, tfPreco;
    private ProdutosDAO produtos;

    private JLabel limage, l_menu, l_descricao, l_altcliente, l_altprodut,
            l_altforn;
    private JPanel panel, panel2, barra_traseira;

    public void init() {
        GUILocalizar Janela = new GUILocalizar();
        Janela.setVisible(true);
    }

    public GUILocalizar() {
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

        btAlterar = new JButton("Alterar");
        btAlterar.setToolTipText("Alterar");
        btAlterar.setBounds(260, 400, 130, 40);

        btExcluir = new JButton("Excluir");
        btExcluir.setToolTipText("Excluir");
        btExcluir.setBounds(400, 400, 130, 40);

        btLocalizar = new JButton("Localizar");
        btLocalizar.setToolTipText("Localizar");
        btLocalizar.setBounds(100, 400, 130, 40);

        btCancelar = new JButton("Limpar");
        btCancelar.setToolTipText("Limpar");
        btCancelar.setBounds(550, 400, 130, 40);

        btAlterar.setBackground(Color.BLUE);
        btAlterar.setForeground(Color.WHITE);
        btExcluir.setBackground(Color.BLUE);
        btExcluir.setForeground(Color.WHITE);
        btLocalizar.setBackground(Color.BLUE);
        btLocalizar.setForeground(Color.WHITE);
        btCancelar.setBackground(Color.BLUE);
        btCancelar.setForeground(Color.WHITE);

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

        setBotoes(true, false, false, false);
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

        btAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                produtos.produto.setCodigo(tfCodigo.getText());
                produtos.produto.setNome(tfNome.getText());
                produtos.produto.setPreco(Double.parseDouble(tfPreco.getText()));
                JOptionPane.showMessageDialog(null, produtos.atualizar(ProdutosDAO.ALTERACAO));
                limpaCampos();
            }
        });

        btExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                produtos.produto.setCodigo(tfCodigo.getText());
                produtos.localizar();
                int n = JOptionPane.showConfirmDialog(null, produtos.produto.getNome(),
                        " Excluir o Produto? ", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, produtos.atualizar(ProdutosDAO.EXCLUSAO));
                    limpaCampos();
                }
            }
        });
        btLocalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizaCampos();
            }
        });
    }

    public void limpaCampos() {
        tfCodigo.setText("");
        tfNome.setText("");
        tfPreco.setText("");
        tfCodigo.requestFocus();
        setBotoes(true, false, false, false);
    }

    public void atualizaCampos() {
        produtos.produto.setCodigo(tfCodigo.getText());
        if (produtos.localizar()) {
            tfCodigo.setText(produtos.produto.getCodigo());
            tfNome.setText(produtos.produto.getNome());
            tfPreco.setText("" + produtos.produto.getPreco());
            setBotoes(true, true, true, true);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            limpaCampos();
        }
    }

    public void setBotoes(boolean bLocalizar, boolean bAlterar, boolean bExcluir, boolean bCancelar) {

        btLocalizar.setEnabled(bLocalizar);

        btAlterar.setEnabled(bAlterar);
        btExcluir.setEnabled(bExcluir);
        btCancelar.setEnabled(bCancelar);
    }

}
