package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.repository.IOdontologoRepository;
import com.dh.ClinicMVC.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    IOdontologoRepository odontologoRepository;
    Odontologo odontologo = new Odontologo("Juan","Gomez","12345");
    @Test
    void guardar() {

        odontologoRepository.save(odontologo);
    }

    @Test
    void listarTodos() {
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void actualizar() {
    }

    @Test
    void eliminar() {
    }

    @Test
    void findByNombre() {
    }

    @Test
    void findByApellido() {
    }

    @Test
    void findByMatricula() {
    }
}