package models;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "partite")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "partiteVinteInCasa", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraDiCasa")
@NamedQuery(name = "partiteVinteInTrasferta", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraOspite")
@NamedQuery(name = "partitePareggiate", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente IS NULL")

public class PartitaDiCalcio extends Evento {
	
	private String squadraDiCasa;
	private String squadraOspite;
	private String squadraVincente;
	private int golSquadraDiCasa;
	private int golSquadraOspite;
	
}
