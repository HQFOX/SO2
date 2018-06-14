<%@page import="java.util.LinkedList"%>
<%@page import="clube_desportivo.beans.Space"%>

<jsp:useBean id="dataManager" scope="application" class="clube_desportivo.database.DataManager"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Listar Espa�os</title>
        <link rel="stylesheet" href="../css/estilo.css" type="text/css"/>
    </head>
    
    <body>
        <jsp:include page="topmenu.jsp" flush="true"/>
        <jsp:include page="leftmenu.jsp" flush="true"/>
        
        <div class="tables">
        
        <h1>Lista de Espa�os do Clube</h1>

            <table>
                <tr>
                    <th>Espa�o</th>
                    <th>Pre�o por hora</th> 
                </tr>

                <%LinkedList<Space> v = dataManager.listSpaces();
                    for (int i = 0; i < v.size(); i++) {%>
                <tr>
                    <td><%=v.get(i).getName()%></td>
                    
                    <td><%=v.get(i).getPrice()%> &euro;</td>
                </tr>
                <% }%>

            </table>    
        </div>
                <p></p>        
        <button class="button" onclick="window.location.href='/clube_desportivo_WebApp/'">P�gina Inicial</a></button>
    </body>
</html>