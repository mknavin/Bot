"# Bot" 
# Bank Bot is based on the Conversation Enhanced Sample Application
# the app connects to a DashDB instance to retrive data pertaining to transactions
# nosqljson.jar,db2jcc_license_cu.jar, db2jcc4.jar need to be present in /locallib dir.

#To build the application you need to be in the root dir of this app and do
gradle.bat build


#To push on bluemix, do a
cf push

# about the application
the bot is training for conversation regarding transactions and Loans. You can ask questions such as 'Get me 3 Card transactions' 'what is my Auto EMI amount'. 'Is it a holiday day after tomorrow'

