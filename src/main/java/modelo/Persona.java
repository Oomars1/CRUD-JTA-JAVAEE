
package modelo;

import javax.persistence.*;

@Entity
@Table(name="USUARIOS")
public class Persona {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(
            name = "usuario_seq",
            sequenceName = "usuario_seq", // Nombre exacto de la secuencia en Oracle
            allocationSize = 1
    )
    private Long id;

    @Column(name="nombre")
    private String nombre;
            
    @Column(name="email")
    private String email;       
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
