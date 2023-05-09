/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.politecnicomalaga.mainseguros;

/**
 *
 * @author mint
 */
public class IncidenciaAjena extends Incidencia{
    
    protected String dniAjeno;
    
    public IncidenciaAjena(String fecha, int hora, String matriculaPropia, String matriculaAjena, String descripcion, String codigoPoliza, String dniAjeno) {
        super(fecha, hora, matriculaPropia, matriculaAjena, descripcion, codigoPoliza);
        this.dniAjeno = dniAjeno;
        this.tipo = "IncidenciaAjena";
    }
    
    @Override
    public String toString(){
        return super.toString() + "#" + dniAjeno;
    }    
}
