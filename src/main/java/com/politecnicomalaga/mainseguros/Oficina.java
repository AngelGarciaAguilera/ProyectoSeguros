/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainseguros;

import java.util.ArrayList;

/**
 *
 * @author mint
 */
public class Oficina {

    private String codOficina;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String cp;
    private String telefono;
    private String email;

    private ArrayList<Cliente> clientes;

    public Oficina(String codOficina, String nombre, String direccion, String ciudad, String cp, String telefono, String email) {
        this.codOficina = codOficina;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.cp = cp;
        this.telefono = telefono;
        this.email = email;

        clientes = new ArrayList<>();
    }

    public Oficina(String sCSV) {
        //Divido las líneas por salto de línea y las almaceno en un Array.
        String[] lineas = sCSV.split("\n");   
        //Divido estas líneas en columnas por el ;.
        //En la primera columna de la primera fila, debe estar el nombre de la clase.
        String[] columnas = lineas[0].split(";");
        
        if (columnas[0].equals("Oficina")) {
            //Si es Oficina, tenemos un objeto Oficina.
            this.codOficina = columnas[1];
            this.nombre = columnas[2];
            this.direccion = columnas[3];
            this.ciudad = columnas[4];
            this.cp = columnas[5];
            this.telefono = columnas[6];
            this.email = columnas[7];
        } else {
            //Si no, nos salimos.
            return;
        }

        //Construyo el ArrayList de clientes.
        this.clientes = new ArrayList<>();
        //Divido por clientes para obtener los clientes que tengo.
        String[] clientesPosibles = sCSV.split("Cliente");
        String miClienteCSV;

        for (int i = 1; i < clientesPosibles.length; i++) {
            //Recorro los clientes y lo guardo en el String, añadiéndole el Cliente que se ha eliminado antes.
            miClienteCSV = "Cliente" + clientesPosibles[i];
            //Este String lo paso al constructor de Cliente por CSV.
            Cliente c = new Cliente(miClienteCSV);
            //Añado este cliente a la lista.
            clientes.add(c);
        }
    }

    public boolean addCliente(String dni, String codigoPoliza, String nombre, String apellidos, String direccionPostal, String telefono, String email) {
        //Recorre todos los clientes de la Lista.
        for (Cliente c : clientes) {
            //Si el dni introducido ya pertenece a un cliente, da error y no se crea el cliente.
            if (c.getDni().equals(dni)) {
                return false;
            }
        }
        //Si cuando ya ha recorrido todos los clientes y ninguno tiene el dni introducido, se crea el cliente y se añade a la Lista.
        Cliente cliente = new Cliente(dni, codigoPoliza, nombre, apellidos, direccionPostal, telefono, email);
        clientes.add(cliente);
        return true;
    }

    public boolean deleteCliente(String dni) {
        //Recorre todos los clientes de la Lista.
        Cliente c = this.buscarCliente(dni);
        if (c != null) {
            if (!c.getIncidencias().isEmpty()) {
                //Si este cliente tiene incidencias...                
                if (!c.incidenciaPendiente(c.getCodigoPoliza())) {
                    //Si no está pendiente, lo puede eliminar.
                    clientes.remove(c);
                    return true;
                }
                //Si tiene incidencias pendientes, devuelve false.
                return false;
            }
            //Si no tiene incidencias, lo elimina y devuelve true.
            clientes.remove(c);
            return true;
        }
        //Si no existe el cliente, devulve false.
        return false;
    }

    public boolean updateCliente(String dniIn, String dni, String codigoPoliza, String nombre, String apellidos, String direccionPostal, String telefono, String email) {
        //Creo un Cliente en el que guardo el resultado de la búsqueda por DNI.
        //Esta puede devolver null si no se ha encontrado ningún cliente con el DNI proporcionado.
        Cliente c = this.buscarCliente(dniIn);
        //Controlo si ha devuelto null o un Cliente.
        if (c != null) {
            //Actualizo todos los datos del Cliente.
            c.setDni(dni);
            c.setNombre(nombre);
            c.setApellidos(apellidos);
            c.setCodigoPoliza(codigoPoliza);
            c.setDireccionPostal(direccionPostal);
            c.setTelefono(telefono);
            c.setEmail(email);

            return true;
        }
        //Si no existe Cliente con el DNI proporcionado, devuelve false;
        return false;

    }

    public Cliente buscarCliente(String dni) {
        //Recorre todos los clientes de la Lista.
        for (Cliente c : clientes) {
            //Si el dni introducido ya pertenece a un cliente, devuelve ese cliente.
            if (c.getDni().equals(dni)) {
                return c;
            }
        }
        //Si no se ha encontrado ningún Cliente con el DNI introducido, devulve null.
        return null;
    }

    public Cliente[] buscarClientes(String apellidos) {
        //Creo una Lista para almacenar los clientes que tengan los apellidos introducidos.
        ArrayList<Cliente> clientesEncontrados = new ArrayList<>();

        for (Cliente c : clientes) {
            //Si los apellidos introducidos pertenecen a el Cliente, este se añade a la Lista.
            if (c.getApellidos().equals(apellidos)) {
                clientesEncontrados.add(c);
            }
        }
        //Compruebo si la Lista no está vacía.
        if (!clientesEncontrados.isEmpty()) {
            //Creo un Array con el tamaño de la Lista anterior y lo devuelvo.
            Cliente[] listaC = new Cliente[clientesEncontrados.size()];
            return clientesEncontrados.toArray(listaC);
        }
        return null;
    }

    public Cliente[] listarClientes() {
        //Compruebo si la Lista está vacía.
        if (clientes.isEmpty()) {
            //Si está vacía devuelve null.
            return null;
        }
        //Si no está vacía, creo un Array con el tamaño de la Lista de clientes y lo devuelvo.
        Cliente[] listaC = new Cliente[clientes.size()];
        return clientes.toArray(listaC);
    }

    public String getCodOficina() {
        return codOficina;
    }

    public void setCodOficina(String codOficina) {
        this.codOficina = codOficina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toCSV() {
        String cadena = String.format("Oficina;%s;%s;%s;%s;%s;%s;%s\n", codOficina, nombre, direccion, ciudad, cp, telefono, email);
        for (Cliente c : clientes) {
            cadena += c.toCSV();
        }
        return cadena;
    }
}
