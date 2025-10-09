package demo;

import java.util.Date;
import java.util.List;

import entities.Aluno;
import entities.Curso;
import entities.Endereco;
import entities.MaterialCurso;
import entities.Professor;
import entities.Telefone;
import models.AlunoModel;
import models.CursoModel;

public class GestaoCursosMain {

	public static void main(String[] args) {

		AlunoModel alunoModel = new AlunoModel();
		
		Aluno aluno = new Aluno("Ana Banana", "123456", new Date(1, 1, 1), "");
		aluno.getEnderecos().add(new Endereco("Rua", "Sem Nome", "1", "ABC", "Florianópolis", "SC", 88000000));
		aluno.getTelefones().add(new Telefone("48", "91234-5678"));

		alunoModel.create(aluno);
		
		alunoModel.closeEntityManagerFactory();
		
		CursoModel cursoModel = new CursoModel();	
		Professor prof = new Professor("João Silva", "456789", "js@mail.com");
		MaterialCurso mc = new MaterialCurso("www.mtm.com");
		Curso curso = new Curso("Matematica", "MTM", mc);
		curso.setProfessor(prof);
		curso.getAlunos().add(aluno);
		
		cursoModel.create(curso);

		curso.setSigla("M");
		cursoModel.update(curso);

		List<Curso> cursos = cursoModel.findAll();
		System.out.println(cursos.toString());

		Long id = cursos.getFirst().getId();
		cursoModel.findById(id);

		cursoModel.delete(curso);
		
		cursoModel.closeEntityManagerFactory();
	}

}
