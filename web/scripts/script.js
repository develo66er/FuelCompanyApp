function addFuelChecker()
{
    var message = '';
    if (document.forms["add-fuel"]["fuel_type"] === null || document.forms["add-fuel"]["fuel_type"] === undefined || document.forms["add-fuel"]["fuel_type"].value === '') {
        document.forms["add-fuel"]["fuel_type"].style = 'border-color:red';
        message += '<p>fuel type is required </p>'
    } else {
        document.forms["add-fuel"]["fuel_type"].style = 'border-color:grey';
    }
    if (document.forms["add-fuel"]["fuel_name"] === null || document.forms["add-fuel"]["fuel_name"] === undefined || document.forms["add-fuel"]["fuel_name"].value === '') {
        document.forms["add-fuel"]["fuel_name"].style = 'border-color:red';
        message += '<p>fuel name is required </p>'
    } else {
        document.forms["add-fuel"]["fuel_name"].style = 'border-color:grey';
    }
    if (document.forms["add-fuel"]["fuel_price"] === null || document.forms["add-fuel"]["fuel_price"] === undefined || document.forms["add-fuel"]["fuel_price"].value === '' || parseFloat(document.forms["add-fuel"]["fuel_price"].value) === 0) {
        document.forms["add-fuel"]["fuel_price"].style = 'border-color:red';
        message += '<p>fuel price should be specified </p>'
    } else {
        document.forms["add-fuel"]["fuel_price"].style = 'border-color:grey';
    }
    if (message === '') {
        document.form1.submit();
    }
    document.getElementById("add-error").innerHTML = '<br/>' + message;
    return false;
}
function updateFuelChecker()
{
    var message = '';
    if (document.forms["update-fuel"]["fuel_type"] === null || document.forms["update-fuel"]["fuel_type"] === undefined || document.forms["update-fuel"]["fuel_type"].value === '') {
        document.forms["update-fuel"]["fuel_type"].style = 'border-color:red';
        message += '<p>fuel type is required </p>'
    } else {
        document.forms["update-fuel"]["fuel_type"].style = 'border-color:grey';
    }
    if (document.forms["update-fuel"]["fuel_name"] === null || document.forms["update-fuel"]["fuel_name"] === undefined || document.forms["update-fuel"]["fuel_name"].value === '') {
        document.forms["update-fuel"]["fuel_name"].style = 'border-color:red';
        message += '<p>fuel name is required </p>'
    } else {
        document.forms["update-fuel"]["fuel_name"].style = 'border-color:grey';
    }
    if (document.forms["update-fuel"]["fuel_price"] === null || document.forms["update-fuel"]["fuel_price"] === undefined || document.forms["update-fuel"]["fuel_price"].value === '' || parseFloat(document.forms["update-fuel"]["fuel_price"].value) === 0) {
        document.forms["update-fuel"]["fuel_price"].style = 'border-color:red';
        message += '<p>fuel price should be specified </p>'
    } else {
        document.forms["update-fuel"]["fuel_price"].style = 'border-color:grey';
    }
    if (message === '')
        document.form1.submit();
    document.getElementById("update-error").innerHTML = '<br/>' + message;
    return false;

}

