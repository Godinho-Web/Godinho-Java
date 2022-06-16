package org.libertas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.libertas.pojo.Jogo;


public class JogoDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexaoHibernate");
	
	EntityManager em = emf.createEntityManager();
	
	public void inserir(Jogo j) {
		em.getTransaction().begin();
		em.persist(j);
		em.getTransaction().commit();
	}
	
	public void alterar(Jogo j) {
		em.getTransaction().begin();
		em.merge(j);
		em.getTransaction().commit();
		
	}
	
	public void excluir(Jogo j) {
		em.getTransaction().begin();
		em.remove(em.merge(j));
		em.getTransaction().commit();
	}
	
	public List<Jogo> listar(){
		Query query = em.createQuery("SELECT j from Jogo j");
		List<Jogo> dados = query.getResultList();
		return dados;
	}
	
	public Jogo consultar(int id) {
		return em.find(Jogo.class, id);
	}
	
}
