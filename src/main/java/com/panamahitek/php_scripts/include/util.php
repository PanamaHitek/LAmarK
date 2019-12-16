<?php

$debug = TRUE;
date_default_timezone_set('America/Panama');

function insertNewPeopleEntry($json) {
    global $mysqli, $debug;
    if ($stmt = $mysqli->prepare('INSERT INTO `utp_people` (`people_id`, `name`, `middle_name`, `last_name_1`, `last_name_2`, `utp_status`, `distinction`, `birthdate`, `email`, `phone`, `main_address`) VALUES(?,?,?,?,?,?,?,?,?,?,?)')) {
        $stmt->bind_param('sssssssssss', $json->{'people_id'}, $json->{'name'}, $json->{'middle_name'}, $json->{'last_name_1'}, $json->{'last_name_2'}, $json->{'utp_status'}, $json->{'distinction'}, $json->{'birthdate'}, $json->{'email'}, $json->{'phone'}, $json->{'main_address'});
        $stmt->execute();
        $stmt->close();
        return 'OK';
    } else {
        return $mysqli->error;
    }
    return null;
}

function loadData($tableName) {
    global $mysqli;
    if ($stmt = $mysqli->prepare("SELECT * from $tableName")) {
        $stmt->execute();
        $stmt->store_result();
        $meta = $stmt->result_metadata();
        $myArray = array();
        while ($field = $meta->fetch_field()) {
            $params[] = &$row[$field->name];
        }
        call_user_func_array(array($stmt, 'bind_result'), $params);
        while ($stmt->fetch()) {
            foreach ($row as $key => $val) {
                $c[$key] = $val;
            }
            $myArray[] = $c;
        }
        $stmt->close();
        return $myArray;
    }
    return null;
}

