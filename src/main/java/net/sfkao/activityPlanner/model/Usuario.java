package net.sfkao.activityPlanner.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("usuario")
public class Usuario {

    @Id
    @NonNull
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

    public Usuario(@NonNull String email, @NonNull String hashedPass, @NonNull String username, @NonNull Integer priority) {
        this.email = email;
        this.hashedPass = hashedPass;
        this.username = username;
        this.priority = priority;
    }

    @NonNull
    private List<Disponibilidad> horasDisponibles = new ArrayList<>();

    @NonNull
    @DBRef(lazy = true)
    List<Actividad> actividadesInscritas = new ArrayList<>();


}
