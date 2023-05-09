/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainseguros;

/**
 *
 * @author mint
 */
public class Incidencia {

    //Singleton
    private static int numSiguiente = 0;

    protected String fecha;
    protected int hora;
    protected String matriculaPropia;
    protected String matriculaAjena;
    protected String descripcion;
    protected String codigoIncidencia;

    protected boolean cobrado;
    protected String tipo;

    public Incidencia(String fecha, int hora, String matriculaPropia, String matriculaAjena, String descripcion, String codigoPoliza) {
        this.fecha = fecha;
        this.hora = hora;
        this.matriculaPropia = matriculaPropia;
        this.matriculaAjena = matriculaAjena;
        this.descripcion = descripcion;

        this.cobrado = false;
        this.tipo = "Incidencia";

        Incidencia.numSiguiente++;
        this.codigoIncidencia = codigoPoliza + "-" + Incidencia.numSiguiente;
    }

    public Incidencia(String sCSV) {
        String[] columnas = sCSV.split(";");

        if (columnas[0].equals("Incidencia")) {
            this.fecha = columnas[1];
            this.hora = Integer.parseInt(columnas[2]);
            this.matriculaPropia = columnas[3];
            this.matriculaAjena = columnas[4];
            this.descripcion = columnas[5];
            this.codigoIncidencia = columnas[6];
            this.cobrado = Boolean.parseBoolean(columnas[7]);
        } else {
            this.fecha = "";
            this.hora = 0;
            this.matriculaPropia = "";
            this.matriculaAjena = "";
            this.descripcion = "";
            this.codigoIncidencia = "";
            this.cobrado = true;
        }
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getMatriculaPropia() {
        return matriculaPropia;
    }

    public void setMatriculaPropia(String matriculaPropia) {
        this.matriculaPropia = matriculaPropia;
    }

    public String getMatriculaAjena() {
        return matriculaAjena;
    }

    public void setMatriculaAjena(String matriculaAjena) {
        this.matriculaAjena = matriculaAjena;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoIncidencia() {
        return codigoIncidencia;
    }

    public boolean isCobrado() {
        return cobrado;
    }

    public void setCobrado() {
        if (!cobrado) {
            cobrado = true;
        }
    }

    @Override
    public String toString() {
        return String.format("%10s#%10d#%10s#%10s#%10s#%10s#%10b", fecha, hora, matriculaPropia, matriculaAjena, descripcion, codigoIncidencia, cobrado);
    }

    public String toCSV() {
        return String.format("Incidencia;%s;%s;%s;%s;%s;%s;%b\n", fecha, hora, matriculaPropia, matriculaAjena, descripcion, codigoIncidencia, cobrado);
    }
}
