package com.dh.ClinicMVC.model;

import java.time.LocalDate;


public class Turno {
    private static Integer contador = 1;
    private Integer id;
    private LocalDate fecha;
    private Paciente paciente;
    private Odontologo odontologo;

    public Turno() {
    }

    public Turno(LocalDate fecha, Paciente paciente, Odontologo odontologo) {
        this.id = contador;
        this.fecha = fecha;
        this.paciente = paciente;
        this.odontologo = odontologo;
        contador ++;
    }

    public static Integer getContador() {
        return contador;
    }

    public static void setContador(Integer contador) {
        Turno.contador = contador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }
}
