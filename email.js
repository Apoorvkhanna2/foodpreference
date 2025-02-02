const express = require("express");
const nodemailer = require("nodemailer");
const bodyParser = require("body-parser");

const app = express();

app.use(bodyParser.json());

// Define a route for handling orders
app.post("/api/orders", (req, res) => {
  // Parse the order details from the request body
  const { name, email, phone, address, instructions } = req.body;

  // Create a Nodemailer transporter with your email service provider's settings
  const transporter = nodemailer.createTransport({
    service: "Gmail", // e.g., "Gmail", "Yahoo", etc.
    auth: {
      user: "khannaapoorv011@gmail.com", // Your email address
      pass: "Hello1234@", // Your email password (consider using environment variables for security)
    },
  });

  // Create an email message
  const mailOptions = {
    from: "khannaapoorv011@gmail.com", // Sender's email address
    to: email, // Recipient's email address (the customer's email)
    subject: "Order Confirmation",
    text: `Dear ${name},\n\nThank you for placing your order with Food Delivery System. Your order will be delivered to the following address:\n\n${address}\n\nAdditional Instructions: ${instructions}\n\nWe will contact you at ${phone} if needed.\n\nEnjoy your meal!\n\nSincerely,\nThe Food Delivery System Team`,
  };

  // Send the email
  transporter.sendMail(mailOptions, (error, info) => {
    if (error) {
      console.error("Error sending email:", error);
      res.status(500).json({ message: "Error sending email" });
    } else {
      console.log("Email sent:", info.response);
      res.status(200).json({ message: "Order placed and email sent" });
    }
  });
});

// Start the server
const port = process.env.PORT || 3000;
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
