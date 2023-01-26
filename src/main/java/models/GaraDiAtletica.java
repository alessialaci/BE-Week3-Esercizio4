package models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "gare")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "garePerVincitore", query = "SELECT g FROM GaraDiAtletica g WHERE g.vincitore = :persona")
@NamedQuery(name = "garePerPartecipante", query = "SELECT g FROM GaraDiAtletica g WHERE :persona MEMBER OF g.setAtleti")
public class GaraDiAtletica extends Evento {
	
	@ManyToMany
	private Set<Persona> setAtleti;
	
	@ManyToOne
	private Persona vincitore;
	
}
