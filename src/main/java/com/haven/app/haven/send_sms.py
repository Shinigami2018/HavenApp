import yagmail

# Initialize SMTP client
yag = yagmail.SMTP("havenappreplies@gmail.com", "qlpm malu nxjp gzrc")

# Get recipient email from user input
receiver_email = input("Enter recipient email: ")

# Send the email
yag.send(
    to=receiver_email,
    subject="Haven App Emergency Mail",
    contents="User needs help! Please call or message as soon as possible!"
)

print("Email sent to", receiver_email)
