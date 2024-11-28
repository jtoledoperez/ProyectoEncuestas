package modelo;

import javax.persistence.*;

@Entity
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private int idRespuesta;

    @ManyToOne
    @JoinColumn(name = "pregunta", referencedColumnName = "id_pregunta", foreignKey = @ForeignKey(name = "respuestas_preguntas_FK"))
    private Pregunta pregunta;

    @Column(name = "texto")
    private String texto;

    public Respuesta() {
    }

    public Respuesta(Pregunta pregunta, String texto) {
        this.pregunta = pregunta;
        this.texto = texto;
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
