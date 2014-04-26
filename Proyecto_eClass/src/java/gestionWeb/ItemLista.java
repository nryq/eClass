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
public class ItemLista {
    
    String url; 
    String titulo; 
    int tamTitulo; 
    String desc;

    public ItemLista(String url, String titulo, int tamTitulo, String desc) {
        this.url = url;
        this.titulo = titulo;
        this.tamTitulo = tamTitulo;
        this.desc = desc;
    }
    


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTamTitulo() {
        return tamTitulo;
    }

    public void setTamTitulo(int tamTitulo) {
        this.tamTitulo = tamTitulo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
     public String getItemLista(){
        String out="";
        out+="<a href=\""+this.url+"\" class=\"list-group-item\">";
        out+=Titulo(this.titulo,this.tamTitulo);
        out+=this.desc;
        out+="</a>";
        return out;
    }
    public String Titulo(String titulo, int tamTitulo){
        return "<h"+tamTitulo+" class=\"list-group-item-heading\">"+titulo+"</h"+tamTitulo+">";
    }
}
