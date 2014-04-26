package gestionNegocio;

import gestionDatos.Connect;
import gestionWeb.Formulario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;


public class Sesion {

	//SalaVirtual suSesion;
	//ArrayList<RecursoDigital> recursos;
	private String hrInicio;
	private String hrFin;
	private String fecha;
	private String tematica;
        private String id_sesion;
        private boolean disponible;
        private String msgBD;

    public Sesion() {
    }
   
     public Sesion(HttpServletRequest request){
        this.tematica = request.getParameter("tematica");
        this.fecha=request.getParameter("fecha");
        
        cambiaBol(request.getParameter("disponible"));
    }
     public void cambiaBol(String algo){
         if((algo=="s")||(algo=="S")){
           this.disponible=true;  
         }else{
             this.disponible=false;
         }
     }

	public void insertaSesion(){
         String[] campos = {"tematica","fecha"};
         String[] val = {this.tematica,this.fecha};
         
         Connect.conectar();
         Connect pc = new Connect();
         
         pc.insertarDatos("sesion", campos, val);
     }
   /*public boolean insertaSesion() {
        boolean ret;
        gestionDatos.Conexion mic = new gestionDatos.Conexion();
        String vals = pc(this.tematica);
        if(mic.isOK()) {
            ret = mic.insercion("sesion", "tematica", vals);
            this.msgBD = mic.ultimomensaje();
        }
        else {
            ret= false;
            this.msgBD = mic.ultimomensaje();
        }
        return ret;
    }*/
    
    
    private String pc(String x) {
        //agrega comillas de inicio y fin a un String
        return "\""+x+"\"";
    }
    /*
    public String crearSesion(Formulario form){
        EstructuraHTML est= new EstructuraHTML();
        est.insertarContenido(form.generaForm(form.crearCampos()));
        est.insertarTitulo("Crear Sesion");
        return est.contenidoHTML();
    }
    */
    
    
    public ResultSet obtenerParticipantesEnSesion(String rut, String id_rol){
        Connect.conectar();
        return Connect.mostrarParticipantesSesionDisponible(rut, id_rol);
        
        
        
        
    }
    
    public int getRol(String rut) {

        try {

            Connect pc = new Connect();
            String[] campos = {"id_rol"};
            String[][] condiciones = {{"rut_participante"}, {rut}};

            ResultSet rs = pc.consultaNormal("participante", campos, condiciones);

            rs.next();
            
            return Integer.parseInt(String.valueOf(rs.getObject("id_rol")));
            
        } catch (SQLException ex) {
            
        };

        return 1;

    }
    
    public void terminarSesion(String id_sesion, String rut){
        Connect pc = new Connect();
        pc.eliminarConexiones(id_sesion, rut);
    }
    
    public void pasarEstado(HttpServletRequest request){
        Connect.conectar();
        Connect.setEstadoSesion(request.getParameter("id_sesion"), request.getParameter("estado"));
       // JOptionPane.showMessageDialog(null, "entre a pasar estado");

    }
    
    public void pasarEstado(String estado, String id_sesion){
        Connect.conectar();
        Connect.setEstadoSesion(estado, id_sesion);
       // JOptionPane.showMessageDialog(null, "entre a pasar estado");

    }
    
    public Formulario generaFormularioCambioEstado(String pagina, String id) {//ahora retorna un Formulario (antes String)
        Formulario form = new Formulario();
        form.Campos("ID_SESION,ESTADO","Código de la sesión,Estado");//separados por ,
        form.poneTitulo("Cambiar Disponible");
        form.setId(id);
        form.insertarAccion(pagina);
        return form;
    }
    /*
    public String cambiarEstadosesion(Formulario form) {
        EstructuraHTML est = new EstructuraHTML();
        est.insertarContenido(form.generaForm(form.crearCampos()));
        est.insertarTitulo("cambiar disponible");
        return est.contenidoHTML();
    }
    */
    public ResultSet mostrarSesionesActivas(){
        Connect.conectar();
        Connect pc = new Connect();
        String[] campos = {"id_sesion", "tematica", "fecha"};
        String[][] condiciones = {{"estaDisponible"},{"S"}};
        return pc.consultaNormal("sesion", campos, condiciones);
        
    }
            
    
    public Formulario generaFormularioWeb(String pagina, String id) {//ahora retorna un Formulario (antes String)
        Formulario form = new Formulario();
        form.Campos("TEMATICA,FECHA","Temática,Fecha");//separados por ,
        form.poneTitulo("Crear Alumno");
        form.setId(id);
        form.insertarAccion(pagina);
        return form;
    }
    /*
    public String IniciarSesion(Formulario form) {
        EstructuraHTML est = new EstructuraHTML();
        est.insertarContenido(form.generaForm(form.crearCampos()));
        est.insertarTitulo("Iniciar sesion");
        return est.contenidoHTML();
    }
    */
    public Formulario generaFormInicioSesion(String pagina, String id) {//ahora retorna un Formulario (antes String)
        Formulario form = new Formulario();
        form.Campos("RUT,CONTRASENA","R.U.T,Contraseña");//separados por,
        form.setId("formLoginSesion");
        form.insertarAccion("SesionPOST.jsp");
        form.setId(id);
      //  JOptionPane.showMessageDialog(null,"formlario pasa por aca");
        return form;
    }
    
    public String buscarSesionesActivas(String urlDetino, int delay, String div){
        String out = "";
        out+="<script language=\"javascript\" type=\"text/javascript\">\n" +
"          var RequestObject = false;\n" +
"           //directorio donde tenemos el archivo\n" +
"          var Archivo = \""+urlDetino+"\";\n" +
"\n" +
"          // el tiempo X que tardará en actualizarse \n" +
"          window.setInterval(\"actualizacion_reloj()\", "+delay+");\n" +
"\n" +
"          if (window.XMLHttpRequest) RequestObject = new XMLHttpRequest();\n" +
"          if (window.ActiveXObject) RequestObject = new ActiveXObject(\"Microsoft.XMLHTTP\");\n" +
"\n" +
"          function ReqChange() { \n" +
"          // Si se ha recibido la información correctamente\n" +
"            if (RequestObject.readyState==4) {\n" +
"             // si la información es válida \n" +
"             if (RequestObject.responseText.indexOf('invalid') == -1) {\n" +
"                        // Buscamos la div con id online \n" +
"                        document.getElementById(\""+div+"\").innerHTML = RequestObject.responseText;\n" +
"                    } else {\n" +
"                        // Por si hay algun error document.getElementById(\"online\").innerHTML = \"Error llamando\"; \n" +
"                    }\n" +
"                }\n" +
"            }\n" +
"\n" +
"            function llamadaAjax() {\n" +
"                // Mensaje a mostrar mientras se obtiene la información remota...\n" +
"                //document.getElementById(\""+div+"\").innerHTML = \"Buscando...\";\n" +
"                // Preparamos la obtención de datos\n" +
"                RequestObject.open(\"post\", Archivo + \"?\" + Math.random(), true);\n" +
"                RequestObject.onreadystatechange = ReqChange;\n" +
"                // Enviamos\n" +
"                RequestObject.send(null);\n" +
"            }\n" +
"\n" +
"            function actualizacion_reloj() {\n" +
"                llamadaAjax();\n" +
"            }\n" +
"        </script>";
        return out;
    }
   

}