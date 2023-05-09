/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainseguros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mint
 */
public class Cliente {

    //Enum
    public enum AtributosCliente {
        DNI, CODIGOPOLIZA, NOMBRE, APELLIDOS, DIRECCIONPOSTAL, TFNO, EMAIL
    };

    private String dni;
    private String codigoPoliza;
    private String nombre;
    private String apellidos;
    private String direccionPostal; //Esta incluye el CP y la Ciudad
    private String telefono;
    private String email;

    private Map<String, Incidencia> incidencias;

    public Cliente(String dni, String codigoPoliza, String nombre, String apellidos, String direccionPostal, String telefono, String email) {
        this.dni = dni;
        this.codigoPoliza = codigoPoliza;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccionPostal = direccionPostal;
        this.telefono = telefono;
        this.email = email;

        this.incidencias = new HashMap<>();
    }

    public Cliente(String sCSV) {
        String[] lineas = sCSV.split("\n");

        String[] columnasCliente = lineas[0].split(";");
        
        if (columnasCliente[0].equals("Cliente")) {
            this.dni = columnasCliente[1];
            this.codigoPoliza = columnasCliente[2];
            this.nombre = columnasCliente[3];
            this.apellidos = columnasCliente[4];
            this.direccionPostal = columnasCliente[5];
            this.telefono = columnasCliente[6];
            this.email = columnasCliente[7];
        } else {
            return;
        }

        //Después de 0 a n Incidencias
        incidencias = new HashMap<>();

        //Si las líneas son más de 1... Hay Incidencias
        for (int i = 1; i < lineas.length; i++) {

            String[] columnasIncidencia = lineas[i].split(";");

            if (columnasIncidencia[0].equals("Incidencia")) {

                if (columnasIncidencia.length > 8 && columnasIncidencia[8].length() >= 1 && columnasIncidencia[8].length() <= 2) {
                    Incidencia in = new IncidenciaUrgente(lineas[i]);
                    incidencias.put(in.getCodigoIncidencia(), in);
                } else if (columnasIncidencia.length > 8 && columnasIncidencia[8].length() > 2) {
                    Incidencia in = new IncidenciaAjena(lineas[i]);
                    incidencias.put(in.getCodigoIncidencia(), in);
                } else {
                    Incidencia in = new Incidencia(lineas[i]);
                    incidencias.put(in.getCodigoIncidencia(), in);
                }
            }
        }
    }

    public boolean addIncidencia(String fecha, int hora, String matriculaPropia, String matriculaAjena, String descripcion, int maxDias, String dniAjeno) {

        if (!dniAjeno.isEmpty()) {
            //Es una IncidenciaAjena ya que tiene un dniAjeno
            IncidenciaAjena iAjena = new IncidenciaAjena(fecha, hora, matriculaPropia, matriculaAjena, descripcion, codigoPoliza, dniAjeno);
            //El put devuelve un objeto si ya había uno con esa Key. Si no, devuelve null.      
            Incidencia antigua = incidencias.put(iAjena.getCodigoIncidencia(), iAjena);
            //Si ha devuelto un objeto...
            if (antigua != null) {
                //Volvemos a poner el objeto que nos ha devuelto donde estaba, y devolvemos null porque no se ha podido añadir el nuevo objeto.
                incidencias.put(antigua.getCodigoIncidencia(), antigua);
                return false;
            }
            //antigua tiene null, es decir, no había una Incidencia en esa Key. Devuelvo true porque ya se ha añadido la nueva.
            return true;
        } else if (maxDias >= 0) {
            IncidenciaUrgente iUrgente = new IncidenciaUrgente(fecha, hora, matriculaPropia, matriculaAjena, descripcion, codigoPoliza, maxDias);
            Incidencia antigua = incidencias.put(iUrgente.getCodigoIncidencia(), iUrgente);
            if (antigua != null) {
                //Volvemos a poner el objeto que nos ha devuelto donde estaba, y devolvemos null porque no se ha podido añadir el nuevo objeto.
                incidencias.put(antigua.getCodigoIncidencia(), antigua);
                return false;
            }
            //antigua tiene null, es decir, no había una Incidencia en esa Key. Devuelvo true porque ya se ha añadido la nueva.
            return true;
        } else {
            Incidencia i = new Incidencia(fecha, hora, matriculaPropia, matriculaAjena, descripcion, codigoPoliza);
            Incidencia antigua = incidencias.put(i.getCodigoIncidencia(), i);
            if (antigua != null) {
                //Volvemos a poner el objeto que nos ha devuelto donde estaba, y devolvemos null porque no se ha podido añadir el nuevo objeto.
                incidencias.put(antigua.getCodigoIncidencia(), antigua);
                return false;
            }
        }
        return true;
    }

