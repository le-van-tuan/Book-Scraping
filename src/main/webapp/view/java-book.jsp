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

    <h2 style="color: #ffb234">The JAVA books on All-IT-EBooks website.</h2>
    <hr>
    <table class="table">
        <thead>
        <tr>
            <th>index</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${javaBooks}" var="item">
            <tr class="success">
                <td>1</td>
                <td>${item.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h2 style="color: #ffb234">Choose one books and upload Books to your Google Drive</h2>
    <hr>
    <div class="col-sm-2">
        <input type="submit" class="btn btn-warning" value="Upload">
        <a style="color:#000;">Please copy with correct name of book.</a>
    </div>
</div>


</div>
</body>
</html>