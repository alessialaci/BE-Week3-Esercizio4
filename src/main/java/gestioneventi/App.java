package gestioneventi;

import java.time.LocalDate;

import dao.EventoDAO;
import dao.LocationDAO;
import dao.PartecipazioneDAO;
import dao.PersonaDAO;

import models.Evento;
import models.Location;
import models.Partecipazione;
import models.Persona;
import models.Sesso;
import models.Stato;
import models.TipoEvento;

import utils.JpaUtil;


public class App extends JpaUtil {
	
	public static void main(String[] args) {
		Location location = saveLocation();
		Evento evento = saveEvento(location);
		Persona persona = savePersona();
		Partecipazione partecipazione = savePartecipazione(evento, persona);
	}
	
	private static Partecipazione savePartecipazione(Evento evento, Persona persona) {
		Partecipazione part = new Partecipazione();
		part.setEvento(evento);
		part.setPersona(persona);
		part.setStato(Stato.CONFERMATA);
		
		PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO();
		partecipazioneDAO.save(part);
		return part;
	}
	
	private static Persona savePersona() {
		Persona per = new Persona();
		per.setNome("Mario");
		per.setCognome("Rossi");
		per.setEmail("mario.rossi@gmail.com");
		per.setSesso(Sesso.MASCHIO);
		per.setDatadinascita(LocalDate.parse("1999-07-11"));
		PersonaDAO personaDAO = new PersonaDAO();
		personaDAO.save(per);
		return per;
	}
	
	private static Location saveLocation() {
		Location loc = new Location();
		loc.setCitta("Milano");
		loc.setNome("Stadio");
		
		LocationDAO locationDAO = new LocationDAO();
		locationDAO.save(loc);
		return loc;
	}
	
	private static Evento saveEvento(Location loc) {
		Evento ev = new Evento();
		ev.setTitolo("Discoteca");
		ev.setDataEvento(LocalDate.parse("2023-07-09"));
		ev.setDescrizione("Venite a scatenarvi con noi tra musica ed ospiti speciali");
		ev.setNumeroMassimoPartecipanti(150);
		ev.setTipoEvento(TipoEvento.PUBBLICO);
		ev.setLocation(loc);
		
		EventoDAO eventoDAO = new EventoDAO();
		eventoDAO.save(ev);
		return ev;
	}
}