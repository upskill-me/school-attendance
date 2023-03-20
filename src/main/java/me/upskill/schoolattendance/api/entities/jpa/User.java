package me.upskill.schoolattendance.api.entities.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false, columnDefinition = "varchar(255)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "varchar(255)")
    private String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "varchar(255)")
    private String email;

    @Column(name = "username", nullable = false, columnDefinition = "varchar(255)")
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "varchar(255)")
    private String password;

    /**
     * The created timestamp
     */
    @Column(name = "created_at", columnDefinition = "bigint", nullable = false)
    @CreatedDate
    private Long createdAt;

    /**
     * The last modified timestamp
     */
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "bigint", nullable = false)
    private Long updatedAt;
}
