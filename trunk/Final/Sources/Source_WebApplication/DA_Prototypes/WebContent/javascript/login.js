/*function login() {

		// Getting the variable's value from a link
		var loginBox = $('a.login-window').attr('href');

		// Fade in the Popup
		$(loginBox).fadeIn(300);

		// Set the center alignment padding + border see css style
		var popMargTop = ($(loginBox).height() + 24) / 2;
		var popMargLeft = ($(loginBox).width() + 24) / 2;

		$(loginBox).css({
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});

		// Add the mask to body
		$('body').append('<div id="mask"></div>');
		$('#mask').fadeIn(300);
		 function close() {
		     $('#mask , .login-popup').fadeOut(300 , function() {
		         $('#mask').remove();
		     });
				}
	}

 */
$(document).ready(function() {
	$('a.login-window').click(function() {

		//Getting the variable's value from a link
		var loginBox = $(this).attr('href');

		//Fade in the Popup
		$(loginBox).fadeIn(300);

		//Set the center alignment padding + border see css style
		var popMargTop = ($(loginBox).height() + 10) / 2;
		var popMargLeft = ($(loginBox).width() + 10) / 2;

		$(loginBox).css({
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});

		// Add the mask to body
		$('body').append('<div id="mask"></div>');
		$('#mask').fadeIn(300);

	});

	$('a.delete-window').click(function() {

		//Getting the variable's value from a link
		var loginBox = $(this).attr('href');

		//Fade in the Popup
		$(loginBox).fadeIn(300);

		//Set the center alignment padding + border see css style
		var popMargTop = ($(loginBox).height() + 10) / 2;
		var popMargLeft = ($(loginBox).width() + 10) / 2;

		$(loginBox).css({
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});

		// Add the mask to body
		$('body').append('<div id="mask"></div>');
		$('#mask').fadeIn(300);

	});

	$('a.info-window').click(function() {

		//Getting the variable's value from a link
		var loginBox = $(this).attr('href');

		//Fade in the Popup
		$(loginBox).fadeIn(300);

		//Set the center alignment padding + border see css style
		var popMargTop = ($(loginBox).height() + 10) / 2;
		var popMargLeft = ($(loginBox).width() + 10) / 2;

		$(loginBox).css({
			'margin-top' : -popMargTop,
			'margin-left' : -popMargLeft
		});

		// Add the mask to body
		$('body').append('<div id="mask"></div>');
		$('#mask').fadeIn(300);

	});
	// When clicking on the button close or the mask layer the popup closed
	$('input.close, #mask').live('click', function() {
		$('#mask , .login-popup').fadeOut(300, function() {
			$('#mask').remove();
		});

	});
});