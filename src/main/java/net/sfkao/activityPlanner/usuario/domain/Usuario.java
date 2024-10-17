package net.sfkao.activityPlanner.usuario.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import net.sfkao.activityPlanner.actividad.domain.Actividad;
import net.sfkao.activityPlanner.disponibilidad.domain.Disponibilidad;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("usuario")
public class Usuario implements UserDetails {

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

    public Usuario(@NonNull String email, @NonNull String hashedPass, @NonNull String username, @NonNull Integer priority) {
        this.email = email;
        this.hashedPass = hashedPass;
        this.username = username;
        this.priority = priority;
    }

    @NonNull
    private List<Disponibilidad> horasDisponibles = new ArrayList<>();

    @NonNull
    @DocumentReference(lazy = true)
    @JsonManagedReference
    List<Actividad> actividadesInscritas = new ArrayList<>();

    private String refreshToken;
    private Instant refreshTokenExpiration;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return hashedPass;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
