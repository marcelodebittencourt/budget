const mysql = require('mysql');
const connection = mysql.createConnection({
    host: 'localhost',
    port: 3306,
    user: 'teste',
    password: 'testesisnema',
    database: 'budget'
});


connection.connect(function(err) {
    if (err) return console.log(err);
    console.log('conectou!');
    createTable(connection);
})

function createTable(conn) {

    const sql = "CREATE TABLE IF NOT EXISTS Entries (" +
        "ID INT NOT NULL AUTO_INCREMENT," +
        "Type CHAR(1) NOT NULL," +
        "Name VARCHAR(25) NOT NULL," +
        "Value DECIMAL(11,2) NOT NULL," +
        "PRIMARY KEY (ID)" +
        ");";

    conn.query(sql, function(error, results, fields) {
        if (error) return console.log(error);
        console.log('criou a tabela!');
        addRows(conn)
    });
}

function addRows(conn) {
    const sql = "INSERT INTO Entries(Type,Name,Value) VALUES ?";
    const values = [
        ['R', 'Salario 1', 1500],
        ['R', 'Salario 2', 2000],
        ['D', 'Habitacao', 1000],
        ['D', 'Saude', 900],
        ['D', 'Transporte', 800],
        ['D', 'Despesas Pessoais', 700],
        ['D', 'Educacao', 600],
        ['D', 'Lazer', 500],
        ['D', 'Outros', 400]
    ];
    conn.query(sql, [values], function(error, results, fields) {
        if (error) return console.log(error);
        console.log('adicionou registros!');
        conn.end(); //fecha a conexão
    });
}