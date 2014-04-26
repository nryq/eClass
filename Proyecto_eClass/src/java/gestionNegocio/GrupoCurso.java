/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionNegocio;

import gestionDatos.Connect;
import gestionWeb.Display;
import gestionWeb.Formulario;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dani
 */
public class GrupoCurso {
    private String nombreCurso;
    private String id_Curso;
     private String msgBD;
     
    
    
     public GrupoCurso() {
    }
    
     public GrupoCurso(HttpServletRequest request){
        this.nombreCurso = request.getParameter("nombre");
        this.id_Curso = request.getParameter("id_curso");
        
        
    }
     public void registrarCurso() {
        boolean ret;
        gestionDatos.Conexion mic = new gestionDatos.Conexion();
        Connect.conectar();
        Connect pc = new Connect();
        String[] campos = {"nombrecurso","id_curso"};
        String[] valores = {this.nombreCurso, this.id_Curso};
        pc.insertarDatos("grupocurso", campos, valores);
    }
    

    public String crearCurso(Formulario form){
        Display dis= new Display();
        dis.setForm(form.generaForm(form.crearCampos()));
        return dis.getForm();
    }
    public Formulario generaFormularioWeb(String pagina, String id) {//ahora retorna un Formulario (antes String)
        Formulario form = new Formulario();
        form.Campos("NOMBRE,ID_CURSO", "Nombre del curso,Abreviación");//separados por "," ("id,id", marcador,marcador)
        form.poneTitulo("Crear Curso");
        form.setId(id);
        form.insertarAccion(pagina);
        return form;
    }
    
    public void inscribirAlumno(Alumno al){

    }    

    public void verificarAsistencia(Alumno a){


    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public void setIdCurso(String idCurso) {
        this.id_Curso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public String getIdCurso() {
        return id_Curso;
    }




    
}
