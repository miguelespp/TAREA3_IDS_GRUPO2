from pymongo import MongoClient

MONGODB_URI = "mongodb+srv://Nico1234:Nico1234@cluster0.a8zlarc.mongodb.net/?retryWrites=true&w=majority"

client = MongoClient(MONGODB_URI)

db = client.tests

account_collection = db.carros

new_account = {
    "color": "rojo",
    "marca": "hyundai",
    "modelo": 2010
}

# insertar mas de un documento
# new_accounts = [
#     {
#       "color": "Gris",
#       "marca": "Lexus",
#       "modelo": 2002
#     },
#     {
#         "color": "Amarillo",
#         "marca": "Audi",
#         "modelo": 1987,
#     }
# ]

result = account_collection.insert_one(new_account)

# para insertar mas de uno usamos insert_many()
# result = account_collection.insert_many(new_accounts)

document_id = result.inserted_id

print(f'El documento ha sido insertado : {document_id}')

client.close()
