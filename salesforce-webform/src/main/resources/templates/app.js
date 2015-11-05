$(document).ready(function() {
    $('#cForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            inputFirstName: {
                validators: {
                    notEmpty: {
                        message: 'The first name is required and cannot be empty'
                    }
                }
            },
            inputLastName: {
                validators: {
                    notEmpty: {
                        message: 'The last name is required and cannot be empty'
                    }
                }
            },
            inputEmail: {
                validators: {
                    notEmpty: {
                        message: 'The email is required and cannot be empty'
                    }
                }
            },
            inputMessage: {
                validators: {
                    notEmpty: {
                        message: 'The message is required and cannot be empty'
                    }
                }
            } 
        }
    });
});