function updatePetrolStationChecker()
{
    var message = '';
    if (document.forms["update-petrolstation"]["address"] === null || document.forms["update-petrolstation"]["address"] === undefined || document.forms["update-petrolstation"]["address"].value === '') {
        document.forms["update-petrolstation"]["address"].style = 'border-color:red';
        message += '<p>address is required </p>'
    } else {
        document.forms["update-petrolstation"]["address"].style = 'border-color:grey';
    }
    if (document.forms["update-petrolstation"]["start_time"] === null || document.forms["update-petrolstation"]["start_time"] === undefined || document.forms["update-petrolstation"]["start_time"].value === '') {
        document.forms["update-petrolstation"]["start_time"].style = 'border-color:red';
        message += '<p>start time is required </p>'
    } else {
        if (!document.forms["update-petrolstation"]["start_time"].value.match(/^\d{2}:\d{2}:\d{2}$/)) {
            document.forms["update-petrolstation"]["start_time"].style = 'border-color:red';
            message += '<p>finish time should be correct hh:mm:ss pattern </p>'
        } else {
            document.forms["update-petrolstation"]["start_time"].style = 'border-color:grey';
        }
    }

    if (document.forms["update-petrolstation"]["finish_time"] === null || document.forms["update-petrolstation"]["finish_time"] === undefined || document.forms["update-petrolstation"]["finish_time"].value === '') {
        document.forms["update-petrolstation"]["finish_time"].style = 'border-color:red';
        message += '<p>finish time is required </p>'
    } else {
        if (!document.forms["update-petrolstation"]["finish_time"].value.match(/^\d{2}:\d{2}:\d{2}$/)) {
            document.forms["update-petrolstation"]["finish_time"].style = 'border-color:red';
            message += '<p>finish time should be correct hh:mm:ss pattern </p>'
        } else {
            document.forms["update-petrolstation"]["finish_time"].style = 'border-color:grey';
        }
    }

    if (message === '')
        document.form1.submit();
    document.getElementById("petrolstation-update-error").innerHTML = '<br/>' + message;
    return false;

}


function addPetrolStationChecker(companyId)
{
    var message = '';
    var form_name = "add-petrolstation-"+companyId;
    if (document.forms[form_name]["address"] === null || document.forms[form_name]["address"] === undefined || document.forms[form_name]["address"].value === '') {
        document.forms[form_name]["address"].style = 'border-color:red';
        message += '<p>address is required </p>'
    } else {
        document.forms[form_name]["address"].style = 'border-color:grey';
    }
    if (document.forms[form_name]["start_time"] === null || document.forms[form_name]["start_time"] === undefined || document.forms[form_name]["start_time"].value === '') {
        document.forms[form_name]["start_time"].style = 'border-color:red';

        message += '<p>start time is required </p>'
    } else {
        if (document.forms[form_name]["start_time"].value.match(/^\d{2}:\d{2}:\d{2}$/) == null) {
            document.forms[form_name]["start_time"].style = 'border-color:red';
            message += '<p>start time should be correct hh:mm:ss pattern </p>'
        } else {
            document.forms[form_name]["start_time"].style = 'border-color:grey';
        }
    }

    if (document.forms[form_name]["finish_time"] === null || document.forms[form_name]["finish_time"] === undefined || document.forms[form_name]["finish_time"].value === '') {
        document.forms[form_name]["finish_time"].style = 'border-color:red';
        message += '<p>finish time is required </p>'
    } else {
        if (!document.forms[form_name]["finish_time"].value.match(/^\d{2}:\d{2}:\d{2}$/)) {
            document.forms[form_name]["finish_time"].style = 'border-color:red';
            message += '<p>finish time should be correct hh:mm:ss pattern </p>'
        } else {
            document.forms[form_name]["finish_time"].style = 'border-color:grey';
        }
    }

    if (message === '')
        document.form1.submit();
    document.getElementById("petrolstation-add-error-"+companyId).innerHTML = '<br/>' + message;
    return false;
}


function updateDispenserChecker()
{
    var message = '';
    if (document.forms["update-dispenser"]["dispenser_type"] === null || document.forms["update-dispenser"]["dispenser_type"] === undefined || document.forms["update-dispenser"]["dispenser_type"].value === '') {
        document.forms["update-dispenser"]["dispenser_type"].style = 'border-color:red';
        message += '<p>dispenser type is required </p>'
    } else {
        document.forms["update-dispenser"]["dispenser_type"].style = 'border-color:grey';
    }
    if (document.forms["update-dispenser"]["dispenser_count"] === null || document.forms["update-dispenser"]["dispenser_count"] === undefined || parseFloat(document.forms["update-dispenser"]["dispenser_count"].value) === 0) {
        document.forms["update-dispenser"]["dispenser_count"].style = 'border-color:red';
        message += '<p>dispenser count is required and should not be 0</p>'
    } else {
        document.forms["update-dispenser"]["dispenser_count"].style = 'border-color:grey';
    }
    if (document.forms["update-dispenser"]["dispenser_model"] === null || document.forms["update-dispenser"]["dispenser_model"] === undefined || document.forms["update-dispenser"]["dispenser_model"].value === '') {
        document.forms["update-dispenser"]["dispenser_model"].style = 'border-color:red';
        message += '<p>dispenser model is required </p>'
    } else {
        document.forms["update-dispenser"]["dispenser_model"].style = 'border-color:grey';
    }
    if (message === '')
        document.form1.submit();
    document.getElementById("dispenser-update-error").innerHTML = '<br/>' + message;
    return false;

}

