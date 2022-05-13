<?php
require_once 'configuration.php';

$response = array();

if ($_POST['id'] && $_POST['todo']) {
  $id = $_POST['id'];
  $todo = $_POST['todo'];
  $stmt = $conn->prepare("UPDATE todo SET todo = ? WHERE id = ?");
  $stmt->bind_param("ss", $todo, $id);

  if ($stmt->execute() == true) {
    $response['error'] = false;
    $response['message'] = "Update successfully!";
  } else {
    $response['error'] = true;
    $response['message'] = "Incorrect id!";
  }
} else {
  $response['error'] = true;
  $response['message'] = "Insufficient Parameters!";
}

echo json_encode($response);