    public boolean deleteIncidencia(String codigoIncidencia) {

        if (incidencias.get(codigoIncidencia) != null) {
            if (incidencias.get(codigoIncidencia).isCobrado()) {
                //Si la incidencia está cobrada la elimino.
                incidencias.remove(codigoIncidencia);
                return true;
            }
        }
        return false;
    }

    public Incidencia buscarIncidencia(String codigoIncidencia) {
        if (incidencias.isEmpty()) {
            return null;
        }
        return incidencias.get(codigoIncidencia);
    }

    public String listarIncidencias() {
        String listaIncidencias = "";

        if (incidencias.isEmpty()) {
            //Si está vacía devuelve null.
            return null;
        }
        Iterator iterador = incidencias.entrySet().iterator();
        while (iterador.hasNext()) {
            Map.Entry incidencia = (Map.Entry) iterador.next();
            //producto = iterador.next(); Si se usase tambien la otra linea comentada.
            listaIncidencias += incidencia.getValue() + "\n";
        }
        return listaIncidencias;
    }

    /*public Incidencia[] listarIncidencias() {
        if (incidencias.isEmpty()) {
            return null;
        }
        List<Incidencia> listaIncidencias = new ArrayList<>(incidencias.values());
        return listaIncidencias.toArray(new Incidencia[0]);
    }*/
    public boolean incidenciaPendiente(String codigoPoliza) {
        String codigoIncidencia = "";

        for (int i = 1; i <= incidencias.size(); i++) {
            codigoIncidencia = codigoPoliza + "-" + i;
            if (!incidencias.get(codigoIncidencia).isCobrado()) {
                //Si está cobrado, no está pendiente.
                return true;
            }
        }

        //Si no está cobrado, está pendiente.
        return false;

        /*for(Incidencia i : incidencias){
            //Si alguna incidencia no está cobrada, devuelve true porque está pendiente.
            if(!i.isCobrado()){
                return true;
            }
        }
        return false;*/
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodigoPoliza() {
        return codigoPoliza;
    }

    public void setCodigoPoliza(String codigoPoliza) {
        this.codigoPoliza = codigoPoliza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(String direccionPostal) {
        this.direccionPostal = direccionPostal;
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

    public Map<String, Incidencia> getIncidencias() {
        return incidencias;
    }

    @Override
    public String toString() {
        return String.format("%10s#%10s#%10s#%10s#%10s#%10s#%10s", dni, codigoPoliza, nombre, apellidos, direccionPostal, telefono, email);
    }
    
    public String toCSV() {
        String cadena = String.format("Cliente;%s;%s;%s;%s;%s;%s;%s\n", dni, codigoPoliza, nombre, apellidos, direccionPostal, telefono, email);
        for (Map.Entry<String, Incidencia> entry : incidencias.entrySet()) {
            //Esto es un forEach para HashMap
            //Guardo el valor de la clave.
            String clave = entry.getKey();
            //Creo un objeto, asignandole los valores del objeto por el que va el for.
            Incidencia incidencia = entry.getValue();
            //Paso a CSV este objeto.
            cadena += incidencia.toCSV();
        }
        return cadena;
    }
}