function addDispenserChecker()
{
    var message = '';
    if (document.forms["add-dispenser"]["dispenser_type"] === null || document.forms["add-dispenser"]["dispenser_type"] === undefined || document.forms["add-dispenser"]["dispenser_type"].value === '') {
        document.forms["add-dispenser"]["dispenser_type"].style = 'border-color:red';
        message += '<p>dispenser type is required </p>'
    } else {
        document.forms["add-dispenser"]["dispenser_type"].style = 'border-color:grey';

    }
    if (document.forms["add-dispenser"]["dispenser_count"] === null || document.forms["add-dispenser"]["dispenser_count"] === undefined || parseFloat(document.forms["add-dispenser"]["dispenser_count"].value) === 0) {
        document.forms["add-dispenser"]["dispenser_count"].style = 'border-color:red';
        message += '<p>dispenser count is required and should not be 0</p>'
    } else {
        document.forms["add-dispenser"]["dispenser_count"].style = 'border-color:grey';

    }
    if (document.forms["add-dispenser"]["dispenser_model"] === null || document.forms["add-dispenser"]["dispenser_model"] === undefined || document.forms["add-dispenser"]["dispenser_model"].value === '') {
        document.forms["add-dispenser"]["dispenser_model"].style = 'border-color:red';
        message += '<p>dispenser model is required </p>'
    } else {
        document.forms["add-dispenser"]["dispenser_model"].style = 'border-color:grey';

    }
    if (message === '')
        document.form1.submit();
    document.getElementById("dispenser-add-error").innerHTML = '<br/>' + message;
    return false;
}

function companyChecker()
{
    var message = '';
    if (document.forms["company-form"]["name"] === null || document.forms["company-form"]["name"] === undefined || document.forms["company-form"]["name"].value === '') {
        document.forms["company-form"]["name"].style = 'border-color:red';
        message += '<p>company name is required </p>'
    } else {
        document.forms["company-form"]["name"].style = 'border-color:grey';
    }
    if (document.forms["company-form"]["address"] === null || document.forms["company-form"]["address"] === undefined || document.forms["company-form"]["address"].value === '') {
        document.forms["company-form"]["address"].style = 'border-color:red';
        message += '<p>fuel name is required </p>'
    } else {
        document.forms["company-form"]["address"].style = 'border-color:grey';
    }
    if (document.forms["company-form"]["inn"] === null || document.forms["company-form"]["inn"] === undefined || document.forms["company-form"]["inn"].value === '') {
        document.forms["company-form"]["inn"].style = 'border-color:red';
        message += '<p>fuel price should be specified </p>'
    } else {
        document.forms["company-form"]["inn"].style = 'border-color:grey';
    }
    if (document.forms["company-form"]["phone"] === null || document.forms["company-form"]["phone"] === undefined || document.forms["company-form"]["phone"].value === '') {
        document.forms["company-form"]["phone"].style = 'border-color:red';
        message += '<p>fuel price should be specified </p>'
    } else {
        document.forms["company-form"]["phone"].style = 'border-color:grey';
    }
    if (message === '') {
        document.form1.submit();
    }
    document.getElementById("company-error").innerHTML = '<br/>' + message;
    return false;
}
