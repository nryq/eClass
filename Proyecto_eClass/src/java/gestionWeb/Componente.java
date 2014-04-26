
package gestionWeb;

import java.util.ArrayList;

public class Componente {
    private String urlDestino;
    private String jspDestino;
    private String label;
    public ArrayList<Componente> subMenu = new ArrayList<Componente>();
    private int tamanoMenu;
    
    public Componente(String url, String jsp, String lbl, ArrayList<Componente> sub){
        this.urlDestino = url;
        this.jspDestino = jsp;
        this.label = lbl;
        this.subMenu = sub;
    }
    
    public Componente obtenerSubMenu(int index){
        return subMenu.get(index);
    }

    public String getUrlDestino() {
        return urlDestino;
    }

    public void setUrlDestino(String urlDestino) {
        this.urlDestino = urlDestino;
    }

    public String getJspDestino() {
        return jspDestino;
    }

    public void setJspDestino(String jspDestino) {
        this.jspDestino = jspDestino;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<Componente> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(ArrayList<Componente> subMenu) {
        this.subMenu = subMenu;
    }

    public int getTamanoMenu() {
        return tamanoMenu;
    }

    public void setTamanoMenu(int tamanoMenu) {
        this.tamanoMenu = tamanoMenu;
    }

    
}
