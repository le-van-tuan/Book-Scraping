<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
    <title>Books Scrapper</title>
</head>
<body>
    <div class="container">
        <h2 style="color: #ffb234">Books download console</h2>
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
        <form action="/home" method="get">
            <input type="submit" class="btn btn-info" value="Reload Google Drive">
        </form>
        <hr style="color: black; size: 2px">
    </div>
</body>
</html>