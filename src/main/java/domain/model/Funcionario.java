package domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {
	private BigDecimal salario;
	private String funcao;
	
	public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		this.salario = salario;
		this.funcao  = funcao;
		
	}
	
	
	
	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	public void aumentaSalario() {
        this.salario = this.salario.multiply(BigDecimal.valueOf(1.1));
    }
	
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String salarioFormatado = String.format(Locale.forLanguageTag("pt-BR"), "%,.2f", salario);
		return "Funcionario [nome=" + getNome() + ", dataNascimento=" + getDataNascimento().format(formatter) + 
				", salario=" + salarioFormatado + ", funcao=" + funcao + "]";
	}



}
