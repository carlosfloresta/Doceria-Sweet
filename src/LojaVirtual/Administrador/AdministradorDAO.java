
package LojaVirtual.Administrador;

import LojaVirtual.BD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDAO {

    public BD bd;

    public Administradores administrador;

    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;

    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;

    public AdministradorDAO() {
        bd = new BD();
        administrador = new Administradores();
    }

    public boolean localizar() {
        sql = "select * from administradores where id = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, administrador.getCodigo());
            resultSet = statement.executeQuery();
            resultSet.first();
            administrador.setCodigo(resultSet.getString(1));
            administrador.setLogin(resultSet.getString(2));
            administrador.setSenha(resultSet.getString(3));
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }

    public boolean checkLogin() {

        sql = "select * from administradores where login = ? and senha = ?";
        try {

            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, administrador.getLogin());
            statement.setString(2, administrador.getSenha());
            resultSet = statement.executeQuery();
            resultSet.first();
            administrador.setLogin(resultSet.getString(1));
            administrador.setSenha(resultSet.getString(2));

            return true;

        } catch (SQLException erro) {
            return false;

        }

    }

    public String atualizar(int operacao) {
        men = "Operacao realizada com sucesso!";
        try {
            if (operacao == INCLUSAO) {
                sql = "insert into administradores values (?,?,?)";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, administrador.getCodigo());
                statement.setString(2, administrador.getLogin());
                statement.setString(3, administrador.getSenha());

            } else if (operacao == ALTERACAO) {
                sql = "update administradores set login = ?, senha = ? where id = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(3, administrador.getCodigo());
                statement.setString(1, administrador.getLogin());
                statement.setString(2, administrador.getSenha());

            } else if (operacao == EXCLUSAO) {
                sql = "delete from administradores where id = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, administrador.getCodigo());
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
