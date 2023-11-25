package MODEL;

import CONTROLLER.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {

    public List<Productos> listar() {
        Conexion db_connect = new Conexion();
        List<Productos> productos = new ArrayList<>();

        try (Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps = null;
            ResultSet rs = null;

            String query = "SELECT * FROM productos";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                int precio = rs.getInt("precio");
                productos.add(new Productos(id, nombre, cantidad, precio));
            }

        } catch (SQLException e) {
            System.out.println("No se pudo traer los productos");
            System.out.println(e);
        }

        return productos;
    }

    public List<Productos> listarMenoresA10() {
        Conexion db_connect = new Conexion();
        List<Productos> productos = new ArrayList<>();

        try (Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps = null;
            ResultSet rs = null;

            String query = "SELECT * FROM productos WHERE cantidad < 10";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                int precio = rs.getInt("precio");

                productos.add(new Productos(id, nombre, cantidad, precio));
            }

        } catch (SQLException e) {
            System.out.println("No se pudo traer los productos");
            System.out.println(e);
        }

        return productos;
    }

    public List<Productos> listarEntre() {
        Conexion db_connect = new Conexion();
        List<Productos> productos = new ArrayList<>();

        try (Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps = null;
            ResultSet rs = null;

            String query = "SELECT * FROM productos WHERE precio between 10000 and 100000";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                int precio = rs.getInt("precio");

                productos.add(new Productos(id, nombre, cantidad, precio));
            }

        } catch (SQLException e) {
            System.out.println("No se pudo traer los productos");
            System.out.println(e);
        }

        return productos;
    }

    public void crearProducto(Productos producto) {
        Conexion db_connect = new Conexion();
        try (Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO `productos`(`nombre`, `cantidad`, `precio`)VALUES (?,?,?)";
                ps = conexion.prepareStatement(query);
                ps.setString(1, producto.getNombre());
                ps.setInt(2, producto.getCantidad());
                ps.setInt(3, producto.getPrecio());
                ps.executeUpdate();
                System.out.println("Producto creado");

            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Productos obtenerProducto(int idProducto) {
        Conexion db_connect = new Conexion();
        Productos p = null;

        try (Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps = null;
            ResultSet rs = null;

            String query = "SELECT * FROM productos WHERE id = ?";
            ps = conexion.prepareStatement(query);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                p = new Productos(rs.getInt("id"), rs.getString("nombre"), rs.getInt("cantidad"), rs.getInt("precio"));
            } else {
                System.out.println("No se encontró ningún producto con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo traer el producto");
            System.out.println(e);
        }

        return p;
    }

    public void borrarProducto(int id) {
        Conexion db_connect = new Conexion();

        try (Connection conexion = db_connect.get_connection()) {
            PreparedStatement ps = null;

            try {
                String query = "DELETE FROM `productos` WHERE id=?";
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("El producto fue borrado");

            } catch (SQLException e) {
                System.out.println("No se pudo borrar el producto");
                System.out.println(e);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void actualizarProducto(Productos producto) {
        Conexion db_connect = new Conexion();

        try (Connection conexion = db_connect.get_connection()) {
            conexion.setAutoCommit(false);

            try {
                
                String query = "UPDATE productos SET nombre = ?,cantidad = ?,precio=? WHERE id = ?";
                try (PreparedStatement ps = conexion.prepareStatement(query)) {
                    ps.setString(1, producto.getNombre());
                    ps.setInt(2, producto.getCantidad());
                    ps.setInt(3, producto.getPrecio());
                    ps.setInt(4, producto.getId());

                    ps.executeUpdate();
                    System.out.println("Try ps="+ps+" ---> query -->> "+query);
                }
                conexion.commit();
                System.out.println("El producto fue editado");
            } catch (SQLException e) {
                conexion.rollback();
                System.out.println("No se edito el producto");
                System.out.println(e);
                
            }

        } catch (SQLException e) {
            System.out.println(e);

        }

    }

}
