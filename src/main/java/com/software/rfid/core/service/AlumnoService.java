package com.software.rfid.core.service;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.NotFoundException;
import com.software.rfid.core.exceptions.UpdateException;
import com.software.rfid.core.persistence.model.Alumno;
import com.software.rfid.core.persistence.repository.AlumnoRepository;
import java.util.List;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/** Daniel Nacher 2024-08-05 */
@Service
public class AlumnoService {

  private final AlumnoRepository alumnoRepository;

  public AlumnoService(AlumnoRepository alumnoRepository) {
    this.alumnoRepository = alumnoRepository;
  }

  public List<Alumno> getAlumnos() {
    return alumnoRepository.findAll();
  }

  public Alumno getAlumnoById(Long id) {
    return alumnoRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(HttpStatus.OK,"No se encontro el alumno"));
  }

  public Alumno saveAlumno(Alumno alumno) {
    return alumnoRepository.save(alumno);
  }

  public Alumno updateAlumno(Alumno alumno) {
    if (alumno.getId() != null) {
      return alumnoRepository.save(alumno);
    } else {
      throw new UpdateException("No se puede actualizar el alumno sin Id");
    }
  }

  public void deleteAlumnoById(Long id) {
    alumnoRepository.deleteById(id);
  }

  public Codigo validarDatos(Alumno alumno) {
    if (StringUtil.isEmpty(alumno.getNombre())) {
      return Codigo.FALTA_NOMBRE;
    }
    if (StringUtil.isEmpty(alumno.getApellido())) {
      return Codigo.FALTA_APELLIDO;
    }
    if (alumno.getFechaNacimiento() == null) {
      return Codigo.FALTA_FECHA_NACIMIENTO;
    }
    if (alumno.getUidCard().isEmpty()) {
      return Codigo.FALTA_UID;
    }
    return Codigo.OK;
  }
}
