<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Welcome</title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Urbanist:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <!-- Styles -->
       <style>
            img {
              height: 300px;
              margin-left: 2em;
            }

            body {
                font-family: 'Urbanist';
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                background-color: #f8f9fa;
            }
            
            h1 {
                margin-left: 1em;
            }


            .container {
                text-align: center;
                display: flex;
                flex-direction: column;
                justify-content: space-around;
                height: 60vh;
            }

            .button {
                margin-left: 50px;
                text-decoration: none;
                padding: 10px 20px;
                border-radius: 10px; /* Rounded corners */
                font-size: 16px;/* Using the Urbanist font */
                width: 70%;
                text-align: center;
                display: inline-block;
                cursor: pointer;
                transition: background-color 0.3s ease, color 0.3s ease;
            }

            /* Black button with white text (Login button) */
            .login-button {
                margin-top: 20px;
                background-color: black;
                color: white;
                border: none;
            }

            .login-button:hover {
                background-color: #333; /* Darker shade on hover */
            }

            /* White button with black border and black text (Register button) */
            .register-button {
                background-color: white;
                color: black;
                border: 2px solid black;
            }

            .register-button:hover {
                background-color: #f2f2f2; /* Light grey background on hover */
            }
        </style>
    </head>
    <body class="blank">
        <div class="container">
            <h1 style="font-weight: 900; font-size: 40px">Programming HORSE</h1>
            <x-application-logo class="w-20 h-20 fill-current text-gray-500" />
            <a href="{{ route('login') }}" class="button login-button">Login</a>
            <a href="{{ route('register') }}" class="button register-button">Register</a>
        </div>
    </body>
</html>
