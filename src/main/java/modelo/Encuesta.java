package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "encuestas")
public class Encuesta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_encuesta")
	private int id_encuesta;
	
	@Column(length = 255)
	private String nombre;

	//private Usuario usuario;
}
