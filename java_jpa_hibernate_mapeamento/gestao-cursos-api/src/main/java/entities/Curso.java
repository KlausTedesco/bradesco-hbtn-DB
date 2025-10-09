package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
    
	private String sigla;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id")
    private MaterialCurso materialCurso;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professor_id")
    private Professor professor;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_curso_aluno",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunos;
    
	public Curso() {
		super();
	}

	public Curso(String nome, String sigla, MaterialCurso materialCurso) {
		super();
		this.nome = nome;
		this.sigla = sigla;
		this.materialCurso = materialCurso;
		this.professor = null;
		this.alunos = new ArrayList<Aluno>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public MaterialCurso getMaterialCurso() {
		return materialCurso;
	}

	public void setMaterialCurso(MaterialCurso materialCurso) {
		this.materialCurso = materialCurso;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", materialCurso=" + materialCurso
				+ ", professor=" + professor + ", alunos=" + alunos + "]";
	}
    
}
