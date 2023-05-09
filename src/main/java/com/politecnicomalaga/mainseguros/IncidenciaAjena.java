/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainseguros;

/**
 *
 * @author mint
 */
public class IncidenciaAjena extends Incidencia {

    protected String dniAjeno;

    public IncidenciaAjena(String fecha, int hora, String matriculaPropia, String matriculaAjena, String descripcion, String codigoPoliza, String dniAjeno) {
        super(fecha, hora, matriculaPropia, matriculaAjena, descripcion, codigoPoliza);
        this.dniAjeno = dniAjeno;
        this.tipo = "IncidenciaAjena";
    }

    public IncidenciaAjena(String sCsv) {
        super(sCsv);
        String[] columnas = sCsv.split(";");

        if (columnas[0].equals("Incidencia")) {
            this.dniAjeno = columnas[8];
        }
    }

    @Override
    public String toString() {
        return super.toString() + "#" + dniAjeno;
    }

    @Override
    public String toCSV() {
        return String.format("Incidencia;%s;%s;%s;%s;%s;%s;%b;%s\n", fecha, hora, matriculaPropia, matriculaAjena, descripcion, codigoIncidencia, cobrado, dniAjeno);
    }
}
