<%@page language="java" contentType="text/html"%>

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="eshop.beans.Book"%>

<jsp:useBean id="dataManager" scope="application" class="eshop.model.DataManager"/>

<% String base = (String)application.getAttribute("base"); %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Browse Catalog</title>
  <link rel="stylesheet" href="css/eshop.css" type="text/css"/>
  </head>
<body>

<jsp:include page="TopMenu.jsp" flush="true"/>
<jsp:include page="LeftMenu.jsp" flush="true"/>

<%     
  String categoryId = request.getParameter("id");
  String categoryName = null;
  System.err.println("\t a procurar a categoria com o id: "+categoryId);
  if (categoryId != null && !categoryId.trim().equals("")) {
    try {
      categoryName = dataManager.getCategoryName(categoryId);
      System.err.println("\t\t categoria encontrada: "+categoryName);
    } 
    catch(NumberFormatException e) {
    }
  }
  if (categoryName != null) {
%>
    <div class="content">
      <h2>Select Catalog</h2>
      <p>Category: <strong><%=categoryName%></strong></p>
      <table>
        <tr>
          <th>Title</th>
          <th>Author</th>
          <th>Price</th>
          <th>Details</th>
          </tr>
<%
        LinkedList<Book> books = dataManager.getBooksInCategoryName(categoryName);
        for (Book book: books) {
          String pId = book.getId();
%>
          <tr>
            <td>
                <a class="link1" href="<%=base%>?action=bookDetails&bookId=<%=pId%>">
                <%=book.getTitle()%>
                </a>
            </td>
            <td><%=book.getAuthor()%></td>
            <td><%=book.getPrice()%></td>
            <td><a class="link1"
              href="<%=base%>?action=bookDetails&bookId=<%=pId%>">
              Details</a>
            </td>
            </tr>
<%
          }
  %>
        </table>
      </div>
<%
  }
  else {
	%><p class="error">Invalid category!</p><%
  }
%>
</body>
</html>
