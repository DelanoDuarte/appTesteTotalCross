/**
 * 
 */
package model;

/**
 * @author delan
 *
 */
public class Funcionario {

	private Integer id;
	private String nome;
	private String sobrenome;
	private double salario;
	private String cargo;

	public Funcionario() {

	}

	public Funcionario(String nome, String sobrenome, double salario, String cargo) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.salario = salario;
		this.cargo = cargo;
	}

	public Funcionario(Integer id, String nome, String sobrenome, double salario, String cargo) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.salario = salario;
		this.cargo = cargo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
