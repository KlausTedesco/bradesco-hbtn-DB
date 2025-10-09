package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Curso;

public class CursoModel {

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

	public void create(Curso c) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
			System.out.println("Curso criado com sucesso !!!");
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao criar curso !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void update(Curso c) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
			System.out.println("Curso atualizado com sucesso: " + c.toString());
		} catch (Exception e) {
			em.close();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Erro ao atualizar curso !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void delete(Curso c) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			Curso pdb = em.find(Curso.class, c.getId());

			if (pdb != null) {
				em.remove(pdb);
				System.out.println("Curso excluido com sucesso !!!");
			} else {
				System.err.println("Entity com ID " + c.getId() + " não encontrado.");
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Erro ao excluir curso !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public Curso findById(Long id) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		Curso pr = null;
		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			pr = em.find(Curso.class, id);

			if (pr == null) {
				System.err.println("Entity com ID " + id + " não encontrado.");
			}

			em.getTransaction().commit();
			System.out.println("Curso encontrado com sucesso: " + pr.toString());
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao encontrar curso !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
		return pr;
	}

	public List<Curso> findAll() {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		List<Curso> cursos = new ArrayList<Curso>();
		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			TypedQuery<Curso> query = em.createQuery("from Curso", Curso.class);

			if (query != null) {
				cursos = query.getResultList();
			} else {
				System.err.println("Erro ao executar query de cursos !!!");
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao listar os cursos  !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
		return cursos;
	}
}
