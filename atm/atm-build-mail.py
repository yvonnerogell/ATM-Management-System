import os

import smtplib

from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

# ****** NOTE ******: using shell=True is strongly discouraged since it possesses security risks
# subprocess.call(["mvn", "clean", "surefire-report:report"], shell=True)

# report = os.system("mvn clean surefire-report:report")
# print("MVN Report: %d\n" % report)

# me == my email address
# you == recipient's email address
me = "salve@materiaessentia.com"
you = "danielle.m.miller@outlook.com"
password = "senderpassword"

val = input("Enter your email: ")
you = val

# Create message container - the correct MIME type is multipart/alternative.
msg = MIMEMultipart('alternative')
msg['Subject'] = "ATM Test Report"
msg['From'] = me
msg['To'] = you

with open('target/site/surefire-report.html') as f:
  read_data = f.read()

  # Create the body of the message (a plain-text and an HTML version).
text = "http://www.python.org"
html = read_data
  #  """\
  # <html>
  #   <head></head>
  #   <body>
  #     <p>Hi!<br>
  #        How are you?<br>
  #        Here is the <a href="http://www.python.org">link</a> you wanted.
  #     </p>
  #   </body>
  # </html>
  # """

# Record the MIME types of both parts - text/plain and text/html.
part1 = MIMEText(text, 'plain')
part2 = MIMEText(html, 'html')

# Attach parts into message container.
# According to RFC 2046, the last part of a multipart message, in this case
# the HTML message, is best and preferred.
msg.attach(part1)
msg.attach(part2)

# Send the message via local SMTP server.
# s = smtplib.SMTP('localhost', 1025)
# sendmail function takes 3 arguments: sender's address, recipient's address
# and message to send - here it is sent as one string.

mail = smtplib.SMTP('smtp.gmail.com', 587)
mail.ehlo()
mail.starttls()
mail.login(me, password)
mail.sendmail(me, you, msg.as_string())
mail.quit()

f.close()