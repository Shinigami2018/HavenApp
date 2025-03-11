import sys
import yagmail

# Check if an argument is passed
if len(sys.argv) < 3:
    print("Error: No recipient email provided!")
    sys.exit(1)

receiver_email = sys.argv[1]  # Get email from Java argument
user_name = sys.argv[2]  # Get username from Java argument
# Initialize SMTP client
yag = yagmail.SMTP("havenappreplies@gmail.com", "qlpm malu nxjp gzrc")

# Send the email
yag.send(
    to=receiver_email,
    subject="Haven App Emergency Mail",
    contents=f"Hello,{receiver_email}\n\n {user_name} needs help immediately. Please contact them as soon as possible.\n\nThank you,\nHaven App"
)

print(f"Email sent to {receiver_email}")
+