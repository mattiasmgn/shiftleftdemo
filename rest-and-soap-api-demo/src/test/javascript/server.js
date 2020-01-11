const NodeStatic = require('node-static');
const path = require('path');
const http = require('http');
const toServe = path.resolve('..', '..', 'main', 'resources', 'static');

const server = new NodeStatic.Server(toServe, { cache: 1 });
http.createServer((req, res) => {
    req.addListener('end', () => {
        server.serve(req, res)
    }).resume();
}).listen(3000);