// Optimized tab switching function
function showTab(index) {
    // Cache DOM queries to avoid repeated DOM access
    const tabs = document.querySelectorAll('.tab');
    const contents = document.querySelectorAll('.tab-content');

    // Use classList manipulation for better performance
    for (let i = 0; i < tabs.length; i++) {
        if (i === index) {
            tabs[i].classList.add('active');
            contents[i].classList.add('active');
        } else {
            tabs[i].classList.remove('active');
            contents[i].classList.remove('active');
        }
    }
}

// Document ready with event delegation for better performance
document.addEventListener("DOMContentLoaded", function() {
    // Set up tab event listeners using event delegation
    const tabContainer = document.querySelector('.tabs');
    if (tabContainer) {
        tabContainer.addEventListener('click', function(e) {
            if (e.target.classList.contains('tab')) {
                const index = Array.from(tabContainer.children).indexOf(e.target);
                showTab(index);
            }
        });
    }

    // Initialize Swiper only once
    const swiperElement = document.querySelector('.swiper-container');
    if (swiperElement) {
        new Swiper(swiperElement, {
            loop: true,
            navigation: {
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev"
            },
            pagination: {
                el: ".swiper-pagination",
                clickable: true
            },
            autoplay: {
                delay: 3000,
                disableOnInteraction: false
            },
            slidesPerView: 1,
            spaceBetween: 10,
        });
    }

    // Initialize other functionality
    initializeOtherTermToggle();
    initializeImageUpload();
    initializeDeleteButtons();
});

function initializeOtherTermToggle() {
    const otherTermCheckbox = document.getElementById('otherTerm');
    const otherTermDetails = document.getElementById('otherTermDetails');

    if (otherTermCheckbox && otherTermDetails) {
        otherTermCheckbox.addEventListener('change', function() {
            otherTermDetails.style.display = this.checked ? 'block' : 'none';
        });

        // Initial state
        otherTermDetails.style.display = otherTermCheckbox.checked ? 'block' : 'none';
    }
}

function initializeImageUpload() {
    const carImagesInput = document.getElementById('carImages');
    const fileList = document.getElementById('fileList');

    if (carImagesInput && fileList) {
        carImagesInput.addEventListener('change', function() {
            fileList.innerHTML = '';

            if (this.files.length > 0) {
                const fileNames = Array.from(this.files)
                    .map(file => `<div>${file.name} (${Math.round(file.size / 1024)} KB)</div>`)
                    .join('');
                fileList.innerHTML = fileNames;
            }
        });
    }
}

function initializeDeleteButtons() {
    document.querySelectorAll('.delete-image').forEach(button => {
        button.addEventListener('click', function() {
            const imageIndex = this.getAttribute('data-index');
            const form = document.querySelector('form');

            if (form) {
                const hiddenInput = document.createElement('input');
                hiddenInput.type = 'hidden';
                hiddenInput.name = 'deleteImageIndexes';
                hiddenInput.value = imageIndex;
                form.appendChild(hiddenInput);
            }

            // Visual feedback
            this.closest('.image-thumbnail').style.display = 'none';
        });
    });

    document.querySelectorAll('.delete-document').forEach(button => {
        button.addEventListener('click', function() {
            const documentIndex = this.getAttribute('data-index');
            const form = document.querySelector('form');

            if (form) {
                const hiddenInput = document.createElement('input');
                hiddenInput.type = 'hidden';
                hiddenInput.name = 'deleteDocumentIndexes';
                hiddenInput.value = documentIndex;
                form.appendChild(hiddenInput);
            }

            // Visual feedback
            this.closest('.document-item').style.display = 'none';
        });
    });
}

function confirmStopRenting(select) {
    let currentStatus = select.getAttribute("data-current-status");

    if (select.value === "STOPPED" && currentStatus === "BOOKING" || select.value === "AVAILABLE") {
        alert("Your car has been booked. Please contact our administrator if your car is no longer available for rent.");
        select.value = currentStatus;
        return;
    }

    if (select.value === "STOPPED" && currentStatus === "AVAILABLE") {
        let confirmAction = confirm("Do you want to stop renting this car?");
        if (!confirmAction) {
            select.value = currentStatus;
        }
    }
    document.addEventListener("DOMContentLoaded", function () {
        const statusSelect = document.getElementById("status");
        const currentStatus = statusSelect?.getAttribute("data-current-status");

        if (currentStatus === "BOOKING") {
            const form = document.querySelector("form");
            const elements = form.querySelectorAll("input, select, textarea");

            // Disable toàn bộ field nhập liệu
            elements.forEach(el => {
                if (el.type !== "hidden") {
                    el.disabled = true;
                }
            });

            // Ẩn nút "Save Changes"
            const saveBtn = document.querySelector(".form-actions button[type='submit']");
            if (saveBtn) {
                saveBtn.style.display = "none";
            }

            // Giữ lại nút "Cancel" nguyên vẹn
        }
    });

}