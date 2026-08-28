package com.Job_Portal.utility;

public class Data {
    public static  String getMessageBody(String otp, String name){
        String subject = "🔐 Your OTP Code - Verify Your Email | Village Job Portal";


        return   "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "<meta charset='UTF-8'>" +
                        "<title>OTP Verification</title>" +
                        "</head>" +

                        "<body style='margin:0;padding:0;background:#f4f6f9;font-family:Arial,sans-serif;'>" +

                        "<table width='100%' cellpadding='0' cellspacing='0' style='background:#f4f6f9;padding:40px 0;'>" +
                        "<tr>" +
                        "<td align='center'>" +

                        "<table width='600' cellpadding='0' cellspacing='0' style='background:#ffffff;border-radius:10px;overflow:hidden;border:1px solid #dddddd;'>" +

                        "<tr>" +
                        "<td align='center' style='background:#2563eb;padding:30px;'>" +
                        "<h1 style='color:#ffffff;margin:0;'>Village Job Portal</h1>" +
                        "<p style='color:#dbeafe;margin-top:10px;'>Email Verification</p>" +
                        "</td>" +
                        "</tr>" +

                        "<tr>" +
                        "<td style='padding:40px;'>" +

                        "<h2>Hello, "+name+"</h2>" +

                        "<p>Thank you for registering with <b>Village Job Portal</b>.</p>" +

                        "<p>Please use the following OTP to verify your email address.</p>" +

                        "<div style='text-align:center;margin:35px 0;'>" +
                        "<span style='background:#2563eb;color:#ffffff;padding:18px 45px;font-size:32px;font-weight:bold;border-radius:8px;letter-spacing:6px;display:inline-block;'>" +
                        otp +
                        "</span>" +
                        "</div>" +

                        "<p><b>This OTP is valid for 5 minutes.</b></p>" +

                        "<p>Please do not share this OTP with anyone.</p>" +

                        "<hr>" +

                        "<p style='color:#666666;font-size:14px;'>" +
                        "If you did not request this verification, please ignore this email." +
                        "</p>" +

                        "</td>" +
                        "</tr>" +

                        "<tr>" +
                        "<td align='center' style='background:#f5f5f5;padding:20px;font-size:13px;color:#777777;'>" +
                        "© 2026 Job Portal. All Rights Reserved." +
                        "</td>" +
                        "</tr>" +

                        "</table>" +

                        "</td>" +
                        "</tr>" +
                        "</table>" +

                        "</body>" +
                        "</html>";

    }
}


