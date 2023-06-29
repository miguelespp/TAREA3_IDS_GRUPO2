import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import kotlin.random.Random

fun main() {

    runBlocking {
        setupConnection()?.let { db: MongoDatabase ->
            //updateSingleDocument(db)
            updateMultipleDocuments(db)
        }
    }
}


suspend fun updateSingleDocument(db: MongoDatabase) {
    val collection = db.getCollection<Carros>("carros")
    val queryParam = Filters.eq("_id", ObjectId("649d0df99542e545974e6f8d"))
    val updateParams = Updates.set("color", "Dorado")
    collection.updateOne(filter = queryParam, update = updateParams).also {
        println("Total docs matched ${it.matchedCount} and modified ${it.modifiedCount}")
    }
}

suspend fun updateMultipleDocuments(db: MongoDatabase) {
    val collection = db.getCollection<Carros>("carros")
    val queryParam = Filters.eq(Carros::marca.name, "Toyota")
    val updateParams = Updates.combine(
        Updates.set(Carros::modelo.name, 2003),
        Updates.set(Carros::color.name, "Verde")
    )

    collection.updateMany(filter = queryParam, update = updateParams).also {
        println("Total docs matched ${it.matchedCount} and modified ${it.modifiedCount}")
    }
}