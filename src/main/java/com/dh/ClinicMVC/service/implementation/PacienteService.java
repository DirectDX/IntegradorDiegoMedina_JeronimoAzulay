package com.dh.ClinicMVC.service.implementation;


import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.exception.BadRequest;
import com.dh.ClinicMVC.repository.IPacienteRepository;
import com.dh.ClinicMVC.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private static IPacienteRepository pacienteRepository;
    @Autowired
    private PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) throws BadRequest {
        Optional<Odontologo> odontologoBDList = pacienteRepository.findByDni(paciente.getDni());
        if (odontologoBDList.isEmpty()) {
            // si el dni no existe, verifica campos vacíos y guardar el odontólogo
            if (paciente.getNombre() == null || paciente.getApellido() == null || paciente.getDni() == null
                    || paciente.getNombre().trim().isEmpty() || paciente.getApellido().trim().isEmpty() || paciente.getDni().trim().isEmpty()) {
                throw new BadRequest("No puedes poner campos vacíos");
            }
            return pacienteRepository.save(paciente);
        } else {
            // La matrícula ya existe en la base de datos
            throw new BadRequest("El Dni del paciente ya existe");
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) {
       return pacienteRepository.findById(id);
    }

    @Override
    public void actualizar(Paciente paciente) throws BadRequest {
        Optional<Odontologo> odontologoBDList = pacienteRepository.findByDni(paciente.getDni());
        if (odontologoBDList.isEmpty()) {
            // si el dni no existe, verifica campos vacíos y guardar el odontólogo
            if (paciente.getNombre() == null || paciente.getApellido() == null || paciente.getDni() == null
                    || paciente.getNombre().trim().isEmpty() || paciente.getApellido().trim().isEmpty() || paciente.getDni().trim().isEmpty()) {
                throw new BadRequest("No puedes poner campos vacíos");
            }
                pacienteRepository.save(paciente);
        } else {
            // La matrícula ya existe en la base de datos

            throw new BadRequest("El dni del paciente ya existe");
        }
    }

    @Override
    public void eliminar(Long id) {
    pacienteRepository.deleteById(id);
    }

    @Override
    public Optional<Odontologo> findByDni(String dni) {
        return pacienteRepository.findByDni(dni);
    }
}
