document.addEventListener("DOMContentLoaded", function () {
    // Initialize Swiper sliders
    initializeSwiper();

    // Load rating counts
    loadRatingCounts();

    // Add active state to rating filter boxes
    initRatingBoxes();
});

function initializeSwiper() {
    document.querySelectorAll(".swiper-container").forEach((swiperEl) => {
        new Swiper(swiperEl, {
            loop: true,
            navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
            },
            pagination: {
                el: ".swiper-pagination",
                clickable: true,
            },
            effect: "fade",
            fadeEffect: {
                crossFade: true
            }
//            autoplay: {
//                delay: 5000,
//                disableOnInteraction: false,
//            }
        });
    });
}

function filterByRating(rating) {
    // Reset active state on all rating boxes
    document.querySelectorAll(".rating-box").forEach(box => {
        box.classList.remove("active");
    });

    // Set active state on clicked box
    if (rating === 0) {
        document.querySelector(".rating-box:first-child").classList.add("active");
    } else {
        document.querySelector(`.rating-box:nth-child(${rating + 1})`).classList.add("active");
    }

    // Filter reviews
    document.querySelectorAll(".review-card").forEach((item) => {
        const itemRatingAttr = item.getAttribute("data-rating");
        const itemRating = itemRatingAttr !== null ? parseInt(itemRatingAttr) : NaN;

        if (rating === 0 || (!isNaN(itemRating) && itemRating === rating)) {
            item.style.display = "block";
            setTimeout(() => {
                item.style.opacity = "1";
                item.style.transform = "translateY(0)";
            }, 10);
        } else {
            item.style.opacity = "0";
            item.style.transform = "translateY(10px)";
            setTimeout(() => {
                item.style.display = "none";
            }, 300);
        }
    });
}

function loadRatingCounts() {
    fetch("/reviews/counts")
        .then(response => response.json())
        .then(data => {
            // Update total reviews count in both places
            document.getElementById("totalReviews").textContent = data.totalReviews;
            document.getElementById("totalReviews-2").textContent = data.totalReviews;

            // Update individual star ratings
            for (let i = 1; i <= 5; i++) {
                let ratingElement = document.getElementById(`rating-${i}`);
                if (ratingElement) {
                    ratingElement.textContent = data.ratingCounts[i] || 0;
                }
            }
        })
        .catch(error => console.error("Error loading review counts:", error));
}

function initRatingBoxes() {
    // Add active state to the "All" filter by default
    document.querySelector(".rating-box:first-child").classList.add("active");

    // Add click event to all rating boxes
    document.querySelectorAll(".rating-box").forEach(box => {
        box.addEventListener('click', function() {
            document.querySelectorAll(".rating-box").forEach(b => {
                b.classList.remove("active");
            });
            this.classList.add("active");
        });
    });
}

// Add CSS class to the rating-box when clicked
document.addEventListener('DOMContentLoaded', function() {
    var style = document.createElement('style');
    style.textContent = `
        .rating-box.active {
            background-color: var(--primary-color);
            color: white;
            border-color: var(--primary-color);
        }
        .rating-box.active .star-icons,
        .rating-box.active .count {
            color: white;
        }
        .review-card {
            transition: opacity 0.3s ease, transform 0.3s ease, box-shadow 0.3s ease;
        }
    `;
    document.head.appendChild(style);
});