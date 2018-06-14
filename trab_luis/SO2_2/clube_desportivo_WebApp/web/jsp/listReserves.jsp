<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="clube_desportivo.beans.Space"%>
<%@page import="java.util.LinkedList"%>
<%@page import="clube_desportivo.beans.Reserve"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="dataManager" scope="application" class="clube_desportivo.database.DataManager"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Reservas</title>        
        <link rel="stylesheet" href="../css/estilo.css" type="text/css"/>
    </head>
    <body>
        <jsp:include page="topmenu.jsp" flush="true"/>
        <jsp:include page="leftmenu.jsp" flush="true"/>
        <div class="tables">    
            <h1>Listar Reservas</h1>

            <%String space = request.getParameter("space");
                if (space != null) {
                    LinkedList<Reserve> v = dataManager.listReserve(space);
                    if (v.isEmpty()) {
            %>
            <td>Não existem Reservas</td>
            <p></p>
            <button class="button" onclick="window.location.href = '/clube_desportivo_WebApp/clube/?action=listReserves'">Voltar</button>
            <%} else {%>
            <table>
                <tr>
                    <th>Cód. Reserva</th>
                    <th>Nome Responsável</th>
                    <th>Data CheckIn</th> 
                    <th>Data Checkout</th> 
                </tr>
                <%for (int i = 0; i < v.size(); i++) {%>
                <tr>
                    <td><%=v.get(i).getCode()%></td>
                    <td><%=v.get(i).getPerson()%></td>
                    <td><%=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").withZone(ZoneId.systemDefault()).format(v.get(i).getCheckin())%></td>
                    <td><%=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").withZone(ZoneId.systemDefault()).format(v.get(i).getCheckout())%></td>
                </tr>

                <%}%>
            </table>
        </div>
        <p></p>        
        <button class="button" onclick="window.location.href = '/clube_desportivo_WebApp/clube/?action=listReserves'">Voltar</a></button>
            <%}
            } else {%>
    <form>
        <input type="hidden" name="action" value="listReserves"/>
        <label for="space">Qual o Espaço?</label>
        <%LinkedList<Space> p = dataManager.listSpaces();%>
        <select id="space" name="space">
            <%for (int i = 0; i < p.size(); i++) {%>
            <option value=<%=p.get(i).getName()%>><%=p.get(i).getName()%></option>
            <%}%>
        </select>
        <input type="submit" value="Pesquisar">        
    </form>

    <%}%>


</body>
</html>