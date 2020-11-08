package LojaVirtual.Clientes;

public class UsuarioFinal {

    private String codigo, nome, endereço;
    private double CPF, CEP;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCPF() {
        return CPF;
    }

    public void setCPF(double CPF) {
        this.CPF = CPF;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;

    }

    public double getCEP() {
        return CEP;
    }

    public void setCEP(double CEP) {
        this.CEP = CEP;
    }

}
