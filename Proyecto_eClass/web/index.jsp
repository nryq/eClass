<%@page import="gestionWeb.Display"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="sticky-footer-navbar.css" rel="stylesheet">
        <title>eClass</title>
    </head>
    <body>

        <div id="logo">
            <h1>eClass</h1>
        </div>
        <div class="container" id="logeo">
            <div class="row">
                <div class="row-lg-offset-5"></div>
                <div class="row-lg-offset-3">
                        <%
                            String login = "<form action=\"Home.jsp\" method=\"post\">"+
                            "<center> <a style=\"color:blue\">Iniciar Sesion en</a> <p><h2 style=\"color:blue\">eClass </h2> </p>"+
                                "<input name=\"usr\" type=\"name\" class=\"form-control\" placeholder=\"Ingrese su R.U.T\" required autofocus><br>"
                                +"<input name=\"pwd\" type=\"password\" class=\"form-control\" placeholder=\"Contraseña\" required><br>"
                                +"<input class=\"btn btn-lg btn-primary btn-block\" value=\"Iniciar sesion\" type=\"submit\">"
                            +"</center>"
                        +"</form>";
                            
                            out.print(new Display().Well(login));
                        %>
                </div>
            </div>
        </div>
    </body>
</html>