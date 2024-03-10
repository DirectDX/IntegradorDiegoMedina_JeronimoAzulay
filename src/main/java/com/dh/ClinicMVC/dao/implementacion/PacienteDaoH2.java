package com.dh.ClinicMVC.dao.implementacion;

import com.dh.ClinicMVC.dao.BD;
import com.dh.ClinicMVC.dao.IDao;

import com.dh.ClinicMVC.model.Domicilio;
import com.dh.ClinicMVC.model.Paciente;
import com.dh.ClinicMVC.service.implementation.DomicilioService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements IDao<Paciente> {

    private static final Logger LOGGER = Logger.getLogger(DomicilioDaoH2.class);

    private static final String INSERT_PACIENTES = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, DOMICILIO_ID) VALUES (?,?,?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM PACIENTES WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM PACIENTES WHERE ID = ?";
    private static final String UPDATE_BY_ID = "UPDATE PACIENTES SET NOMBRE = ?, APELLIDO= ?, DNI= ?, FECHA_INGRESO = ?, DOMICILIO_ID =? WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM PACIENTES";
    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Estamos guardando un PACIENTE");
        Connection connection = null;
        DomicilioService domicilioService = new DomicilioService();

        try {
            domicilioService.guardar(paciente.getDomicilio());


            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_PACIENTES, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getDni());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso())); //parseado, asi funciona
            psInsert.setInt(5, paciente.getDomicilio().getId());


            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                paciente.setId(rs.getInt(1));
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
        LOGGER.info("Este es el id del paciente: " + paciente.getId());
        return paciente;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        Connection conexion = null;
        Paciente paciente = null;
        Domicilio domicilio = new Domicilio();
        DomicilioService domicilioService = new DomicilioService();

        try {
            conexion = BD.getConnection();
            PreparedStatement psSearchByID =  conexion.prepareStatement(SELECT_BY_ID);
            psSearchByID.setInt(1, id);
            ResultSet rs  = psSearchByID.executeQuery();

            while (rs.next()){
                paciente = new Paciente();
                paciente.setId(rs.getInt(1));
                paciente.setNombre(rs.getString(2));
                paciente.setApellido(rs.getString(3));
                paciente.setDni(rs.getString(4));
                paciente.setFechaIngreso(rs.getDate(5).toLocalDate());
                domicilio = domicilioService.buscarPorId(rs.getInt(6));
                paciente.setDomicilio(domicilio);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
        DomicilioService domicilioService = new DomicilioService();
        try {
            connection = BD.getConnection();
            Paciente paciente = buscarPorId(id);
            PreparedStatement psDelete = connection.prepareStatement(DELETE_BY_ID);
            psDelete.setInt(1,id);
            psDelete.execute();
            LOGGER.info("Se elimino correctamente el paciente con id: "+ id);
            domicilioService.eliminar(paciente.getDomicilio().getId());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void actualizar(Paciente paciente) {
        Connection connection = null;
        DomicilioService domicilioService = new DomicilioService();
        try {
            connection = BD.getConnection();
            PreparedStatement update = connection.prepareStatement(UPDATE_BY_ID);
            update.setString(1, paciente.getNombre());
            update.setString(2, paciente.getApellido());
            update.setString(3, paciente.getDni());
            update.setDate(4, Date.valueOf(paciente.getFechaIngreso())); //parseado, asi funciona
            domicilioService.actualizar(paciente.getDomicilio());
            update.setInt(5, paciente.getDomicilio().getId());
            update.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        LOGGER.info("Se actualizaron los datos correctamente: "+ paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        Connection connection = null;
        DomicilioService domicilioService = new DomicilioService();
        List<Paciente> pacientes = new ArrayList<>();

        try {
            connection = BD.getConnection();
            PreparedStatement listarSql = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = listarSql.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt(1));
                paciente.setNombre(rs.getString(2));
                paciente.setApellido(rs.getString(3));
                paciente.setDni(rs.getString(4));
                paciente.setFechaIngreso(rs.getDate(5).toLocalDate());

                Domicilio domicilio = domicilioService.buscarPorId(rs.getInt(6));
                paciente.setDomicilio(domicilio);
                pacientes.add(paciente);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("Lista de pacientes: " + pacientes);
        return pacientes;
    }
}
