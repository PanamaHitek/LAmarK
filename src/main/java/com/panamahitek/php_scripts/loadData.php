<?php
header('Content-type: application/json');
include_once 'include/db_connect.php';
include_once 'include/util.php';
$myArray = loadData($_GET["table_name"]);
echo json_encode($myArray);
?>