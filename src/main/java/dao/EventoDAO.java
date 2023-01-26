package dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.Concerto;
import models.Evento;
import models.GaraDiAtletica;
import models.Genere;
import models.PartitaDiCalcio;
import models.Persona;

import utils.JpaUtil;


public class EventoDAO extends JpaUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(EventoDAO.class);

	public void save(Evento ev) {
		try {
			EntityTransaction transaction = em.getTransaction();
			
			transaction.begin();
			em.persist(ev);
			transaction.commit();
			
			System.out.println( "Evento inserito correttamente" );
		} catch(Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error saving object: " + ev.getClass().getSimpleName(), ex);
			throw ex;
		} finally {
			em.close();
		}
	}
	
	public void refresh(Evento evento) {
		try {
			em.refresh(evento);
		} finally {
			em.close();
		}
	}
	
	public Evento getById(Long id) {
		try {
			return em.find(Evento.class, id);
		} finally {
			em.close();
		}
	}
	
	public void delete(Evento object) {
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
	
	// Queries
	public List<Concerto> getConcertiInStreaming(boolean inStreaming) {
		Query q = em.createNamedQuery("concertiInStreaming");
		q.setParameter("streaming", inStreaming);;
		
		return q.getResultList();
	}
	
	public List<Concerto> getConcertiPerGenere(List<Genere> listaGeneri) {
		Query q = em.createNamedQuery("concertiPerGenere");
		q.setParameter("listagenere", listaGeneri);
		
		return q.getResultList();
	}
	
	
	
	public List<PartitaDiCalcio> getPartiteVinteInCasa() {	
		return executeNamedQuery("partiteVinteInCasa", PartitaDiCalcio.class);
	}
	
	public List<PartitaDiCalcio> getPartiteVinteInTrasferta(List<Genere> listaGeneri) {
		return executeNamedQuery("partiteVinteInTrasferta", PartitaDiCalcio.class);
	}
	
	public List<PartitaDiCalcio> getpartiteVinteInCasa(List<Genere> listaGeneri) {
		return executeNamedQuery("partiteVinteInCasa", PartitaDiCalcio.class);
	}
	
	private <T> List<T> executeNamedQuery(String namedQuery, Class<T> returnClass) {
		Query q = em.createNamedQuery("concertiPerGenere");
		q.setParameter("listagenere", namedQuery);
		
		return q.getResultList();
	}
	
	
	
	
	public List<Evento> getEventiPerInvitato(Persona invitato) {
		Query q = em.createNamedQuery("concertiPerGenere");
		q.setParameter("persona", invitato);
		
		return q.getResultList();
	}
	
	public List<GaraDiAtletica> getGareDiAtleticaPerVincitore(Persona vincitore) {
		Query q = em.createNamedQuery("garePerVincitore");
		q.setParameter("persona", vincitore);
		
		return q.getResultList();
	}
	
	public List<GaraDiAtletica> getGareDiAtleticaPerPartecipante(Persona partecipante) {
		Query q = em.createNamedQuery("garePerVincitore");
		q.setParameter("persona", partecipante);
		
		return q.getResultList();
	}
	
	
	
}
