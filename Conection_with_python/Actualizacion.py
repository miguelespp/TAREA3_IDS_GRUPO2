import pprint

from pymongo import MongoClient
from bson.objectid import ObjectId

MONGODB_URI = "mongodb+srv://Nico1234:Nico1234@cluster0.a8zlarc.mongodb.net/?retryWrites=true&w=majority"

client = MongoClient(MONGODB_URI)

db = client.tests

account_collection = db.carros

id_act = input("Introduzca la id del documento a actualizar => ")

doc_to_update = {
    "_id": ObjectId(id_act)
}

add_to_balance = {"$inc": {"modelo": 2}}

pprint.pprint(account_collection.find_one(doc_to_update))

result = account_collection.update_one(doc_to_update, add_to_balance)
print("Document update "+str(result.modified_count))

pprint.pprint(account_collection.find_one(doc_to_update))

client.close()