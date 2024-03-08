package com.dh.ClinicMVC.dao.implementacion;



import com.dh.ClinicMVC.dao.BD;
import com.dh.ClinicMVC.dao.IDao;
import com.dh.ClinicMVC.model.Turno;

import java.sql.*;
import java.util.List;

public class TurnoDaoH2 implements IDao<Turno> {
    private static final String INSERT_TURNO = "INSERT INTO TURNOS (FECHA, HORA, PACIENTE_ID, ODONTOLOGO_ID) VALUES (?,?,?,?)";

    @Override
    public Turno guardar(Turno turno) {
        Connection connection = null;

        try {
            connection = BD.getConnection();


            PreparedStatement insertTurno = connection.prepareStatement(INSERT_TURNO, Statement.RETURN_GENERATED_KEYS);
            insertTurno.setDate(1, new  java.sql.Date(turno.getFecha().getTime()));
            insertTurno.setTime(2, turno.getHora());
            insertTurno.setInt(3, turno.getPaciente().getId());
            insertTurno.setInt(4,turno.getOdontologo().getId()); //parseado, asi funciona

            insertTurno.execute();

            ResultSet rs = insertTurno.getGeneratedKeys();
            while (rs.next()) {
                turno.setId(rs.getInt(1));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Turno turno) {

    }

    @Override
    public List<Turno> listarTodos() {
        return null;
    }
}
