<%-- 
    Document   : Profile
    Created on : 24-04-2014, 12:32:34 AM
    Author     : Enrique
--%>


<%@page import="gestionNegocio.Alumno"%>
<%@page import="gestionNegocio.Profesor"%>
<%@page import="gestionWeb.ItemLista"%>
<%@page import="gestionNegocio.Login"%>
<%@page import="gestionNegocio.Acceso"%>
<%@page import="gestionDatos.Connect"%>
<%@page import="gestionWeb.Display"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="sticky-footer-navbar.css" rel="stylesheet">
        <title>Perfil</title>
    </head>
    <body>
        <%
            Acceso acc = new Acceso();
            String perfil = "";
            Alumno a = new Alumno();
            
            int tipo = Integer.parseInt(String.valueOf(Connect.rs.getObject("id_rol")));
        
            acc.setNombre(Connect.rs.getObject("nombre").toString());
            out.println(acc.mostrarMenu(tipo));
            
            switch(tipo){
                case 0: perfil = a.perfilParticipante(Connect.rs);
                    break;
                case 1: Profesor p = new Profesor();
                        perfil = p.perfilProfesor(Connect.rs);
                    break;
                default:perfil = a.perfilParticipante(Connect.rs);
                    break;
            }
            %> <div class="row-sm-offset-5"> <%
                out.print(perfil);
            %><div><%
            
        %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="dist/js/bootstrap.min.js"></script>
    </body>
</html>
