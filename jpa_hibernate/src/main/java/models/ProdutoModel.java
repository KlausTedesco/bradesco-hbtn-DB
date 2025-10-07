package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Produto;

public class ProdutoModel {
	
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

	public void create(Produto p) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Produto criado com sucesso !!!");
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao criar o produto !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void update(Produto p) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			Produto pdb = em.find(Produto.class, p.getId());

			if (pdb != null) {
				pdb.setNome(p.getNome());
				pdb.setPreco(p.getPreco());
				pdb.setQuantidade(p.getQuantidade());
				pdb.setStatus(p.isStatus());
			} else {
				System.err.println("Entity com ID " + p.getId() + " não encontrado.");
			}

			em.getTransaction().commit();
			System.out.println("Produto atualizado com sucesso: " + p.toString());
		} catch (Exception e) {
			em.close();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public void delete(Produto p) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			Produto pdb = em.find(Produto.class, p.getId());

			if (pdb != null) {
				em.remove(pdb);
				System.out.println("Produto excluido com sucesso !!!");
			} else {
				System.err.println("Entity com ID " + p.getId() + " não encontrado.");
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.close();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.err.println("Erro ao excluir o produto !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
	}

	public Produto findById(Produto p) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		Produto pr = null;
		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			pr = em.find(Produto.class, p.getId());

			if (pr == null) {
				System.err.println("Entity com ID " + p.getId() + " não encontrado.");
			}

			em.getTransaction().commit();
			System.out.println("Produto encontrado com sucesso: " + pr.toString());
		} catch (Exception e) {
			em.close();
			System.err.println("Erro ao encontrar o produto !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
		return pr;
	}

	public List<Produto> findAll() {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		List<Produto> produtos = new ArrayList<Produto>();
		try {
			System.out.println("Iniciando a transação");
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT p FROM Produto p");

			if (query != null) {
				produtos = query.getResultList();
			} else {
				System.err.println("Erro ao listar os produtos !!!");
			}
		} catch (Exception e) {
			em.close();
			System.err.println("Erro  ao listar os produtos  !!!" + e.getMessage());
		} finally {
			em.close();
			System.out.println("Finalizando a transação");
		}
		return produtos;
	}
}
