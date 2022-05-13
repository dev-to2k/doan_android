<?php
require_once 'configuration.php';

$response = array();

if ($_POST['id']) {
  $id = $_POST['id'];
  $stmt = $conn->prepare("SELECT todo FROM todo WHERE id = ?");
  $stmt->bind_param("s", $id);
  $result = $stmt->execute();

  if ($result == true) {
    $response['error'] = false;
    $response['message'] = "Retrieval Successful";
    $stmt->store_result();
    $stmt->bind_result($todo);
    $stmt->fetch();
    $response['todo'] = $todo;
  }
} else {
  $response['error'] = true;
  $response['message'] = "Insufficient Parameters";
}

echo json_encode($response);
