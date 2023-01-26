package models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "concerti")
@Getter
@Setter
@ToString
@NoArgsConstructor
@NamedQuery(name = "concertiInStreaming", query = "SELECT c FROM Concerto c WHERE c.inStreaming = :streaming")
@NamedQuery(name = "concertiPerGenere", query = "SELECT c from Concerto c WHERE c.genere IN :listagenere")
public class Concerto extends Evento {

	@Column(name = "genere")
	@Enumerated(EnumType.STRING)
	private Genere genere;
	
	@Column(name = "in_streaming")
	private boolean inStreaming;

	public Concerto(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			int numeroMassimoPartecipanti, Location location, Genere genere, boolean inStreaming) {
		super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
		this.genere = genere;
		this.inStreaming = inStreaming;
	}
	
}
