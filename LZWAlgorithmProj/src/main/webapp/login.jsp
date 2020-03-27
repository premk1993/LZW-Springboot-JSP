<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body style="background-color:#b3b3ff">

<div class="container">
  <br>
  <h1 class="form-signin-heading text-muted">Welcome to LZW Algorithm</h1>
  <form class="form-signin" action="/login">
  
    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter Name" name="name">
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>
    
    <button type="submit" class="btn btn-default" >Submit</button>
  </form>
</div>

</body>
<style>
  .form-signin {
    max-width: 350px;
    padding: 15px;
    margin: 0 auto;
      margin-top:50px;
  }
  
  
  .form-signin-heading {
    color: Black;
    text-align: center;
    text-shadow: 0 2px 2px rgba(0,0,0,.5);
  }
  </style>
</html>
