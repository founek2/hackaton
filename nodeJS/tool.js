
var socket = io();
var image = 'http://localhost:3000/lamp.png';
var makers = [];
var kek = [[49.184396, 16.581840],[49.184307, 16.581671],[49.184200, 16.581478],[49.184102, 16.581250],[49.183997, 16.581006],[49.183923, 16.580754],[49.183823, 16.580440],[49.183753, 16.580134],[49.183693, 16.579745]]
for(var i = 0; i < kek.length; i++){
    makers[i] = null
}
socket.on('point', function(msg){

    if (makers[msg.on] == null){
        var coords = kek[msg.on];
        var latLng = new google.maps.LatLng(coords[0],coords[1]);
        var marker = new google.maps.Marker({
            position: latLng,
            map: map
        });
        makers[msg.on] = marker;

    }
    if (makers[msg.off] != null){
        makers[msg.off].setMap(null)
        makers[msg.off] = null;
    }


});

socket.on('pointArchive', function(msg){
    console.log(msg)

    for (var i = 0; i < msg.length; i++) {
        var coords = kek[msg[i]];
        var latLng = new google.maps.LatLng(coords[0],coords[1]);
        var marker = new google.maps.Marker({
            position: latLng,
            map: map
        });
    }
});