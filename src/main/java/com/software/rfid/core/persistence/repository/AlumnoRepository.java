package com.software.rfid.core.persistence.repository;

import com.software.rfid.core.persistence.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

/** Daniel Nacher 2024-08-05 */

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {}
