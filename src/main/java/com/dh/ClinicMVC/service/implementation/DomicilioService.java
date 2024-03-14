package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.dao.implementacion.DomicilioDaoH2;
import com.dh.ClinicMVC.model.Domicilio;
import com.dh.ClinicMVC.service.IDomicilioService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
    public void eliminar(Integer id) {
        iDao.eliminar(id);
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        iDao.actualizar(domicilio);
    }

    @Override
    public List<Domicilio> listarTodos() {
        return iDao.listarTodos();
    }
    public Domicilio buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }
}
