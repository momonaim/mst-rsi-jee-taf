<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${categorie.id == null ? 'Nouvelle Catégorie' : 'Modifier Catégorie'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">

</head>
<body>
<div class="content-wrapper">
    <nav class="main-nav">
        <ul>
            <li><a href="/produits">Produits</a></li>
            <li><a href="/categories" class="active">Catégories</a></li>
            <li><a href="/marques">Marques</a></li>
        </ul>
    </nav>

    <div class="container">
        <header class="page-header">
            <h2>${categorie.id == null ? 'Nouvelle Catégorie' : 'Modifier Catégorie'}</h2>
        </header>

        <div class="card form-card">
            <form:form action="/categories" method="post" modelAttribute="categorie">
                <form:hidden path="id" />
                
                <div class="form-group">
                    <label for="nom">Nom:</label>
                    <form:input path="nom" id="nom" cssClass="form-control" />
                    <form:errors path="nom" cssClass="error" />
                </div>
                
                <div class="actions-bar">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="/categories" class="btn btn-secondary">Annuler</a>
                </div>
            </form:form>
        </div>
    </div>
    </div>
    <footer class="footer">
        <p>&copy; 2025 MST-RSI</p>
        <p>MOUADILI Abdelmounim</p>
    </footer>
    <script>
        // Add flash message support
        const flashMessage = document.querySelector('.flash-message');
        if (flashMessage) {
            setTimeout(() => {
                flashMessage.style.display = 'none';
            }, 3000);
        }
    </script>

</body>
</html>