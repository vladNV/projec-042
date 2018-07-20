// requests
const WHO_AM_I = '/whoAmI';

// get identity for each page
whoAmI();

    function whoAmI() {
        let puppet = new Puppet();
        puppet.fetch('GET', WHO_AM_I).then(json => populateIdentity(json)).catch(error => alert(error));
    }

    function populateIdentity(json) {
        let obj = JSON.parse(json);

        document.getElementById('username').innerText = obj.login;
        document.getElementById('registration').innerText = obj.registrationDate;
        document.getElementById('role').innerText = obj.role;
        document.getElementById('uid').innerText = obj.id;
    }
