package br.com.antonio.helpdesk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {

    @Id
    private UUID id;

    private String username;

    private String password;

    private String name;

    private String email;

    private boolean active;

    @Column(name = "created_by")
    private UUID createBy;

    @Column(name = "created_at")
    private Date createAt;

    @Column(name = "updated_by")
    private UUID updatedBy;

    @Column(name = "updated_at")
    private Date  updatedAt;

}
