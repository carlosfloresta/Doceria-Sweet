package LojaVirtual.Administrador;

import LojaVirtual.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public BD bd;

    public Usuarios usuario;

    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;

    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;

    public UsuarioDAO() {
        bd = new BD();
        usuario = new Usuarios();
    }

    public boolean localizar() {
        sql = "select * from usuarios where id = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, usuario.getCodigo());
            resultSet = statement.executeQuery();
            resultSet.first();
            usuario.setCodigo(resultSet.getString(1));
            usuario.setLogin(resultSet.getString(2));
            usuario.setSenha(resultSet.getString(3));
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }

    public boolean checkLogin() {

        sql = "select * from usuarios where login = ? and senha = ?";
        try {

            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            resultSet = statement.executeQuery();
            resultSet.first();
            usuario.setLogin(resultSet.getString(1));
            usuario.setSenha(resultSet.getString(2));

            return true;

        } catch (SQLException erro) {
            return false;

        }

    }

    public String atualizar(int operacao) {
        men = "Operacao realizada com sucesso!";
        try {
            if (operacao == INCLUSAO) {
                sql = "insert into usuarios values (?,?,?)";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, usuario.getCodigo());
                statement.setString(2, usuario.getLogin());
                statement.setString(3, usuario.getSenha());

            } else if (operacao == ALTERACAO) {
                sql = "update usuarios set login = ?, senha = ? where id = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(3, usuario.getCodigo());
                statement.setString(1, usuario.getLogin());
                statement.setString(2, usuario.getSenha());

            } else if (operacao == EXCLUSAO) {
                sql = "delete from usuarios where id = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, usuario.getCodigo());
            }
            if (statement.executeUpdate() == 0) {
                men = "Falha na operacao!";
            }
        } catch (SQLException erro) {
            men = "Falha na operacao " + erro.toString();
        }
        return men;
    }

}
