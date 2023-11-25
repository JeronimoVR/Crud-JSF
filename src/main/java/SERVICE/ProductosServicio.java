package SERVICE;

import CONTROLLER.Productos;
import MODEL.ProductosDAO;
import java.util.List;

public class ProductosServicio {

    public List<Productos> listarTodos() {
        ProductosDAO con = new ProductosDAO();
        return con.listar();
    }

    public List<Productos> listarMenoresA10() {
        ProductosDAO con = new ProductosDAO();
        return con.listarMenoresA10();
    }

    public List<Productos> listarEntre() {
        ProductosDAO con = new ProductosDAO();
        return con.listarEntre();
    }

    public Productos obtenerProducto(int id) {
        ProductosDAO con = new ProductosDAO();
        return con.obtenerProducto(id);
    }

    public void crear(Productos producto) {
        ProductosDAO con = new ProductosDAO();
        con.crearProducto(producto);
    }

    public void actualizar(Productos producto) {
        ProductosDAO con = new ProductosDAO();
        con.actualizarProducto(producto);
    }

    public void eliminar(int id) {
        ProductosDAO con = new ProductosDAO();
        con.borrarProducto(id);
    }

}
