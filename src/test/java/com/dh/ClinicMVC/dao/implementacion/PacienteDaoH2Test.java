package com.dh.ClinicMVC.dao.implementacion;

import com.dh.ClinicMVC.dao.BD;
import com.dh.ClinicMVC.model.Domicilio;
import com.dh.ClinicMVC.model.Paciente;
import com.dh.ClinicMVC.service.implementation.PacienteService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteDaoH2Test {
    @Test
    public static void main(String[] args) {
        BD.crearTablas();
        Domicilio domicilio = new Domicilio("1",1,"1","1");
        Domicilio domicilio2 = new Domicilio("1",1,"1","1");
        Paciente paciente = new Paciente("12","12","12", LocalDate.now(),domicilio);
        Paciente pacient2 = new Paciente("12","12","12", LocalDate.now(),domicilio2);
        PacienteService pacienteService = new PacienteService();
        pacienteService.guardar(paciente);
        pacienteService.guardar(pacient2);
        
    }

}