package ir.thatsmejavad.backgroundable.core.sealeds

import ir.thatsmejavad.backgroundable.ListType as ProtoListType

sealed interface List {
    data object ListType : List
    data object GridType : List
    data object StaggeredType : List

    companion object {
        fun toList(theme: ProtoListType): List {
            return when (theme) {
                ProtoListType.LIST_TYPE_LIST -> ListType
                ProtoListType.LIST_TYPE_GRID -> GridType
                ProtoListType.LIST_TYPE_STAGGERED -> StaggeredType
                ProtoListType.UNRECOGNIZED -> StaggeredType
            }
        }

        fun fromList(theme: List): ProtoListType {
            return when (theme) {
                ListType -> ProtoListType.LIST_TYPE_LIST
                StaggeredType -> ProtoListType.LIST_TYPE_STAGGERED
                GridType -> ProtoListType.LIST_TYPE_GRID
            }
        }
    }
}
