package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.dao.implementacion.PacienteDaoH2;
import com.dh.ClinicMVC.model.Paciente;
import com.dh.ClinicMVC.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService implements IPacienteService {
    private static IDao<Paciente> iDao;
    public PacienteService() {
        iDao = new PacienteDaoH2();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
       return iDao.guardar(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return iDao.listarTodos();
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }

    @Override
    public void actualizar(Paciente paciente) {
        iDao.actualizar(paciente);
    }

    @Override
    public void eliminar(Integer id) {
    iDao.eliminar(id);
    }
}
