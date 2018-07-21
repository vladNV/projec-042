class Puppet {

    fetch(url) {
        return new Promise(function (resolve, reject) {
            const xhr = new XMLHttpRequest();

            xhr.open('GET', url, true);

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

    push(url, json) {
        return new Promise(function (resolve, reject) {
            const xhr = new XMLHttpRequest();

            xhr.open('POST', url, true);

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
