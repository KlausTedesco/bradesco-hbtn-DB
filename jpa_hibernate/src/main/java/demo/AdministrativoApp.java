package demo;

import java.util.Date;
import java.util.List;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

public class AdministrativoApp {

	public static void main(String[] args) {
		ProdutoModel produtoModel = new ProdutoModel();

		Produto p1 = new Produto();
		p1.setNome("TV");
		p1.setPreco(300.0);
		p1.setQuantidade(100);
		p1.setStatus(true);	

		Produto p2 = new Produto();
		p2.setNome("Som");
		p2.setPreco(150.0);
		p2.setQuantidade(200);
		p2.setStatus(true);

		// 1) Criando um produto
		produtoModel.create(p1);
		produtoModel.create(p2);

		// 2) Buscar 1 produto pelo ID
		produtoModel.findById(p1);
		
		// 3) Deletar produto
		produtoModel.delete(p2);
		
		// 4) Atualizar produto
		p1.setNome("TV");
		p1.setPreco(350.0);
		p1.setQuantidade(60);
		p1.setStatus(true);	
		
		produtoModel.update(p1);
		
		// 5) Buscando todos os produtos na base de dados
		List<Produto> produtos = produtoModel.findAll();
		System.out.println(produtos.toString());
		System.out.println("Qtde de produtos encontrados : " + produtos.size());
		
		ProdutoModel.closeEntityManagerFactory();
		
		System.out.println("-----------------------------------------------------");
		
		PessoaModel pessoaModel = new PessoaModel();

		Pessoa p3 = new Pessoa();
		p3.setNome("João Silva");
		p3.setEmail("js@mail.com");
		p3.setIdade(23);
		p3.setCpf("12345678900");
		p3.setDataNascimento(new Date(2002, 1, 12));

		Pessoa p4 = new Pessoa();
		p4.setNome("Ana Banana");
		p4.setEmail("ab@mail.com");
		p4.setIdade(33);
		p4.setCpf("98765432100");
		p4.setDataNascimento(new Date(1992, 1, 13));

		// 1) Criando uma pessoa
		pessoaModel.create(p3);
		pessoaModel.create(p4);

		// 2) Buscar 1 pessoa pelo ID
		pessoaModel.findById(p3);
		
		// 3) Deletar pessoa
		pessoaModel.delete(p3);
		
		// 4) Atualizar pessoa
		p4.setNome("Ana Oliveira");
		p4.setEmail("ao@mail.com");
		p4.setIdade(33);
		p4.setCpf("98765432100");
		p4.setDataNascimento(new Date(1992, 1, 13));
		
		pessoaModel.update(p4);
		
		// 5) Buscando todas as pessoas na base de dados
		List<Pessoa> pessoas = pessoaModel.findAll();
		System.out.println(pessoas.toString());
		System.out.println("Qtde de pessoas encontrados : " + pessoas.size());
		
		PessoaModel.closeEntityManagerFactory();

	}
}
