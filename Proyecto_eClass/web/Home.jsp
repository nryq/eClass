<%-- 
    Document   : Home
    Created on : 23-04-2014, 02:20:28 AM
    Author     : Enrique
--%>


<%@page import="gestionDatos.Connect"%>
<%@page import="gestionNegocio.Acceso"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="sticky-footer-navbar.css" rel="stylesheet">

        <title>Home</title>
    </head>
    <body>

        <%
            try {

                Acceso acc = new Acceso();
                
                String rut = request.getParameter("usr");
                String pass = request.getParameter("pwd");

                Connect.buscarRegistro("rut_participante", "clave", rut,
                        pass, "participante");
                int tipo = Integer.parseInt(String.valueOf(Connect.rs.getObject("id_rol")));

                acc.setNombre(Connect.rs.getObject("nombre").toString());
                out.println(acc.mostrarMenu(tipo));
            } catch (Exception ex) {
                out.println("Usuario no valido! " + ex.toString());
            }
        %>
    </body>
</html>
