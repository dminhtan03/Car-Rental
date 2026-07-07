document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".car-info-and-actions").forEach(carItem => {
        let carStatus = carItem.querySelector(".status").textContent.trim();
        let carID = carItem.querySelector("input[name='carID']").value;
        let buttonContainer = carItem.querySelector(".car-actions #actionButton");

        if (carStatus === "AVAILABLE") {
            fetch(`/cars/checkPendingDeposit?carId=${carID}`)
                .then(response => response.json())
                .then(data => {
                    if (data.hasPendingDeposit) {
                        buttonContainer.innerHTML = `<button class="confirm-deposit" onclick="confirmDeposit(${carID})">Confirm Deposit</button>`;
                    } else {
                        buttonContainer.style.display = "none";
                    }
                })
                .catch(error => console.error("Error fetching booking status:", error));
        } else if (carStatus === "BOOKING") {
            // Kiểm tra trạng thái booking
            fetch(`/cars/checkPendingPayment?carId=${carID}`)
                .then(response => response.json())
                .then(data => {
                    if (data.hasPendingPayment) {
                        buttonContainer.innerHTML = `<button class="confirm-deposit" onclick="confirmPayment(${carID})">Confirm Payment</button>`;
                    } else {
                        buttonContainer.style.display = "none";
                    }
                })
                .catch(error => console.error("Error fetching booking status:", error));
        }

    });

    const logoutBtn = document.getElementById("logout");
    const logoutModal = document.getElementById("logoutModal");
    const cancelBtn = document.getElementById("cancel");
    const confirmLogoutBtn = document.getElementById("confirmLogout");

    document.querySelectorAll('.swiper-container').forEach((container) => {
        new Swiper(container, {
            loop: true,
            autoplay: {
                delay: 5000, // 5 giây đổi ảnh
                disableOnInteraction: false, // Không dừng khi người dùng tương tác
            },
            navigation: {
                nextEl: container.querySelector('.swiper-button-next'),
                prevEl: container.querySelector('.swiper-button-prev'),
            },
            pagination: {
                el: container.querySelector('.swiper-pagination'),
                clickable: true,
            },
            slidesPerView: 1,
            spaceBetween: 10,
        });
    });

    if (logoutModal && logoutBtn && cancelBtn && confirmLogoutBtn) {
        console.log("Logout elements loaded successfully!");

        logoutBtn.addEventListener("click", function (event) {
            event.preventDefault();
            logoutModal.style.display = "flex"; // Đổi thành flex để hiển thị đúng với CSS
        });

        cancelBtn.addEventListener("click", function () {
            logoutModal.style.display = "none";
        });

        confirmLogoutBtn.addEventListener("click", function () {
            window.location.href = "/auth/logout"; // Điều hướng đến trang logout
        });

        // Đóng modal khi nhấn ngoài vùng nội dung
        window.addEventListener("click", function (event) {
            if (event.target === logoutModal) {
                logoutModal.style.display = "none";
            }
        });
    }
});
function fetchDistricts() {
    const city = document.getElementById("city").value;
    fetch(`/districts?city=${city}`)
        .then(response => response.json())
        .then(data => {
            const districtSelect = document.getElementById("district");
            districtSelect.innerHTML = '<option value="">Select District</option>';
            data.forEach(district => {
                districtSelect.innerHTML += `<option value="${district}">${district}</option>`;
            });
            document.getElementById("ward").innerHTML = '<option value="">Select Ward</option>';
        });
}

function fetchWards() {
    const district = document.getElementById("district").value;
    fetch(`/wardsByDistrict?district=${district}`)
        .then(response => response.json())
        .then(data => {
            const wardSelect = document.getElementById("ward");
            wardSelect.innerHTML = '<option value="">Select Ward</option>';
            data.forEach(ward => {
                wardSelect.innerHTML += `<option value="${ward.wardCode}">${ward.ward}</option>`;
            });
        });
}
function showDialog(action) {
    const dialog = document.getElementById('dialog');
    const title = document.getElementById('dialog-title');
    const transactionTypeInput = document.getElementById('transactionType');
    const walletForm = document.getElementById('walletForm');

    // Đặt tiêu đề và giá trị transactionType
    title.innerText = action === 'WITHDRAWAL' ? 'WITHDRAWAL' : 'TOP-UP';
    transactionTypeInput.value = action;

    // Hiển thị dialog với form mặc định
    dialog.style.display = 'block';
    walletForm.style.display = 'block'; // Đảm bảo form hiển thị khi mở dialog thủ công
}

function closeDialog() {
    const dialog = document.getElementById('dialog');
    const walletForm = document.getElementById('walletForm');

    dialog.style.display = 'none';
    walletForm.style.display = 'block'; // Reset form visibility khi đóng
}

// Tự động hiển thị dialog nếu có thông báo từ server
document.addEventListener('DOMContentLoaded', function () {
    const dialog = document.getElementById('dialog');
    const title = document.getElementById('dialog-title');
    const walletForm = document.getElementById('walletForm');

    var error = /*[[${error}]]*/ null;
    var success = /*[[${success}]]*/ null;

    if (error || success) {
        title.innerText = error ? 'Error' : 'Success';
        dialog.style.display = 'block';
        walletForm.style.display = 'none'; // Ẩn form nếu chỉ hiển thị thông báo
    }
});
function previewImageFile() {
    const input = document.getElementById('drivingLicenseFile');
    const previewContainer = document.getElementById('previewContainer');
    const previewImage = document.getElementById('previewImageFile');

    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewContainer.style.display = 'block';
        };

        reader.readAsDataURL(input.files[0]);
    } else {
        previewContainer.style.display = 'none';
    }
}


function confirmDeposit(carId) {
    if (!carId) {
        Swal.fire("Error", "Invalid car ID", "error");
        return;
    }

    Swal.fire({
        title: "Confirm Deposit?",
        text: "Are you sure you want to confirm the deposit?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes, confirm it!",
        cancelButtonText: "Cancel"
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`/bookings/confirmDeposit/${carId}`, {
                method: "POST",
                headers: { "Content-Type": "application/json" }
            })
                .then(response => {
                    if (response.ok) {
                        Swal.fire("Success", "Deposit confirmed successfully!", "success").then(() => {
                            location.reload();
                        });
                    } else {
                        Swal.fire("Error", "Failed to confirm deposit!", "error");
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    Swal.fire("Error", "Something went wrong!", "error");
                });
        }
    });
}

function confirmPayment(carId) {
    if (!carId) {
        Swal.fire("Error", "Invalid car ID", "error");
        return;
    }

    Swal.fire({
        title: "Confirm Deposit?",
        text: "Are you sure you want to confirm the payment?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes, confirm it!",
        cancelButtonText: "Cancel"
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`/bookings/confirmPayment/${carId}`, {
                method: "POST",
                headers: { "Content-Type": "application/json" }
            })
                .then(response => {
                    if (response.ok) {
                        Swal.fire("Success", "Payment confirmed successfully!", "success").then(() => {
                            location.reload();
                        });
                    } else {
                        Swal.fire("Error", "Failed to confirm payment!", "error");
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    Swal.fire("Error", "Something went wrong!", "error");
                });
        }
    });
}



