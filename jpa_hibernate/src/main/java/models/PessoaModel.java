package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Pessoa;

public class PessoaModel {
	
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("admin-jpa"); 
        }
        return emf;
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

	public void create(Pessoa p) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Pessoa criado com sucesso !!!");
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao criar a pessoa !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void update(Pessoa p) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			Pessoa pdb = em.find(Pessoa.class, p.getId());

			if (pdb != null) {
				pdb.setNome(p.getNome());
				pdb.setEmail(p.getEmail());
				pdb.setIdade(p.getIdade());
				pdb.setCpf(p.getCpf());
				pdb.setDataNascimento(p.getDataNascimento());
			} else {
				System.err.println("Entity com ID " + p.getId() + " não encontrado.");
			}

			em.getTransaction().commit();
			System.out.println("Pessoa atualizado com sucesso: " + p.toString());
		} catch (Exception e) {
			em.close();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Erro ao atualizar a pessoa !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void delete(Pessoa p) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			Pessoa pdb = em.find(Pessoa.class, p.getId());

			if (pdb != null) {
				em.remove(pdb);
				System.out.println("Pessoa excluido com sucesso !!!");
			} else {
				System.err.println("Entity com ID " + p.getId() + " não encontrado.");
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Erro ao excluir a pessoa !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public Pessoa findById(Pessoa p) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		Pessoa pr = null;
		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			pr = em.find(Pessoa.class, p.getId());

			if (pr == null) {
				System.err.println("Entity com ID " + p.getId() + " não encontrado.");
			}

			em.getTransaction().commit();
			System.out.println("Pessoa encontrado com sucesso: " + pr.toString());
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao encontrar a pessoa !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
		return pr;
	}

	public List<Pessoa> findAll() {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		List<Pessoa> produtos = new ArrayList<Pessoa>();
		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT p FROM Pessoa p");

			if (query != null) {
				produtos = query.getResultList();
			} else {
				System.err.println("Erro ao listar as pessoas !!!");
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			System.err.println("Erro  ao listar as pessoas  !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
		return produtos;
	}
}
