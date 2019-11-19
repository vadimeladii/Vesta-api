<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Vesta Reset Forgot Password</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;,
            color: #000000
        }

        .button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">
    <tr>
        <td align="center" bgcolor="#78ab46" style="padding: 40px 0 30px 0;">
            <form id="Vesta-Api">
            <h1>Vesta-Api</h1>
            </form>
        </td>
    </tr>
    <tr>
        <td bgcolor="#eaeaea" style="padding: 40px 30px 40px 30px;">
            <p>
                Hi ${name},
            </p>
            <p>
                You recently requested to reset your password for your Vesta account.
                Click the button below to reset it.
            </p>
                <button>
                    <b>
                        <a href="C/reset/password?token=${token}">Reset your password</a>
                    </b>
                </button>
            <p>
            If you did not requested a password reset, please ignore this email or reply to let us know.
                This password reset is only valid for next 30 minutes.
            </p>
            <p>Thanks,</p>
            <p>Vesta Team.</p>
        </td>
    </tr>
    <tr>
        <td bgcolor="#777777" style="padding: 30px 30px 30px 30px;">
            <p>
                Pentalog Romania | Complex Diplomat, Str.Sevastopol, N° 13-17, ap.305, Bucuresti, Romania
            </p>
            <p>
                <a href="tel:+0311013013" title="phone">Tel. : +0311 013 013</a>
                | Email :
                <a href="mailto:jobs@pentalog.md" title="email">jobs@pentalog.md</a>
            </p>
            <p>
                Copyright © 1993 -
                2019 - Pentalog SA -All Rights Reserved. |
                <a href="https://www.pentalog.ro/legal-statement">Legal Terms</a>
            </p>
        </td>
    </tr>
</table>

</body>
</html>