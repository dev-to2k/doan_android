<?php
require_once 'configuration.php';

$response = array();

if (
  isset($_POST['todo'])
) {
  $title = $_POST['todo'];

  $stmt = $conn->prepare("INSERT INTO `todo` (`todo`) VALUES (?)");
  $stmt->bind_param("s", $title);

  if ($stmt->execute() == true) {
    $response['error'] = false;
    $response['message'] = "todo created successfully!";
  } else {
    $response['error'] = true;
    $response['message'] = "failed\n" . $conn->error;
  }
} else {
  $response['error'] = true;
  $response['message'] = "Insufficient parameters";
}

echo json_encode($response);
