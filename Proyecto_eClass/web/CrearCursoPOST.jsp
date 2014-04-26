<%-- 
    Document   : CrearCursoPOST
    Created on : 24-04-2014, 01:33:05 AM
    Author     : Enrique
--%>

<%@page import="gestionNegocio.GrupoCurso"%>
<%@page import="gestionDatos.Connect"%>
<%@page import="gestionNegocio.Acceso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="sticky-footer-navbar.css" rel="stylesheet">
        <title>Crear Curso</title>
    </head>
    <body>
        <%
            Acceso acc = new Acceso();
            int tipo = Integer.parseInt(String.valueOf(Connect.rs.getObject("id_rol")));
            acc.setNombre(Connect.rs.getObject("nombre").toString());
            out.println(acc.mostrarMenu(tipo));

            GrupoCurso gc = new GrupoCurso(request);
            
            gc.registrarCurso();
        %>
    </body>
</html>
