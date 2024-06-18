package app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import domain.model.Funcionario;

public class Principal {

	public static void main(String[] args) {
		List<Funcionario> funcionarios = new ArrayList<>();
		
		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Calo", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        // Remover o funcionário com o nome João
        funcionarios.removeIf(f -> f.getNome().equals("João"));
        
        // Imprimir todos os funcionários com todas suas informações, sendo que:
        // informação de data deve ser exibido no formato dd/mm/aaaa;
        // informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        funcionarios.forEach(System.out::println);
        
        // Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        
        funcionarios.forEach(Funcionario::aumentaSalario);
        
        // Imprimir todos os funcionários com todas suas informações após aumento
        System.out.println("\nApós aumento de salário:");
        funcionarios.forEach(System.out::println);
        
        
     // Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        
        
        // Imprimir os funcionários, agrupados por função.
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, funcs) -> {
            System.out.println("Função: " + funcao);
            funcs.forEach(System.out::println);
        });
        
        
        // Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);
        
        
        
        // Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow();
        
        int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("\nFuncionário com a maior idade:");
        System.out.println("Nome: " + funcionarioMaisVelho.getNome() + ", Idade: " + idade);

        
     // Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\nLista de funcionários por ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);
        
        
        // Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: " + String.format(Locale.forLanguageTag("pt-BR"), "%,.2f", totalSalarios));
        
        
     // Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha " + qtdSalariosMinimos + " salários mínimos");
        });
		

	}

}
