
var socket = io();
var image = 'http://localhost:3000/lamp.png';
var makers = [];
//var kek = [[49.184396, 16.581840],[49.184307, 16.581671],[49.184200, 16.581478],[49.184102, 16.581250],[49.183997, 16.581006],[49.183923, 16.580754],[49.183823, 16.580440],[49.183753, 16.580134],[49.183693, 16.579745]]

var kek = [[49.184471, 16.581806],
    [49.184367, 16.581622],
    [49.184240, 16.581348],
    [49.184160, 16.581200],
    [49.184052, 16.580891],
    [49.183929, 16.580603],
    [49.183859, 16.580348],
    [49.183811, 16.580129],
    [49.183752, 16.579752]];

for(var i = 0; i < kek.length; i++){
    makers[i] = [null, null]
}

socket.on('point', function(msg){
    console.log(msg.on)
if(msg.on != undefined){
    console.log("  sad")
    if (makers[msg.on][0] == null){
        if(kek[msg.on]){
            var coords = kek[msg.on];
            var latLng = new google.maps.LatLng(coords[0],coords[1]);
            var marker = new google.maps.Marker({
                position: latLng,
                map: map
            });
            makers[msg.on] = [marker, true];
        }


    }
}
 if (msg.off){
     if (makers[msg.off][0] != null && makers[msg.off][1]){
         makers[msg.off][0].setMap(null)
         makers[msg.off] = [null, null];
     }

 }


});

socket.on('break', function(msg){
    console.log(msg)
    if(msg.number != undefined){
        if (msg.break == false){
                if(makers[msg.number][1] == true){
                    makers[msg.number][0].setMap(null)
                    makers[msg.number] = [null, null];
                }
                console.log("asdasdasdas")
                var coords = kek[Number(msg.number)];
                var latLng = new google.maps.LatLng(coords[0],coords[1]);
                var image = 'http://127.0.0.1:3000/err.png';

                var marker = new google.maps.Marker({
                    position: latLng,
                    map: map,
                   icon: image
                });
                makers[msg.number] = [marker, false];
        }
        console.log(makers[msg.number][1])
        if (msg.break == true &&  makers[msg.number][1] == false){
            console.log("true Break")
                makers[msg.number][0].setMap(null)
                makers[msg.number] = [null,null];

        }
    }
});