<?php
require_once 'configuration.php';
//an array to display response
$response = array();
$sql = "SELECT todo FROM todo";
$result = $conn->query($sql);
if ($result == TRUE) {
  $response['error'] = false;
  $response['message'] = "Retrieval Successful!";
  $alldata = $result->fetch_all(MYSQLI_ASSOC);
  $response['alldata'] = $alldata;
  $conn->close();
} else {
  $response['error'] = true;
  $response['message'] = "Insufficient Parameters";
}
echo json_encode($response);
