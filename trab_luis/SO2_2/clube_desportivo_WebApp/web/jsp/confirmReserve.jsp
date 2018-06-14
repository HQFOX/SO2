<%@page import="clube_desportivo.beans.Reserve"%>
<%@page import="java.time.Instant"%>
<jsp:useBean id="dataManager" scope="application" class="clube_desportivo.database.DataManager"/>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmação de Reserva</title>
        <link rel="stylesheet" href="../css/estilo.css" type="text/css"/>
    </head>
    <body>
        <jsp:include page="topmenu.jsp" flush="true"/>
        <jsp:include page="leftmenu.jsp" flush="true"/>
        <h1>Reserva</h1>

        <%
            String space = request.getParameter("space");
            String checkin = request.getParameter("checkin");
            String checkout = request.getParameter("checkout");
            String name = request.getParameter("name");
            Integer phone = Integer.parseInt(request.getParameter("phone"));
            Integer persons = Integer.parseInt(request.getParameter("persons"));

            if (space != null && space != "" && checkin != null
                    && checkin != "" && checkout != null && checkout != ""
                    && phone != null && persons != null) {

                Instant chin = Instant.parse(checkin.replace("%3A", ":") + ":00Z");
                Instant chout = Instant.parse(checkout.replace("%3A", ":") + ":00Z");
                
                
                
                %>
                
                <%

                Reserve r = new Reserve(space, name, phone, persons, chin, chout);
                Integer code = dataManager.reserveSpace(r);

                if (code > 0) {%>
                     <p>Reserva feita com sucesso, código: <%=code%></p>
                     <button class="button" onclick="window.location.href='/clube_desportivo_WebApp/'">Página Inicial</button>
                <%} else {
                    
                %>
                <p>Não foi possivel reservar</p>
                <button class="button" onclick="window.location.href='/clube_desportivo_WebApp/'">Página Inicial</button>
                <%
                }

            }
        %>
            
            
            





    </body>
</html>
