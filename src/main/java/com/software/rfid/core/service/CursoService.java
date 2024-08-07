package com.software.rfid.core.service;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.NotFoundException;
import com.software.rfid.core.exceptions.RfidException;
import com.software.rfid.core.exceptions.UpdateException;
import com.software.rfid.core.persistence.model.Alumno;
import com.software.rfid.core.persistence.model.Curso;
import com.software.rfid.core.persistence.repository.AlumnoRepository;
import com.software.rfid.core.persistence.repository.CursoRepository;
import java.util.List;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/** Daniel Nacher 2024-08-05 */
@Service
public class CursoService {

  private final CursoRepository cursoRepository;
  private final AlumnoRepository alumnoRepository;

  public CursoService(CursoRepository cursoRepository, AlumnoRepository alumnoRepository) {
    this.cursoRepository = cursoRepository;
    this.alumnoRepository = alumnoRepository;
  }

  public List<Curso> getCursos() {
    return cursoRepository.findAll();
  }

  public Curso getCursoById(Long id) {
    return cursoRepository
        .findById(id)
        .orElseThrow(() -> new RfidException(HttpStatus.OK, "No se encontro el curso"));
  }

  public Curso saveCurso(Curso curso) {
    return cursoRepository.save(curso);
  }

  public Curso updateCurso(Curso curso) {
    if (curso.getId() != null) {
      return cursoRepository.save(curso);
    } else {
      throw new UpdateException("No se puede actualizar el curso sin Id");
    }
  }

  public void deleteCursoById(Long id) {
    cursoRepository.deleteById(id);
  }

  public Codigo validarDatos(Curso curso) {
    if (StringUtil.isEmpty(curso.getNombre())) {
      return Codigo.FALTA_NOMBRE;
    }
    if (StringUtil.isEmpty(curso.getDescripcion())) {
      return Codigo.FALTA_DESCRIPCION;
    }
    return Codigo.OK;
  }

  public Curso agregarAlumnoACurso(Long cursoId, Alumno alumno, boolean reasignar) {
    Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new NotFoundException(HttpStatus.OK, "Curso no encontrado"));

    Alumno alumnoExistente = alumnoRepository.findById(alumno.getId())
            .orElseThrow(() -> new NotFoundException(HttpStatus.OK, "Alumno no encontrado"));

    if(!reasignar && alumnoExistente.getCurso()!=null) {
      throw new RfidException(HttpStatus.BAD_REQUEST, "El alumno ya esta asignado a una clase: " + alumnoExistente.getCurso().getNombre());
    } else {
      alumnoExistente.setCurso(curso);
      alumnoRepository.save(alumnoExistente);
      return curso;
    }
  }

}
