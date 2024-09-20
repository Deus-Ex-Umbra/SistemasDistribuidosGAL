package retencionjudicial;
public class Cuenta {
    private Banco banco;
    private String numero_cuenta;
    private String ci;
    private String nombres;
    private String apellidos;
    private Double saldo;

    public Cuenta(Banco banco, String numero_cuenta, String ci, String nombres, String apellidos, Double saldo) {
        this.banco = banco;
        this.numero_cuenta = numero_cuenta;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.saldo = saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getNumeroCuenta() {
        return numero_cuenta;
    }

    public void setNumeroCuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return banco + "-" + numero_cuenta + "-" + ci + "-" + nombres + "-" + apellidos + "-" + saldo;
    }
}
