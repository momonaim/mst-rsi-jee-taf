<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Produits</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                <h2>Liste des Produits</h2>
                <div class="actions-bar">
                    <a href="/produits/new" class="btn btn-primary">
                        <i class="icon">+</i> Nouveau Produit
                    </a>
                </div>
            </header>

            <div class="card">
                <div class="table-responsive">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Prix</th>
                                <th>Marque</th>
                                <th>Catégorie</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${produitsPage.content}" var="p">
                                <tr>
                                    <td>${p.id}</td>
                                    <td>${p.nom}</td>
                                    <td>${p.prix} €</td>
                                    <td>${p.marque.nom}</td>
                                    <td>${p.categorie.nom}</td>
                                    <td class="actions">
                                        <a href="/produits/edit/${p.id}" class="btn btn-sm btn-secondary">Modifier</a>
                                        <a href="/produits/delete/${p.id}" 
                                           class="btn btn-sm btn-danger">
                                            Supprimer
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <form method="get" action="/produits" class="page-size-form">
                    <label for="size">Afficher :</label>
                    <select name="size" id="size" onchange="this.form.submit()">
                        <option value="5" ${produitsPage.size == 5 ? 'selected' : ''}>5</option>
                        <option value="10" ${produitsPage.size == 10 ? 'selected' : ''}>10</option>
                        <option value="20" ${produitsPage.size == 20 ? 'selected' : ''}>20</option>
                    </select>
                    par page
                    <input type="hidden" name="page" value="0"/>
                </form>

                <div class="pagination">
                    <c:if test="${!produitsPage.first}">
                        <a href="?page=${produitsPage.number - 1}&size=${produitsPage.size}">&laquo; Précédent</a>
                    </c:if>

                    <span>Page ${produitsPage.number + 1} sur ${produitsPage.totalPages}</span>

                    <c:if test="${!produitsPage.last}">
                        <a href="?page=${produitsPage.number + 1}&size=${produitsPage.size}">Suivant &raquo;</a>
                    </c:if>
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
                <p>Êtes-vous sûr de vouloir supprimer ce produit ?</p>
                <p class="text-muted">Cette action ne peut pas être annulée.</p>
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
                // Remove message from URL but keep pagination parameters
                const newUrl = new URL(window.location.href);
                newUrl.searchParams.delete('message');
                newUrl.searchParams.delete('type');
                window.history.replaceState({}, document.title, newUrl);
            }

            // Update delete links to use modal
            document.querySelectorAll('a[href^="/produits/delete/"]').forEach(link => {
                link.addEventListener('click', function(e) {
                    e.preventDefault();
                    showDeleteModal(this.href);
                });
            });
        });
    </script>
</body>
</html>
