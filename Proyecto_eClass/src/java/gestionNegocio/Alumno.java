package gestionNegocio;

import gestionDatos.Connect;
import gestionWeb.Display;
import gestionWeb.Formulario;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;


public class Alumno {

	private String Matricula;
        private String nombre;
	private String rut;
        private String curso;
        //private GrupoCurso curso; 
        private String clave;
        private String msgBD;
        private final String id="0";
        
    public Alumno() {
    }
    
     public Alumno(HttpServletRequest request){
        this.nombre = request.getParameter("nombre");
        this.rut = request.getParameter("rut");
        this.clave = request.getParameter("clave");    
        this.curso = request.getParameter("curso");
       // this.curso.setIdCurso(request.getParameter("curso"));
        
    }
   public void registrarAlumno() {
        boolean ret;
        gestionDatos.Conexion mic = new gestionDatos.Conexion();
        Connect.conectar();
        Connect pc = new Connect();
        String[] campos = {"rut_participante, nombre, clave, id_curso"};
        String[] valores = {this.rut, this.nombre, this.clave, this.curso};
        pc.insertarDatos("participante", campos, valores);
    }
   
    public String perfilParticipante(ResultSet rs){
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
   
   
   /*
    public String mostrarFormCursoAlumno() {
        Formulario form = new Formulario();
        form.Campos("RUT,ID_CURSO");//separados por ,
        form.setId("formAsignacion");
        form.insertarAccion("AsignarAlumnoPOST.jsp");
        EstructuraHTML est = new EstructuraHTML();
        est.insertarContenido(form.generaForm(form.crearCampos()));
        est.insertarTitulo("Asignar curso a alumno");
        return est.contenidoHTML();
    }*/

    public String insertarAlumnoCurso(HttpServletRequest request) {
        Connect.conectar();
        Connect.asignarAlumnoCurso(request.getParameter("id_curso"), request.getParameter("rut"));
        Connect pc = new Connect();

        String[] campos = {"nombre, rut_participante, id_curso"};
        String[][] condiciones = {{"rut_participante"}, {request.getParameter("rut")}};

        ResultSet rs = pc.consultaNormal("participante", campos, condiciones);

        return Formulario.generarTabla(rs);

    }

    private String pc(String x) {
        //agrega comillas de inicio y fin a un String
        return "\"" + x + "\"";
    }
/*
    public String crearAlumno(Formulario form) {
        EstructuraHTML est = new EstructuraHTML();
        est.insertarContenido(form.generaForm(form.crearCampos()));
        est.insertarTitulo("Ingrese los datos del alumno");
        return est.contenidoHTML();
    }
*/
    public Formulario generaFormularioWeb(String pagina, String nombre) {//ahora retorna un Formulario (antes String)
        
        Formulario form = new Formulario();
        form.Campos("NOMBRE,RUT,CLAVE,CURSO", "Nombre,R.U.T,Contraseña,Curso");//separados por ,
        form.setId(nombre);
        form.poneTitulo("Ingrese los datos del alumno");
        
        form.insertarAccion(pagina);
        return form;
    }

    public void insertarDatosAlumno(HttpServletRequest request) {
        System.out.println(request.getParameter("nombre"));
        System.out.println(request.getParameter("rut"));
        System.out.println(request.getParameter("clave"));
        System.out.println(request.getParameter("curso"));

        String[] campos = {"nombre", "rut_participante", "clave", "id_rol", "id_curso"};

        String[] valores = {request.getParameter("nombre"),
            request.getParameter("rut"),
            request.getParameter("clave"),
            "0",
            request.getParameter("curso")};

        Connect.conectar();
        Connect pc = new Connect();

        pc.insertarDatos("participante", campos, valores);

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