/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDatos;

import gestionNegocio.Sesion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MauricioPC
 */
public class Connect {

    static Connection con;
    public static ResultSet rs;
    private static String nombreAdmin = "root";
    private static String passAdmin = "";
    private static String dirHost = "jdbc:mysql://localhost/eclass";
    
    public static ResultSet mostrarParticipantesSesionDisponible(String id_sesion, String id_rol) {
        String sql = "";
        
        sql += "select p.nombre, p.rut_participante from participante p"
                + " inner join conexion c on(p.rut_participante = c.rut_participante)"
                + "where c.id_sesion = \""+ id_sesion + "\" and p.id_rol = \"" + id_rol +"\"";
        
        /*sql += "select * from participante";*/
        
  /*      sql += "SELECT p.nombre, s.id_sesion from participante p "
                + "inner join conexion c "
                + "on (p.rut_participante = c.rut_participante) "
                + "inner join sesion s "
                + "on (c.id_sesion = s.id_sesion) "
                + "where (s.id_sesion = "
                + "(select s.id_sesion from sesion s "
                + "inner join conexion c "
                + "on(c.id_sesion = s.id_sesion) "
                + "inner join participante p "
                + "on(c.rut_participante = p.rut_participante) "
                + "where (p.rut_participante = \"" + rut + "\") "
                + "and s.estaDisponible = 'S'))";*/
        try {
            Statement st;
            st = con.createStatement();
            ResultSet rs1 = st.executeQuery(sql);
            return rs1;
        } catch (Exception ex) {
            return null;
        }

    }
    
    public void conectarASesion(String rut_participante, String id_sesion){
        Connect.conectar();
        String sql = "";
        Connect pc = new Connect();
        String[] campos = {"rut_participante", "id_sesion"};
        String[][] condiciones = {{"rut_participante"},{rut_participante}};

        try {
            if (Connect.comprobarConectado(rut_participante, id_sesion) == 0) {
                String[] valores = {rut_participante, id_sesion};
                pc.insertarDatos("conexion", campos, valores);
            } else {
                Sesion s = new Sesion();
                s.terminarSesion(id_sesion, rut_participante);
                String[] valores = {rut_participante, id_sesion};
                pc.insertarDatos("conexion", campos, valores);
            }
        } catch (Exception ex) {
        }




    }
    
    public static ResultSet mostrarTematicaSesion(String id_sesion){
        Connect pc = new Connect();
        String[] campos = {"tematica"};
        String[][] condiciones = {{"id_sesion"},{id_sesion}};
        return pc.consultaNormal("sesion", campos, condiciones);
        
    }
    
    public void eliminarConexiones(String id_sesion, String rut) {
        try {
            Connect.conectar();
            String sql = "";
            sql += "delete from conexion where id_sesion=" + id_sesion +" and rut_participante="+rut;
            Statement st;
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex) {
            
        }
    }
    
    public static int comprobarConectado(String rut, String id_sesion) {
        try {
            String sql = "select count(*) from conexion where rut_participante=\"" + rut + "\" and id_sesion= \"" + id_sesion + "\"";
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return Integer.parseInt(String.valueOf(rs.getObject("count(*)")));
        } catch (Exception ex) {
            return 0;
        }
    }

    public static void setEstadoSesion(String id_sesion, String estado) {
        try {
            String sql = "update sesion set estaDisponible = \"" + estado + "\" where id_sesion =  \" " + id_sesion + "\"";
            Statement st;
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex) {
        }
    }

    public static void conectar() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(dirHost, nombreAdmin, passAdmin);
            if (con.isValid(0)) {
                System.out.println("Conexion exitosa");
            } else {
                System.out.println("No hay conexion");
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        conectar();
    }
  
