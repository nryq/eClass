package gestionWeb;

import com.mysql.jdbc.ResultSetMetaData;
import java.sql.ResultSet;

public class Formulario {

    private String metodo;
    private String accion;// es lo que ejcutará cuando se complete
    private String[] camposForm;
    private String id;
    private String nombreSelect;
    private String titulo;
    private String[] marcador;

    public Formulario() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreSelect() {
        return nombreSelect;
    }

    public void setNombreSelect(String nombreSelect) {
        this.nombreSelect = nombreSelect;
    }
    
     public void poneTitulo(String tt){
        this.titulo=tt;
    }
    

    public void insertarMetodo(String met) {
        this.metodo = met;//post,get,
    }

    public void insertarAccion(String ac) {
        this.accion = ac;//url donde dirije al presionar enviar
    }

    public void Campos(String ca, String ma) {
        this.camposForm = ca.split(",");
        this.marcador = ma.split(",");
        
    }

    public String mostrar() {
        //  System.out.println(""+this.campos[0]+"-" + this.campos[1]+"-"+this.campos[2]);
        String retu = "" + this.camposForm[0] + "-" + this.camposForm[1] + "-" + this.camposForm[2];
        return retu;
    }

    public String crearCampos() {
        String todocampo = "";
        int cant = this.camposForm.length;
        for (int i = 0; i < cant; i++) {
            todocampo +=
                    "	<INPUT type=\"text\" class=\"form-control\" "
                    + "name=" + this.camposForm[i].toLowerCase()
                    + " id=\""+ this.id +"\" placeholder="
                    + "\""+marcador[i]+"\">\n";
            todocampo += "\n";
        }
        return todocampo;
    }
    
    public String generarFormOpciones(String opciones){
        String[] opc = opciones.split(",");
        String out = "";
        out += "<form method=\"post\" id=\" "+ this.id +"\" action=\""+ this.accion +" \">";
        out += "<select name=\""+ this.nombreSelect +"\">";
        
        for(int i=0;i<opc.length;i++){
            out += "<option value=\" "+ opc[i] +"   \"> "+ opc[i] +"</option>";
        }
        
        out += "</select>";
        out += "<input type=\"submit\" method=\"post\">";
        return out;
    }

    public String generaForm(String val) {
        String r;
        r = "<FORM method=\"post\" action=\" " + this.accion + "\" id=\"" + this.id + "\">";
        r += val;
        r += "<INPUT type=\"submit\" value=\"Enviar\""
                + " class=\"btn btn-lg btn-primary btn-block\"> </FORM>";
        return r;
    }

    public static String generarTabla(ResultSet rs) {
        int cantidadFilas = 0;
        int cantidadColumnas = 0;
        String tablita = "";
        try {
            ResultSetMetaData metaDatos = (ResultSetMetaData) rs.getMetaData();
            rs.next();
            while (rs.next()) {
                cantidadFilas++;
            }
            rs.first();
            cantidadColumnas = metaDatos.getColumnCount();
            tablita += "<table class=\"table\">";
            int i=0;
            while(i <= cantidadFilas){
                tablita += "<tr>";
                for(int j=1;j<=cantidadColumnas;j++){
                    tablita+= "<td>" +rs.getString(j)+"</td>";
                    
                }
                tablita+="<td><button type=\"button\" class=\"btn btn-info\">Entrar</button></td>";
                tablita += "</tr>";
                rs.next();
                i++;
            }
            tablita += "</table>";
        } catch (Exception ex) {
         //   JOptionPane.showMessageDialog(null, tablita + ex.toString());
        }
        return tablita;
    }
    
    public static String generarTablaLINKS(ResultSet rs) {
        int cantidadFilas = 0;
        int cantidadColumnas = 0;
        String tablita = "";
        try {
            ResultSetMetaData metaDatos = (ResultSetMetaData) rs.getMetaData();
            rs.next();
            while (rs.next()) {
                cantidadFilas++;
            }
            rs.first();
            cantidadColumnas = metaDatos.getColumnCount();
            tablita += "<table border=2>";
            int i=0;
            while(i <= cantidadFilas){
                tablita += "<tr>";
                for(int j=1;j<=cantidadColumnas;j++){
                    tablita+= "<td>" +rs.getString(j)+"</td>";
                    tablita += "<td> links </td>";
                }
                
                tablita += "</tr>";
                rs.next();
                i++;
            }
            tablita += "</table>";
        } catch (Exception ex) {
         //   JOptionPane.showMessageDialog(null, tablita + ex.toString());
        }
        return tablita;
    }
    
    public static String generarTablaSESION(ResultSet rs, String rut) {
       
        String campoOculto = "<input type=\"hidden\" name=\"rut\" value=\""+rut+"\">";
        int cantidadFilas = 0;
        int cantidadColumnas = 0;
        String tablita = "<form action=\"SesionPOST.jsp\">";
        try {
            ResultSetMetaData metaDatos = (ResultSetMetaData) rs.getMetaData();
            rs.next();
            while (rs.next()) {
                cantidadFilas++;
            }
            rs.first();
            cantidadColumnas = metaDatos.getColumnCount();
            tablita += "<table border=2>";
            int i=0;
            while(i <= cantidadFilas){
                tablita += "<tr>";
                for(int j=1;j<=cantidadColumnas;j++){
                    tablita+= "<td>" +rs.getString(j)+"</td>";
                }
                
                tablita +="<td>"+
                            generarRadioBoton(rs.getObject("id_sesion").toString())+"</td>";
                
                tablita += "</tr>";
                rs.next();
                i++;
            }
            tablita += "</table>";
        } catch (Exception ex) {
            
        }
        tablita += "<input type =\"submit\" value= \"Let's Begin!\">";
        return tablita + campoOculto + "</form>";
    }
    
    public static String generarRadioBoton(String id_sesion){
        return "<input name=\"faradio1\" type=\"radio\" value=\""+ id_sesion +"\"<br>";
        
        
    }
    
    public static String generarBoton(String action, String label, String rut, String id_sesion){
        String campoOculto = "<input type=\"hidden\" name=\"rut\" value=\""+rut+"\">"
                +"<input type=\"hidden\" name=\"id_sesion\" value=\""+id_sesion+"\">";

        return "<form action=\"" + action+" \"> "
                + "<input type=\"submit\" method=\"post\" value=\" " + label +" \">" + campoOculto
                + "</form>";
        
        
    }
    
    
}
