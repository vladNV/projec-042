import * as help from './utils.js';

// utils
const helper = new help.Utils();

// requests
const CREATE_NEW_REQUEST = '/business/creation';

// regexps
const NAME = /^[A-Za-zА-Яа-яё\-\s]+$/;
const PHONE = /^\+?[\d\-]{7,15}$/;
const PASSPORT = /^[\d]{8}$/;
const POSITION = /(DIRECTOR|BOOKER|MANAGER|OTHER)/;
const TRANSPORT = /(AUTO|PLANE|SHIP|TRAIN|BUS|OTHER)/;
const RATE_NUMBER = /^[\w]{1,45}$/;
const PRICE = /^[\d]{1,15}$/;

const MIN_CHARS_FOR_SEARHCING = 2;
/**
 * @return {boolean}
 */
const IS_EMPTY = function (val) {
    return (!val || 0 === val.length);
};

/**
 * @return {boolean}
 */
function DATE_IS_EMPTY(date) {
  let count = 0;
  Array.from(Object.keys(date)).forEach(j => {
     let boolean = IS_EMPTY(date[j]);
     if (boolean) {
         count++;
     }
  });
  return !(count == 0);
}

    window.searchBy = function (input, url) {
        if (input.value.length <= MIN_CHARS_FOR_SEARHCING) {
            return;
        }
        let puppet = new Puppet();
        puppet.fetch(url + input.value).then(set => populateFoundItems(set, input)).catch(err => alert(err));
    };

    window.prepareDataForSending = function () {
        let requestJson = {
            // employee
            fullname: helper.fetch('fullname'),
            phone: helper.fetch('phone'),
            passport: helper.fetch('passport'),
            qualification: helper.fetch('qualification'),
            position: helper.fetch('position'),

            // requisition
            description: helper.fetch('description', ''),

            fromDate: {
                year: helper.fetch('yearfrom'),
                month: helper.fetch('monthfrom'),
                day: helper.fetch('dayfrom'),
                hour: helper.fetch('hourfrom'),
                minute: helper.fetch('minutefrom'),
            },

            tillDate: {
                year: helper.fetch('yeartill'),
                month: helper.fetch('monthtill'),
                day: helper.fetch('daytill'),
                hour: helper.fetch('hourtill'),
                minute: helper.fetch('minutetill'),
            },

            // facility
            title: helper.fetch('title'),
            direction: helper.fetch('direction'),

            // transport
            transport: helper.fetch('transport'),
            type: helper.fetch('type'),

            // trip
            rate: helper.fetch('rate'),
            price: helper.fetch('price'),

            departure: {
                year: helper.fetch('yeardeparture'),
                month: helper.fetch('monthdeparture'),
                day: helper.fetch('daydeparture'),
                hour: helper.fetch('hourdeparture'),
                minute: helper.fetch('minutedeparture'),
            }
        };

        let errors = additionalValidation(requestJson);
        if (errors.length > 0) {
            alert(errors.join("\n"));
            return;
        }
        let flow = new Puppet();
        flow.push(CREATE_NEW_REQUEST, requestJson).then(e => alert('Заявка успешно создана!\n ' +
            'Вы можете посмотреть все заявки, перейдя по ссылке в меню выше!')).catch(err => alert(err));
    };

    window.dateTime = function (id) {
        // generate four inputs (year, date, hours, minutes)
        let year = helper.buildInput('number', 'year' + id, 'year-' + id, true, 'Год');
        let day = helper.buildInput('number', 'day' + id, 'date-' + id, true, 'День');
        let hour = helper.buildInput('number', 'hour' + id, 'hour-' + id, true, 'Часы');
        let minute = helper.buildInput('number', 'minute' + id, 'minute-' + id, true, 'Минуты');

        let months = [
            {val: '1', text: 'Январь', restriction: longMonth(day)},
            {val: '2', text: 'Февраль', restriction: february(day, year.value)},
            {val: '3', text: 'Март', restriction: longMonth(day)},
            {val: '4', text: 'Апрель', restriction: smallMonth(day)},
            {val: '5', text: 'Май', restriction: longMonth(day)},
            {val: '6', text: 'Июнь', restriction: smallMonth(day)},
            {val: '7', text: 'Июль', restriction: longMonth(day)},
            {val: '8', text: 'Август', restriction: longMonth(day)},
            {val: '9', text: 'Сентябрь', restriction: smallMonth(day)},
            {val: '10', text: 'Октябрь', restriction: longMonth(day)},
            {val: '11', text: 'Ноябрь', restriction: smallMonth(day)},
            {val: '12', text: 'Декабрь', restriction: longMonth(day)},
        ];

        // restrictions
        helper.setMinMax(2018, 2032, year);
        helper.setMinMax(0, 23, hour);
        helper.setMinMax(0, 59, minute);

        // generate one select box for month
        let month = helper.buildSelect('month' + id, 'month-' + id, true, months);


        year.onchange = function(){validateYear(year)};
        month.onchange = function(){validateMonth(month)};
        day.onchange = function(){validateDay(year, month, day)};
        hour.onchange = function(){validateHour(hour)};
        minute.onchange = function(){valdateMinute(minute)};

        let newDate = [year, month, day];
        let newTime = [hour, minute];

        let finalDate = document.getElementById(id + 'Date');
        let finalTime = document.getElementById(id + 'Time');

        newDate.map(child => helper.createAndWrap('div', child, 'col')).forEach(child => finalDate.appendChild(child));
        newTime.map(child => helper.createAndWrap('div', child, 'col')).forEach(child => finalTime.appendChild(child));
    };

    window.validateName = function (name) {
        if (!IS_EMPTY(name) && NAME.test(name.value)) {
            success(name.id);
            return true;
        }
        caution('Имя может содержать только буквы и пробелы!', name.id);
        return false;
    };

    window.validateRateNumber = function (input) {
      if (RATE_NUMBER.test(input.value)) {
          success(input.id);
          return true;
      }
      caution('Номер рейта может содерать только цифры, буквы и нижнее подчеркивание!', input.id);
      return false;
    };
    
    window.validateLength = function(input, min, max) {
        if (input.value.length >= min && input.value.length <= max) {
            success(input.id);
            return true;
        }
        caution('Недопустимая длина! min - ' + min + ' , max - ' + max, input.id);
        return false;
    };

    window.validatePassport = function (input) {
      if (PASSPORT.test(input.value)) {
          success(input.id);
          return true;
      }
      caution('Пасспорт должен содержать только 8 цифр и !', input.id);
      return false;
    };

    window.validatePrice = function (number) {
        if (PRICE.test(number.value)) {
            success(number.id);
            return true;
        }
        caution('Могут быть только цифры, не больше 15, и не меньше 1 !', number.id);
        return false;
    };
    
    window.validatePhone = function (phone) {
        if (PHONE.test(phone.value)) {
            success(phone.id);
            return true;
        }
        caution('В номере можно указаывать цифры, символ "-" и также + в начале, максимум 15 !', phone.id);
        return false;
    };

    window.validateTransport = function(transport) {
        let arr = ['AUTO', 'PLANE', 'SHIP', 'TRAIN', 'BUS', 'OTHER'];
        if (arr.includes(transport.value)) {
            success(transport.id);
            return true;
        }
        caution('Выберите тип из списка!',transport.id);
        return false;
    };

    window.validatePosition = function(position) {
        let arr = ['DIRECTOR', 'BOOKER', 'MANAGER', 'OTHER'];
        if (arr.includes(position.value)) {
            success(position.id);
            return false;
        }
        caution('Выберите тип из списка!',position.id);
        return false;
    };

    window.populateEmployeePhone = function (e) {
        let input = e.target;
        let val = input.value;
        let list = input.getAttribute('list');

        if (val.length <= MIN_CHARS_FOR_SEARHCING) {
            return;
        }

        let dlist = document.getElementById(list).childNodes;

        Array.from(dlist).filter(d => d.value === val).forEach(d => {
            let qualification = document.getElementById('qualification');
            let passport = document.getElementById('passport');
            let name = document.getElementById('fullname');
            let position = document.getElementById('position');

            qualification.value = d.getAttribute('qualification');
            passport.value = d.getAttribute('passport');
            name.value = d.getAttribute('fullname');
            position.value = d.getAttribute('position');

            validatePhone(d);
            validateName(name);
            validatePassport(passport);
            validatePosition(position);
            validateLength(qualification, 0, 100);
        });
    };

    window.populateEmployeeName = function (e) {
        let input = e.target;
        let val = input.value;
        let list = input.getAttribute('list');

        if (val.length <= MIN_CHARS_FOR_SEARHCING) {
            return;
        }

        let dlist = document.getElementById(list).childNodes;

        Array.from(dlist).filter(d => d.value  === val).forEach(d => {
            let qualification = document.getElementById('qualification');
            let passport = document.getElementById('passport');
            let phone = document.getElementById('phone');
            let position = document.getElementById('position');

            qualification.value = d.getAttribute('qualification');
            passport.value = d.getAttribute('passport');
            phone.value = d.getAttribute('phone');
            position.value = d.getAttribute('position');

            validateName(d);
            validatePassport(passport);
            validatePhone(phone);
            validatePosition(position);
            validateLength(qualification, 0, 100);
        });
    };

    window.populateEmployeePassport = function (e) {
        let input = e.target;
        let val = input.value;
        let list = input.getAttribute('list');


        if (val.length <= MIN_CHARS_FOR_SEARHCING) {
            return;
        }

        let dlist = document.getElementById(list).childNodes;

        Array.from(dlist).filter(d => d.value === val).forEach(d => {
            let qualification = document.getElementById('qualification');
            let name = document.getElementById('fullname');
            let phone = document.getElementById('phone');
            let position = document.getElementById('position');

            qualification.value = d.getAttribute('qualification');
            name.value = d.getAttribute('fullname');
            phone.value = d.getAttribute('phone');
            position.value = d.getAttribute('position');

            validatePassport(d);
            validateName(name);
            validatePhone(phone);
            validatePosition(position);
            validateLength(qualification, 0, 100);
        });
    };

    window.populateFacilityLocation = function (e) {
        let input = e.target;
        let val = input.value;
        let list = input.getAttribute('list');

        if (val.length <= MIN_CHARS_FOR_SEARHCING) {
            return;
        }

        let dlist = document.getElementById(list).childNodes;

        Array.from(dlist).filter(d => d.value === val).forEach(d => {
            let direction = document.getElementById('direction');
            direction.value = d.getAttribute('direction');

            validateLength(d, 0, 100);
            validateLength(direction, 0, 150);
        });
    };

    window.populateFacilityDirection = function (e) {
        let input = e.target;
        let val = input.value;
        let list = input.getAttribute('list');

        if (val.length <= MIN_CHARS_FOR_SEARHCING) {
            return;
        }

        let dlist = document.getElementById(list).childNodes;

        Array.from(dlist).filter(d => d.value === val).forEach(d => {
            let location = document.getElementById('title');
            location.value = d.getAttribute('title');

            validateLength(d, 0, 150);
            validateLength(location, 0, 100);
        });
    };

    function populateFoundItems(json, input) {
        if (input.value.length <= MIN_CHARS_FOR_SEARHCING) {
            return;
        }

        let dataList = document.getElementById(input.id + '-data');

        while (dataList.firstChild) {
            dataList.removeChild(dataList.firstChild);
        }

        let list = JSON.parse(json);

        // let names = Array.from(list.map(e => e.fullname));
        list.forEach(e => {
            let opt = document.createElement('option');
            Object.keys(e).forEach(j => {
                opt.setAttribute(j, e[j]);
            });
            opt.setAttribute('value', e[input.id]);
            dataList.appendChild(opt);
        });
    }

    function validateYear(year) {
        if (year.value >= '2018' && year.value <= '2032') {
            success(year.id);
            return true;
        }
        caution('Год в пределах от 2018 до 2032!', year.id);
        return false;
    }

    function validateDay(iYear, iMonth, iDay) {
        let day = iDay.value;
        let month = iMonth.value;
        let year = iYear.value;

        if (day <= 0) {
            return false;
        }

        let day31 = ['1', '3', '5', '7', '8', '10', '12'];
        let day30 = ['4', '6', '9', '11'];

        if (day31.includes(month) && day > 31) {
            caution('Последний день в месяце 31!', iDay.id);
            return false;
        }

        if (day30.includes(month) && day > 30) {
            caution('Последний день в месяце 30!', iDay.id);
            return false;
        }

        if (month === '2') {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) && day > 29) {
                caution('Последний день февраля в году 29!', iDay.id);
                return false;
            }
            if (day > 28) {
                caution('Последний день февраля в году 28!', iDay.id);
                return false;
            }
        }
        success(iDay.id);
        return true;
    }

    function validateMonth(month) {
        if (month.value >= 1 && month.value <= 12) {
            success(month.id);
            return true;
        }
        caution('Некорректный месяц!', month.id);
        return false;
    }

    function validateHour(hour) {
        if (hour.value >= 0 && hour.value <= 23) {
            success(hour.id);
            return true;
        }
        caution('Часы в промежутке от 0 до 23', hour.id);
        return false;
    }

    function valdateMinute(minute) {
        if (minute.value >= 0 && minute.value <= 59) {
            success(minute.id);
            return true;
        }
        caution('Минуты в промежутке от 0 до 59!', minute.id);
        return false;
    }

    function success(id) {
        let elem = document.getElementById(id);
        elem.setAttribute('style', 'border: 3px green solid; box-shadow: 0 0 10px; outline: none !important;');
    }

    function caution(msg, id) {
        let elem = document.getElementById(id);
        elem.setAttribute('style', 'border: 3px red solid; box-shadow: 0 0 10px; outline: none !important;');
        elem.setAttribute('title', msg);
    }

    function additionalValidation(requestJson) {
        let errors = [];

        if (IS_EMPTY(requestJson.fullname) || !NAME.test(requestJson.fullname)) {
            errors.push('Неправильно введено имя!');
        }

        if (!PHONE.test(requestJson.phone)) {
            errors.push('Неправильно введен номер телефона!');
        }

        if (!PASSPORT.test(requestJson.passport)) {
            errors.push('Неправильно введен номер паспорта!');
        }

        if (IS_EMPTY(requestJson.qualification)) {
            errors.push('Поле квалификация не может быть пустым!');
        }

        if (!POSITION.test(requestJson.position)) {
            errors.push('Неправильно выбрана должность!');
        }

        if (IS_EMPTY(requestJson.description)) {
            errors.push('Описание не может быть пустым!');
        }

        if (DATE_IS_EMPTY(requestJson.fromDate)) {
            errors.push('Дата начала не может быть пустой!');
        }

        if (DATE_IS_EMPTY(requestJson.tillDate)) {
            errors.push('Дата конца не может быть пустой!');
        }

        if (DATE_IS_EMPTY(requestJson.departure)) {
            errors.push('Дата отправления не может быть пустой!');
        }

        if (IS_EMPTY(requestJson.transport)) {
            errors.push('Название транспорта не может быть пустым!');
        }

        if (!TRANSPORT.test(requestJson.type)) {
            errors.push('Неправильный тип транспорта!');
        }

        if (IS_EMPTY(requestJson.direction)) {
            errors.push('Направление не может быть пустым!');
        }

        if (!RATE_NUMBER.test(requestJson.rate)) {
            errors.push('Неправильно введен номер!');
        }

        if (!PRICE.test(requestJson.price)) {
            errors.push('Неправильно введена цена!');
        }

        if (IS_EMPTY(requestJson.title)) {
            errors.push('Название не может быть пустым!');
        }

        return errors;
    }

    function longMonth(day) {
        helper.setMinMax(1, 31, day);
    }

    function smallMonth(day) {
        helper.setMinMax(1, 30, day)
    }

    function february(day, yearAsNumber) {
        if (typeof yearAsNumber !== 'number') return;
        let endDay = 28;
        if ((yearAsNumber % 4 == 0 && yearAsNumber % 100 != 0) || (yearAsNumber % 400 == 0)) {
            endDay = 29
        }
        helper.setMinMax(1, endDay, day)
    }

    document.querySelector('input[list="fullname-data"]').addEventListener('input', populateEmployeeName);
    document.querySelector('input[list="phone-data"]').addEventListener('input', populateEmployeePhone);
    document.querySelector('input[list="passport-data"]').addEventListener('input', populateEmployeePassport);
    document.querySelector('input[list="title-data"]').addEventListener('input', populateFacilityLocation);
    document.querySelector('input[list="direction-data"]').addEventListener('input', populateFacilityDirection);
