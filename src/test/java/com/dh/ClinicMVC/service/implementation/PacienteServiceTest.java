package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.service.IPacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private IPacienteService pacienteService;
    Paciente paciente1 = new Paciente(null,"J","J","1", LocalDate.now(),null,null);
    Paciente paciente2 = new Paciente(null,"A","A","2", LocalDate.now(),null,null);

    @Test
    void guardar() throws Exception {
      Paciente pacienteGuardado  pacienteService.guardar(paciente1);


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
    void findByDni() {
    }
}