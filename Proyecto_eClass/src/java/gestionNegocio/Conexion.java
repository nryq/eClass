package gestionnegocio;

import gestionDatos.Connect;
import gestionWeb.Formulario;
import java.sql.ResultSet;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public class Conexion {

    private int duracion;
    private Date fecha;
    //private Alumno al;
    private String id_Conexion;
    private String id_curso;
    private String id_sesion;

    public Conexion() {
    }

    public Conexion(HttpServletRequest request) {
        this.id_curso = request.getParameter("id_curso");
        this.id_sesion = request.getParameter("id_sesion");
    }
    
    

    public void conectaParticipante() {
        Connect.conectar();
        Connect.conectarAlumnos(this.id_curso, this.id_sesion);
        Connect pc = new Connect();
        //mostrar conectados o conexiones
       /* 
         String[] campos = {"id_conexion", "rut_participante", "duracion", "fecha", "id_sesion"};

         ResultSet rs = pc.consultaNormal("conexion", campos, null);
         */
    }

    private String pc(String x) {
        //agrega comillas de inicio y fin a un String
        return "\"" + x + "\"";
    }

    public String generaForm(String pagina) {
        Formulario form = new Formulario();
        form.Campos("ID_CURSO,ID_SESION", "Código del curso,Código de la sesión");//separados por ,
        form.poneTitulo("COnectar Participantes");
        form.insertarAccion(pagina);
        return form.generaForm(form.crearCampos());
    }
}