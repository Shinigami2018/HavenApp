import sys
import yagmail

# Check if an argument is passed
if len(sys.argv) < 2:
    print("Error: No recipient email provided!")
    sys.exit(1)

receiver_email = sys.argv[1]  # Get email from Java argument
user_name = sys.argv[2]

# Initialize SMTP client
yag = yagmail.SMTP("havenappreplies@gmail.com", "qlpm malu nxjp gzrc")

# Send the email
yag.send(
    to=receiver_email,
    subject="Welcome to Haven App",
    contents=f"Welcome {user_name}!\n\n We are glad to have you on board! We hope you find our app useful.\n\nThank you,\nHaven App"
)

print(f"Email sent to {receiver_email}")
