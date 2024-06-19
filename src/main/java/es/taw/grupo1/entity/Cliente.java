package es.taw.grupo1.entity;

import es.taw.grupo1.dto.ClienteDTO;
import es.taw.grupo1.dto.DTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente implements DTO<ClienteDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_idusuario", nullable = false)
    private Usuario usuarioIdusuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrenador_identrenador")
    private Entrenador entrenadorIdentrenador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rutina_idrutina")
    private Rutina rutinaIdrutina;


    public ClienteDTO toDTO() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setRutinaIdrutina(this.getRutinaIdrutina().getId());
        clienteDTO.setUsuarioIdusuario(this.getUsuarioIdusuario().getId());
        clienteDTO.setEntrenadorIdentrenador(this.getEntrenadorIdentrenador().getId());
        clienteDTO.setId(id);
        return clienteDTO;

    }
}