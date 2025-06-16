<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${produit.id == null ? 'Nouveau Produit' : 'Modifier Produit'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">

</head>
<body>
<div class="content-wrapper">
    <nav class="main-nav">
        <ul>
            <li><a href="/produits" class="active">Produits</a></li>
            <li><a href="/categories">Catégories</a></li>
            <li><a href="/marques">Marques</a></li>
        </ul>
    </nav>

    <div class="container">
        <header class="page-header">
            <h2>${produit.id == null ? 'Nouveau Produit' : 'Modifier Produit'}</h2>
        </header>

        <div class="card">
            <form:form action="/produits" method="post" modelAttribute="produit" cssClass="product-form">
                <form:hidden path="id" />
                
                <div class="form-group">
                    <label for="nom">Nom:</label>
                    <form:input path="nom" id="nom" cssClass="form-control" />
                    <form:errors path="nom" cssClass="error" />
                </div>
                
                <div class="form-group">
                    <label for="prix">Prix:</label>
                    <form:input path="prix" id="prix" type="number" min="0" step="0.1" cssClass="form-control" />
                    <form:errors path="prix" cssClass="error" />
                </div>
                
                <div class="form-group">
                    <label for="marque">Marque:</label>
                    <form:select path="marque" id="marque" cssClass="form-control">
                        <form:options items="${marques}" itemValue="id" itemLabel="nom" />
                    </form:select>
                    <form:errors path="marque" cssClass="error" />
                </div>
                
                <div class="form-group">
                    <label for="categorie">Catégorie:</label>
                    <form:select path="categorie" id="categorie" cssClass="form-control">
                        <form:options items="${categories}" itemValue="id" itemLabel="nom" />
                    </form:select>
                    <form:errors path="categorie" cssClass="error" />
                </div>
                
                <div class="actions-bar">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <a href="/produits" class="btn btn-secondary">Annuler</a>
                </div>
            </form:form>
        </div>
    </div>
        </div>

    <footer class="footer">
        <p>&copy; 2025 MST-RSI</p>
        <p>MOUADILI Abdelmounim</p>
    </footer>
</body>
</html>
