package org.example;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário "João"
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários
        System.out.println("Funcionários:");
        funcionarios.forEach(funcionario -> {
            String dataFormatada = funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String salarioFormatado = formatarNumero(funcionario.getSalario());
            System.out.println("Nome: " + funcionario.getNome() +
                    ", Data de Nascimento: " + dataFormatada +
                    ", Salário: " + salarioFormatado +
                    ", Função: " + funcionario.getFuncao());
        });

        // 3.4 - Aumentar salário em 10%
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(BigDecimal.TEN));

        // 3.5 - Agrupar funcionários por função em um Map
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(funcionario -> {
                String salarioFormatado = formatarNumero(funcionario.getSalario());
                System.out.println("Nome: " + funcionario.getNome() +
                        ", Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                        ", Salário: " + salarioFormatado);
            });
        });

        // 3.8 - Imprimir funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(funcionario -> {
                    String salarioFormatado = formatarNumero(funcionario.getSalario());
                    System.out.println("Nome: " + funcionario.getNome() +
                            ", Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                            ", Salário: " + salarioFormatado +
                            ", Função: " + funcionario.getFuncao());
                });

        // 3.9 - Imprimir funcionário com maior idade
        System.out.println("\nFuncionário com maior idade:");
        Funcionario maisVelho = Collections.max(funcionarios,
                Comparator.comparing(Funcionario::getDataNascimento));
        LocalDate hoje = LocalDate.now();
        int idade = hoje.getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idade + " anos");

        // 3.10 - Imprimir lista de funcionários por ordem alfabética
        System.out.println("\nLista de funcionários por ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(funcionario -> {
                    String salarioFormatado = formatarNumero(funcionario.getSalario());
                    System.out.println("Nome: " + funcionario.getNome() +
                            ", Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                            ", Salário: " + salarioFormatado +
                            ", Função: " + funcionario.getFuncao());
                });

        // 3.11 - Imprimir total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: " + formatarNumero(totalSalarios));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário (salário mínimo R$1212.00)
        System.out.println("\nSalários em múltiplos de salário mínimo:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioMinimo = new BigDecimal("1212.00");
            BigDecimal multiploSalarioMinimo = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println(funcionario.getNome() + ": " + multiploSalarioMinimo + " salários mínimos");
        });
    }

    // Método auxiliar para formatar números
    private static String formatarNumero(BigDecimal numero) {
        return String.format("%,.2f", numero);
    }
}

class Funcionario {
    private String nome;
    private LocalDate dataNascimento;
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.funcao = funcao;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void aumentarSalario(BigDecimal percentual) {
        BigDecimal aumento = salario.multiply(percentual.divide(BigDecimal.valueOf(100)));
        this.salario = salario.add(aumento);
    }
}
