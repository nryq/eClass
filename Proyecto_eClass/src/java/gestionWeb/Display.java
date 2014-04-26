/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionWeb;

import javax.swing.JOptionPane;

/**
 *
 * @author Enrique
 */
public class Display {
    
    String contenido;
    
    
    public String Well(String elemento){
        return "<div class=\"well well-lg\">"+ elemento +"</div>";
    }
    public String Lista(ItemLista il[]){
        String out = "";
        out+="<div class=\"list-group\" align=\"left\">";
        for(int c=0;c<il.length;c++){
            out+=il[c].getItemLista();
        }
        out+="</div>";
        return out;
    }

    public String getForm() {
        return this.contenido;
    }

    public void setForm(String generaForm) {
         this.contenido = Well(generaForm);
    }
    public String Tabla(String etiqueta, String campo){
        String out = "";
        String[] etiquetas = etiqueta.split(",");
        String[] campos = campo.split(",");
        
        for(int c=0;c<etiquetas.length;c++){
            out+="<div class=\"row-md-offset-3 - container\"><div class=\"col-md-6\" align=\"left\">\n" +
                    etiquetas[c] +
"        </div><div class=\"col-md-6\">\n" +
"          "+campos[c]+"\n" +
"        </div>";
        }
        
        
        
        return out;
    }
    
}
