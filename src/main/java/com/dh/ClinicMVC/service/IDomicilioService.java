package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.model.Domicilio;

import java.util.List;

public interface IDomicilioService {
    public Domicilio guardar (Domicilio domicilio);
    public void eliminar(Integer id);
    public void actualizar(Domicilio domicilio);
    public List<Domicilio> listarTodos();
}
