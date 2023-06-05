package ir.thatsmejavad.backgroundable.data.db.relation

import androidx.room.Embedded
import androidx.room.Relation
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity

data class MediaWithResources(
    @Embedded val media: MediaEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "media-id",
    )
    val resources: List<ResourceEntity>,
)
