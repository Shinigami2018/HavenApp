import yagmail

yag = yagmail.SMTP("sameenpcc2018@gmail.com", "fhae rkkc srki vgpt")
yag.send("arifulm926@gmail.com", "Subject Here", "This is a test email from Python!")
print("Email sent!")
