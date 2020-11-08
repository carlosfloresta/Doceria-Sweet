package LojaVirtual.Clientes;

import LojaVirtual.BD;
import java.sql.*;

public class UsuarioFinalDAO {

    public UsuarioFinal usuariofinal;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;

    public UsuarioFinalDAO() {
        bd = new BD();
        usuariofinal = new UsuarioFinal();
    }

    public boolean localizar() {
        sql = "select * from usuario_final where codigo = ?";
        try {
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, usuariofinal.getCodigo());
            resultSet = statement.executeQuery();
            resultSet.first();
            usuariofinal.setCodigo(resultSet.getString(1));
            usuariofinal.setNome(resultSet.getString(2));
            usuariofinal.setCPF(resultSet.getDouble(3));
            usuariofinal.setEndereço(resultSet.getString(4));
            usuariofinal.setCEP(resultSet.getDouble(5));
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }

    public String atualizar(int operacao) {
        men = "Operacao realizada com sucesso!";
        try {
            if (operacao == INCLUSAO) {
                sql = "insert into usuario_final values (?,?,?,?,?)";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, usuariofinal.getCodigo());
                statement.setString(2, usuariofinal.getNome());
                statement.setString(3, "" + usuariofinal.getCPF());
                statement.setString(4, "" + usuariofinal.getEndereço());
                statement.setString(5, "" + usuariofinal.getCEP());

            } else if (operacao == ALTERACAO) {
                sql = "update usuario_final set nome = ?, cpf = ?, endereco = ?, cep = ? where codigo = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(5, usuariofinal.getCodigo());
                statement.setString(1, usuariofinal.getNome());
                statement.setString(2, "" + usuariofinal.getCPF());
                statement.setString(3, "" + usuariofinal.getEndereço());
                statement.setString(4, "" + usuariofinal.getCEP());

            } else if (operacao == EXCLUSAO) {
                sql = "delete from usuario_final where codigo = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, usuariofinal.getCodigo());
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
