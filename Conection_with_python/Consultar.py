import pprint

from pymongo import MongoClient
from bson.objectid import ObjectId

MONGODB_URI = "mongodb+srv://Nico1234:Nico1234@cluster0.a8zlarc.mongodb.net/?retryWrites=true&w=majority"

client = MongoClient(MONGODB_URI)

db = client.tests

account_collection = db.carros

id_find = input("Introduzca la id del documento a buscar => ")

find_document = {
    "_id": ObjectId(id_find)
}

result = account_collection.find_one(find_document)

pprint.pprint(result)

client.close()