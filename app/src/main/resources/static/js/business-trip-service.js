// requests
const BUSINESS_TRIPS = '/business/trip/';
const GET_AMOUNT_OF_BUSINESS_TRIPS = '/business/count';
const DOWNLOAD_BUSINESS_TRIPS = '/business/download';

// numeric constants
const COUNT_ON_ONE_PAGE = 10;

/**
 * json example
 * employeeId:
 */

    function getAmountOfBusinessTrips() {
        let puppet = new Puppet();
        puppet.fetch('GET', GET_AMOUNT_OF_BUSINESS_TRIPS).then(count => createPages(count)).catch(err => alert(err));
    }

    function getBusinessTrips(number) {
        let puppet = new Puppet();
        puppet.fetch('GET', BUSINESS_TRIPS + number).then(json => populateBusinessTrips(json)).catch(err => alert(err));
    }

    function downloadBusinessTrips() {
        window.location.href = DOWNLOAD_BUSINESS_TRIPS;
    }

    function populateBusinessTrips(json) {
        let table = document.getElementById('data-body');

        while (table.firstChild) {
            table.removeChild(table.firstChild);
        }

        let trips = JSON.parse(json);

        trips.forEach(trip => {
            let tr = row();
            cell(trip.prime, tr);
            // cell(trip.employeeId);
            cell(trip.fullname, tr);
            cell(trip.passport, tr);
            cell(trip.position, tr);
            // cell(trip.qualification);
            // cell(trip.description);
            cell(trip.from, tr);
            cell(trip.till, tr);
            cell(trip.departureDate, tr);
            cell(trip.rate, tr);
            cell(trip.price, tr);
            cell(trip.type, tr);
            cell(trip.transport, tr);
            cell(trip.title, tr);
            cell(trip.direction, tr);
            table.appendChild(tr);
        });
    }
    
    function createPages(pages) {
        let quantityOfPages = Math.ceil(pages / COUNT_ON_ONE_PAGE);

        if (quantityOfPages <= 1) {
            return;
        }

        let pagination = document.getElementById('pagination');
        for (let i = 0; i < quantityOfPages; i++) {
            let btn = document.createElement('button');
            btn.setAttribute('type', 'button');
            btn.onclick = function () {
                getBusinessTrips(i + 1);
            };
            btn.setAttribute('class', 'btn btn-link');
            btn.innerText = (i + 1);
            pagination.appendChild(btn);
        }
    }

    function row() {
        return document.createElement('tr');
    }

    function cell(val, row) {
        let td = document.createElement('td');
        td.innerText = val;
        row.appendChild(td);
    }
