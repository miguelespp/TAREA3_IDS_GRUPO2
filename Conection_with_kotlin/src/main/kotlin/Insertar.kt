import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId

fun main() {
    runBlocking {
        setupConnection()?.let { db: MongoDatabase ->
            addItem(database = db)
            addItems(database = db)
        }
    }

}

suspend fun addItem(database: MongoDatabase) {

    val collection = database.getCollection<Carros>(collectionName = "carros")
    val item = Carros(
        id = ObjectId(),
        color = "Azul",
        marca = "Toyota",
        modelo = 2015
    )

    collection.insertOne(item).also {
        println("Item added with id - ${it.insertedId}")
    }

}

suspend fun addItems(database: MongoDatabase) {
    val collection = database.getCollection<Carros>(collectionName = "carros")
    val newRestaurants = collection.find<Carros>().first().run {
        listOf(
            this.copy(
                id = ObjectId(), color = "Plateado", marca = "BMW", modelo = 2015
            ),
            this.copy(
                id = ObjectId(), color = "Negro", marca = "Ford", modelo = 2007
            )
        )
    }

    collection.insertMany(newRestaurants).also {
        println("Total items added ${it.insertedIds.size}")
    }
}

