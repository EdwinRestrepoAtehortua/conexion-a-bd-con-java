package com.mycompany.crudmodulosdelsoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudModulosDelSoftware {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_tienda";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Método para eliminar un cliente por su ID
    public static void eliminarCliente(int id) {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             Statement statement = conexion.createStatement()) {
            int filasAfectadas = statement.executeUpdate("DELETE FROM clientes WHERE cliId = " + id);
            if (filasAfectadas > 0) {
                System.out.println("Cliente eliminado correctamente");
            } else {
                System.out.println("No se encontró ningún cliente con el ID especificado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudModulosDelSoftware.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para insertar un nuevo cliente
    public static void insertarCliente(String nombre, String apellido) {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             Statement statement = conexion.createStatement()) {
            int filasAfectadas = statement.executeUpdate("INSERT INTO clientes (cliNombre, cliApellido) VALUES ('" + nombre + "', '" + apellido + "')");
            if (filasAfectadas > 0) {
                System.out.println("Cliente insertado correctamente");
            } else {
                System.out.println("No se pudo insertar el cliente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudModulosDelSoftware.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para actualizar los datos de un cliente por su ID
    public static void actualizarCliente(int id, String nuevoNombre, String nuevoApellido) {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             Statement statement = conexion.createStatement()) {
            int filasAfectadas = statement.executeUpdate("UPDATE clientes SET cliNombre = '" + nuevoNombre + "', cliApellido = '" + nuevoApellido + "' WHERE cliId = " + id);
            if (filasAfectadas > 0) {
                System.out.println("Cliente actualizado correctamente");
            } else {
                System.out.println("No se encontró ningún cliente con el ID especificado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudModulosDelSoftware.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Método para mostrar todos los clientes
    public static void mostrarClientes() {
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM clientes")) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("cliId") + ", Nombre: " + rs.getString("cliNombre") + ", Apellido: " + rs.getString("cliApellido"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudModulosDelSoftware.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        // Ejemplo de cómo utilizar los métodos CRUD
        mostrarClientes(); // Mostrar todos los clientes
        //insertarCliente("", ""); // Insertar un nuevo cliente
        mostrarClientes(); // Mostrar todos los clientes después de la inserción
        actualizarCliente(0, "", ""); // Actualizar los datos del cliente con ID 1
        mostrarClientes(); // Mostrar todos los clientes después de la actualización
        //eliminarCliente(); // Eliminar el cliente con ID 1
        mostrarClientes(); // Mostrar todos los clientes después de la eliminación
    }
}




