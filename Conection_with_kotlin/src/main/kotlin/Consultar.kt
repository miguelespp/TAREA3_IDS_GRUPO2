import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId


fun main() {

    runBlocking {
        setupConnection()?.let { db: MongoDatabase ->
            readSpecificDocument(database = db)
        }
    }

}

suspend fun readSpecificDocument(database: MongoDatabase) {
    val collection = database.getCollection<Carros>(collectionName = "carros")
    val queryParams = Filters
        .and(
            listOf(
                eq("_id", ObjectId("649d0df99542e545974e6f8d")),
            )
        )


    collection
        .find<Carros>(queryParams)
        .limit(2)
        .collect {
            println(it)
        }

}
