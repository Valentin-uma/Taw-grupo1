package es.taw.grupo1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ejercicio")
public class Ejercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idejercicio", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "tipo", nullable = false, length = 45)
    private String tipo;

    @Column(name = "descripcion", nullable = false, length = 400)
    private String descripcion;

    @Column(name = "url_demo")
    private String urlDemo;

    @Lob
    @Column(name = "dificultad", nullable = false)
    private String dificultad;

    @Column(name = "calorias", nullable = false)
    private Integer calorias;

    @Column(name = "grupo_muscular", nullable = false, length = 45)
    private String grupoMuscular;

}