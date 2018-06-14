<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservar Espaços</title>                
        <link rel="stylesheet" href="../css/estilo.css" type="text/css"/>
    </head>
    <body>

        <%
            String space = request.getParameter("space");
            String checkin = request.getParameter("checkin");
            String checkout = request.getParameter("checkout");

            if (space != null && space != "" && checkin != null && checkin != "" && checkout != null && checkout != "") 
    {%>
        <jsp:include page="topmenu.jsp" flush="true"/>
        <jsp:include page="leftmenu.jsp" flush="true"/>
        
        <h1>Reservar Espaço</h1>
            
        <form>
            <input type="hidden" name="action" value="confirmReserve"/>
            <input type="hidden" name="space" value="<%=space%>"/>
            <input type="hidden" name="checkin" value="<%=checkin%>"/>
            <input type="hidden" name="checkout" value="<%=checkout%>"/>
            Nome do Responsável <input type="text" name="name" pattern="[a-zA-Z ]+" required><br>
            Nº Telefone <input type="text" name="phone" pattern="[29][0-9]{8}" required><br>
            Nº de pessoas <input type="number" name="persons" min="1" required><br>

            <input type="submit" value="Confirmar">
            <button class="button" onclick="window.location.href='/clube_desportivo_WebApp/clube/?action=checkReserve'">Voltar</button>            
        </form>       
        
        <%    } else {

                response.sendRedirect("/clube_desportivo_WebApp/clube/?action=checkReserve");

            }%>
    </body>
</html>
