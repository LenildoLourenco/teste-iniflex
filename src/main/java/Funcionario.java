import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void aumentarSalario(BigDecimal percentualAumento) {
        BigDecimal multiplicador = BigDecimal.ONE.add(percentualAumento.divide(BigDecimal.valueOf(100)));
        this.salario = this.salario.multiply(multiplicador);
    }

    @Override
    public String toString() {
        return super.toString() + ", Salário: " + String.format("%,.2f", salario) + ", Função: " + funcao;
    }
}
