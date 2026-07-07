   let currentImageIndex = 0;
        let carData = {};
        const imageIds = ['frontImage', 'backImage', 'leftImage', 'rightImage'];
        let carImage = null; // Initialize as null, will be set in DOMContentLoaded

        function saveStepData(step) {
            if (step === 1) {
                const elements = {
                    licensePlate: document.getElementById('licensePlate'),
                    brand: document.getElementById('brand'),
                    productionYear: document.getElementById('productionYear'),
                    transmission: document.querySelector('input[name="transmission"]:checked'),
                    color: document.getElementById('color'),
                    model: document.getElementById('model'),
                    seats: document.getElementById('seats'),
                    fuel: document.querySelector('input[name="fuel"]:checked')
                };

                // Only save if elements exist
                if (elements.licensePlate) carData.licensePlate = elements.licensePlate.value;
                if (elements.brand) carData.brand = elements.brand.value;
                if (elements.productionYear) carData.productionYear = elements.productionYear.value;
                if (elements.transmission) carData.transmission = elements.transmission.value;
                if (elements.color) carData.color = elements.color.value;
                if (elements.model) carData.model = elements.model.value;
                if (elements.seats) carData.seats = elements.seats.value;
                if (elements.fuel) carData.fuel = elements.fuel.value;

                // Handle file inputs
                const registrationPaper = document.getElementById('registrationPaper');
                const inspectionCertificate = document.getElementById('inspectionCertificate');
                const insurance = document.getElementById('insurance');

                if (registrationPaper?.files[0]) carData.registrationPaper = registrationPaper.files[0];
                if (inspectionCertificate?.files[0]) carData.inspectionCertificate = inspectionCertificate.files[0];
                if (insurance?.files[0]) carData.insurance = insurance.files[0];
            } else if (step === 2) {
                const elements = {
                    mileage: document.getElementById('mileage'),
                    city: document.getElementById('city'),
                    district: document.getElementById('district'),
                    wardCode: document.getElementById('wardCode'),
                    addressNumber: document.getElementById('addressNumber'),
                    fuelConsumption: document.getElementById('fuelConsumption'),
                    description: document.getElementById('description')
                };

                // Only save if elements exist
                if (elements.mileage) carData.mileage = elements.mileage.value;
                if (elements.city) carData.city = elements.city.value;
                if (elements.district) carData.district = elements.district.value;
                if (elements.wardCode) {
                    carData.wardCode = elements.wardCode.value;
                    // Store the ward name from the selected option
                    const wardSelect = document.getElementById('wardCode');
                    const selectedOption = wardSelect.options[wardSelect.selectedIndex];
                    carData.wardName = selectedOption.text;
                }
                if (elements.addressNumber) carData.addressNumber = elements.addressNumber.value;
                if (elements.fuelConsumption) carData.fuelConsumption = elements.fuelConsumption.value;
                if (elements.description) carData.description = elements.description.value;

                // Handle checkboxes
                const checkboxes = {
                    hasBluetooth: document.getElementById('hasBluetooth'),
                    hasGPS: document.getElementById('hasGPS'),
                    hasCamera: document.getElementById('hasCamera'),
                    hasSunRoof: document.getElementById('hasSunRoof'),
                    hasChildLock: document.getElementById('hasChildLock'),
                    hasChildSeat: document.getElementById('hasChildSeat'),
                    hasDVD: document.getElementById('hasDVD'),
                    hasUSB: document.getElementById('hasUSB')
                };

                // Only save if elements exist
                if (checkboxes.hasBluetooth) carData.hasBluetooth = checkboxes.hasBluetooth.checked;
                if (checkboxes.hasGPS) carData.hasGPS = checkboxes.hasGPS.checked;
                if (checkboxes.hasCamera) carData.hasCamera = checkboxes.hasCamera.checked;
                if (checkboxes.hasSunRoof) carData.hasSunRoof = checkboxes.hasSunRoof.checked;
                if (checkboxes.hasChildLock) carData.hasChildLock = checkboxes.hasChildLock.checked;
                if (checkboxes.hasChildSeat) carData.hasChildSeat = checkboxes.hasChildSeat.checked;
                if (checkboxes.hasDVD) carData.hasDVD = checkboxes.hasDVD.checked;
                if (checkboxes.hasUSB) carData.hasUSB = checkboxes.hasUSB.checked;

                // Handle file inputs
                const frontImage = document.getElementById('frontImage');
                const backImage = document.getElementById('backImage');
                const leftImage = document.getElementById('leftImage');
                const rightImage = document.getElementById('rightImage');

                if (frontImage?.files[0]) carData.frontImage = frontImage.files[0];
                if (backImage?.files[0]) carData.backImage = backImage.files[0];
                if (leftImage?.files[0]) carData.leftImage = leftImage.files[0];
                if (rightImage?.files[0]) carData.rightImage = rightImage.files[0];
            } else if (step === 3) {
                const elements = {
                    basePrice: document.getElementById('basePrice'),
                    deposit: document.getElementById('deposit'),
                    noSmoking: document.getElementById('noSmoking'),
                    noPet: document.getElementById('noPet'),
                    noFood: document.getElementById('noFood'),
                    otherTerm: document.getElementById('other'),
                    otherTerms: document.getElementById('otherTerms')
                };

                // Only save if elements exist
                if (elements.basePrice) carData.basePrice = elements.basePrice.value;
                if (elements.deposit) carData.deposit = elements.deposit.value;
                if (elements.noSmoking) carData.noSmoking = elements.noSmoking.checked;
                if (elements.noPet) carData.noPet = elements.noPet.checked;
                if (elements.noFood) carData.noFood = elements.noFood.checked;
                if (elements.otherTerm) carData.otherTerm = elements.otherTerm.checked;
                if (elements.otherTerms) carData.otherTerms = elements.otherTerms.value;
            }
        }
            async function validateStep(step) {
            let isValid = true;

            // Xóa lỗi cũ
            document.querySelectorAll('.error-message').forEach(error => {
                error.textContent = '';
                error.style.display = 'none';
            });

            // Validate Step 1
            if (step === 1) {
                const licensePlate = document.getElementById('licensePlate').value;
                const brand = document.getElementById('brand').value;
                const productionYear = document.getElementById('productionYear').value;
                const transmission = document.querySelector('input[name="transmission"]:checked');
                const color = document.getElementById('color').value;
                const model = document.getElementById('model').value;
                const seats = document.getElementById('seats').value;
                const fuel = document.querySelector('input[name="fuel"]:checked');
                 const registrationPaper = document.getElementById('registrationPaper').files[0];
            const inspectionCertificate = document.getElementById('inspectionCertificate').files[0];
            const insurance = document.getElementById('insurance').files[0];
        const allowedExtensions = ['pdf', 'jpg', 'jpeg', 'png'];
        function checkFile(file, errorElementId, label) {
                if (!file) {
                    isValid = false;
                    document.getElementById(errorElementId).textContent = `${label} is required!`;
                    document.getElementById(errorElementId).style.display = 'block';
                } else {
                    const extension = file.name.split('.').pop().toLowerCase();
                    if (!allowedExtensions.includes(extension)) {
                        isValid = false;
                        document.getElementById(errorElementId).textContent = `${label} must be PDF, JPG, or PNG!`;
                        document.getElementById(errorElementId).style.display = 'block';
                    }
                }
            }

            checkFile(registrationPaper, 'registrationPaperError', 'Registration paper');
            checkFile(inspectionCertificate, 'inspectionCertificateError', 'Inspection certificate');
            checkFile(insurance, 'insuranceError', 'Insurance');
                 // Validate license plate format
            if (!licensePlate.match(/^[0-9]{2}[A-Z]-[0-9]{3}\.[0-9]{2}$/)) {
                isValid = false;
                document.getElementById('licensePlateError').textContent = "Invalid license plate format!";
                document.getElementById('licensePlateError').style.display = 'block';
            }
        // Validate duplicate license plate (AJAX)
        await fetch('/cars/check-license?plate=' + encodeURIComponent(licensePlate))
            .then(response => response.json())
            .then(data => {
                if (!data.available) {
                    isValid = false;
                    document.getElementById('licensePlateError').textContent = "This license plate already exists!";
                    document.getElementById('licensePlateError').style.display = 'block';
                }
            })
            .catch(error => {
                console.error("Error checking license plate:", error);
            });
                if (!brand) {
                    isValid = false;
                    document.getElementById('brandError').textContent = "Brand is required!";
                    document.getElementById('brandError').style.display = 'block';
                }

                if (!productionYear) {
                    isValid = false;
                    document.getElementById('productionYearError').textContent = "Production year is required!";
                    document.getElementById('productionYearError').style.display = 'block';
                }

               if (!transmission) {
                    isValid = false;
                    document.getElementById('transmissionError').textContent = "Transmission type is required!";
                    document.getElementById('transmissionError').style.display = 'block';
                }

                if (!color) {
                    isValid = false;
                    document.getElementById('colorError').textContent = "Color is required!";
                    document.getElementById('colorError').style.display = 'block';
                }

                if (!model) {
                    isValid = false;
                    document.getElementById('modelError').textContent = "Model is required!";
                    document.getElementById('modelError').style.display = 'block';
                }

                if (!seats) {
                    isValid = false;
                    document.getElementById('seatsError').textContent = "Seats are required!";
                    document.getElementById('seatsError').style.display = 'block';
                }

                if (!fuel) {
                    isValid = false;
                    document.getElementById('fuelError').textContent = "Fuel type is required!";
                    document.getElementById('fuelError').style.display = 'block';
                }
            }

            // Validate Step 2
            if (step === 2) {
                const mileage = document.getElementById('mileage').value;
                const city = document.getElementById('city').value;
                const district = document.getElementById('district').value;
                const wardCode = document.getElementById('wardCode').value;
                const addressNumber = document.getElementById('addressNumber').value;

                if (!mileage || parseFloat(mileage) <= 0) {
                    isValid = false;
                    document.getElementById('mileageError').textContent = "Mileage must be a positive number!";
                    document.getElementById('mileageError').style.display = 'block';
                }

                if (!city) {
                    isValid = false;
                    document.getElementById('cityError').textContent = "Please select a city!";
                    document.getElementById('cityError').style.display = 'block';
                }

                if (!district) {
                    isValid = false;
                    document.getElementById('districtError').textContent = "Please select a district!";
                    document.getElementById('districtError').style.display = 'block';
                }

                if (!wardCode) {
                    isValid = false;
                    document.getElementById('wardCodeError').textContent = "Please select a ward!";
                    document.getElementById('wardCodeError').style.display = 'block';
                }

                if (!addressNumber) {
                    isValid = false;
                    document.getElementById('addressNumberError').textContent = "Address is required!";
                    document.getElementById('addressNumberError').style.display = 'block';
                }
            }

            // Validate Step 3
            if (step === 3) {
                const basePrice = document.getElementById('basePrice').value;
                const deposit = document.getElementById('deposit').value;
            const frontImage = document.getElementById('frontImage').files[0];
            const backImage = document.getElementById('backImage').files[0];
            const leftImage = document.getElementById('leftImage').files[0];
            const rightImage = document.getElementById('rightImage').files[0];

            const imageExtensions = ['jpg', 'jpeg', 'png', 'gif', 'bmp', 'webp'];

            function checkImage(file, errorElementId, label) {
                if (!file) {
                    isValid = false;
                    document.getElementById(errorElementId).textContent = `${label} is required!`;
                    document.getElementById(errorElementId).style.display = 'block';
                } else {
                    const extension = file.name.split('.').pop().toLowerCase();
                    if (!imageExtensions.includes(extension)) {
                        isValid = false;
                        document.getElementById(errorElementId).textContent = `${label} must be a valid image format!`;
                        document.getElementById(errorElementId).style.display = 'block';
                    }
                }
            }

            checkImage(frontImage, 'frontImageError', 'Front image');
            checkImage(backImage, 'backImageError', 'Back image');
            checkImage(leftImage, 'leftImageError', 'Left image');
            checkImage(rightImage, 'rightImageError', 'Right image');

                if (!basePrice || parseFloat(basePrice) <= 0) {
                    isValid = false;
                    document.getElementById('basePriceError').textContent = "Base price must be a positive number!";
                    document.getElementById('basePriceError').style.display = 'block';
                }

                if (!deposit || parseFloat(deposit) <= 0) {
                    isValid = false;
                    document.getElementById('depositError').textContent = "Deposit must be a positive number!";
                    document.getElementById('depositError').style.display = 'block';
                }
            }

            return isValid;
        }


