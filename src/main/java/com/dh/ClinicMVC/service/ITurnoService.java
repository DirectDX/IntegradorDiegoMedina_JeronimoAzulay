package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.entity.Turno;

import java.util.List;

public interface ITurnoService {
    Turno guardar(Turno turno);
    List<Turno> listarTodos();

    Turno buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(Turno turno);
    List<Turno> buscarPorPacienteId(Long pacienteId);
    List<Turno> buscarPorOdontologoId(Long odontologoId);
}
