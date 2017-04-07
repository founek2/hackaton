/**
 * Created by martas on 7.4.17.
 */
/**
 * Created by martin on 19.12.16.
 */
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.get('/', function (req, res) {
    res.sendFile(__dirname + '/index.html');
});
app.get('/index2.html', function (req, res) {
    res.sendFile(__dirname + '/index2.html');
});
app.get('/js/socket.io.min.js', function (req, res) {
    res.sendFile(__dirname + '/js/socket.io.min.js');
});
app.get('/js/tool.js', function (req, res) {
    res.sendFile(__dirname + '/js/tool.js');
});
app.get('/style.js', function (req, res) {
    res.sendFile(__dirname + '/style.css');
});
app.get('/lamp.png', function (req, res) {
    res.sendFile(__dirname + '/lamp.png');
});

app.post('/point', function (req, res) {
    console.log(req.body)
    io.sockets.in('room').emit('point', req.body.point);
    res.send()

})


io.on('connection', function (socket) {
    socket.join('room');



});

http.listen(3000, function () {
    console.log('listening on *:3000');
});