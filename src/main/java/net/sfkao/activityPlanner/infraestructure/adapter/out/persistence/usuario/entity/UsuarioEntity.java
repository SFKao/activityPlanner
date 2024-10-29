package net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.usuario.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import net.sfkao.activityPlanner.infraestructure.adapter.out.persistence.actividad.entity.ActividadEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("usuario")
public class UsuarioEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @NonNull
    @EqualsAndHashCode.Include
    private String email;

    @NonNull
    private String hashedPass;

    @NonNull
    private String username;

    @NonNull
    private Integer priority = 0;

    private String refreshToken;
    private Instant refreshTokenExpiration;

    @NonNull
    private List<DisponibilidadEntity> horasDisponibles = new ArrayList<>();

    @NonNull
    @DocumentReference(lazy = true)
    @JsonManagedReference
    List<ActividadEntity> actividadesInscritas = new ArrayList<>();

    public UsuarioEntity(@NonNull String email, @NonNull String hashedPass, @NonNull String username, @NonNull Integer priority) {
        this.email = email;
        this.hashedPass = hashedPass;
        this.username = username;
        this.priority = priority;
    }

}
