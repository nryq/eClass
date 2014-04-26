package gestionWeb;

import java.util.ArrayList;

public class Menu {
    private String nav = "";
    private String nombreUser="";
    private String salidaMenu;
    private ArrayList<Componente> componentes;
    private final String ggleapis = "https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js";
    private String jsDir = "bootstrap/js/bootstrap.min.js";
    

    public Menu(ArrayList<Componente> componentes){
        this.componentes = componentes;
    }
    
    public void setComponente(ArrayList<Componente> componentes){
        this.componentes = componentes;
    }
    
    public void agregarComponente(String jsp, String div, String label, ArrayList<Componente> subMenus){
        componentes.add(new Componente(div, jsp, label, subMenus));
    }
    
    public Componente obtenerComponente(int index){
        return componentes.get(index);
    }
    
    public void generarMenu(String usr) {
        this.salidaMenu = "";
        salidaMenu += 
                "<div class=\"navbar navbar-inverse navbar\"> " +
                    "<div class=\"container\"> "
                + "<div class=\"navbar-header\">"+
                    "<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n" +
                        "<span class=\"sr-only\">Toggle navigation</span>\n" +
                        "<span class=\"icon-bar\"></span>\n" +
                        "<span class=\"icon-bar\"></span>\n" +
                        "<span class=\"icon-bar\"></span>\n" +
                    "</button>"
                + "<a class=\"navbar-brand\" href=\"Profile.jsp\">"+usr+"</a>"
                +   "</div>"
                
                + "<div class=\"navbar-collapse collapse\">"
                + "<ul class=\"nav navbar-nav\">";

        for (int i = 0; i < componentes.size(); i++) {

            if (componentes.get(i).subMenu != null) {

                salidaMenu += "<li class=\"dropdown\"><a href=\"#\" "
                        + "class=\"dropdown-toggle\" data-toggle=\"dropdown\">"
                        + componentes.get(i).getLabel()
                        + "<b class=\"caret\"></b></a>";
                //if (componentes.get(i).subMenu != null) {
                    salidaMenu += "<ul class=\"dropdown-menu\">";
                    for (int j = 0; j < componentes.get(i).subMenu.size(); j++) {
                        salidaMenu += "<li> <a href=\""
                                + componentes.get(i).subMenu.get(j).getUrlDestino() + "\">"
                                + componentes.get(i).subMenu.get(j).getLabel()
                                + "</a></li>";
                    }
                    salidaMenu += "</ul>";

            } else {
                salidaMenu += "<li><a href=\"" + componentes.get(i).getUrlDestino()
                        + "\">" + componentes.get(i).getLabel() + "</a>";
            }
            salidaMenu += "</li>";

        }

        salidaMenu += "</ul> </div> </div> </div>"
                + "<script src=\""+ggleapis+"\"></script>\n" +
                "<script src=\""+jsDir+"\"></script>";
    }

    public String obtenerMenu(){
        return this.salidaMenu;
    }

}
