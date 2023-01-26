package models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "persone")
@Getter
@Setter
@ToString
public class Persona {
	
	@Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_seq")
	@SequenceGenerator(name = "persona_seq", sequenceName = "persona_sequence")
	private int id;
	
	private String nome;
	private String cognome;
	private String email;
	private LocalDate datadinascita;
	
	@Enumerated(EnumType.STRING)
	private Sesso sesso;
	
	@OneToMany(mappedBy = "persona")
	@OrderBy("dataEvento")
	private Set<Partecipazione> listapartecipazione = new HashSet<>();

}