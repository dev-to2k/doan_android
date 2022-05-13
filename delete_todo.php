<?php
require_once 'configuration.php';

$response = array();

if ($_POST['id']) {
  $id = $_POST['id'];
  $stmt = $conn->prepare("DELETE FROM todo WHERE id = ?");
  $stmt->bind_param("s", $id);

  if ($stmt->execute()) {
    $response['error'] = false;
    $response['message'] = "Delete successfully!";
  } else {
    $response['error'] = true;
    $response['message'] = "Delete failed!";
  }
} else {
  $response['error'] = true;
  $response['message'] = "Insufficient Parameters!";
}

echo json_encode($response);
