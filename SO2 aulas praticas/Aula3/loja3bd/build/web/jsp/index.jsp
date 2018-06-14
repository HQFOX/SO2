<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Loja3</title>
        <link rel="stylesheet" href="css/eshop.css" type="text/css"/>
    </head>
    <body>
        <jsp:include page="TopMenu.jsp" flush="true"/>
        <jsp:include page="LeftMenu.jsp" flush="true"/>

        <div class="content">
            <h1>Welcome to e-Shop  (Loja de Livros)</h1>

            <small><p align="right"> <%
                GregorianCalendar cal = new GregorianCalendar();
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                out.println(fmt.format(cal.getTime()));
                %> </p>
            </small>
        </div>

    </body>
</html>
