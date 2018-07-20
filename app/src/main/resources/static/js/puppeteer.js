class Puppet {

    fetch(method, url) {
        return new Promise(function (resolve, reject) {
            const xhr = new XMLHttpRequest();

            xhr.open(method, url, true);

            xhr.onload = function() {
                if (this.status != 200) {
                    let error = new Error(this.statusText);
                    error.code = this.status;
                    console.log(
                        'request with ' + url + ' ' +
                        'was returned with status ' + error.code + ' ' +
                        'and error message is ' + error
                    );
                    reject(error)
                } else {
                    return resolve(this.response);
                }
            };
            xhr.send();
        });
    }

    push(method, url, json) {
        if (method.toUpperCase() === 'GET') {
            return;
        }
        return new Promise(function (resolve, reject) {
            const xhr = new XMLHttpRequest();

            xhr.open(method, url, true);

            xhr.onload = function () {
                if (this.status != 200) {
                    let error = new Error(this.statusText);
                    error.code = this.status;
                    console.log(
                        'request with ' + url + ' ' +
                        'was returned with status ' + error.code + ' ' +
                        'and error message is ' + error
                    );
                    reject(error);
                } else {
                    return resolve(this.response);
                }
            };
            xhr.send(JSON.stringify(json));
        });
    }
}
