<%@page language="java" contentType="text/html"%>
<%@page import="eshop.beans.Book"%>

<%-- Isto é um comentário JSP... --%>
<%-- a indicacao abaixo disponibiliza a variável dataManager, para acesso aos dados --%>
<jsp:useBean id="dataManager" scope="application" class="eshop.model.DataManager"/>

<%
    String base = (String) application.getAttribute("base");
    String imageURL = (String) application.getAttribute("imageURL");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Book details</title>
        <link rel="stylesheet" href="css/eshop.css" type="text/css"/>
    </head>
    <body>
        <jsp:include page="TopMenu.jsp" flush="true"/>
        <jsp:include page="LeftMenu.jsp" flush="true"/>
        <div class="content">
            <h2>Book details</h2>
            <%
                try {
                    String bookId = request.getParameter("bookId");
                    Book book = dataManager.getBookDetails(bookId);
                    if (book != null) {
            %>
            <table>
                <tr>
                    <td>
                        <img src="<%=(imageURL + book.getId())%>.gif"/> 
                    </td>
                    <td>
                        <b><%=book.getTitle()%></b><br/>
                        <%=book.getAuthor()%><br/>
                        Price: $<%=book.getPrice()%>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <a class="link1"
                           href="<%=base%>?action=addItem&bookId=<%=book.getId()%>">
                            Add To Cart</a>
                    </td>
                </tr>
            </table>
            <%
                }
            } catch (Exception e) {
            %><p class="error">Invalid book identifier!</p><%
            }
            %>
        </div>  <!-- final da seccao de content -->
    </body>
</html>
