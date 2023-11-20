package ir.thatsmejavad.backgroundable.mappers

import io.kotest.matchers.shouldBe
import ir.thatsmejavad.backgroundable.core.sealeds.MediaType
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.model.Collection
import ir.thatsmejavad.backgroundable.model.media.Media
import ir.thatsmejavad.backgroundable.model.media.Resources
import org.junit.jupiter.api.Test

class MappersTest {

    @Test
    fun mediaMapperTest() {
        val media = Media(
            id = 4881,
            width = 8310,
            url = "https://search.yahoo.com/search?p=inceptos",
            height = 5898,
            alt = "hi",
            type = MediaType.Photo,
            liked = false,
            photographer = "habitasse",
            resources = Resources(
                landscape = "qui",
                large = "faucibus",
                large2x = "propriae",
                medium = "utamur",
                original = "mutat",
                portrait = "accommodare",
                small = "enim",
                tiny = "postulant"
            ),
            avgColor = "massa",
            photographerId = 3686,
            photographerUrl = "https://www.google.com/#q=contentiones"

        )
        val expected = media.toEntity(null)
        val actual = MediaEntity(
            id = 4881,
            width = 8310,
            height = 5898,
            url = "https://search.yahoo.com/search?p=inceptos",
            alt = "hi",
            type = MediaType.Photo,
            collectionId = null,
            photographer = "habitasse",
            avgColor = "massa",
            photographerId = 3686,
            photographerUrl = "https://www.google.com/#q=contentiones",
            orderId = 0

        )
        expected shouldBe actual
    }

    @Test
    fun collectionMapperTest() {
        val collection = Collection(
            id = "quo",
            title = "suavitate",
            description = null,
            mediaCount = 8445,
            photosCount = 7632,
            isPrivate = false,
            videosCount = 2744
        )

        val expected = collection.toEntity()

        val actual = CollectionEntity(
            id = "quo",
            title = "suavitate",
            description = null,
            mediaCount = 8445,
            photosCount = 7632,
            videosCount = 2744,
            isPrivate = false,
            orderId = 0
        )

        expected shouldBe actual
    }
}
