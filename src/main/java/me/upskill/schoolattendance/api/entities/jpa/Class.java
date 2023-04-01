package me.upskill.schoolattendance.api.entities.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "classes")
@EntityListeners(AuditingEntityListener.class)
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "school_id", nullable = false, columnDefinition = "bigint")
    private long schoolId;

    @Column(name = "number", nullable = false, columnDefinition = "integer")
    private long number;

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
