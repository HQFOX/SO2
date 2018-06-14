<%@page language="java" contentType="text/html"%>

<%@page import="eshop.beans.Category"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Enumeration"%>

<% String base = (String)application.getAttribute("base"); %>

<jsp:useBean id="dataManager" scope="application"
  class="eshop.model.DataManager"/>
<div class="menu"> 
  <div class="box">
    <div class="title">Quick Search</div>
    <p>Book Title/Author:</p>
    <form style="border: 0px solid; padding: 0; margin: 0;">
      <input type="hidden" name="action" value="search"/>
      <input id="text" type="text" name="keyword" size="15"/>
      <input id="submit" type="submit" value="Search"/>
      </form>
    </div>
  <div class="box">
    <div class="title">Categories</div>
<% 
    LinkedList<Category> categories = dataManager.getCategoryList();
    for (Category c: categories) {
      out.println("<p><a href=" + base + "?action=selectCatalog&id="
        + c.getId() + ">" + c.getName() + "</a></p>"
        );
      }
 %>
    </div>
  </div>
