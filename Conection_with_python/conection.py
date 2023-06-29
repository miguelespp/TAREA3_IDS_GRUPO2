from pymongo import MongoClient

MONGODB_URI = "mongodb+srv://Nico1234:Nico1234@cluster0.a8zlarc.mongodb.net/?retryWrites=true&w=majority"

client = MongoClient(MONGODB_URI)

for db_name in client.list_database_names():
    print(db_name)

client.close()
