let tabName;

function tab () {
    let tabNav = document.querySelectorAll('.tabs-nav__item'),
        tabContent = document.querySelectorAll('.tab');

    tabNav.forEach(item => {
        item.addEventListener('click', selectTabNav)
    });

    function selectTabNav() {
        tabNav.forEach(item => {
            item.classList.remove('is-active');
        });
        this.classList.add('is-active');
        tabName = this.getAttribute('data-tab-name');
        selectTabContent(tabName);
    }

    function selectTabContent(tabName) {
        tabContent.forEach(item => {
            item.classList.contains(tabName) ? item.classList.add('is-active') : item.classList.remove('is-active');
        })
    }

};


function showBalanceForm() {
    let request = new XMLHttpRequest();
    let sum = prompt("Введите сумму пополнения", "0");
    if (parseFloat(sum)) {
        var body = "sum=" + sum;
        request.open("POST", "http://localhost:8080/jspModule2_war_exploded2/webCustomerView.jsp?" + body);
        request.onreadystatechange = reqReadyStateChange(request);
        request.send();
    } else {
        alert("Введите число");
    }
}

function reqReadyStateChange(request) {
    if (request.readyState === 4) {
        let status = request.status;
        if (status === 200) {
            document.getElementById("output").innerHTML = request.responseText;
        }
    }
}

tab();