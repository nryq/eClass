<%-- 
    Document   : SesionesActivas
    Created on : 25-04-2014, 12:57:14 AM
    Author     : Enrique
--%>

<%@page import="gestionWeb.Formulario"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="gestionDatos.Connect"%>
<%@page import="gestionWeb.ItemLista"%>
<%@page import="gestionWeb.Display"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="gestionNegocio.Sesion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Sesion s = new Sesion();
            //ResultSet rs = s.mostrarSesionesActivas();
            
            out.println(Formulario.generarTabla(s.mostrarSesionesActivas()));
            
        %>
    </body>
</html>
