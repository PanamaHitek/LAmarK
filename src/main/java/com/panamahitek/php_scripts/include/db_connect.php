<?php
include_once 'psl-config.php';

$mysqli = new mysqli(HOST, USER, PASSWORD, DATABASE);
if ($mysqli->connect_error) {
    header("Location: ../error.php?err=No se ha podido conectar a MySQL");
    exit();
}

 /* change character set to utf8 */
    if (!$mysqli->set_charset("utf8")) {
       // printf("Error loading character set utf8: %s\n", $mysqli->error);
    } else {
        //printf("Current character set: %s\n", $mysqli->character_set_name());
    }