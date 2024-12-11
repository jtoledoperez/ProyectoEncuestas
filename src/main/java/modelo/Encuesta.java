package modelo;
 
import javax.persistence.*;
import java.util.Date;
 
@Entity
@Table(name = "encuestas")
public class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encuesta")
    private int idEncuesta;

    @Column(name = "nombre", nullable = false)
    private String nombre;
 
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario", foreignKey = @ForeignKey(name = "encuestas_usuarios_FK"))
    private Usuario usuario;

    @Column(name = "caducidad", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date caducidad;

 
    @Transient  
    private boolean caducada;

    public Encuesta(int idEncuesta) {
    	this.idEncuesta = idEncuesta;
    }
    
    public Encuesta() {
    }

    public Encuesta(String nombre, Usuario usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }
    public boolean isCaducada() {
        return caducada;
    }

    
    public void setCaducada(boolean caducada) {
        this.caducada = caducada;
    }


}
