<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script language = "javascript" src = "js/jquery-2.0.3.js"></script>
    <script src = "js/ajax-utils.js"></script>
    <title>Build Route</title>
</head>
<body>
<%  model.User user = (model.User)session.getAttribute("user");
    if (user != null){
%>
<form action ="selectStartingPoint.jsp">
    <input type = "submit" value = "<< Back" id = "backButton">
</form>
<button  id = "undoButton" disabled>Undo...</button>
<div id = "mainDiv"></div>
<button id = "finished">Finished</button>

<style>
    .destination{
        width: 400px;
        height: 50px;
        border: 1px solid black;
        background-color: #d14415;
    }
    .routeCity{
        width: 240px;
        height: 50px;
        border: 1px solid black;
        background-color: #d14415;
    }
    p{
        user-select: none;
    }
</style>

<script>
    $(document).ready(function() {
        loadCities();
        $("#undoButton").click(function () {
            $.ajax({
                type: "DELETE",
                url: "RouteController?all=false",
                data: {
                    userId: <%= user.getId() %>
                }
            });
            $.ajax({
                type: "GET",
                url: "RouteController",
                data: {
                    all: 'false',
                    user: '<%= user %>'
                },
                success: loadCities()
            });
        });
        $("#finished").click(function () {
            $.ajax({
                type: "GET",
                url: "RouteController",
                data: {
                    all: "true",
                    user: '<%= user %>'
                },
                success: function () {
                    loadRoute();
                    setTimeout(function () {
                        $.ajax({
                            type: "DELETE",
                            url: "FinishedController?all=true",
                            data: {
                                all: "true",
                                user: '<%= user %>'
                            }
                        });
                    }, 2000);
                }
            })
        });
    });
    function loadRoute(){
        getFullRoute(<%= user.getId() %>, function (cities) {
            var htmlString = "<h2>Route</h2>";
            for (var city in cities) {
                console.log(city);
                htmlString += routeCityToDiv(cities[city].CityID, cities[city].Name);
            }
            $("#mainDiv").html(htmlString);
        });
    }
    function loadCities(){
        getCityDestinations(<%= user.getId() %>, function (cities) {
            console.log(cities);
            var htmlString = "<h2>Current city:" + cities[0].Name + "</h2>";
            htmlString += "<h3>Destinations:</h3>";
            cities = cities.slice(1);
            for (var city in cities) {
                console.log(city);
                htmlString += cityToDiv(cities[city].CityID, cities[city].Name, cities[city].Distance);
            }
            $("#mainDiv").html(htmlString);
            $(".destination").css("cursor", "pointer");
            $(".destination").click(function () {
                var cityName = $(this).find(">:nth-child(2)").text().slice(6);
                $("#undoButton").prop("disabled", false);
                $("#mainDiv").html("");
                console.log(cityName);
                $.ajax({
                    type: "POST",
                    url: "RouteController",
                    data: {
                        city: cityName,
                        user: '<%= user %>'
                    },
                    success: function(){ setTimeout(loadCities(), 10000); }
                });
            });
        });
    }
    function getCityDestinations(userId, callbackFunction){
        $.getJSON(
            "RouteController",
            { userId: userId,
                all: "false"},
            callbackFunction
        );
    }
    function getFullRoute(userId, callbackFunction){
        $.getJSON(
            "RouteController",
            {
                userId: userId,
                all: "true"},
            callbackFunction
        );
    }
    function cityToDiv(cityId, name, distance) {
        return "<div class = 'destination'><p style = 'float: left; margin-left: 20px;'>CityID: " + cityId + "</p>" +
            "<p style = 'margin-left: 20px; float: left'>Name: " + name + "</p>" +
            "<p style = 'margin-left: 20px; float: left'>Distance: " + distance + "</p></div><br>";
    }
    function routeCityToDiv(cityId, name){
        return "<div class = 'routeCity'><p style = 'float: left; margin-left: 20px;'>CityID: " + cityId + "</p>" +
            "<p style = 'margin-left: 20px; float: left'>Name: " + name + "</p></div><br>";
    }
</script>
</body>
</html>
<%
    }
%>