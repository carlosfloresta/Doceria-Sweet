package LojaVirtual.Administrador;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MenuAdm extends JInternalFrame {

    private JTabbedPane j1;
    private JMenuBar mnBarra;
    private JButton miSair, miLogoff;
    private JLabel labelusuario, label2;

    private JLabel limage, l_menu, l_descricao, l_altcliente, l_altprodut,
            l_altforn;
    private JPanel panel, panel2;
    private JPanel barra_traseira;

    public void setNome(String Usuario) {
        label2.setText(Usuario);
    }

    public MenuAdm() {

        inicializarComponentes();

    }

    private void inicializarComponentes() {
        setBounds(150, 20, 1050, 680);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setClosable(true);
        setLocation(-2, -30);

        j1 = new JTabbedPane();
        j1.setBounds(0, 0, 1040, 600);

        GuiCadastrarUsuario usuariofinal = new GuiCadastrarUsuario();
        MostraUsuarios lista_usuario = new MostraUsuarios();
        GuiLocalizarUsuario localizarUsuario = new GuiLocalizarUsuario();

        j1.addTab("Cadastrar Cliente", usuariofinal);
        j1.addTab("Listar Clientes", lista_usuario);
        j1.addTab("Localizar Cliente", localizarUsuario);
        j1.setBackground(Color.BLUE);
        j1.setForeground(Color.WHITE);
        add(j1);

        label2 = new JLabel();
        label2.setForeground(Color.WHITE);
        barra_traseira = new JPanel();
        barra_traseira.setBounds(0, 600, 1050, 38);
        barra_traseira.setBackground(new Color(159, 182, 205));
        this.add(barra_traseira);
        barra_traseira.add(label2);
    }

}
