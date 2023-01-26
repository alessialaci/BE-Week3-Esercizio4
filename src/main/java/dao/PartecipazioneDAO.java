package dao;

import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Partecipazione;
import utils.JpaUtil;


public class PartecipazioneDAO extends JpaUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(EventoDAO.class);
	
	public void save(Partecipazione par) {
		try {
			t.begin();
			em.persist(par);
			t.commit();
			
			System.out.println( "Partecipazione inserita correttamente" );
		} catch(Exception e) {
			logger.error("Error saving object: " + par.getClass().getSimpleName(), e);
		}
	}

	public void refresh(Partecipazione partecipazione) {
		try {
			em.refresh(partecipazione);
		} finally {
			em.close();
		}
	}
	
	public Partecipazione getById(int id) {	
		try {
			return em.find(Partecipazione.class, id);
		} finally {
			em.close();
		}
	}
	
	public void delete(Partecipazione object) {
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
