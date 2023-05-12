<?php

    header("Access-Control-Allow-Origin: *");

    $conn = new mysqli("localhost", "id20023280_memecomic", "Fullstack_123", "id20023280_meme");

    if ($conn->connect_error) 
    {
        $arr = ["result" => "error", "message" => "Unable to connect"];
    } 
    else 
    {
        if(isset($_GET['id'])){
            $id = $_GET['id'];
            $sql = "SELECT * FROM restaurant_list WHERE id like '%$id%'";
        } else {
            $sql = "SELECT * FROM restaurant_list";
        }
    }
    $stmt = $conn->prepare($sql);
    $stmt->execute();
    $result = $stmt->get_result();
    if ($result->num_rows > 1) {
        $data=[];
        while($r=mysqli_fetch_assoc($result)){
            array_push($data, $r);
        }
        $arr = $data;
    } else if($result->num_rows <= 1) {
        $r=mysqli_fetch_assoc($result);
        $arr = $r;
    } else {
        $arr = ["result" => "error", "message" => "sql error: $sql"];
    }
    echo json_encode($arr);
    $stmt->close();
    $conn->close();
?>