    public static boolean asignarAlumnoCurso(String id_curso, String rut) {
        try {
            String sql = "update participante set id_curso =  \""+ id_curso +"\" where rut_participante = \""+rut+"\"";
            Statement st;
            st = con.createStatement();;
            st.executeUpdate(sql);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    public static boolean conectarAlumnos(String id_curso, String id_sesion) {
        try {
            String sql = "";
            sql += "insert into conexion (rut_participante, id_sesion)"
                    + "select rut_participante, " + id_sesion + " from participante "
                    + "where id_curso = \""+ id_curso+"\"";
            Statement st;
            st = con.createStatement();
           // JOptionPane.showMessageDialog(null,"Query\n"+sql);
            st.executeUpdate(sql);
            return true;
        } catch (Exception ex) {
           // JOptionPane.showMessageDialog(null, ex.toString());
            return false;
        }
    }
    
    public static ResultSet mostrarParticipantesSesion(String idsesion, int idrol) {
        String sql = "";
        sql +=  "  SELECT p.nombre, p.rut_participante FROM participante p "
                + "INNER JOIN conexion c ON (c.rut_participante = p.rut_participante) "
                + "INNER JOIN sesion s ON (s.id_sesion = c.id_sesion) where "
                + "(s.id_sesion = \""+idsesion+"\") and (p.id_rol = \""+idrol+"\" )";
        //JOptionPane.showMessageDialog(null, sql);
        try {
            Statement st;
            st = con.createStatement();
            ResultSet rs1 = st.executeQuery(sql);
            return rs1;
        } catch (Exception ex) {
            return null;
        }

    }

    public void insertarDatos(String tabla, String[] campos, String[] valores) {
        try {
            String sql = "";
            sql += "insert into " + tabla + "(";

            for (int i = 0; i < campos.length; i++) {

                if (i == campos.length - 1) {
                    sql += campos[i];
                    break;
                }

                sql += campos[i] + ",";
            }
            sql += ")";

            sql += " values (";

            for (int i = 0; i < valores.length; i++) {
                if (i == valores.length - 1) {
                    sql += "\""+valores[i]+"\"";
                    break;
                }
                sql += "\""+valores[i]+"\"" + ",";
            }

            sql += ")";
            
            Statement st;
            st = con.createStatement();
            st.executeUpdate(sql);
            System.out.println("insercion ejecutada");

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error");
        }
    }

    public ResultSet consultaSesionDisponible(String rut){
        try {
            String sql = "select estaDisponible from "
                    + "sesion s inner join conexion c on "
                    + "(s.id_sesion = c.id_sesion) "
                    + "where estaDisponible = 'S' and c.rut_participante = \""+rut+"\"";

            Statement st;
            st = con.createStatement();
            ResultSet st1 = st.executeQuery(sql);
            return st1;
        } catch (Exception ex) {
            return null;
        }

    }
    
    public ResultSet mostrarSesionDisponible(String rut) {
        try {
            String sql = "select id_sesion from conexion c " +
                "inner join sesion s " +
                "on (c.id_sesion = s.id_sesion ) " +
                "where (c.rut_participante = \""+rut+
                "\" ) and (s.estaDisponible = 'S')";
            Statement st;
            st = con.createStatement();
            ResultSet st1 = st.executeQuery(sql);
            return st1;
        } catch (Exception ex) {
            return null;
        }
    }

    public ResultSet mostrarParticipantesDisponibles(String id_sesion) {
        try {
            String sql = "select rut_participante, nombre from participante p "
                    + "inner join conexion c on "
                    + "(c.rut_participante = p.rut_participante) "
                    + "inner join sesion s on "
                    + "(s.id_sesion = c.id_sesion )"
                    + "where s.estaDisponible = 'S' "
                    + "and s.id_sesion = \"" + id_sesion + "\"";
            Statement st;
            st = con.createStatement();
            ResultSet st1 = st.executeQuery(sql);
            return st1;

        } catch (Exception ex) {
            return null;
        }



    }

    
    public ResultSet consultaNormal(String tabla, String[] campos, String[][] condiciones) {
        try {
            String sql = "";
            sql += "select ";

            for (int i = 0; i < campos.length; i++) {
                if (i == campos.length - 1) {
                    sql += " " + campos[i];
                    break;
                } else {
                    sql += " " + campos[i] + ", ";
                }
            }

            sql += " from " + tabla;

            if (condiciones != null) {
                sql += " where ";
                for (int i = 0; i < condiciones[0].length; i++) {
                    if (i == condiciones[0].length - 1) {
                        sql += "( " + condiciones[0][i] + " = " + "\"" + condiciones[1][i] + "\"" + " )";
                        break;
                    }
                    sql += "( " + condiciones[0][i] + " = " + "\"" + condiciones[1][i] + "\"" + " )";
                    sql += " and ";
                }

            }


            Statement st;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            
            
            System.out.println("consulta ejecutada");
            
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
            return null;
        }
    }

    public static boolean buscarRegistro(String campo1, String campo2, 
            String matricula, String contrasena, String tabla) 
    {
        Connect.conectar();

        Statement st;
        String sql = "";

        try {
            System.out.println(matricula);
            System.out.println(contrasena);
            st = con.createStatement();
            sql = "SELECT * FROM " + tabla + " WHERE " + campo1 + " =" + "\"" + matricula + "\"" + " AND +" + campo2 + "=" + "\"" + contrasena + "\"";
            rs = st.executeQuery(sql);
            
            if (!rs.next()) {
                return false;
            } else {
                System.out.println("Matricula: " + rs.getObject(campo1)
                        + "\nNombre: " + rs.getObject("nombre") + "\nID curso" + rs.getObject("id_curso"));
                
                return true;
            }
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
        return false;
    }
}
