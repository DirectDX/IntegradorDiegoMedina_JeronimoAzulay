package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.model.Domicilio;
import com.dh.ClinicMVC.model.Odontologo;
import com.dh.ClinicMVC.service.implementation.OdontologoService;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Integer id);
    void actualizar(Odontologo odontologo);
    void eliminar(Integer id);

}
