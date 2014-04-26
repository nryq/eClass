package gestionNegocio;

import gestionWeb.Formulario;
import javax.servlet.http.HttpServletRequest;

public class Administrador {
     
        private String nombre;
	private String rut;
        private String clave;
        private String msgBD;
        private final String id="2";
        
         public Administrador() {
    }
    
     public Administrador(HttpServletRequest request){
        this.nombre = request.getParameter("nombre");
        this.rut = request.getParameter("rut");
        this.clave = request.getParameter("clave");     
        
    }
    
     
      public boolean insertaAdministrador() {
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
    
    
    private String pc(String x) {
        //agrega comillas de inicio y fin a un String
        return "\""+x+"\"";
    }

     public String generaFormularioWeb(String pagina) {
    Formulario form= new Formulario();
    form.Campos("NOMBRE,RUT,CLAVE", "Nombre,R.U.T,contraseña");//separados por ,
    form.poneTitulo("Crear Administrador");
    form.insertarAccion(pagina);
    return form.generaForm(form.crearCampos());
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