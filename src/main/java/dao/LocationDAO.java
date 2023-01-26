package dao;

import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Location;
import utils.JpaUtil;


public class LocationDAO extends JpaUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(EventoDAO.class);
	
	public void save(Location lo) {
		try {
			EntityTransaction transaction = em.getTransaction();
			
			t.begin();
			em.persist(lo);
			t.commit();
			
			System.out.println( "Location inserita correttamente" );
		} catch(Exception e) {
			em.getTransaction().rollback();
			logger.error("Error saving object: " + lo.getClass().getSimpleName(), e);
			throw e;
		} finally {
			em.close();
		}
	}
	
	public void refresh(Location location) {
		try {
			em.refresh(location);
		} finally {
			em.close();
		}
	}
	
	public Location getById(int id) {
		try {
			return em.find(Location.class, id);
		} finally {
			em.close();
		}
	}
	
	public void delete(Location object) {
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