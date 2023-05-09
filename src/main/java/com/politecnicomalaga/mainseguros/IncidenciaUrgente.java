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
    
    @Override
    public String toString(){
        return super.toString() + "#" + maxDias;
    }
}
