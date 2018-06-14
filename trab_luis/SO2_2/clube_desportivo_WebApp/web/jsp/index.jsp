<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Clube Desportivo</title>
        <link rel="stylesheet" href="css/estilo.css" type="text/css"/>
    </head>
    <body>
        <jsp:include page="topmenu.jsp" flush="true"/>
        <jsp:include page="leftmenu.jsp" flush="true"/>

        <div class="tables">
            <h1> Bem vindo à Webapp do Clube Desportivo Praça do Giraldo </h1>
        </div>
        <div id="map"></div>
        <script>
            function initMap() {
                var praça = {lat: 38.570796, lng: -7.909537};
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 15,
                    center: praça
                });
                var marker = new google.maps.Marker({
                    position: praça,
                    map: map
                });
            }
        </script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDa_y4lbTSD2abMddX4plS-IH-K-gWzWi0&callback=initMap">
        </script>

        <img src="images/praça.jpg" alt="Futsal" style="width:400px;height:400px;display: inline-block">

        <img class="mySlides" src="images/futsal.gif" style="width:400px;height:400px;display: inline-block">
        <img class="mySlides" src="images/templo.jpeg" style="width:400px;height:400px;display: inline-block">
        <img class="mySlides" src="images/templo2.jpg" style="width:400px;height:400px;display: inline-block">            
        <img class="mySlides" src="images/tenis.gif" style="width:400px;height:400px;display: inline-block">            
        <img class="mySlides" src="images/praça2.jpeg" style="width:400px;height:400px;display: inline-block">               
        <img class="mySlides" src="images/padel.jpg" style="width:400px;height:400px;display: inline-block">

        <script>
            var myIndex = 0;
            carousel();

            function carousel() {
                var i;
                var x = document.getElementsByClassName("mySlides");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                myIndex++;
                if (myIndex > x.length) {
                    myIndex = 1
                }
                x[myIndex - 1].style.display = "inline-block";
                setTimeout(carousel, 2500); // Change image every 2 seconds
            }
        </script>



    </body>

</html>
