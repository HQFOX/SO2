<%@page import="editora.beans.Artigo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="editora.beans.Autor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dataManager" scope="application" class="editora.database.DataManager"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verificar Reservas</title>        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    </head>
    <body style="background-color: rgb(0, 17, 28)">
        <jsp:include page="menu.jsp" flush="true"/>
    <body>
        <div class="container">
            <section>
                <div class="row" >
                    <div class="row">
                        <%  String codigo = request.getParameter("keyword");
                            ArrayList<Artigo> v = dataManager.obtainArticles(Integer.parseInt(codigo));
                            if (v.size() == 0) {
                        %><h2 style="color:white;"> Não existem Artigos deste Autor!</h2%><%
                                }
                                for (int i = 0; i < v.size(); i++) {%>
                        <div class="col-md">
                            <div class="">
                                <div class="text-center text-white">
                                    <img class="img-fluid" src="images/imagem<%=(v.get(i).getId())%>.jpg" style="width:200px;height:200px;"> 
                                    <h5 style="color:white"> Titulo: <%=(v.get(i).getTitle())%></h5>
                                    <h6 style="color:white"> Autor: <%=(v.get(i).getAutor())%></h6>
                                    <h5 style="color:white"> Stock: <%=(v.get(i).getStock())%></h5>
                                    <button type="button" class="btn btn-group button" style ="width: 33%; margin: 0 auto; padding: 0; display: table-cell; vertical-align: middle;" onclick="window.location.href = '/editora_WebApp/?action=detailArtigo&bookId=<%=(v.get(i).getId())%>'">Detalhes</button>
                                </div>
                            </div> 
                        </div>
                        <%}%>                                    
                    </div>
                </div>
            </section>
            <button class="btn btn-primary btn-block"style="position: fixed; left: 0; bottom: 0; width: 100%; background-color: #e7e7e7; color: black; text-align: center; border-color: #e7e7e7;" onclick="window.location.href = '/editora_WebApp/'">Página Inicial</a></button>
        </div>
    </body>
</html>
