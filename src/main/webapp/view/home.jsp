<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>
    <div class="container">



        <h2 style="color: #ffb234">The current documents from Google Driver</h2>
        <hr>
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${filesDriver}" var="item">
                <tr class="success">
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <hr>
        <input type="submit" class="btn btn-info" value="Reload Google Drive">
        <hr style="color: black; size: 2px">
        <hr style="color:#ff6936; size: 2px">


        <h2 style="color: #ffb234">Create folder on google drive to store books.</h2>
        <div class="col-sm-2">
            <form action="/folder/create", method="post">
                <input type="text" class="form-control" name="folderName"  placeholder="Input folder-name.">
                <input type="submit" class="btn btn-warning" value="create-folder">
            </form>
        </div>

        <hr>
        <h2 style="color: #ffb234"></h2>
        <div class="col-sm-8">
            <form action="/all-it-ebooks/java", method="get">
                <input type="submit" class="btn btn-warning" value="Click here to show JAVA books">
            </form>
        </div>
</body>
</html>