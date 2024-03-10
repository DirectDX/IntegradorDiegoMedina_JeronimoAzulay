package com.dh.ClinicMVC.dao.implementacion;



import com.dh.ClinicMVC.dao.BD;
import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.model.Odontologo;
import com.dh.ClinicMVC.model.Paciente;
import com.dh.ClinicMVC.model.Turno;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDaoList implements IDao<Turno> {
    private static List<Turno> turnoLista = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(TurnoDaoList.class);
    private static final String INSERT_TURNO = "INSERT INTO TURNOS (FECHA, HORA, PACIENTE_ID, ODONTOLOGO_ID) VALUES (?,?,?,?)";

    @Override
    public Turno guardar(Turno turno) {
        turnoLista.add(turno);
        LOGGER.info("Este es el id del turno: " + turno.getId());
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        for (Turno turno:turnoLista) { if ( turno.getId() == id) {
            LOGGER.info("Turno encontrado");
            return turno;
            }
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        if (buscarPorId(id) != null) {
        turnoLista.remove(buscarPorId(id));
        } else  {

        }
    }

    @Override
    public void actualizar(Turno turno) {

    }

    @Override
    public List<Turno> listarTodos() {
        return null;
    }
}
