<%-- 
    Document   : ListaSesionesActivas
    Created on : 24-04-2014, 10:31:05 PM
    Author     : Enrique
--%>

<%@page import="gestionNegocio.Sesion"%>
<%@page import="gestionDatos.Connect"%>
<%@page import="gestionNegocio.Acceso"%>
<%@page import="gestionWeb.Display"%>
<%@page import="gestionWeb.ItemLista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="sticky-footer-navbar.css" rel="stylesheet">
        <title>Sesiones Activas</title>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="jqueryform.js"></script>
        
            <% 
            //script que actualiza la lista de sesiones activas
                Sesion z = new Sesion();
                out.print(z.buscarSesionesActivas("SesionesActivas.jsp", 1000, "div1"));
            %>
    </head>
    <body>

    

        <%
            Acceso acc = new Acceso();

            int tipo = Integer.parseInt(String.valueOf(Connect.rs.getObject("id_rol")));

            acc.setNombre(Connect.rs.getObject("nombre").toString());
            out.println(acc.mostrarMenu(tipo));

            ItemLista il[] = new ItemLista[1];
            il[0] = new ItemLista("ListaSesionesActivas.jsp", "", 4, "Tematica del dia: xxx");
            Display lista = new Display();

            String salida = lista.Well(lista.Lista(il));

            out.print(salida);

        %>

        <div class="row">
            <div class="container">
                <div class="row-lg-offset-3" id="div1">
                </div>
            </div>
        </div>
        

    </body>
</html>
