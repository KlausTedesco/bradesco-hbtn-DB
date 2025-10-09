package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Aluno;

public class AlunoModel {

	private static EntityManagerFactory emf;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
		}
		return emf;
	}

	public static void closeEntityManagerFactory() {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}

	public void create(Aluno p) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Aluno criado com sucesso !!!");
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao criar aluno !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void update(Aluno a) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			em.merge(a);
			em.getTransaction().commit();
			System.out.println("Aluno atualizado com sucesso: " + a.toString());
		} catch (Exception e) {
			em.close();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Erro ao atualizar aluno !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void delete(Aluno a) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			Aluno pdb = em.find(Aluno.class, a.getId());

			if (pdb != null) {
				em.remove(pdb);
				System.out.println("Aluno excluido com sucesso !!!");
			} else {
				System.err.println("Entity com ID " + a.getId() + " não encontrado.");
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Erro ao excluir aluno !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public Aluno findById(Long id) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		Aluno pr = null;
		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			pr = em.find(Aluno.class, id);

			if (pr == null) {
				System.err.println("Entity com ID " + id + " não encontrado.");
			}

			em.getTransaction().commit();
			System.out.println("Aluno encontrado com sucesso: " + pr.toString());
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao encontrar aluno !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
		return pr;
	}

	public List<Aluno> findAll() {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			TypedQuery<Aluno> query = em.createQuery("from Aluno", Aluno.class);

			if (query != null) {
				alunos = query.getResultList();
			} else {
				System.err.println("Erro ao executar query de alunos !!!");
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao listar os alunos  !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
		return alunos;
	}
}
