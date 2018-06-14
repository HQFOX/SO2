<%@page import="clube_desportivo.beans.Reserve"%>
<%@page import="java.time.Instant"%>
<%@page import="java.util.LinkedList"%>
<%@page import="clube_desportivo.beans.Space"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dataManager" scope="application" class="clube_desportivo.database.DataManager"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verificar Reservas</title>        
        <link rel="stylesheet" href="../css/estilo.css" type="text/css"/>
    </head>
    <body>
        <jsp:include page="topmenu.jsp" flush="true"/>
        <jsp:include page="leftmenu.jsp" flush="true"/>
        
        <h1>Verificar Disponibilidade </h1>
        
            <%
                String space = request.getParameter("space");
                String checkin = request.getParameter("checkin");
                String checkout = request.getParameter("checkout");
                if (space != null && checkin != "" && checkout != "") {
                    Instant chin = Instant.parse(checkin.replace("%3A", ":") + ":00Z");
                    Instant chout = Instant.parse(checkout.replace("%3A", ":") + ":00Z");

                    boolean isPossible = dataManager.checkReserve(space, chin, chout);

                    if (isPossible) {
            %>
            <p>É possivel Reservar!</p>
            <p>Tem um custo de <%=dataManager.getReserveCost(space, chin, chout)%>€</p>
            <button class="button" onclick="window.location.href='/clube_desportivo_WebApp/clube/?action=reserveSpace&space=<%=space%>&checkin=<%=checkin%>&checkout=<%=checkout%>'">Fazer Reserva!</button>
            <button class="button" onclick="window.location.href='/clube_desportivo_WebApp/'">Página Inicial</button> 
            <%} else {
            %>
            <p>Não é possivel reservar. Escolha outra data</p>
            <button class="button" onclick="window.location.href='/clube_desportivo_WebApp/'">Página Inicial</button>
            <%}
            } else {
            %>
            <form>
                <input type="hidden" name="action" value="checkReserve"/>

                <label for="space">Qual o Espaço?</label>
                <%LinkedList<Space> p = dataManager.listSpaces();%>
                <select id="space" name="space">
                    <%for (int i = 0; i < p.size(); i++) {%>
                    <option value=<%=p.get(i).getName()%>><%=p.get(i).getName()%></option>
                    <%}%>
                </select>

                <p><label>Checkin:</label>
                    <input type="datetime-local" id="checkin" name="checkin"></p>
                <p><label>Checkout:</label> 
                    <input type="datetime-local" id="checkout" name="checkout"></p>

                <input type="submit" value="Verificar">    
            </form>
            <button class="button" onclick="window.location.href='/clube_desportivo_WebApp/'">Página Inicial</button>

            <%}%>

    </body>
</html>
