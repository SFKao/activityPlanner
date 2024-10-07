package net.sfkao.activityPlanner.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetTime;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@Document("usuario")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

    @NonNull
    private List<Disponibilidad> horasDisponibles;

    @NonNull
    @DBRef(lazy = true)
    List<Actividad> actividadesInscritas;


}
