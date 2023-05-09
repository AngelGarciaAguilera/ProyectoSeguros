/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainseguros;

/**
 *
 * @author mint
 */
public class IncidenciaUrgente extends Incidencia{
    
    protected int maxDias;
    
    public IncidenciaUrgente(String fecha, int hora, String matriculaPropia, String matriculaAjena, String descripcion, String codigoPoliza, int maxDias) {
        super(fecha, hora, matriculaPropia, matriculaAjena, descripcion, codigoPoliza);
        this.maxDias = maxDias;
        this.tipo = "IncidenciaUrgente";
    }
    
    public IncidenciaUrgente(String sCsv) {
        super(sCsv);
        String[] columnas = sCsv.split(";");

        if (columnas[0].equals("Incidencia")) {
            this.maxDias = Integer.parseInt(columnas[8]);
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + "#" + maxDias;
    }
    
    @Override
    public String toCSV() {
        return String.format("Incidencia;%s;%s;%s;%s;%s;%s;%b;%d\n", fecha, hora, matriculaPropia, matriculaAjena, descripcion, codigoIncidencia, cobrado, maxDias);
    }
}
