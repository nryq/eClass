<%-- 
    Document   : CrearCursoForm
    Created on : 24-04-2014, 01:18:59 AM
    Author     : Enrique
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="gestionNegocio.Acceso"%>
<%@page import="gestionDatos.Connect"%>
<%@page import="gestionNegocio.GrupoCurso"%>
<%@page import="gestionWeb.Formulario"%>
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
            GrupoCurso gc = new GrupoCurso();
            
            int tipo = Integer.parseInt(String.valueOf(Connect.rs.getObject("id_rol")));
            acc.setNombre(Connect.rs.getObject("nombre").toString());
            out.println(acc.mostrarMenu(tipo));
            
            String form = gc.crearCurso(gc.generaFormularioWeb("CrearCursoPOST.jsp","nuevoCurso"));
            
            //JOptionPane.showMessageDialog(null, form);
            
            out.print(form);
            
            
            
            //crearCurso.crearCampos();
            
        %>
    </body>
</html>
