package com.software.rfid.core.service;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.NotFoundException;
import com.software.rfid.core.exceptions.UpdateException;
import com.software.rfid.core.persistence.model.Profesor;
import com.software.rfid.core.persistence.repository.ProfesorRepository;
import java.util.List;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/** Daniel Nacher 2024-08-05 */
@Service
public class ProfesorService {

  private final ProfesorRepository profesorRepository;

  public ProfesorService(ProfesorRepository profesorRepository) {
    this.profesorRepository = profesorRepository;
  }

  public List<Profesor> getProfesores() {
    return profesorRepository.findAll();
  }

  public Profesor getProfesorById(Long id) {
    return profesorRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(HttpStatus.OK, "No se encontro el profesor"));
  }

  public Profesor saveProfesor(Profesor profesor) {
      return profesorRepository.save(profesor);
  }

  public Profesor updateProfesor(Profesor profesor) {
      if(profesor.getId()!=null) {
          return profesorRepository.save(profesor);
      } else {
          throw new UpdateException("No se puede actualizar el profesor sin Id");
      }
  }

  public void deleteProfesorById(Long id) {
      profesorRepository.deleteById(id);
  }

    public Codigo validarDatos(Profesor profesor) {
        if (StringUtil.isEmpty(profesor.getNombre())) {
            return Codigo.FALTA_NOMBRE;
        }
        if (StringUtil.isEmpty(profesor.getApellido())) {
            return Codigo.FALTA_APELLIDO;
        }
        if (profesor.getFechaNacimiento() == null) {
            return Codigo.FALTA_FECHA_NACIMIENTO;
        }
        if (profesor.getUidCard().isEmpty()) {
            return Codigo.FALTA_UID;
        }
        return Codigo.OK;
    }
}
