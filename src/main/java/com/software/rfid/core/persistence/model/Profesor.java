package com.software.rfid.core.persistence.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/** Daniel Nacher 2024-08-05 */
@Entity
@Table(name = "profesor")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private LocalDate fechaNacimiento;

    @Column
    private String uidCard;

    @Column
    private LocalDate fechaIngreso;

    @OneToMany(mappedBy = "profesor")
    private List<Clase> clases;

    @Column
    private boolean activo;
}
