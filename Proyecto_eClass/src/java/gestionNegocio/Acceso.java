package gestionNegocio;

import gestionWeb.Componente;
import gestionWeb.Menu;
import java.util.ArrayList;

public class Acceso {
    
    public String rut;
    public String nombre;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setRut(String rut){
        this.rut = rut;
    }
    
    public String getRut(){
        return this.rut;
    }
    
    public Acceso() {
    }
    

    public String mostrarMenu(int tipo){
        switch(tipo){
            case 0:
                return mostrarMenuAlumno();
            case 1:
                return mostrarMenuProfesor();
            case 2:
                return mostrarMenuAdministrador();
            case 3:
                return mostrarMenuAlumno(); // invitado
        }
        
        return null;
    }
    
    public String mostrarMenuAdministrador(){
        ArrayList<Componente> menusAdministrador = new ArrayList<Componente>();
        
        menusAdministrador.add(new Componente("#contenido", "CrearProfesor.jsp", "Registrar Profesor", null));
        menusAdministrador.add(new Componente("#contenido", "CrearAlumno.jsp", "Registrar Alumno", null));
        menusAdministrador.add(new Componente("#contenido", "CrearCurso.jsp", "Registrar Curso", null));
        menusAdministrador.add(new Componente("#contenido", "FormConsulta.jsp", "Ver registros", null));
        menusAdministrador.add(new Componente("#contenido", "SesionLogin.jsp?rut="+this.rut, "Entrar a sesion", null));
        menusAdministrador.add(new Componente("#contenido", "index.jsp", "Cerrar sesion", null));
        Menu out = new Menu(menusAdministrador);
        out.generarMenu(nombre);
        return out.obtenerMenu();
    }
    
    public String mostrarMenuAlumno(){
        ArrayList<Componente> menusAlumno = new ArrayList<Componente>();
      //menusAlumno.add(new Componente("#contenido", "registroAlumnos.jsp", "Perfil", null));
      //menusAlumno.add(new Componente("#contenido", "registroAlumnos.jsp", "Archivos", null));
      //menusAlumno.add(new Componente("#contenido", "registroAlumnos.jsp", "Ver curso", null));
        menusAlumno.add(new Componente("#contenido", "SesionLOGIN.jsp?rut="+this.rut, "Entrar a sesion", null));
        menusAlumno.add(new Componente("#contenido", "registroAlumnos.jsp", "Cerrar sesion", null));
        Menu out = new Menu(menusAlumno);
        out.generarMenu(nombre);
        return out.obtenerMenu();
    }
    
    public String mostrarMenuProfesor(){
        
        //JOptionPane.showMessageDialog(null, "3pasando por mostrarmenuprofe");
        ArrayList<Componente> menusProfesor = new ArrayList<Componente>();
        ArrayList<Componente> subMenuCurso = new ArrayList<Componente>();
        ArrayList<Componente> subMenuArchivo = new ArrayList<Componente>();
        ArrayList<Componente> subMenuSesion = new ArrayList<Componente>();
        
        
        subMenuCurso.add(new Componente("CrearCursoForm.jsp", "CrearCurso.jsp", "Registrar curso", null));
   //   subMenuCurso.add(new Componente("#contenido", "registroAlumnos.jsp", "Ver curso", null));
        subMenuCurso.add(new Componente("AsignarAlumnos.jsp", "AsignarAlumnos.jsp", "Añadir alumno a curso", null));
        subMenuCurso.add(new Componente("ConectarAlumnos.jsp", "ConectarAlumnos.jsp", "Asignar sesion", null));

        subMenuArchivo.add(new Componente("formularioEnvio.jsp", "formularioEnvio.jsp", "Subir", null));
        subMenuArchivo.add(new Componente("egistroAlumnos.jsp", "registroAlumnos.jsp", "Ver subidos", null));
        
        subMenuSesion.add(new Componente("CrearSesion.jsp", "CrearSesion.jsp", "Crear", null));
        subMenuSesion.add(new Componente("registroAlumnos.jsp", "registroAlumnos.jsp", "Modificar", null));
        subMenuSesion.add(new Componente("ListaSesionesActivas.jsp", "ListaSesionesActivas.jsp", "Entrar", null));
        subMenuSesion.add(new Componente("SesionHabilitarCerrar.jsp", "SesionHabilitarCerrar.jsp", "Cambiar disponibilidad", null));
        
      //  menusProfesor.add(new Componente("#contenido", "registroAlumnos.jsp", "Perfil", null));
        menusProfesor.add(new Componente("CrearAlumno.jsp", "CrearAlumno.jsp", "Registrar alumno", null));
        menusProfesor.add(new Componente("registroAlumnos.jsp", "registroAlumnos.jsp", "Curso", subMenuCurso));
        menusProfesor.add(new Componente("registroAlumnos.jsp", "registroAlumnos.jsp", "Archivo", subMenuArchivo));
        menusProfesor.add(new Componente("registroAlumnos.jsp?rut="+this.rut, "registroAlumnos.jsp?rut="+this.rut, "Sesion", subMenuSesion));
        menusProfesor.add(new Componente("registroAlumnos.jsp", "registroAlumnos.jsp", "Cerrar sesion", null));
        
        Menu men = new Menu(menusProfesor);
        men.generarMenu(nombre);
        return men.obtenerMenu();

        
    }
    
    

    
    
}
