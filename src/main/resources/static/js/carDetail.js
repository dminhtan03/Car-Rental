// carDetail.js
document.addEventListener("DOMContentLoaded", function() {
    // Initialize Swiper
    // Update your Swiper initialization in carDetail.js
    const swiper = new Swiper(".swiper-container", {
        loop: true,
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev"
        },
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
            dynamicBullets: true
        },
        autoplay: {
            delay: 3000,
            disableOnInteraction: false
        },
        slidesPerView: 1,
        spaceBetween: 10,
        // Add lazy loading for better performance
        lazy: {
            loadPrevNext: true,
        },
        // Add effect for smoother transitions
        effect: "fade",
        fadeEffect: {
            crossFade: true
        }
    });



    // Initialize tabs
    showTab(0);

    // Logout modal functionality
    const logoutLink = document.getElementById('logout');
    const logoutModal = document.getElementById('logoutModal');
    const cancelBtn = document.getElementById('cancel');
    const confirmLogoutBtn = document.getElementById('confirmLogout');

    if (logoutLink) {
        logoutLink.addEventListener('click', function(e) {
            e.preventDefault();
            logoutModal.style.display = 'flex';
        });
    }

    if (cancelBtn) {
        cancelBtn.addEventListener('click', function() {
            logoutModal.style.display = 'none';
        });
    }

    if (confirmLogoutBtn) {
        confirmLogoutBtn.addEventListener('click', function() {
            window.location.href = '/auth/logout';
        });
    }
});

function showTab(index) {
    const tabs = document.querySelectorAll('.tab');
    const tabContents = document.querySelectorAll('.tab-content');

    tabs.forEach(tab => tab.classList.remove('active'));
    tabContents.forEach(content => content.classList.remove('active'));

    tabs[index].classList.add('active');
    tabContents[index].classList.add('active');
}

