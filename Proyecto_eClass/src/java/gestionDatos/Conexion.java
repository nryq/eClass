package gestionDatos;

import java.sql.*;

public class Conexion {

    private Connection con;
    private boolean ok;
    private String msg;
    private String nombreAdmin = "root";
    private String passAdmin = "";
    private String dirHost = "http://localhost/phpmyadmin/";

    public Conexion() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //this.con = DriverManager.getConnection("jdbc:mysql://sistemas.dis.ufro.cl:3306/eclass", "eclass", "emd7712");
            this.con = DriverManager.getConnection( dirHost, nombreAdmin, passAdmin);
            this.ok = con.isValid(0);
            if (this.ok) {
                this.msg = con.getWarnings().getMessage();
                if (this.msg.isEmpty()) {
                    this.msg = "OK DB Connection";
                }

            } else {
                this.msg = "NO DB Connection";
            }
            System.out.println(msg);
        } catch (Exception e1) {
            this.msg = e1.getMessage();
            this.ok = false;

        }
    }

    public boolean consulta(String tabla, String rut, String pass) {
        boolean flag = false;

        Statement st;
        String sql = "";

        try {
            st = con.createStatement();
            sql = "SELECT FROM " + tabla + " WHERE rut =" + rut + " AND constrasena=" + pass;
            st.execute(sql);
            flag = true;
        } catch (Exception e1) {
            flag = false;
            this.ok = false;
            this.msg = e1.getMessage() + " SQL:" + sql;
        }


        return flag;
    }

    public boolean insercion(String tabla, String campos, String valores) {
        //campos y valores separados por , ; se asume todos los valores van entre comillas
        boolean ret = true;
        Statement st;
        String sql = "no sql";
        try {
            st = con.createStatement();
            sql = "INSERT INTO " + tabla + " (" + campos + ") VALUES ";
            sql += "(" + valores + ")";
            st.execute(sql);
            ret = true;
            this.msg = " inserción exitosa ";
        } catch (Exception e1) {
            ret = false;
            this.ok = false;
            this.msg = e1.getMessage() + " SQL:" + sql;
        }

        return ret;
    }

    public boolean isOK() {
        this.msg = "Probará validez de conexión";
        boolean ret;
        try {
            return ret = this.con.isValid(0);
        } catch (Exception e) {
            this.msg = "Problema en conexión";
            ret = false;
        }
        return ret;
    }

    public String ultimomensaje() {
        System.out.println(msg);
        return this.msg;
    }
}
