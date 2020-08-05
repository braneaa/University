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