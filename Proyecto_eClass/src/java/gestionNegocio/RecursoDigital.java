/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionNegocio;


/**
 *
 * @author Dani
 */
public class RecursoDigital {
    private String nombre;
    private String ruta="archivosDispo/";
    

    public RecursoDigital() {
    }
    
    public String rutaArchivo(){
        String ret="";
        ret+=this.ruta+this.nombre; 
        return ret;
    }
    public void setNombre(String nuevoNombre){
        this.nombre=nuevoNombre;
    }
    
    public String fijarPdfEmbedido(String rutaFinal){
        String conf="<div>";
        conf+="<embed src=\""+rutaFinal+"#toolbar=0&navpanes=0&scrollbar=0\" width=\"500\" height=\"390\">";
        conf+="</div>";
        return conf;
    }
    
  
    
}
