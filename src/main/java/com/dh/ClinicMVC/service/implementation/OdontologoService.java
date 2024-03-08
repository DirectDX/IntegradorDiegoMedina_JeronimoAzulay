package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.dao.implementacion.OdontologoDaoH2;
import com.dh.ClinicMVC.model.Odontologo;
import com.dh.ClinicMVC.service.IOdontologoService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> iDao;

    public OdontologoService() {
        iDao = new OdontologoDaoH2();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return iDao.guardar(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return iDao.listarTodos();
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        iDao.actualizar(odontologo);
    }

    @Override
    public void eliminar(Integer id) {
        iDao.eliminar(id);
    }
}
