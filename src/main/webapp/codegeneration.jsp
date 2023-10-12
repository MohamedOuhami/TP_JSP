<%-- 
    Document   : codegeneration
    Created on : 10 Oct 2023, 21:04:25
    Author     : moham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Code Generation</title>

    <!-- Include Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form action="/user/resetpassword" method="POST" class="mt-5">
        <input type="text" name="gen_code" value="${requestScope.code}" hidden>
        <div class="form-group">
            <label for="entered_code">Code de Génération :</label>
            <input type="text" class="form-control" name="entered_code" id="entered_code">
        </div>

        <button type="submit" class="btn btn-primary">Valider</button>
    </form>
</div>

<!-- Include Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
