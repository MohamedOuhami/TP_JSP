<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sign Up page</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <h1 class="text-center">Sign Up</h1>
                <form action="/user/create" method="POST" onsubmit="return validateForm();">
                    <input type="text" name="signup" hidden value="signup">
                    <div class="form-group">
                        <label for="nom">Nom:</label>
                        <input type="text" class="form-control" id="nom" name="nom" required>
                    </div>
                    <div class="form-group">
                        <label for="prenom">Prenom:</label>
                        <input type="text" class="form-control" id="prenom" name="prenom" required>
                    </div>
                    <div class="form-group">
                        <label for="dateNaissance">Date de naissance:</label>
                        <input type="date" class="form-control" id="dateNaissance" name="dateNaissance" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <div class="form-group">
                        <label for="password_dup">Confirm Password:</label>
                        <input type="password" class="form-control" id="password_dup" name="password_dup" required>
                    </div>
                    <span id="passwordMismatch" style="color: red; display: none;">Passwords do not match.</span>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form>
            </div>
        </div>
    </div>
    <!-- Include Bootstrap JS and jQuery (optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    
    <script>
        function validateForm() {
            var password = document.getElementById("password").value;
            var passwordDup = document.getElementById("password_dup").value;
            var passwordMismatch = document.getElementById("passwordMismatch");
            
            if (password !== passwordDup) {
                passwordMismatch.style.display = "block";
                return false;
            } else {
                passwordMismatch.style.display = "none";
                return true;
            }
        }
    </script>
</body>
</html>
