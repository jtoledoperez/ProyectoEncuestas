package modelo;

import javax.persistence.*;

@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    private int idPregunta;

    @ManyToOne
    @JoinColumn(name = "encuesta", referencedColumnName = "id_encuesta", foreignKey = @ForeignKey(name = "preguntas_encuestas_FK"))
    private Encuesta encuesta;

    @Column(name = "texto")
    private String texto;

    public Pregunta() {
    }

    public Pregunta(Encuesta encuesta, String texto) {
        this.encuesta = encuesta;
        this.texto = texto;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
