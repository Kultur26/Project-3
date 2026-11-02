

// ---------- Cart helpers ----------
function getCart() {
    return JSON.parse(localStorage.getItem('cart') || '[]');
}

function saveCart(cart) {
    localStorage.setItem('cart', JSON.stringify(cart));
    updateCartBadge();
}

function updateCartBadge() {
    const count = getCart().reduce((sum, x) => sum + x.quantity, 0);
    const badge = document.getElementById('cartBadge');
    if (badge) {
        badge.textContent = count;
        badge.style.display = count > 0 ? 'flex' : 'none';
    }
}

// ---------- Read product data from DOM ----------
function getProductFromCard(card) {
    const name  = card.querySelector('.product-name').textContent.trim();
    const price = parseFloat(card.querySelector('.product-price').textContent.replace(/[^\d.]/g, ''));
    const image = card.querySelector('.product-card-image img')?.getAttribute('src') || '';
    const category = card.dataset.category || '';
    return { id: name.toLowerCase().replace(/\s+/g, '-'), name, price, imageUrl: image, category };
}

// ---------- Add to cart ----------
function attachAddToCart() {
    document.querySelectorAll('.add-to-cart').forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.stopPropagation();
            const card = btn.closest('.product-card');
            const item = getProductFromCard(card);

            const cart = getCart();
            const existing = cart.find(x => x.id === item.id);
            if (existing) {
                existing.quantity += 1;
            } else {
                cart.push({ ...item, quantity: 1 });
            }
            saveCart(cart);

            btn.classList.add('added');
            btn.querySelector('span:first-child').textContent = 'Added ✓';
            setTimeout(() => {
                btn.classList.remove('added');
                btn.querySelector('span:first-child').textContent = 'Add to Cart';
            }, 1200);
        });
    });
}

// ---------- Search + Filter ----------
function applyFilters() {
    const query = document.getElementById('searchInput').value.toLowerCase().trim();
    const activePill = document.querySelector('.pill.active');
    const category = activePill ? activePill.dataset.category : 'All';

    document.querySelectorAll('.product-card').forEach(card => {
        const name = card.querySelector('.product-name').textContent.toLowerCase();
        const cardCategory = card.dataset.category || '';

        const matchesSearch = !query || name.includes(query);
        const matchesCategory = category === 'All' || cardCategory === category;

        card.style.display = (matchesSearch && matchesCategory) ? '' : 'none';
    });
}

// ---------- Init ----------
document.addEventListener('DOMContentLoaded', () => {
    // Search
    document.getElementById('searchInput').addEventListener('input', applyFilters);

    // Category pills
    document.querySelectorAll('.pill').forEach(pill => {
        pill.addEventListener('click', () => {
            document.querySelectorAll('.pill').forEach(p => p.classList.remove('active'));
            pill.classList.add('active');
            applyFilters();
        });
    });

    // Cart
    attachAddToCart();
    updateCartBadge();

    // Admin FAB (optional — shows if logged-in user is ADMIN)
    const user = (typeof getCurrentUser === 'function') ? getCurrentUser() : null;
    if (user && user.role === 'ADMIN') {
        const fab = document.getElementById('fabAdd');
        fab.style.display = 'block';
        fab.addEventListener('click', () => alert('Admin product form coming soon'));
    }
});