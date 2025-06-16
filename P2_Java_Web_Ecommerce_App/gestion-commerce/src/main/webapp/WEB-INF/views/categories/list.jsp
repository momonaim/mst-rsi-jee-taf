<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Catégories</title>
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
                <h2>Liste des Catégories</h2>
                <div class="actions-bar">
                    <a href="/categories/new" class="btn btn-primary">
                        <i class="icon">+</i> Nouvelle Catégorie
                    </a>
                </div>
            </header>

            <c:if test="${not empty message}">
                <div class="flash-message success">
                    ${message}
                </div>
            </c:if>

            <div class="card">
                <div class="table-responsive">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${categories}" var="categorie">
                                <tr>
                                    <td>${categorie.id}</td>
                                    <td>${categorie.nom}</td>
                                    <td class="actions">                                        <a href="/categories/edit/${categorie.id}" class="btn btn-sm btn-secondary">Modifier</a>
                                        <a href="/categories/delete/${categorie.id}" 
                                           class="btn btn-sm btn-danger">
                                            Supprimer
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2025 MST-RSI</p>
        <p>MOUADILI Abdelmounim</p>
    </footer>

    <!-- Delete Confirmation Modal -->
    <div class="modal-backdrop" id="deleteModal">
        <div class="modal-dialog">
            <div class="modal-header">
                <h3 class="modal-title">Confirmation de suppression</h3>
            </div>
            <div class="modal-body">
                <p>Êtes-vous sûr de vouloir supprimer cette catégorie ?</p>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" onclick="closeDeleteModal()">Annuler</button>
                <button class="btn btn-danger" onclick="confirmDelete()">Supprimer</button>
            </div>
        </div>
    </div>

    <!-- Snackbar for notifications -->
    <div class="snackbar" id="snackbar"></div>

    <script>
        let deleteUrl = '';

        // Show delete confirmation modal
        function showDeleteModal(url) {
            deleteUrl = url;
            document.getElementById('deleteModal').classList.add('show');
        }

        // Close delete confirmation modal
        function closeDeleteModal() {
            document.getElementById('deleteModal').classList.remove('show');
            deleteUrl = '';
        }

        // Confirm deletion
        function confirmDelete() {
            if (deleteUrl) {
                window.location.href = deleteUrl;
            }
            closeDeleteModal();
        }

        // Show snackbar message
        function showSnackbar(message, type = 'success') {
            const snackbar = document.getElementById('snackbar');
            snackbar.textContent = message;
            snackbar.className = 'snackbar ' + type + ' show';

            setTimeout(() => {
                snackbar.classList.remove('show');
            }, 3000);
        }

        // Check for message parameter in URL
        document.addEventListener('DOMContentLoaded', function() {
            const urlParams = new URLSearchParams(window.location.search);
            const message = urlParams.get('message');
            const type = urlParams.get('type') || 'success';
            
            if (message) {
                showSnackbar(decodeURIComponent(message), type);
                // Remove message from URL
                window.history.replaceState({}, document.title, window.location.pathname);
            }
        });

        // Update delete links to use modal
        document.querySelectorAll('a[href^="/categories/delete/"]').forEach(link => {
            link.addEventListener('click', function(e) {
                e.preventDefault();
                showDeleteModal(this.href);
            });
        });
    </script>
</body>
</html>