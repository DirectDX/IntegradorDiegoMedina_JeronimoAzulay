package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.dao.implementacion.DomicilioDaoH2;
import com.dh.ClinicMVC.model.Domicilio;
import com.dh.ClinicMVC.service.IDomicilioService;

import java.util.List;

public class DomicilioService implements IDomicilioService {
    private IDao<Domicilio> iDao;

    public DomicilioService() {
        this.iDao = new DomicilioDaoH2();
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        return iDao.guardar(domicilio);
    }

    @Override
    public List<Domicilio> listarTodos() {
        return iDao.listarTodos();
    }
    public Domicilio buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }
}
