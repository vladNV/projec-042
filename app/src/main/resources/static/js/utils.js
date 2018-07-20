export function Utils() {

    this.buildInput = function (type, id, name, required, placeholder) {
        let input = this.create('input');
        setCommon(id, name, required, input);

        input.setAttribute('placeholder', placeholder);
        input.setAttribute('type', type);

        return input;
    };

    this.createAndWrap = function (whatCreate, whatWrap, clazz) {
        let created = this.create(whatCreate);
        created.setAttribute('class', clazz);
        created.appendChild(whatWrap);
        return created;
    };

    this.fetch = function (id) {
        console.log(document.getElementById(id).value);
        return document.getElementById(id).value;
    };

    this.buildSelect = function (id, name, required, options) {
        let select = this.create('select');
        setCommon(id, name, required, select);

        setUpOptions(options, select);

        return select;
    };

    this.setMinMax = function (min, max, where) {
        where.setAttribute('min', min);
        where.setAttribute('max', max);
    };

    let setCommon = function (id, name, required, obj) {
        obj.setAttribute('id', id);
        obj.setAttribute('name', name);
        obj.setAttribute('required', required);

        // defaults
        obj.setAttribute('class', 'form-control');
    };

    this.create = function (what) {
        return document.createElement(what);
    };

    let create_ = function (what) {
        return document.createElement(what);
    };

    let setUpOptions = function (options, select) {
        if (Array.isArray(options)) {
            options.forEach(o => {
                let option = create_('option');
                option.setAttribute('value', o.val);
                option.text = retrieveText(o.text);
                select.add(option);
            });
        }
    };

    let retrieveText = function(obj) {return obj != null  ? obj : null;}
}