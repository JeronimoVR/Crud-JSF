package CONTROLLER;

import MAIL.Correo;
import SERVICE.ProductosServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Bean implements Serializable {
//Propiedades de productos
    private List<Productos> listarProductos = new ArrayList<>();
    private Productos producto;
    private ProductosServicio crud = new ProductosServicio();

//Propiedades para enviar correo
    private Correo correo;
    private String to;
    private String subject;
    private String body;

    public Bean() {
        producto = new Productos();
        correo = new Correo();
    }

    public List<Productos> listar() {
        listarProductos.clear();
        listarProductos = crud.listarTodos();
        return null;
    }

    public List<Productos> listarMenores10() {
        listarProductos.clear();
        listarProductos = crud.listarMenoresA10();
        return null;
    }

    public List<Productos> listarEntre() {
        listarProductos.clear();
        listarProductos = crud.listarEntre();
        return null;
    }

    public String obtenerProducto(int id) {
        this.producto = crud.obtenerProducto(id);
        return "ActualizarProducto";
    }

    public void insertar() {
        crud.crear(producto);
        producto = new Productos();
        listar();
    }

    public String actualizar() {
        crud.actualizar(this.producto);
        producto = new Productos();
        listar();
        return "ListarTodos";
    }

    public String eliminar(int id) {
        crud.eliminar(id);
        producto = new Productos();
        listar();
        return null;
    }

    public List<Productos> getListarProductos() {
        return listarProductos;
    }

    public void setListarProductos(List<Productos> listarProductos) {
        this.listarProductos = listarProductos;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Correo getCorreo() {
        return correo;
    }

    public void setCorreo(Correo correo) {
        this.correo = correo;
    }

    /**
     Metodo para enviar correo
     * Se deben setear los atributos to y subject de la clase
     * @return String Enviar mail
     */
    public String enviarCorreo() {
        correo.setTo(to);
        correo.setSubject(subject);
        correo.sendEmail(crud.listarTodos());
        return "EnviarMail";
    }

}
