package dao;

import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Persona;
import utils.JpaUtil;


public class PersonaDAO extends JpaUtil {

	private static final Logger logger = LoggerFactory.getLogger(EventoDAO.class);
	
	public void save(Persona p) {
		
		try {
			EntityTransaction transaction = em.getTransaction();
			
			transaction.begin();
			em.persist(p);
			transaction.commit();
			
			System.out.println( "Persona inserita correttamente" );
		} catch(Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error saving object: " + p.getClass().getSimpleName(), ex);
			throw ex;
		} finally {
			em.close();
		}
		
	}
	
	public void refresh(Persona persona) {
		try {
			em.refresh(persona);
		} finally {
			em.close();
		}
	}
	
	public Persona getById(Long id) {
		try {
			return em.find(Persona.class, id);
		} finally {
			em.close();
		}
	}

	public void delete(Persona object) {
		try {
			EntityTransaction transaction = em.getTransaction();
			
			transaction.begin();
			em.remove(object);
			transaction.commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error deleting object: " + object.getClass().getSimpleName(), ex);
			throw ex;
		} finally {
			em.close();
		}
	}
	
}
