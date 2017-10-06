<?php
 
 require("DataBase.php");
 require ("config.php");
 $dataBase = new DataBase($HTTP,$USUARIO,$CONTRASENA,$DB); // nombre de la base de datos

$especie = $_POST['idEspecie'];
	// Inicio de análisis de variables
	//Consulta a memoria
 	 $result = $dataBase->doQuery("call ($idEspecie);"); //Agregar el nombre del sp antes de los ()
 	 echo json_encode($result);
 	 $dataBase->close();
 	
 ?>