package net.sfkao.activityPlanner.usuario.domain.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "usuario", createIndex = true)
public class UsuarioElastic {


    @Id
    @NonNull
    @EqualsAndHashCode.Include
    private String id;

    @NonNull
    @EqualsAndHashCode.Include
    @Field(type = FieldType.Keyword)
    private String email;

    @NonNull
    @Transient
    private String hashedPass;

    @NonNull
    @Field(type = FieldType.Keyword)
    private String username;

    @NonNull
    private Integer priority = 0;
}

