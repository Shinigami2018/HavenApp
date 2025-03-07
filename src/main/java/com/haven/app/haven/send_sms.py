import sys
import yagmail

# Check if an argument is passed
if len(sys.argv) < 2:
    print("Error: No recipient email provided!")
    sys.exit(1)

receiver_email = sys.argv[1]  # Get email from Java argument

# Initialize SMTP client
yag = yagmail.SMTP("havenappreplies@gmail.com", "qlpm malu nxjp gzrc")

# Send the email
yag.send(
    to=receiver_email,
    subject="Haven App Emergency Mail",
    contents="User needs help! Please call or message as soon as possible!"
)

print(f"Email sent to {receiver_email}")
