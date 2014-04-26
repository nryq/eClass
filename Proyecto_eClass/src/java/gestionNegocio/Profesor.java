package gestionNegocio;

import gestionDatos.Connect;
import gestionWeb.Display;
import gestionWeb.Formulario;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;


public class Profesor {

	//Chat suChat;
	private String nombre;
	private String rut;
	private String especialidad;
        private String clave;
        private String msgBD;
        private final String id="1";
       
    public Profesor() {
    }
    
     public Profesor(HttpServletRequest request){
        this.nombre = request.getParameter("nombre");
        this.rut = request.getParameter("rut");
        this.clave = request.getParameter("clave");     
        
    }
    
     
      public boolean insertaProfesor() {
        boolean ret;
        gestionDatos.Conexion mic = new gestionDatos.Conexion();
        String vals = pc(this.nombre)+","+pc(this.rut)+","+pc(this.clave)+","+pc(this.id);
        if(mic.isOK()) {
            ret = mic.insercion("participante", "nombre,rut_participante,clave,id_rol", vals);
            this.msgBD = mic.ultimomensaje();
        }
        else {
            ret= false;
            this.msgBD = mic.ultimomensaje();
        }
        return ret;
    }
    public String perfilProfesor(ResultSet rs){
        try{
            String out="";
            Display tabla = new Display();
            
            String cmp = Connect.rs.getObject("nombre").toString()+","
                    +Connect.rs.getObject("rut_participante").toString();
            out+=tabla.Well(tabla.Tabla("Nombre,R.U.T",cmp));
            return out;
        }catch(Exception ex){
            return null;
        }
    }
    
    private String pc(String x) {
        //agrega comillas de inicio y fin a un String
        return "\""+x+"\"";
    }
/*
    public String crearProfesor(Formulario form){
        EstructuraHTML est= new EstructuraHTML();
        est.insertarContenido(form.generaForm(form.crearCampos()));
        est.insertarTitulo("Crear Profesor");
        return est.contenidoHTML();
    }*/
    public Formulario generaFormularioWeb(String pagina, String id) {//ahora retorna un Formulario (antes String)
        Formulario form = new Formulario();
        form.Campos("NOMBRE,RUT,CLAVE", "Nombre,R.U.T,Contraseña");//separados por ,
        form.poneTitulo("Crear Profesor");
        form.setId(id);
        form.insertarAccion(pagina);
        return form;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getClave() {
        return clave;
    }

    public String getId() {
        return id;
    }
        
        
        

}