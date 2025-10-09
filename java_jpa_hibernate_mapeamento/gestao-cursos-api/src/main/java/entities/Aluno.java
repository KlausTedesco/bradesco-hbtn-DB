package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeCompleto;
    
	private String matricula;
    
	@Temporal(TemporalType.DATE)
	private Date nascimento;
	
	private String email;
    
	@ManyToMany(mappedBy = "alunos")
    private List<Curso> cursos;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones;
    
    
      
	public Aluno() {
		super();
		this.cursos = new ArrayList<Curso>();
		this.enderecos = new ArrayList<Endereco>();
		this.telefones = new ArrayList<Telefone>();
	}

	public Aluno(String nomeCompleto, String matricula, Date nascimento, String email) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.matricula = matricula;
		this.nascimento = nascimento;
		this.email = email;
		this.cursos = new ArrayList<Curso>();
		this.enderecos = new ArrayList<Endereco>();
		this.telefones = new ArrayList<Telefone>();
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nomeCompleto=" + nomeCompleto + ", matricula=" + matricula + ", nascimento="
				+ nascimento + ", email=" + email + ", cursos=" + cursos + ", enderecos=" + enderecos + ", telefones="
				+ telefones + "]";
	}
	
}
