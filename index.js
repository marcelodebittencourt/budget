const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const port = 3000; //porta padrão
const mysql = require('mysql');

//configurando o body parser para pegar POSTS mais tarde
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

//definindo as rotas
const router = express.Router();
router.get('/', (req, res) => res.json({ message: 'Funcionando!' }));
app.use('/', router);

router.get('/entries', (req, res) => {
    execSQLQuery('SELECT * FROM Entries', res);
})

router.get('/entries/:id?', (req, res) => {
    let filter = '';
    if (req.params.id) filter = ' WHERE ID=' + parseInt(req.params.id);
    execSQLQuery('SELECT * FROM Entries' + filter, res);
})

router.delete('/entries/:id', (req, res) => {
    execSQLQuery('DELETE FROM Entries WHERE ID=' + parseInt(req.params.id), res);
})

router.post('/entries', (req, res) => {
    const type = req.body.type.substring(0, 1);
    const name = req.body.name.substring(0, 25);
    const value = req.body.value.substring(0, 11);

    execSQLQuery(`INSERT INTO Entries(Type, Name, Value) VALUES('${type}','${name}','${value}')`, res);

    //retornar http 201 se inseriu ok
});

router.patch('/entries/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const type = req.body.type.substring(0, 1);
    const name = req.body.name.substring(0, 25);
    const value = req.body.value.substring(0, 11);

    execSQLQuery(`UPDATE Entries SET Type='${type}', Name='${name}', Value='${value}' WHERE ID=${id}`, res);
})

//inicia o servidor
app.listen(port);
console.log('API funcionando!');

function execSQLQuery(sqlQry, res) {
    const connection = mysql.createConnection({
        host: 'localhost',
        port: 3306,
        user: 'teste',
        password: 'testesisnema',
        database: 'budget'
    });

    connection.query(sqlQry, function(error, results, fields) {
        if (error)
            res.json(error);
        else
            res.json(results);
        connection.end();
        console.log('executou!');
    });
}