async function showStep(step) {
    if (await validateStep(step - 1)) {
        console.log("showStep called with step:", step);

        if (step > 1) {
            saveStepData(step - 1);
        }

        console.log("Dữ liệu carData trước khi hiển thị Step " + step + ":", carData);

        document.querySelectorAll('.step-content').forEach(div => div.style.display = 'none');
        let stepElement = document.getElementById('step' + step);
        if (stepElement) {
            stepElement.style.display = 'block';

            // Cập nhật dữ liệu Step 4 nếu đến Step 4
            if (step === 4) {
                const carModelElement = document.getElementById('carModel');
                const carPriceElement = document.getElementById('carPrice');
                const carLocationElement = document.getElementById('carLocation');
                const carImageElement = document.getElementById('carImage');

                if (carModelElement) carModelElement.textContent =
                    (carData.brand ? carData.brand + ' ' : '') +
                    (carData.model ? carData.model + ' ' : '') +
                    (carData.productionYear ? '(' + carData.productionYear + ')' : '') || "Unknown Car";
               if (carPriceElement) {
                   const price = carData.basePrice || 0;
                   carPriceElement.textContent = new Intl.NumberFormat('en-US').format(price);
               }
                if (carLocationElement) carLocationElement.textContent =
                    (carData.city ? carData.city + ", " : "") +
                    (carData.district ? carData.district + ", " : "") +
                    (carData.wardName ? carData.wardName : (carData.wardCode ? carData.wardCode : ""));

                // Kiểm tra xem có ảnh không, nếu không thì dùng ảnh mặc định
                if (carImageElement) {
                    if (carData.frontImage) {
                        try {
                            carImageElement.src = URL.createObjectURL(carData.frontImage);
                        } catch (error) {
                            console.error("Error creating object URL:", error);
                            carImageElement.src = '/images/default-car.jpg';
                        }
                    } else {
                        carImageElement.src = '/images/default-car.jpg';
                    }
                }
            }
        } else {
            console.error("Không tìm thấy Step " + step);
        }

        $(".step-bar a").removeClass("step_active");
        $('.step-bar a[data-step="' + step + '"]').addClass("step_active");
    }
}

        function submitForm(event) {
            if (event) {
                event.preventDefault();
            }

            try {
             // Validate required fields
                const requiredFields = document.querySelectorAll('[required]');
                let isValid = true;
                let firstInvalidField = null;

                requiredFields.forEach(field => {
                    if (!field.value.trim()) {
                        isValid = false;
                        field.classList.add('error');
                        if (!firstInvalidField) {
                            firstInvalidField = field;
                        }
                    } else {
                        field.classList.remove('error');
                    }
                });

                // Validate wardCode is a valid integer
                const wardCodeSelect = document.getElementById('wardCode');
                if (wardCodeSelect && wardCodeSelect.value) {
                    const wardCodeValue = parseInt(wardCodeSelect.value);
                    if (isNaN(wardCodeValue)) {
                        isValid = false;
                        wardCodeSelect.classList.add('error');
                        if (!firstInvalidField) {
                            firstInvalidField = wardCodeSelect;
                        }
                    }
                }

                // Validate file inputs
                const requiredFiles = [
                    { id: 'registrationPaper', name: 'Registration Paper', param: 'REGISTRATION' },
                    { id: 'inspectionCertificate', name: 'Inspection Certificate', param: 'INSPECTION' },
                    { id: 'insurance', name: 'Insurance', param: 'INSURANCE' },
                    { id: 'frontImage', name: 'Front Image', param: 'frontImage' },
                    { id: 'backImage', name: 'Back Image', param: 'backImage' },
                    { id: 'leftImage', name: 'Left Image', param: 'leftImage' },
                    { id: 'rightImage', name: 'Right Image', param: 'rightImage' }
                ];

                requiredFiles.forEach(file => {
                    const input = document.getElementById(file.id);
                    if (!input?.files[0]) {
                        isValid = false;
                        showError(input, `${file.name} is required`);
                    }
                });

                if (!isValid) {
                    if (firstInvalidField) {
                        firstInvalidField.focus();
                    }
                    return;
                }

                const formData = new FormData();

                // Add all form fields with correct parameter names
                const form = document.getElementById('carForm');
                const formElements = form.elements;
                for (let i = 0; i < formElements.length; i++) {
                    const element = formElements[i];
                    if (element.type === 'file') {
                        // Skip file inputs, they'll be handled separately
                        continue;
                    }
                    if (element.type === 'radio') {
                        // Only add radio buttons if they're checked
                        if (element.checked) {
                            formData.append(element.name, element.value);
                        }
                    } else if (element.type === 'checkbox') {
                        // Always add checkbox values, false if unchecked
                        formData.append(element.name, element.checked);
                    } else if (element.name && element.value) {
                        // For wardCode, ensure it's a valid integer
                        if (element.name === 'wardCode') {
                            const wardCodeValue = parseInt(element.value);
                            if (!isNaN(wardCodeValue)) {
                                formData.append(element.name, wardCodeValue.toString());
                            }
                        } else {
                            formData.append(element.name, element.value);
                        }
                    }
                }

                // Add otherTermDescription if it exists
                const otherTermDescription = document.getElementById('otherTermText');
                if (otherTermDescription) {
                    formData.append('otherTermDescription', otherTermDescription.value);
                }

                // Add file inputs with correct parameter names
                requiredFiles.forEach(file => {
                    const input = document.getElementById(file.id);
                    if (input?.files[0]) {
                        formData.append(file.param, input.files[0]);
                    }
                });

                // Submit the form
                fetch('/cars/add', {
                    method: 'POST',
                    body: formData
                })
                .then(response => {
                    if (!response.ok) {
                        return response.text().then(text => {
                            throw new Error(text || `HTTP error! status: ${response.status}`);
                        });
                    }
                    return response.text();
                })
                .then(() => {
                    window.location.href = '/cars/myCar';
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error submitting form: ' + error.message);
                });
            } catch (error) {
                console.error('Error preparing form data:', error);
                alert('Error preparing form data: ' + error.message);
            }
        }

        // Add cancel functionality
        function cancelForm() {
            if (confirm('Are you sure you want to cancel? All entered data will be lost.')) {
                window.location.href = '/cars/myCar';
            }
        }

        document.addEventListener('DOMContentLoaded', function() {
            console.log("DOM đã tải xong");

            // Initialize carImage
            carImage = document.getElementById('carImage');

            // Set up form submission
            const carForm = document.getElementById('carForm');
            if (carForm) {
                carForm.addEventListener('submit', submitForm);
            }

            // Set up cancel buttons
            const cancelButtons = document.querySelectorAll('.cancel');
            cancelButtons.forEach(button => {
                button.addEventListener('click', cancelForm);
            });

            // Initialize other term details visibility
            toggleOtherTermDetails();

            // Kiểm tra xem phần tử carImage có tồn tại không
            if (!carImage) {
                console.error("Lỗi: #carImage không tồn tại trong DOM.");
            }

            // Đảm bảo carData tồn tại
            if (!window.carData) window.carData = {};

            // Lưu dữ liệu hình ảnh
            carData.frontImage = document.getElementById('frontImage')?.files[0] || null;
            carData.backImage = document.getElementById('backImage')?.files[0] || null;
            carData.leftImage = document.getElementById('leftImage')?.files[0] || null;
            carData.rightImage = document.getElementById('rightImage')?.files[0] || null;

            if (carImage) {
                updateImage();  // Hiển thị ảnh đầu tiên sau khi trang tải xong
            }
        });

        function fetchDistricts() {
            const city = document.getElementById("city").value;
            const districtSelect = document.getElementById("district");
            const wardSelect = document.getElementById("wardCode");

            // Xóa các option cũ
            districtSelect.innerHTML = '<option value="">Select District</option>';
            wardSelect.innerHTML = '<option value="">Select Ward</option>';

            if (city) {
                fetch(`/cars/district?city=${city}`)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }
                        return response.text();
                    })
                    .then(data => {
                        districtSelect.innerHTML += data;
                    })
                    .catch(error => console.error("Error fetching districts:", error));
            }
        }

        function fetchWards() {
            const city = document.getElementById("city").value;
            const district = document.getElementById("district").value;
            const wardSelect = document.getElementById("wardCode");

            // Xóa các option cũ
            wardSelect.innerHTML = '<option value="">Select Ward</option>';

            if (district && city) {
                fetch(`/cars/ward?district=${district}&city=${city}`)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }
                        return response.text();
                    })
                    .then(data => {
                        wardSelect.innerHTML += data;
                    })
                    .catch(error => console.error("Error fetching wards:", error));
            }
        }
        function previewImage(event, previewId) {
            const input = event.target;
            const preview = document.getElementById(previewId);

            if (input.files && input.files[0]) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    preview.src = e.target.result;
                    preview.style.display = 'block';
                };
                reader.readAsDataURL(input.files[0]);
            } else {
                preview.src = '';
                preview.style.display = 'none';
            }
        }


        function updateImage() {
            if (!carImage) {
                console.warn("Car image element not found");
                return;
            }

            const imageFile = carData[imageIds[currentImageIndex]];
            if (imageFile) {
                try {
                    carImage.src = URL.createObjectURL(imageFile);
                } catch (error) {
                    console.error("Error creating object URL:", error);
                    carImage.src = '/images/default-car.jpg';
                }
            } else {
                carImage.src = '/images/default-car.jpg';
            }
        }

        function toggleOtherTermDetails() {
            const otherTermCheckbox = document.getElementById('otherTerm');
            const otherTermDetails = document.getElementById('otherTermDetails');
            const otherTermText = document.getElementById('otherTermText');

            if (otherTermCheckbox && otherTermDetails) {
                otherTermDetails.style.display = otherTermCheckbox.checked ? 'block' : 'none';
                if (!otherTermCheckbox.checked) {
                    otherTermText.value = '';
                }
            }
        }

        function nextImage() {
            currentImageIndex = (currentImageIndex + 1) % imageIds.length;
            updateImage();
        }

        function prevImage() {
            currentImageIndex = (currentImageIndex - 1 + imageIds.length) % imageIds.length;
            updateImage();
        }
function previewImage(event, previewId) {
    const input = event.target;
    const preview = document.getElementById(previewId);

    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
            preview.title = "Click to re-upload";
        };
        reader.readAsDataURL(input.files[0]);

        // Gán sự kiện click ảnh để mở lại file input
        preview.onclick = () => input.click();
    } else {
        preview.src = '';
        preview.style.display = 'none';
    }
}